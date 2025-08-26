package be.ehealth.technicalconnector.handler;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.ehealth.technicalconnector.ws.impl.strategy.RetryStrategy;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MessageLevelRetryHandler extends AbstractSOAPHandler {
   private static final Logger LOG = LoggerFactory.getLogger(MessageLevelRetryHandler.class);
   private final Configuration config = ConfigFactory.getConfigValidator();
   public static final String PROP_MESSAGELEVEL_RETRY_ACTIVATED = "be.ehealth.technicalconnector.handler.message.level.retry.activated";
   public static final String PROP_MESSAGELEVEL_RETRY_XSLT_LOCATION = "be.ehealth.technicalconnector.handler.message.level.retry.xslt.location";

   public MessageLevelRetryHandler() {
   }

   public boolean handleInbound(SOAPMessageContext context) {
      if (Boolean.TRUE.equals(this.config.getBooleanProperty("be.ehealth.technicalconnector.handler.message.level.retry.activated", Boolean.TRUE))) {
         try {
            Element body = ConnectorXmlUtils.getFirstChildElement(context.getMessage().getSOAPBody());
            String xsltLocation = this.config.getProperty("be.ehealth.technicalconnector.handler.message.level.retry.xslt.location", "/templates/bcp.switch.xslt");
            if (LOG.isDebugEnabled()) {
               LOG.debug("Analysing body: [{}]", ConnectorXmlUtils.toString((Node)body));
            }

            String xlstResult = ConnectorXmlUtils.xslt(new DOMSource(body), new StreamSource(ConnectorIOUtils.getResourceAsStream(xsltLocation)));
            if ("SWITCH".equals(xlstResult)) {
               LOG.info("Notifying retry strategy to activate next");
               RetryStrategy.RetryNotifier.activate(context.getMessage());
            } else {
               LOG.debug("Not activating next one.. expecting [SWITCH] but was [{}]", xlstResult);
            }
         } catch (Exception e) {
            LOG.error("Unable to determine retry, skipping logic", e);
         }
      }

      return true;
   }
}
