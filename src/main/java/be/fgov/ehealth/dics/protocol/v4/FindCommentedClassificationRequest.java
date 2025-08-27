package be.fgov.ehealth.dics.protocol.v4;

import org.taktik.connector.technical.adapter.XmlDateAdapter;
import be.fgov.ehealth.commons.protocol.v2.RequestType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "FindCommentedClassificationRequestType",
   propOrder = {"findByProduct", "findByCommentedClassification"}
)
@XmlRootElement(
   name = "FindCommentedClassificationRequest"
)
public class FindCommentedClassificationRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "FindByProduct"
   )
   protected FindByVirtualProductType findByProduct;
   @XmlElement(
      name = "FindByCommentedClassification"
   )
   protected FindByCommentedClassificationType findByCommentedClassification;
   @XmlAttribute(
      name = "lang",
      namespace = "http://www.w3.org/XML/1998/namespace",
      required = true
   )
   protected String lang;
   @XmlAttribute(
      name = "SearchDate"
   )
   @XmlJavaTypeAdapter(XmlDateAdapter.class)
   @XmlSchemaType(
      name = "date"
   )
   protected DateTime searchDate;

   public FindByVirtualProductType getFindByProduct() {
      return this.findByProduct;
   }

   public void setFindByProduct(FindByVirtualProductType value) {
      this.findByProduct = value;
   }

   public FindByCommentedClassificationType getFindByCommentedClassification() {
      return this.findByCommentedClassification;
   }

   public void setFindByCommentedClassification(FindByCommentedClassificationType value) {
      this.findByCommentedClassification = value;
   }

   public String getLang() {
      return this.lang;
   }

   public void setLang(String value) {
      this.lang = value;
   }

   public DateTime getSearchDate() {
      return this.searchDate;
   }

   public void setSearchDate(DateTime value) {
      this.searchDate = value;
   }
}
