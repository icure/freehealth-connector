package be.apb.standards.smoa.schema.model.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BvacList",
   propOrder = {"bvacDocument"}
)
public class BvacList {
   @XmlElement(
      required = true
   )
   protected List<BvacDocument> bvacDocument;

   public List<BvacDocument> getBvacDocument() {
      if (this.bvacDocument == null) {
         this.bvacDocument = new ArrayList();
      }

      return this.bvacDocument;
   }
}
