package org.etsi.uri._01903.v1_3;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "OtherTimeStampType"
)
@XmlRootElement(
   name = "OtherTimeStamp"
)
public class OtherTimeStamp extends GenericTimeStampType implements Serializable {
   private static final long serialVersionUID = 1L;
}
