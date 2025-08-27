package be.recipe.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "recipeExceptionDetails",
   propOrder = {"code", "errorMap"}
)
public class RecipeExceptionDetails implements Serializable {
   private static final long serialVersionUID = 1L;
   protected String code;
   @XmlElement(
      required = true
   )
   protected ErrorMap errorMap;

   public String getCode() {
      return this.code;
   }

   public void setCode(String value) {
      this.code = value;
   }

   public ErrorMap getErrorMap() {
      return this.errorMap;
   }

   public void setErrorMap(ErrorMap value) {
      this.errorMap = value;
   }

   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(
      name = "",
      propOrder = {"entries"}
   )
   public static class ErrorMap implements Serializable {
      private static final long serialVersionUID = 1L;
      @XmlElement(
         name = "entry"
      )
      protected List<Entry> entries;

      public List<Entry> getEntries() {
         if (this.entries == null) {
            this.entries = new ArrayList();
         }

         return this.entries;
      }

      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(
         name = "",
         propOrder = {"key", "value"}
      )
      public static class Entry implements Serializable {
         private static final long serialVersionUID = 1L;
         protected String key;
         protected RecipeError value;

         public String getKey() {
            return this.key;
         }

         public void setKey(String value) {
            this.key = value;
         }

         public RecipeError getValue() {
            return this.value;
         }

         public void setValue(RecipeError value) {
            this.value = value;
         }
      }
   }
}
