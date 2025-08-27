package be.fgov.ehealth.ehbox.core.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FreeInformationsType",
   propOrder = {"encryptableOldFreeInformation", "table", "encryptableFreeText"}
)
public class FreeInformationsType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptableOldFreeInformation"
   )
   protected EncryptableOldFreeInformation encryptableOldFreeInformation;
   @XmlElement(
      name = "Table"
   )
   protected Table table;
   @XmlElement(
      name = "EncryptableFreeText"
   )
   protected byte[] encryptableFreeText;

   public EncryptableOldFreeInformation getEncryptableOldFreeInformation() {
      return this.encryptableOldFreeInformation;
   }

   public void setEncryptableOldFreeInformation(EncryptableOldFreeInformation value) {
      this.encryptableOldFreeInformation = value;
   }

   public Table getTable() {
      return this.table;
   }

   public void setTable(Table value) {
      this.table = value;
   }

   public byte[] getEncryptableFreeText() {
      return this.encryptableFreeText;
   }

   public void setEncryptableFreeText(byte[] value) {
      this.encryptableFreeText = value;
   }
}
