package be.fgov.ehealth.technicalconnector.services.daas;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorXmlUtils;
import be.fgov.ehealth.daas.complextype.v1.Actor;
import be.fgov.ehealth.daas.complextype.v1.RoutingInformation;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.w3c.dom.Node;

public class AttributeValue {
   private Object value;

   public AttributeValue(Object value) {
      this.value = value;
   }

   public ValueType getValueType() {
      if (this.value instanceof String) {
         return AttributeValue.ValueType.STRING;
      } else if (this.value instanceof Node) {
         return AttributeValue.ValueType.NODE;
      } else if (this.value instanceof Actor) {
         return AttributeValue.ValueType.ACTOR;
      } else {
         return this.value instanceof RoutingInformation ? AttributeValue.ValueType.ROUTING_INFORMATION : AttributeValue.ValueType.UNKNOWN_OBJECT;
      }
   }

   public String asString() throws TechnicalConnectorException {
      if (this.value instanceof String) {
         return (String)this.asObject(String.class);
      } else {
         return this.value instanceof Node ? ConnectorXmlUtils.toString((Node)this.value) : ConnectorXmlUtils.toString(this.value);
      }
   }

   public Node asNode() throws TechnicalConnectorException {
      if (this.value instanceof String) {
         return ConnectorXmlUtils.getDocumentBuilder().newDocument().createTextNode((String)this.value);
      } else {
         return (Node)(this.value instanceof Node ? (Node)this.value : ConnectorXmlUtils.toDocument(this.value));
      }
   }

   public Actor asActor() throws TechnicalConnectorException {
      return (Actor)this.asObject(Actor.class);
   }

   public RoutingInformation asRoutingInformation() throws TechnicalConnectorException {
      return (RoutingInformation)this.asObject(RoutingInformation.class);
   }

   public <T> T asObject(Class<T> clazz) throws TechnicalConnectorException {
      Validate.notNull(clazz);
      if (this.value.getClass().isAssignableFrom(clazz)) {
         return (T)this.value;
      } else {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, new Object[]{"Unable to cast object to disered type [" + clazz.getName() + "]"});
      }
   }

   public Object getValue() {
      return this.value;
   }

   public String toString() {
      return ReflectionToStringBuilder.toString(this);
   }

   public static enum ValueType {
      STRING,
      NODE,
      ACTOR,
      ROUTING_INFORMATION,
      UNKNOWN_OBJECT;

      private ValueType() {
      }
   }
}
