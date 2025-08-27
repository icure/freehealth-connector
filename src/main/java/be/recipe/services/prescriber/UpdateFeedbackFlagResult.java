package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "updateFeedbackFlagResult"
)
@XmlRootElement(
   name = "updateFeedbackFlagResult"
)
public class UpdateFeedbackFlagResult extends ResponseType {

}
