package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SignedDataObjectPropertiesType",
   propOrder = {"dataObjectFormats", "commitmentTypeIndications", "allDataObjectsTimeStamps", "individualDataObjectsTimeStamps"}
)
@XmlRootElement(
   name = "SignedDataObjectProperties"
)
public class SignedDataObjectProperties implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "DataObjectFormat"
   )
   protected List<DataObjectFormat> dataObjectFormats;
   @XmlElement(
      name = "CommitmentTypeIndication"
   )
   protected List<CommitmentTypeIndication> commitmentTypeIndications;
   @XmlElement(
      name = "AllDataObjectsTimeStamp"
   )
   protected List<XAdESTimeStampType> allDataObjectsTimeStamps;
   @XmlElement(
      name = "IndividualDataObjectsTimeStamp"
   )
   protected List<XAdESTimeStampType> individualDataObjectsTimeStamps;
   @XmlAttribute(
      name = "Id"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlID
   @XmlSchemaType(
      name = "ID"
   )
   protected String id;

   public List<DataObjectFormat> getDataObjectFormats() {
      if (this.dataObjectFormats == null) {
         this.dataObjectFormats = new ArrayList();
      }

      return this.dataObjectFormats;
   }

   public List<CommitmentTypeIndication> getCommitmentTypeIndications() {
      if (this.commitmentTypeIndications == null) {
         this.commitmentTypeIndications = new ArrayList();
      }

      return this.commitmentTypeIndications;
   }

   public List<XAdESTimeStampType> getAllDataObjectsTimeStamps() {
      if (this.allDataObjectsTimeStamps == null) {
         this.allDataObjectsTimeStamps = new ArrayList();
      }

      return this.allDataObjectsTimeStamps;
   }

   public List<XAdESTimeStampType> getIndividualDataObjectsTimeStamps() {
      if (this.individualDataObjectsTimeStamps == null) {
         this.individualDataObjectsTimeStamps = new ArrayList();
      }

      return this.individualDataObjectsTimeStamps;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }
}
