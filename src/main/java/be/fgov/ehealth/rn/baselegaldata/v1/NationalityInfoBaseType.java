package be.fgov.ehealth.rn.baselegaldata.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalityInfoBaseType",
   propOrder = {"nationalityCode", "nationalityDescriptions", "inceptionDate"}
)
@XmlSeeAlso({NationalityInfoWithSourceType.class})
public class NationalityInfoBaseType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NationalityCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer nationalityCode;
   @XmlElement(
      name = "NationalityDescription"
   )
   protected List nationalityDescriptions;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;

   public Integer getNationalityCode() {
      return this.nationalityCode;
   }

   public void setNationalityCode(Integer value) {
      this.nationalityCode = value;
   }

   public List getNationalityDescriptions() {
      if (this.nationalityDescriptions == null) {
         this.nationalityDescriptions = new ArrayList();
      }

      return this.nationalityDescriptions;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }
}
