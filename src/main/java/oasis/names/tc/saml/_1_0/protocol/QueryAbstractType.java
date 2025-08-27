package oasis.names.tc.saml._1_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "QueryAbstractType"
)
@XmlSeeAlso({SubjectQueryAbstractType.class})
public abstract class QueryAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
}
