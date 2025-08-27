package be.cin.nip.async.generic;

import be.cin.mycarenet.esb.common.v2.OrigineType;
import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"origin", "replyToEtk", "queryParameters", "msgQuery", "tAckQuery"}
)
@XmlRootElement(
        name = "get"
)
public class Get implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
           name = "Origin",
           required = true
   )
   protected OrigineType origin;
   @XmlElement(
           name = "Reply-to-etk"
   )
   protected byte[] replyToEtk;
   @XmlElement(
           name = "QueryParameters"
   )
   protected QueryParameters queryParameters;
   @XmlElement(
           name = "MsgQuery",
           required = true
   )
   protected MsgQuery msgQuery;
   @XmlElement(
           name = "TAckQuery",
           required = true
   )
   protected Query tAckQuery;

   public OrigineType getOrigin() {
      return this.origin;
   }

   public void setOrigin(OrigineType value) {
      this.origin = value;
   }

   public byte[] getReplyToEtk() {
      return this.replyToEtk;
   }

   public void setReplyToEtk(byte[] value) {
      this.replyToEtk = value;
   }

   public QueryParameters getQueryParameters() {
      return this.queryParameters;
   }

   public void setQueryParameters(QueryParameters value) {
      this.queryParameters = value;
   }

   public MsgQuery getMsgQuery() {
      return this.msgQuery;
   }

   public void setMsgQuery(MsgQuery value) {
      this.msgQuery = value;
   }

   public Query getTAckQuery() {
      return this.tAckQuery;
   }

   public void setTAckQuery(Query value) {
      this.tAckQuery = value;
   }
}
