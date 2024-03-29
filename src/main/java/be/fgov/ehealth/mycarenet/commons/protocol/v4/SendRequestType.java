package be.fgov.ehealth.mycarenet.commons.protocol.v4;

import be.fgov.ehealth.commons.protocol.v2.RequestType;
import be.fgov.ehealth.mycarenet.commons.core.v4.BlobType;
import be.fgov.ehealth.mycarenet.commons.core.v4.CommonInputType;
import be.fgov.ehealth.mycarenet.commons.core.v4.RoutingType;
import org.w3._2005._05.xmlmime.Base64Binary;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
  name = "SendRequestType",
  propOrder = {"commonInput", "routing", "detail", "xades"}
)
public class SendRequestType extends RequestType implements Serializable {
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
