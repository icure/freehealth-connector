package org.w3._2001._04.xmlenc;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "EncryptedDataType"
)
@XmlRootElement(
   name = "EncryptedData"
)
public class EncryptedData extends EncryptedType implements Serializable {
   private static final long serialVersionUID = 1L;
}
