package be.fgov.ehealth.consultrn.ssinhistory.protocol.v1;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   name = "ConsultRelatedSsinsRequest"
)
public class ConsultRelatedSsinsRequest extends ConsultSsinRequestType {
   private static final long serialVersionUID = -5662414438761135502L;
}
