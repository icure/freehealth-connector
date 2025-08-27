package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listFeedbacksResult",
   propOrder = {"feedbacks"}
)
@XmlRootElement(
   name = "listFeedbacksResult"
)
public class ListFeedbacksResult extends ResponseType {
   protected List<ListFeedbackItem> feedbacks;

   public List<ListFeedbackItem> getFeedbacks() {
      if (this.feedbacks == null) {
         this.feedbacks = new ArrayList();
      }

      return this.feedbacks;
   }
}
