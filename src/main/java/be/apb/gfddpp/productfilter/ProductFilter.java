package be.apb.gfddpp.productfilter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "",
   propOrder = {"version", "precedence", "productList"}
)
@XmlRootElement(
   name = "product-filter"
)
public class ProductFilter {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "dateTime"
   )
   protected XMLGregorianCalendar version;
   @XmlElement(
      required = true
   )
   protected String precedence;
   protected ProductList productList;

   public XMLGregorianCalendar getVersion() {
      return this.version;
   }

   public void setVersion(XMLGregorianCalendar value) {
      this.version = value;
   }

   public String getPrecedence() {
      return this.precedence;
   }

   public void setPrecedence(String value) {
      this.precedence = value;
   }

   public ProductList getProductList() {
      return this.productList;
   }

   public void setProductList(ProductList value) {
      this.productList = value;
   }
}
