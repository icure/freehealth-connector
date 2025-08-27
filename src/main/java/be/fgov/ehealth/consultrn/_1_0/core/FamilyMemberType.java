package be.fgov.ehealth.consultrn._1_0.core;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FamilyMemberType",
   propOrder = {"personData", "relationship", "entryDate"}
)
public class FamilyMemberType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "PersonData",
      required = true
   )
   protected InhabitantType personData;
   @XmlElement(
      name = "Relationship"
   )
   protected RelationshipType relationship;
   @XmlElement(
      name = "EntryDate",
      type = String.class
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime entryDate;

   public InhabitantType getPersonData() {
      return this.personData;
   }

   public void setPersonData(InhabitantType value) {
      this.personData = value;
   }

   public RelationshipType getRelationship() {
      return this.relationship;
   }

   public void setRelationship(RelationshipType value) {
      this.relationship = value;
   }

   public DateTime getEntryDate() {
      return this.entryDate;
   }

   public void setEntryDate(DateTime value) {
      this.entryDate = value;
   }
}
