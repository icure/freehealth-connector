package org.taktik.connector.technical.handler;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import jakarta.xml.soap.MimeHeader;
import jakarta.xml.soap.MimeHeaders;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HarFileHandler extends AbstractSOAPHandler {
   private static final String TIMINGS = "timings";
   private static final Logger LOG = LoggerFactory.getLogger(HarFileHandler.class);
   private DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
   private static final String MESSAGE_ENDPOINT_ADDRESS = "jakarta.xml.ws.service.endpoint.address";
   private static final ObjectMapper objectMapper = new ObjectMapper();
   private ObjectNode harJson;
   private Long start;
   private Long recieved;
   private Long split;
   private String outputdir = ConfigFactory.getConfigValidator().getProperty("connector.output.dir", System.getProperty("java.io.tmpdir").replaceAll("[/\\\\]?$","") + File.separator);
   private static Properties applicationProps = new Properties();

   public boolean handleFault(SOAPMessageContext ctx) {
      Boolean outbound = (Boolean)ctx.get("jakarta.xml.ws.handler.message.outbound");
      if (outbound) {
         return false;
      } else {
         this.handleMessage(ctx);
         return true;
      }
   }

   public boolean handleInbound(SOAPMessageContext context) {
      this.setHandler();
      SOAPMessage msg = context.getMessage();

      try {
         String soapenv = this.getEnvelope(msg);
         ObjectNode response = objectMapper.createObjectNode();
         response.put("status", 200);
         response.put("statusText", "OK");
         response.put("httpVersion", "HTTP/1.1");
         response.set("headers", this.handleHeaders(msg.getMimeHeaders()));
         response.set("cookies", objectMapper.createArrayNode());
         ObjectNode content = objectMapper.createObjectNode();
         content.put("size", soapenv.getBytes().length);
         response.put("headersSize", -1);
         response.put("bodySize", -1);
         response.put("redirectURL", "");
         content.put("mimeType", "text/xml; charset=utf-8");
         if (msg.getMimeHeaders() != null) {
            String[] header = msg.getMimeHeaders().getHeader("Content-Type");
            if (header != null && header.length > 0) {
               content.put("mimeType", header[0]);
            }
         }

         content.put("text", soapenv);
         response.set("content", content);
         this.getEntry().set("response", response);
         ((ObjectNode)this.getEntry().get("timings")).put("wait", this.recieved - this.split);
         long end = System.currentTimeMillis();
         ((ObjectNode)this.getEntry().get("timings")).put("receive", end - this.recieved);
         this.getEntry().put("time", end - this.start);
         this.saveHar();
      } catch (Exception var8) {
         LOG.error(var8.getMessage(), var8);
      }

      return true;
   }

   public boolean handleOutbound(SOAPMessageContext context) {
      this.setHandler();
      SOAPMessage msg = context.getMessage();

      try {
         ObjectNode request = objectMapper.createObjectNode();
         request.put("method", "POST");
         request.put("url", context.get("jakarta.xml.ws.service.endpoint.address").toString());
         request.put("httpVersion", "HTTP/1.1");
         request.set("headers", this.handleHeaders(msg.getMimeHeaders()));
         request.set("queryString", objectMapper.createArrayNode());
         request.set("cookies", objectMapper.createArrayNode());
         request.put("headersSize", -1);
         request.set("postData", this.getPostData(msg));
         request.put("time", "1");
         request.put("bodySize", -1);
         this.split = System.currentTimeMillis();
         ((ObjectNode)this.getEntry().get("timings")).put("send", this.split - this.start);
         this.getEntry().set("request", request);
      } catch (Exception var4) {
         LOG.error(var4.getMessage(), var4);
      }

      return true;
   }

   private void saveHar() throws IOException, TechnicalConnectorException {
      String fileName = IdGeneratorFactory.getIdGenerator("uuid").generateId() + ".har";
      File file = new File(this.outputdir, fileName);
      LOG.info("Writing har file on location: {}", file.getPath());
      objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, this.harJson);
   }

   private ObjectNode getPostData(SOAPMessage msg) throws SOAPException, IOException {
      ObjectNode postData = objectMapper.createObjectNode();
      postData.put("mimeType", "multipart/form-data");
      postData.set("params", objectMapper.createArrayNode());
      postData.put("text", this.getEnvelope(msg));
      return postData;
   }

   private String getEnvelope(SOAPMessage message) throws SOAPException, IOException {
      ByteArrayOutputStream stream = new ByteArrayOutputStream();

      String var3;
      try {
         message.writeTo(stream);
         if (stream.size() >= 1232896) {
            var3 = "message to large to log";
            return var3;
         }

         var3 = stream.toString(Charset.UTF_8.getName());
      } finally {
         ConnectorIOUtils.closeQuietly((Object)stream);
      }

      return var3;
   }

   private ArrayNode handleHeaders(MimeHeaders headers) throws IOException {
      ArrayNode response = objectMapper.createArrayNode();
      if (headers != null) {
         Iterator headersIterator = headers.getAllHeaders();

         while(headersIterator.hasNext()) {
            MimeHeader mimheader = (MimeHeader)headersIterator.next();
            ObjectNode header = objectMapper.createObjectNode();
            header.put("name", mimheader.getName());
            header.put("value", mimheader.getValue());
            response.add(header);
         }
      }

      return response;
   }

   private void prepareHarFile() {
      LOG.info("Start creating har file");
      ObjectNode creator = objectMapper.createObjectNode();
      creator.put("name", applicationProps.getProperty("application.name", "UNKOWN"));
      creator.put("version", applicationProps.getProperty("application.version", "UNKOWN"));
      ArrayNode entries = objectMapper.createArrayNode();
      ObjectNode entry = objectMapper.createObjectNode();
      entry.put("startedDateTime", this.dateFormatter.format(new Date()));
      entry.set("cache", objectMapper.createArrayNode());
      entry.set("timings", objectMapper.createObjectNode());
      entries.add(entry);
      ObjectNode log = objectMapper.createObjectNode();
      log.put("version", "1.2");
      log.set("creator", creator);
      log.set("entries", entries);
      this.harJson = objectMapper.createObjectNode();
      this.harJson.set("log", log);
   }

   private ObjectNode getEntry() {
      ObjectNode log = (ObjectNode)this.harJson.get("log");
      ArrayNode entries = (ArrayNode)log.get("entries");
      return (ObjectNode)entries.get(0);
   }

   private void setHandler() {
      if (this.start == null) {
         this.start = System.currentTimeMillis();
      } else {
         this.recieved = System.currentTimeMillis();
      }

      if (this.harJson == null) {
         this.prepareHarFile();
      }

   }

   static {
      InputStream is = null;

      try {
         is = ConnectorIOUtils.getResourceAsStream("/application.properties");
         applicationProps.load(is);
      } catch (TechnicalConnectorException var6) {
         LOG.error(var6.getMessage(), var6);
      } catch (IOException var7) {
         LOG.error(var7.getMessage(), var7);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)is);
      }

   }
}