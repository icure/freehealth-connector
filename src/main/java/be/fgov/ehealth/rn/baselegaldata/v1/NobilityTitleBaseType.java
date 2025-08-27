package be.fgov.ehealth.rn.baselegaldata.v1;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NobilityTitleBaseType",
   propOrder = {"nobilityTitleCode", "nobilityTitleDescriptions", "inceptionDate"}
)
public class NobilityTitleBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NobilityTitleCode"
   )
   protected String nobilityTitleCode;
   @XmlElement(
      name = "NobilityTitleDescription"
   )
   protected List<NobilityTitleBaseType> nobilityTitleDescriptions;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public String getNobilityTitleCode() {
      return this.nobilityTitleCode;
   }

   public void setNobilityTitleCode(String value) {
      this.nobilityTitleCode = value;
   }

   public List<NobilityTitleBaseType> getNobilityTitleDescriptions() {
      if (this.nobilityTitleDescriptions == null) {
         this.nobilityTitleDescriptions = new ArrayList();
      }

      return this.nobilityTitleDescriptions;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
