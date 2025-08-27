package be.fgov.ehealth.ehbox.core.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"encryptableLeftCell", "encryptableRightCell"}
)
public class Row implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "EncryptableLeftCell"
   )
   protected byte[] encryptableLeftCell;
   @XmlElement(
      name = "EncryptableRightCell"
   )
   protected byte[] encryptableRightCell;

   public byte[] getEncryptableLeftCell() {
      return this.encryptableLeftCell;
   }

   public void setEncryptableLeftCell(byte[] value) {
      this.encryptableLeftCell = value;
   }

   public byte[] getEncryptableRightCell() {
      return this.encryptableRightCell;
   }

   public void setEncryptableRightCell(byte[] value) {
      this.encryptableRightCell = value;
   }
}
