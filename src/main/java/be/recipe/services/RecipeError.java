package be.recipe.services;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "recipeError",
   propOrder = {"message", "resolution", "severity"}
)
@XmlRootElement(
   name = "recipeError"
)
public class RecipeError implements Serializable {
   private static final long serialVersionUID = 1L;
   protected String message;
   protected String resolution;
   protected String severity;

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String value) {
      this.message = value;
   }

   public String getResolution() {
      return this.resolution;
   }

   public void setResolution(String value) {
      this.resolution = value;
   }

   public String getSeverity() {
      return this.severity;
   }

   public void setSeverity(String value) {
      this.severity = value;
   }
}
