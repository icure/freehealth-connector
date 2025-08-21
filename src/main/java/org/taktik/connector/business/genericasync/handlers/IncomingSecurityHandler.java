package org.taktik.connector.business.genericasync.handlers;

import org.apache.wss4j.dom.engine.WSSConfig;
import org.apache.wss4j.dom.engine.WSSecurityEngine;
import org.apache.wss4j.dom.engine.WSSecurityEngineResult;
import org.apache.wss4j.dom.handler.WSHandlerResult;
import org.taktik.connector.technical.config.domain.Duration;
import org.taktik.connector.technical.handler.AbstractSOAPHandler;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.ProtocolException;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.dom.handler.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class IncomingSecurityHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(IncomingSecurityHandler.class);
   private static final QName WSSE = new QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security", "wsse");
   private static final Set<QName> QNAME_LIST = new HashSet<>();
   private WSSConfig config;
   private int timeStampTTL = 30;
   private int timeStampFutureTTL = 30;

   /** @deprecated */
   @Deprecated
   private IncomingSecurityHandler() {
      this.config = WSSConfig.getNewInstance();
   }

   public IncomingSecurityHandler(Duration timestampTTL, Duration timeStampFutureTTL) {
      this();
      this.setTimeStampTTL((int)timestampTTL.convert(TimeUnit.SECONDS));
      this.setTimeStampFutureTTL((int)timeStampFutureTTL.convert(TimeUnit.SECONDS));
   }

   public boolean handleInbound(SOAPMessageContext context) {
      SOAPMessage message = context.getMessage();
      WSSecurityEngine secEngine = new WSSecurityEngine();
      RequestData requestData = new RequestData();
      requestData.setWssConfig(this.config);
      requestData.setTimeStampTTL(this.getTimeStampTTL());
      requestData.setTimeStampFutureTTL(this.getTimeStampFutureTTL());

      try {
         SOAPHeader header = message.getSOAPHeader();
         if (header != null) {
            NodeList list = header.getElementsByTagNameNS(WSSE.getNamespaceURI(), WSSE.getLocalPart());
            if (list != null) {
               LOG.debug("Verify WS Security Header");

               for(int j = 0; j < list.getLength(); ++j) {
                  WSHandlerResult results = secEngine.processSecurityHeader((Element)list.item(j), requestData);

                   for (WSSecurityEngineResult result : results.getResults()) {
                       if (!(Boolean) result.get("validated-token")) {
                           StringBuffer sb = new StringBuffer();
                           sb.append("Unable to validate incoming soap message. Action [");
                           sb.append(result.get("action"));
                           sb.append("].");
                           throw new ProtocolException(sb.toString());
                       }
                   }
               }
            }
         }

         return true;
      } catch (WSSecurityException | SOAPException var12) {
         throw new ProtocolException(var12);
      }
   }

   public Set<QName> getHeaders() {
      return QNAME_LIST;
   }

   static {
      QNAME_LIST.add(WSSE);
   }

   public void setTimeStampTTL(int timeStampTTL) {
      this.timeStampTTL = timeStampTTL;
   }

   public int getTimeStampTTL() {
      return timeStampTTL;
   }

   public void setTimeStampFutureTTL(int timeStampFutureTTL) {
      this.timeStampFutureTTL = timeStampFutureTTL;
   }

   public int getTimeStampFutureTTL() {
      return timeStampFutureTTL;
   }
}
