package be.fgov.ehealth.errors.service.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "TraceType",
   propOrder = {"traceElements"}
)
public class TraceType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "TraceElement",
      required = true
   )
   protected List<String> traceElements;

   public List<String> getTraceElements() {
      if (this.traceElements == null) {
         this.traceElements = new ArrayList();
      }

      return this.traceElements;
   }
}
