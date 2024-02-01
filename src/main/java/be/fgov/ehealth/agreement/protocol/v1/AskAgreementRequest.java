package be.fgov.ehealth.agreement.protocol.v1;

import be.fgov.ehealth.mycarenet.commons.core.v3.BlobType;
import be.fgov.ehealth.mycarenet.commons.core.v3.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v3.RoutingType;
import be.fgov.ehealth.mycarenet.commons.protocol.v3.SendRequestType;
import org.w3._2005._05.xmlmime.Base64Binary;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(
   name = "AskAgreementRequest",
   namespace = "urn:be:fgov:ehealth:mycarenet:agreement:protocol:v1"
)
public class AskAgreementRequest extends SendRequestType implements Serializable {
   private static final long serialVersionUID = 1L;

  @XmlElement(
    name = "CommonInput",
    required = true
  )
  protected CommonInputType commonInput;
  @XmlElement(
    name = "Routing"
  )
  protected RoutingType routing;
  @XmlElement(
    name = "Detail",
    required = true
  )
  protected BlobType detail;
  @XmlElement(
    name = "Xades"
  )

  protected Base64Binary xades;

  public CommonInputType getCommonInput() {
    return this.commonInput;
  }

  public void setCommonInput(CommonInputType value) {
    this.commonInput = value;
  }

  public RoutingType getRouting() {
    return this.routing;
  }

  public void setRouting(RoutingType value) {
    this.routing = value;
  }

  public BlobType getDetail() {
    return this.detail;
  }

  public void setDetail(BlobType value) {
    this.detail = value;
  }

  public Base64Binary getXades() {
    return this.xades;
  }

  public void setXades(Base64Binary value) {
    this.xades = value;
  }
}
