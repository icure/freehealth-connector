package be.ehealth.technicalconnector.utils.impl;

import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import java.util.HashMap;
import java.util.Map;
import javax.activation.DataHandler;
import javax.xml.bind.attachment.AttachmentUnmarshaller;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.SOAPException;

public class AttachmentUnmarshallerImpl extends AttachmentUnmarshaller {
   private Map<String, AttachmentPart> attachments = new HashMap();
   private boolean xop;

   public AttachmentUnmarshallerImpl(boolean xop) {
      this.xop = xop;
   }

   public Map<String, AttachmentPart> getAttachmentPartMap() {
      return this.attachments;
   }

   public DataHandler getAttachmentAsDataHandler(String cid) {
      AttachmentPart attachment = (AttachmentPart)this.attachments.get(ConnectorXmlUtils.sanitizePartId(cid));

      try {
         return attachment.getDataHandler();
      } catch (SOAPException e) {
         throw new IllegalStateException(e);
      }
   }

   public byte[] getAttachmentAsByteArray(String cid) {
      try {
         DataHandler handler = this.getAttachmentAsDataHandler(cid);
         return ConnectorIOUtils.getBytes(handler.getInputStream());
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

   public boolean isXOPPackage() {
      return this.xop;
   }
}
