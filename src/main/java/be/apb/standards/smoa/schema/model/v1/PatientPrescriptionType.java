package be.apb.standards.smoa.schema.model.v1;

import be.apb.standards.smoa.schema.id.v1.AbstractPrescriptionIdType;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PatientPrescriptionType",
   propOrder = {"id", "abstractPatient", "child", "baby", "prescriptionDate", "executableFrom", "executableUntil", "abstractCareProvider", "recipeLines", "kmehrPrescription"}
)
public class PatientPrescriptionType extends AbstractPatientPrescriptionType {
   @XmlElement(
      required = true
   )
   protected AbstractPrescriptionIdType id;
   @XmlElementRef(
      name = "abstract-Patient",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractPatientType> abstractPatient;
   protected Boolean child;
   protected Boolean baby;
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar prescriptionDate;
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar executableFrom;
   @XmlSchemaType(
      name = "date"
   )
   protected XMLGregorianCalendar executableUntil;
   @XmlElementRef(
      name = "abstract-CareProvider",
      namespace = "http://www.apb.be/standards/smoa/schema/model/v1",
      type = JAXBElement.class
   )
   protected JAXBElement<? extends AbstractCareProviderType> abstractCareProvider;
   protected RecipeLinesComplexType recipeLines;
   protected KmehrPrescriptionType kmehrPrescription;

   public AbstractPrescriptionIdType getId() {
      return this.id;
   }

   public void setId(AbstractPrescriptionIdType value) {
      this.id = value;
   }

   public JAXBElement<? extends AbstractPatientType> getAbstractPatient() {
      return this.abstractPatient;
   }

   public void setAbstractPatient(JAXBElement<? extends AbstractPatientType> value) {
      this.abstractPatient = value;
   }

   public Boolean isChild() {
      return this.child;
   }

   public void setChild(Boolean value) {
      this.child = value;
   }

   public Boolean isBaby() {
      return this.baby;
   }

   public void setBaby(Boolean value) {
      this.baby = value;
   }

   public XMLGregorianCalendar getPrescriptionDate() {
      return this.prescriptionDate;
   }

   public void setPrescriptionDate(XMLGregorianCalendar value) {
      this.prescriptionDate = value;
   }

   public XMLGregorianCalendar getExecutableFrom() {
      return this.executableFrom;
   }

   public void setExecutableFrom(XMLGregorianCalendar value) {
      this.executableFrom = value;
   }

   public XMLGregorianCalendar getExecutableUntil() {
      return this.executableUntil;
   }

   public void setExecutableUntil(XMLGregorianCalendar value) {
      this.executableUntil = value;
   }

   public JAXBElement<? extends AbstractCareProviderType> getAbstractCareProvider() {
      return this.abstractCareProvider;
   }

   public void setAbstractCareProvider(JAXBElement<? extends AbstractCareProviderType> value) {
      this.abstractCareProvider = value;
   }

   public RecipeLinesComplexType getRecipeLines() {
      return this.recipeLines;
   }

   public void setRecipeLines(RecipeLinesComplexType value) {
      this.recipeLines = value;
   }

   public KmehrPrescriptionType getKmehrPrescription() {
      return this.kmehrPrescription;
   }

   public void setKmehrPrescription(KmehrPrescriptionType value) {
      this.kmehrPrescription = value;
   }
}
