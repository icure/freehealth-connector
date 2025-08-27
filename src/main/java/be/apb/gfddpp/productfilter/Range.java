package be.apb.gfddpp.productfilter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "range",
   propOrder = {"start", "end"}
)
public class Range {
   protected String start;
   protected String end;

   public String getStart() {
      return this.start;
   }

   public void setStart(String value) {
      this.start = value;
   }

   public String getEnd() {
      return this.end;
   }

   public void setEnd(String value) {
      this.end = value;
   }
}
