package be.fgov.ehealth.dics.protocol.v3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindByCommentedClassificationType",
   propOrder = {"anyNamePart", "commentedClassificationCode"}
)
public class FindByCommentedClassificationType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "AnyNamePart"
   )
   protected String anyNamePart;
   @XmlElement(
      name = "CommentedClassificationCode"
   )
   protected String commentedClassificationCode;

   public String getAnyNamePart() {
      return this.anyNamePart;
   }

   public void setAnyNamePart(String value) {
      this.anyNamePart = value;
   }

   public String getCommentedClassificationCode() {
      return this.commentedClassificationCode;
   }

   public void setCommentedClassificationCode(String value) {
      this.commentedClassificationCode = value;
   }
}
