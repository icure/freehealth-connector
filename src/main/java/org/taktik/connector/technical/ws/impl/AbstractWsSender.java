package org.taktik.connector.technical.ws.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.taktik.connector.technical.exception.RetryNextEndpointException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;
import org.taktik.connector.technical.ws.impl.strategy.InvokeStrategy;
import org.taktik.connector.technical.ws.impl.strategy.InvokeStrategyContext;
import org.taktik.connector.technical.ws.impl.strategy.InvokeStrategyFactory;
import be.fgov.ehealth.technicalconnector.bootstrap.bcp.EndpointDistributor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import jakarta.activation.DataHandler;
import jakarta.xml.soap.AttachmentPart;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPEnvelope;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.soap.SOAPPart;
import jakarta.xml.ws.handler.Handler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public abstract class AbstractWsSender {
   public static final String MESSAGECONTEXT_ENDPOINT_ADDRESS = "jakarta.xml.ws.service.endpoint.address";
   /** @deprecated */
   @Deprecated
   public static final String PROP_RETRY_STRATEGY = "org.taktik.connector.technical.ws.genericsender.invokestrategy";
   private static final Log log = LogFactory.getLog(AbstractWsSender.class);
   private static MessageFactory mf;
   private static final HttpClient httpClient;

   public GenericResponse send(GenericRequest genericRequest) throws TechnicalConnectorException {
      List<InvokeStrategy> strategies = InvokeStrategyFactory.getList((String)genericRequest.getRequestMap().get("jakarta.xml.ws.service.endpoint.address"));
      InvokeStrategyContext ctx = new InvokeStrategyContext(genericRequest);

      for (InvokeStrategy strategy : strategies) {
         log.debug("Using invoke strategy [" + strategy.getClass() + "]");
         if (strategy.invoke(ctx)) {
            break;
         }
      }

      if (ctx.hasException()) {
         throw ctx.getException();
      } else {
         return ctx.getResponse();
      }
   }

   protected GenericResponse call(GenericRequest genericRequest) throws TechnicalConnectorException {
      Handler[] chain = genericRequest.getHandlerchain();

      try {
         SOAPMessageContext request = this.createSOAPMessageCtx(genericRequest);
         request.putAll(genericRequest.getRequestMap());
         request.put("jakarta.xml.ws.handler.message.outbound", true);
         executeHandlers(chain, request);

         SOAPMessage message = request.getMessage();

         // Resolve endpoint
         String requestedTarget = (String) request.get(MESSAGECONTEXT_ENDPOINT_ADDRESS);
         String target = EndpointDistributor.getInstance().getActiveEndpoint(requestedTarget);
         request.put(MESSAGECONTEXT_ENDPOINT_ADDRESS, target);

         // Extract request timeout from context
         int readTimeout = Integer.parseInt(
            (String) request.get("connector.soaphandler.connection.request.timeout"));

         // Serialize SOAPMessage to bytes
         message.saveChanges();
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         message.writeTo(baos);
         byte[] requestBody = baos.toByteArray();

         // Extract Content-Type from SOAPMessage MIME headers
         String contentType = getContentType(message);

         // Build HTTP request
         HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
            .uri(URI.create(target))
            .timeout(Duration.ofMillis(readTimeout))
            .header("Content-Type", contentType)
            .POST(HttpRequest.BodyPublishers.ofByteArray(requestBody));

         // Copy SOAPAction header
         String[] soapActionHeaders = message.getMimeHeaders().getHeader("SOAPAction");
         if (soapActionHeaders != null && soapActionHeaders.length > 0) {
            httpRequestBuilder.header("SOAPAction", soapActionHeaders[0]);
         }

         // Copy User-Agent header
         String[] userAgentHeaders = message.getMimeHeaders().getHeader("User-Agent");
         if (userAgentHeaders != null && userAgentHeaders.length > 0) {
            httpRequestBuilder.header("User-Agent", userAgentHeaders[0]);
         }

         // Send via HttpClient (virtual-thread-friendly, no pinning)
         HttpResponse<byte[]> httpResponse = httpClient.send(
            httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofByteArray());

         // Convert response headers to MIME headers for SAAJ deserialization
         MimeHeaders responseMimeHeaders = new MimeHeaders();
         httpResponse.headers().map().forEach((name, values) -> {
            for (String value : values) {
               responseMimeHeaders.addHeader(name, value);
            }
         });

         // Deserialize response into SOAPMessage
         SOAPMessage responseMessage = mf.createMessage(
            responseMimeHeaders, new ByteArrayInputStream(httpResponse.body()));

         SOAPMessageContext reply = createSOAPMessageCtx(responseMessage);
         reply.putAll(genericRequest.getRequestMap());
         reply.put("jakarta.xml.ws.handler.message.outbound", false);
         ArrayUtils.reverse(chain);
         executeHandlers(chain, reply);
         return new GenericResponse(reply.getMessage());
      } catch (InterruptedException ie) {
         Thread.currentThread().interrupt();
         throw new TechnicalConnectorException(
            TechnicalConnectorExceptionValues.ERROR_WS, ie,
            new Object[]{"SOAP request interrupted"});
      } catch (Exception ex) {
         throw translate(ex);
      }
   }

   private static SOAPMessageContext createSOAPMessageCtx(SOAPMessage msg) {
      return new SOAPMessageContextImpl(msg);
   }

   private static TechnicalConnectorException translate(Exception e) throws RetryNextEndpointException {
      if (e instanceof RetryNextEndpointException) {
         throw (RetryNextEndpointException)e;
      } else if (e instanceof SOAPException || e instanceof IOException) {
         throw new RetryNextEndpointException(e);
      } else if (e instanceof TechnicalConnectorException) {
         return (TechnicalConnectorException)e;
      } else {
         Throwable reason = ExceptionUtils.getRootCause(e);
         log.error("Cannot send SOAP message. Reason [" + reason + "]", e);
         return new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, reason, new Object[]{"Cannot send SOAP message"});
      }
   }

   private static String getContentType(SOAPMessage message) {
      String[] contentTypes = message.getMimeHeaders().getHeader("Content-Type");
      if (contentTypes != null && contentTypes.length > 0) {
         return contentTypes[0];
      }
      return "text/xml; charset=utf-8";
   }

   private static void executeHandlers(Handler[] handlers, SOAPMessageContext ctx) throws TechnicalConnectorException {
      Handler[] arr$ = handlers;
      int len$ = handlers.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         Handler handler = arr$[i$];
         if (!handler.handleMessage(ctx)) {
            TechnicalConnectorExceptionValues errorValue = TechnicalConnectorExceptionValues.ERROR_WS;
            log.error(MessageFormat.format(errorValue.getMessage(), "Error while executing handler " + handler.getClass()));
            throw new TechnicalConnectorException(errorValue, new Object[]{"Error while executing handler."});
         }
      }

   }

   protected String getCurrentEndpoint(GenericRequest genericRequest) {
      return (String)genericRequest.getRequestMap().get("jakarta.xml.ws.service.endpoint.address");
   }

   protected SOAPMessageContext createSOAPMessageCtx(GenericRequest genericRequest) throws TechnicalConnectorException {
      try {
         SOAPMessage soapMessage = mf.createMessage();
         SOAPPart soapPart = soapMessage.getSOAPPart();
         if (genericRequest.isXopEnabled()) {
            soapMessage.getMimeHeaders().addHeader("Content-Type", "application/xop+xml");
            soapPart.addMimeHeader("Content-ID", "<root.message@ehealth.fgov.be>");
            soapPart.addMimeHeader("Content-Transfer-Encoding", "8bit");
         }

         SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
         SOAPBody soapBody = soapEnvelope.getBody();
         org.w3c.dom.Document payloadDoc = genericRequest.getPayload();
         org.w3c.dom.Node importedNode = soapPart.importNode(payloadDoc.getDocumentElement(), true);
         soapBody.appendChild(importedNode);
         Map<String, DataHandler> handlers = genericRequest.getDataHandlerMap();

         AttachmentPart part;
         for(Entry<String, DataHandler>  handlerEntry : handlers.entrySet()) {
            DataHandler handler = handlerEntry.getValue();
            part = soapMessage.createAttachmentPart(handler);
            part.setContentType(handler.getContentType());
            if (genericRequest.isXopEnabled()) {
               part.addMimeHeader("Content-Transfer-Encoding", "binary");
               part.setContentId("<" + (String)handlerEntry.getKey() + ">");
            } else {
               part.setContentId((String)handlerEntry.getKey());
            }
            soapMessage.addAttachmentPart(part);
         }

         return createSOAPMessageCtx(soapMessage);
      } catch (SOAPException var11) {
         throw translate(var11);
      }
   }

   static {
      try {
         mf = MessageFactory.newInstance();

         // Trust-all SSLContext matching ConfigurationModuleSSLVerifier behavior
         TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
         }};
         SSLContext sc = SSLContext.getInstance("TLS");
         sc.init(null, trustAllCerts, new SecureRandom());

         httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(60))
            .sslContext(sc)
            .build();
      } catch (Exception e) {
         throw new IllegalArgumentException(e);
      }
   }
}
