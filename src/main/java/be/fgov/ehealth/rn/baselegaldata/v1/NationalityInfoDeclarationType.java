package be.fgov.ehealth.rn.baselegaldata.v1;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.taktik.connector.technical.adapter.XmlDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "NationalityInfoDeclarationType",
   propOrder = {"nationalityCode", "inceptionDate", "expiryDate"}
)
public class NationalityInfoDeclarationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "NationalityCode"
   )
   @XmlSchemaType(
      name = "unsignedShort"
   )
   protected Integer nationalityCode;
   @XmlElement(
      name = "InceptionDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime inceptionDate;
   @XmlElement(
      name = "ExpiryDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime expiryDate;

   public Integer getNationalityCode() {
      return this.nationalityCode;
   }

   public void setNationalityCode(Integer value) {
      this.nationalityCode = value;
   }

   public DateTime getInceptionDate() {
      return this.inceptionDate;
   }

   public void setInceptionDate(DateTime value) {
      this.inceptionDate = value;
   }

   public DateTime getExpiryDate() {
      return this.expiryDate;
   }

   public void setExpiryDate(DateTime value) {
      this.expiryDate = value;
   }
}
