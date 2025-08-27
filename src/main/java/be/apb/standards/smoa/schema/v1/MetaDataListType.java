package be.apb.standards.smoa.schema.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MetaDataListType",
   propOrder = {"metaData"}
)
public class MetaDataListType {
   @XmlElement(
      required = true
   )
   protected List<MetaDataType> metaData;

   public List<MetaDataType> getMetaData() {
      if (this.metaData == null) {
         this.metaData = new ArrayList();
      }

      return this.metaData;
   }
}
