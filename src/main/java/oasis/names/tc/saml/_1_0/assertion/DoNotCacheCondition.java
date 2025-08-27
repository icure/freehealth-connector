package oasis.names.tc.saml._1_0.assertion;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DoNotCacheConditionType"
)
@XmlRootElement(
   name = "DoNotCacheCondition"
)
public class DoNotCacheCondition extends ConditionAbstractType implements Serializable {
   private static final long serialVersionUID = 1L;
}
