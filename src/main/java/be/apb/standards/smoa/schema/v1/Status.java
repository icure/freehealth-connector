package be.apb.standards.smoa.schema.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "status",
   propOrder = {"error"}
)
public class Status extends AbstractStatus {
   protected List<Error> error;

   public List<Error> getError() {
      if (this.error == null) {
         this.error = new ArrayList();
      }

      return this.error;
   }
}
