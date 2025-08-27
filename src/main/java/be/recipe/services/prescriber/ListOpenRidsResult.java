package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listOpenRidsResult",
   propOrder = {"prescriptions", "hasMoreResults", "session"}
)
@XmlRootElement(
   name = "listOpenRidsResult"
)
public class ListOpenRidsResult extends ResponseType {
   protected List<String> prescriptions;
   protected Boolean hasMoreResults;
   protected byte[] session;

   public List<String> getPrescriptions() {
      if (this.prescriptions == null) {
         this.prescriptions = new ArrayList();
      }

      return this.prescriptions;
   }

   public Boolean isHasMoreResults() {
      return this.hasMoreResults;
   }

   public void setHasMoreResults(Boolean value) {
      this.hasMoreResults = value;
   }

   public byte[] getSession() {
      return this.session;
   }

   public void setSession(byte[] value) {
      this.session = value;
   }

   public Boolean getHasMoreResults() {
      return this.hasMoreResults;
   }

}
