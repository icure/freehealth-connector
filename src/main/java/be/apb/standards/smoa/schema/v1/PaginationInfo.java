package be.apb.standards.smoa.schema.v1;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PaginationInfo",
   propOrder = {"paginationIndex", "paginationSize", "recordCount"}
)
public class PaginationInfo {
   protected int paginationIndex;
   protected int paginationSize;
   protected int recordCount;

   public int getPaginationIndex() {
      return this.paginationIndex;
   }

   public void setPaginationIndex(int value) {
      this.paginationIndex = value;
   }

   public int getPaginationSize() {
      return this.paginationSize;
   }

   public void setPaginationSize(int value) {
      this.paginationSize = value;
   }

   public int getRecordCount() {
      return this.recordCount;
   }

   public void setRecordCount(int value) {
      this.recordCount = value;
   }
}
