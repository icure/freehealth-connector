package org.taktik.connector.business.chapterIV.wrapper.impl;

import org.taktik.connector.business.chapterIV.wrapper.WrappedXmlObject;
import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.utils.impl.JaxbContextFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public final class WrappedObjectMarshallerHelper {
   public static byte[] toXMLByteArray(WrappedXmlObject<?> wrappedXmlObject) {
      if (wrappedXmlObject != null && wrappedXmlObject.getXmlObject() != null) {
         try {
            Marshaller marshaller = JaxbContextFactory.getJaxbContextForClass(wrappedXmlObject.getXmlObject().getClass()).createMarshaller();
            marshaller.setProperty("jaxb.encoding", Charset.UTF_8.getName());
            marshaller.setProperty("jaxb.formatted.output", false);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            marshaller.marshal(wrappedXmlObject.getXmlObject(), bos);
            bos.close();
            return bos.toByteArray();
         } catch (JAXBException var3) {
            throw new IllegalArgumentException("JAXBException " + var3);
         } catch (IOException var4) {
            throw new IllegalArgumentException("IOException " + var4);
         }
      } else {
         return new byte[0];
      }
   }
}
