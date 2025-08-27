package be.cin.nip.async.generic;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "internalPostResponse",
   propOrder = {"_return"}
)
@XmlRootElement(
   name = "internalPostResponse"
)
public class InternalPostResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "return",
      required = true
   )
   protected PostResponseReturn _return;

   public PostResponseReturn getReturn() {
      return this._return;
   }

   public void setReturn(PostResponseReturn value) {
      this._return = value;
   }
}
