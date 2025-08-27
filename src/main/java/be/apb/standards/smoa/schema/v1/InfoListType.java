package be.apb.standards.smoa.schema.v1;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "InfoListType",
   propOrder = {"info"}
)
public class InfoListType {
   @XmlElement(
      name = "Info",
      required = true
   )
   protected List<MetaDataType> info;

   public List<MetaDataType> getInfo() {
      if (this.info == null) {
         this.info = new ArrayList();
      }

      return this.info;
   }
}
