package be.fgov.ehealth.consultrn._1_0.protocol;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "DeleteInscriptionReplyType"
)
@XmlRootElement(
   name = "DeleteInscriptionReply"
)
public class DeleteInscriptionReply extends ConsultRnReplyType implements Serializable {
   private static final long serialVersionUID = 1L;
}
