package be.ehealth.technicalconnector.utils.impl;

import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
      AttachmentPart attachment = (AttachmentPart)this.attachments.get(sanitizePartId(decode(cid)));

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

   private static String decode(String cid) {
      try {
         return URLDecoder.decode(cid, "UTF-8");
      } catch (UnsupportedEncodingException e) {
         throw new IllegalStateException(e);
      }
   }

   private static String sanitizePartId(String cid) {
      return cid.replaceAll("cid:", "");
   }

   public boolean isXOPPackage() {
      return this.xop;
   }
}
