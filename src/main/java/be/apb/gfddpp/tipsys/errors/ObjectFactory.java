package be.apb.gfddpp.tipsys.errors;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
   private static final QName _RowSet_QNAME = new QName("http://www.apb.be/tipsys/errors", "rowSet");

   public Errors createErrors() {
      return new Errors();
   }

   public Error createError() {
      return new Error();
   }

   @XmlElementDecl(
      namespace = "http://www.apb.be/tipsys/errors",
      name = "rowSet"
   )
   public JAXBElement<Errors> createRowSet(Errors value) {
      return new JAXBElement(_RowSet_QNAME, Errors.class, (Class)null, value);
   }
}
