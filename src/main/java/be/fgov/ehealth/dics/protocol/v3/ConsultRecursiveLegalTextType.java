package be.fgov.ehealth.dics.protocol.v3;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.dics.core.v3.reimbursementlaw.submit.LegalTextKeyType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultRecursiveLegalTextType",
   propOrder = {"content", "type", "sequenceNr", "lastModifiedOn", "legalTexts"}
)
public class ConsultRecursiveLegalTextType extends LegalTextKeyType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Content",
      required = true
   )
   protected ConsultTextType content;
   @XmlElement(
      name = "Type",
      required = true
   )
   protected String type;
   @XmlElement(
      name = "SequenceNr",
      required = true
   )
   protected BigInteger sequenceNr;
   @XmlElement(
      name = "LastModifiedOn",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime lastModifiedOn;
   @XmlElement(
      name = "LegalText"
   )
   protected List<ConsultRecursiveLegalTextType> legalTexts;
   @XmlAttribute(
      name = "StartDate",
      required = true
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime startDate;
   @XmlAttribute(
      name = "EndDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime endDate;

   public ConsultTextType getContent() {
      return this.content;
   }

   public void setContent(ConsultTextType value) {
      this.content = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public BigInteger getSequenceNr() {
      return this.sequenceNr;
   }

   public void setSequenceNr(BigInteger value) {
      this.sequenceNr = value;
   }

   public DateTime getLastModifiedOn() {
      return this.lastModifiedOn;
   }

   public void setLastModifiedOn(DateTime value) {
      this.lastModifiedOn = value;
   }

   public List<ConsultRecursiveLegalTextType> getLegalTexts() {
      if (this.legalTexts == null) {
         this.legalTexts = new ArrayList();
      }

      return this.legalTexts;
   }

   public DateTime getStartDate() {
      return this.startDate;
   }

   public void setStartDate(DateTime value) {
      this.startDate = value;
   }

   public DateTime getEndDate() {
      return this.endDate;
   }

   public void setEndDate(DateTime value) {
      this.endDate = value;
   }
}
