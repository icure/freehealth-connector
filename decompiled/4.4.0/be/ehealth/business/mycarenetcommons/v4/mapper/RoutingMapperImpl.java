package be.ehealth.business.mycarenetcommons.v4.mapper;

import be.ehealth.business.mycarenetdomaincommons.domain.Attribute;
import be.ehealth.business.mycarenetdomaincommons.domain.CareReceiverId;
import be.ehealth.business.mycarenetdomaincommons.domain.Identifier;
import be.ehealth.business.mycarenetdomaincommons.domain.Period;
import be.ehealth.business.mycarenetdomaincommons.domain.Routing;
import be.ehealth.business.mycarenetdomaincommons.domain.Subject;
import be.fgov.ehealth.mycarenet.commons.core.v4.AttributeType;
import be.fgov.ehealth.mycarenet.commons.core.v4.CareReceiverIdType;
import be.fgov.ehealth.mycarenet.commons.core.v4.PeriodType;
import be.fgov.ehealth.mycarenet.commons.core.v4.RoutingType;
import be.fgov.ehealth.mycarenet.commons.core.v4.SubjectType;
import java.util.ArrayList;
import java.util.List;

public class RoutingMapperImpl implements RoutingMapper {
   public RoutingMapperImpl() {
   }

   public RoutingType map(Routing input) {
      if (input == null) {
         return null;
      } else {
         RoutingType routingType = new RoutingType();
         routingType.setSubject(this.subjectToSubjectType(input.getSubject()));
         routingType.setCareReceiver(this.map(input.getCareReceiver()));
         routingType.setReferenceDate(input.getReferenceDate());
         routingType.setPeriod(this.map(input.getPeriod()));
         return routingType;
      }
   }

   public CareReceiverIdType map(CareReceiverId input) {
      if (input == null) {
         return null;
      } else {
         CareReceiverIdType careReceiverIdType = new CareReceiverIdType();
         careReceiverIdType.setSsin(input.getSsinNumber());
         careReceiverIdType.setRegNrWithMut(input.getRegistrationNumberWithMutuality());
         careReceiverIdType.setMutuality(input.getMutuality());
         return careReceiverIdType;
      }
   }

   public PeriodType map(Period input) {
      if (input == null) {
         return null;
      } else {
         PeriodType periodType = new PeriodType();
         periodType.setStart(input.getBegin());
         periodType.setEnd(input.getEnd());
         return periodType;
      }
   }

   protected be.fgov.ehealth.mycarenet.commons.core.v4.Identifier identifierToIdentifier(Identifier identifier) {
      if (identifier == null) {
         return null;
      } else {
         be.fgov.ehealth.mycarenet.commons.core.v4.Identifier identifier1 = new be.fgov.ehealth.mycarenet.commons.core.v4.Identifier();
         identifier1.setValue(identifier.getValue());
         identifier1.setType(identifier.getType());
         return identifier1;
      }
   }

   protected List<be.fgov.ehealth.mycarenet.commons.core.v4.Identifier> identifierListToIdentifierList(List<Identifier> list) {
      if (list == null) {
         return null;
      } else {
         List<be.fgov.ehealth.mycarenet.commons.core.v4.Identifier> list1 = new ArrayList(list.size());

         for(Identifier identifier : list) {
            list1.add(this.identifierToIdentifier(identifier));
         }

         return list1;
      }
   }

   protected AttributeType attributeToAttributeType(Attribute attribute) {
      if (attribute == null) {
         return null;
      } else {
         AttributeType attributeType = new AttributeType();
         attributeType.setValue(attribute.getValue());
         attributeType.setKey(attribute.getKey());
         return attributeType;
      }
   }

   protected List<AttributeType> attributeListToAttributeTypeList(List<Attribute> list) {
      if (list == null) {
         return null;
      } else {
         List<AttributeType> list1 = new ArrayList(list.size());

         for(Attribute attribute : list) {
            list1.add(this.attributeToAttributeType(attribute));
         }

         return list1;
      }
   }

   protected SubjectType subjectToSubjectType(Subject subject) {
      if (subject == null) {
         return null;
      } else {
         SubjectType subjectType = new SubjectType();
         subjectType.setType(subject.getType());
         subjectType.setSubType(subject.getSubType());
         if (subjectType.getIdentifiers() != null) {
            List<be.fgov.ehealth.mycarenet.commons.core.v4.Identifier> list = this.identifierListToIdentifierList(subject.getIdentifiers());
            if (list != null) {
               subjectType.getIdentifiers().addAll(list);
            }
         }

         if (subjectType.getAttributes() != null) {
            List<AttributeType> list1 = this.attributeListToAttributeTypeList(subject.getAttributes());
            if (list1 != null) {
               subjectType.getAttributes().addAll(list1);
            }
         }

         return subjectType;
      }
   }
}
