package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreateFeedbackResponseType"
)
@XmlRootElement(
   name = "CreateFeedbackResponse"
)
public class CreateFeedbackResponse extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
}
