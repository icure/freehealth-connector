package be.fgov.ehealth.consultrn.core.v2;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public ResultType createResultType() {
      return new ResultType();
   }

   public ExistingPersonsType createExistingPersonsType() {
      return new ExistingPersonsType();
   }
}
