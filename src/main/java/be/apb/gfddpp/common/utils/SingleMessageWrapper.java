package be.apb.gfddpp.common.utils;

import be.apb.gfddpp.common.exceptions.GFDDPPException;
import be.apb.gfddpp.common.status.StatusCode;
import be.apb.gfddpp.domain.CareServiceIdType;
import be.apb.gfddpp.domain.PharmacyIdType;
import be.apb.gfddpp.helper.PropertyHandler;
import be.apb.standards.smoa.schema.id.v1.AbstractCareServiceIdType;
import be.apb.standards.smoa.schema.id.v1.AbstractPharmacyIdType;
import be.apb.standards.smoa.schema.id.v1.EntityIdType;
import be.apb.standards.smoa.schema.id.v1.NihiiIdType;
import be.apb.standards.smoa.schema.model.v1.AbstractEntityType;
import be.apb.standards.smoa.schema.model.v1.HistoryProductType;
import be.apb.standards.smoa.schema.model.v1.MaxSetProductType;
import be.apb.standards.smoa.schema.model.v1.MedicationHistoryType;
import be.apb.standards.smoa.schema.model.v1.MinSetPatient;
import be.apb.standards.smoa.schema.model.v1.ReferenceCareServiceType;
import be.apb.standards.smoa.schema.model.v1.ReferencePharmacyType;
import be.apb.standards.smoa.schema.model.v1.StatusMessageType;
import be.apb.standards.smoa.schema.v1.AbstractEventType;
import be.apb.standards.smoa.schema.v1.ArchivePrescriptionCommentEventType;
import be.apb.standards.smoa.schema.v1.ArchivePrescriptionEventType;
import be.apb.standards.smoa.schema.v1.BvacEventType;
import be.apb.standards.smoa.schema.v1.DigitalSignatureType;
import be.apb.standards.smoa.schema.v1.EventFolderType;
import be.apb.standards.smoa.schema.v1.HeaderType;
import be.apb.standards.smoa.schema.v1.ID;
import be.apb.standards.smoa.schema.v1.MedicationHistoryEvent;
import be.apb.standards.smoa.schema.v1.MedicationSchemeEntriesRequest;
import be.apb.standards.smoa.schema.v1.ObjectFactory;
import be.apb.standards.smoa.schema.v1.PharmaceuticalCareEventType;
import be.apb.standards.smoa.schema.v1.SenderType;
import be.apb.standards.smoa.schema.v1.SingleMessage;
import be.apb.standards.smoa.schema.v1.SmoaMessageType;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class SingleMessageWrapper {
   private static String VERSION = null;
   private static final String SMC_VERSION_PROP = "smcVersion";
   private static final String SMC_PROP_FILE = "/smc.properties";
   private final SingleMessage singleMessage;
   private static final Logger LOG = LogManager.getLogger(SingleMessageWrapper.class);

   public SingleMessageWrapper(CareServiceIdType type, String id) throws GFDDPPException {
      DatatypeFactory dataTypeFactory;
      try {
         dataTypeFactory = DatatypeFactory.newInstance();
      } catch (DatatypeConfigurationException var8) {
         throw new RuntimeException(var8);
      }

      ObjectFactory schemaObjectFactory = new ObjectFactory();
      SmoaMessageType message = schemaObjectFactory.createSmoaMessageType();
      HeaderType headerType = schemaObjectFactory.createHeaderType();
      headerType.setMessageCreateDate(dataTypeFactory.newXMLGregorianCalendar(new GregorianCalendar()));
      headerType.setMessageID(Utils.generateGUID("M" + type.toString() + id));
      headerType.setVersion(getSMCVersion());
      headerType.setSender(this.createCareServiceSender(type, id));
      message.setHeader(headerType);
      EventFolderType eventFolderType = schemaObjectFactory.createEventFolderType();
      eventFolderType.setEvents(schemaObjectFactory.createEventFolderTypeEvents());
      eventFolderType.setEntitySpace(schemaObjectFactory.createEventFolderTypeEntitySpace());
      message.setAbstractFolder(schemaObjectFactory.createEventFolder(eventFolderType));
      this.singleMessage = schemaObjectFactory.createSingleMessage();
      this.singleMessage.setUnsigned(message);
   }

   public SingleMessageWrapper(SingleMessage singleMessage) {
      this.singleMessage = singleMessage;
   }

   private static void initVersion() throws GFDDPPException {
      if (VERSION == null) {
         PropertyHandler propertyHandler = new PropertyHandler("/smc.properties");
         if (!propertyHandler.hasProperty("smcVersion")) {
            throw new GFDDPPException(StatusCode.COMMON_ERROR_SMC_VERSION_NOT_FOUND);
         }

         VERSION = propertyHandler.getProperty("smcVersion");
      }

   }

   private static String getSMCVersion() throws GFDDPPException {
      initVersion();
      return VERSION;
   }

   public SingleMessage getWrappedMessage() {
      return this.singleMessage;
   }

   public boolean isSigned() {
      return this.getWrappedMessage().getSigned() != null && this.getWrappedMessage().getSigned().getMessage() != null;
   }

   public DigitalSignatureType getSignature() {
      return this.isSigned() ? this.getWrappedMessage().getSigned().getSignature() : null;
   }

   public SmoaMessageType getSmoaMessage() {
      return this.isSigned() ? this.getWrappedMessage().getSigned().getMessage() : this.getWrappedMessage().getUnsigned();
   }

   public HeaderType getHeader() {
      SmoaMessageType smoa = this.getSmoaMessage();
      return smoa != null ? smoa.getHeader() : null;
   }

   public String getVersion() {
      HeaderType header = this.getHeader();
      return header != null ? header.getVersion() : null;
   }

   public String getMessageID() {
      HeaderType header = this.getHeader();
      return header != null ? header.getMessageID() : null;
   }

   public String getSenderType() {
      SenderType sender = this.getHeader().getSender();
      AbstractCareServiceIdType id = this.getCareServiceIdFromSender(sender);
      if (id != null) {
         return CareServiceIdType.valueOf(id.getClass()).name();
      } else {
         AbstractPharmacyIdType idFromSender = this.getPharmacyIdFromSender(sender);
         if (idFromSender != null) {
            return PharmacyIdType.valueOf(idFromSender.getClass()).name();
         } else {
            throw new IllegalStateException("unsupported sender in xml instance");
         }
      }
   }

   public String getSenderID() {
      SenderType sender = this.getHeader().getSender();
      AbstractCareServiceIdType id = this.getCareServiceIdFromSender(sender);
      if (id != null) {
         return CareServiceIdType.valueOf(id.getClass()).getIdFrom(id);
      } else {
         AbstractPharmacyIdType idFromSender = this.getPharmacyIdFromSender(sender);
         if (idFromSender != null) {
            return PharmacyIdType.valueOf(idFromSender.getClass()).getIdFrom(idFromSender);
         } else {
            throw new IllegalStateException("unsupported sender in xml instance");
         }
      }
   }

   private AbstractCareServiceIdType getCareServiceIdFromSender(SenderType sender) {
      return sender.getAbstractCareService() != null && sender.getAbstractCareService().getValue() != null && ReferenceCareServiceType.class.isAssignableFrom(sender.getAbstractCareService().getValue().getClass()) ? ((ReferenceCareServiceType)sender.getAbstractCareService().getValue()).getCareServiceId() : null;
   }

   private AbstractPharmacyIdType getPharmacyIdFromSender(SenderType sender) {
      return sender.getAbstractPharmacy() != null && sender.getAbstractPharmacy().getValue() != null && ReferencePharmacyType.class.isAssignableFrom(sender.getAbstractPharmacy().getValue().getClass()) ? ((ReferencePharmacyType)sender.getAbstractPharmacy().getValue()).getPharmacyId() : null;
   }

   private SenderType createCareServiceSender(CareServiceIdType type, String cbe) {
      be.apb.standards.smoa.schema.model.v1.ObjectFactory modelObjectFactory = new be.apb.standards.smoa.schema.model.v1.ObjectFactory();
      ReferenceCareServiceType refCareService = modelObjectFactory.createReferenceCareServiceType();
      refCareService.setCareServiceId(type.createId(cbe));
      ObjectFactory schemaObjectFactory = new ObjectFactory();
      SenderType sender = schemaObjectFactory.createSenderType();
      sender.setAbstractCareService(modelObjectFactory.createRefCareService(refCareService));
      return sender;
   }

   public EventFolderType getBody() {
      return (EventFolderType)this.getSmoaMessage().getAbstractFolder().getValue();
   }

   public List<AbstractEntityType> getEntities() {
      return this.getBody().getEntitySpace().getEntity();
   }

   public List<AbstractEventType> getEvents() {
      return this.getBody().getEvents().getEvent();
   }

   public Iterator<MaxSetProductType> createIteratorOverAllDispensedProducts() {
      return new DispensedProductsIterator(this);
   }

   public <T extends AbstractEventType> List<T> getAllEventsOfType(Class<T> clazz) {
      List<T> result = new LinkedList<>();
      for (AbstractEventType type : this.getEvents()) {
         if (clazz.isInstance(type)) {
            result.add((T) type);
         }
      }

      return Collections.unmodifiableList(result);
   }

   public <T extends AbstractEntityType> List<T> getAllEntitiesOfType(Class<T> clazz) {
      List<T> result = new LinkedList<>();
      for (AbstractEntityType type : this.getEntities()) {
         if (clazz.isInstance(type)) {
            result.add((T) type);
         }
      }

      return Collections.unmodifiableList(result);
   }

   public List<MaxSetProductType> getAllDispensedProducts() {
      List<MaxSetProductType> result = new LinkedList<>();

      for (PharmaceuticalCareEventType session : this.getAllEventsOfType(PharmaceuticalCareEventType.class)) {
         result.addAll(getAllDispensedProducts(session));
      }

      return Collections.unmodifiableList(result);
   }

   private static List<MaxSetProductType> getAllDispensedProducts(PharmaceuticalCareEventType session) {
      List<MaxSetProductType> result = new LinkedList<>();
      Iterator i$;
      if (session.getDispensedWithoutPrescription() != null) {
         i$ = session.getDispensedWithoutPrescription().getProduct().iterator();

         while(i$.hasNext()) {
            MaxSetProductType product = (MaxSetProductType)i$.next();
            result.add(product);
         }
      }

      i$ = session.getDispensedForSamePrescription().iterator();

      while(i$.hasNext()) {
         PharmaceuticalCareEventType.DispensedForSamePrescription dispensedForSamePrescription = (PharmaceuticalCareEventType.DispensedForSamePrescription)i$.next();
         result.addAll(dispensedForSamePrescription.getProduct());
      }

      return Collections.unmodifiableList(result);
   }

   public List<String> getAllDGUIDs() {
      List<String> result = new LinkedList<>();
      List<MedicationHistoryEvent> medicationHistoryEvents = this.getAllEventsOfType(MedicationHistoryEvent.class);
      Iterator i$ = medicationHistoryEvents.iterator();

      while(i$.hasNext()) {
         MedicationHistoryEvent event = (MedicationHistoryEvent)i$.next();

         for (MedicationHistoryType entity : event.getMedicationHistoryEntity()) {
            result.add(((EntityIdType) entity.getEntityId()).getId());
         }
      }

      i$ = this.getAllDispensedProducts().iterator();

      while(i$.hasNext()) {
         MaxSetProductType type = (MaxSetProductType)i$.next();
         result.add(type.getDispensationGUID());
      }

      return result;
   }

   public List<MedicationHistoryType> getAllMedicationHistoryEntries() {
      List<MedicationHistoryType> result = new LinkedList<>();

      for (MedicationHistoryEvent event : this.getAllEventsOfType(MedicationHistoryEvent.class)) {
         result.addAll(event.getMedicationHistoryEntity());
      }

      return result;
   }

   public List<MedicationSchemeEntriesRequest> getAllMedicationSchemeEntryRequests() {
      return this.getAllEventsOfType(MedicationSchemeEntriesRequest.class);
   }

   public List<String> getAllUniqueMedicationHistoryEntriesEncryptionIds() {
      List<String> encryptionids = new ArrayList<>();

      for(int i = 0; i < this.getAllMedicationHistoryEntries().size(); ++i) {
         MedicationHistoryType medicationHistoryType = this.getAllMedicationHistoryEntries().get(i);
         if (medicationHistoryType != null) {
            if (medicationHistoryType.getEncryptedContent() != null) {
               String encryptionid = medicationHistoryType.getEncryptedContent().getEncryptionKeyId();
               if (!encryptionids.contains(encryptionid)) {
                  LOG.info("UNIQUE - ENCRYPTIOID: " + encryptionid);
                  encryptionids.add(encryptionid);
               }
            }
         } else {
            LOG.info("WRAPPER - medicationHistoryType is NULL");
         }
      }

      return encryptionids;
   }

   public List<HistoryProductType> getAllMedicationHistoryProducts() {
      List<HistoryProductType> result = new LinkedList<>();

      for (MedicationHistoryType medicationHistoryType : this.getAllMedicationHistoryEntries()) {
         if (medicationHistoryType != null && medicationHistoryType.getProduct() != null) {
            result.add(medicationHistoryType.getProduct());
         }
      }

      return Collections.unmodifiableList(result);
   }

   public List<PharmaceuticalCareEventType.DispensedForSamePrescription> getAllDispensedForSamePrescriptions() {
      List<PharmaceuticalCareEventType.DispensedForSamePrescription> result = new LinkedList<>();

      for (PharmaceuticalCareEventType pharmaceuticalCareEventType : this.getAllEventsOfType(PharmaceuticalCareEventType.class)) {
         List<PharmaceuticalCareEventType.DispensedForSamePrescription> dispensedForSamePrescriptions = pharmaceuticalCareEventType.getDispensedForSamePrescription();

         for (PharmaceuticalCareEventType.DispensedForSamePrescription dispensedForSamePrescription : dispensedForSamePrescriptions) {
            if (dispensedForSamePrescription != null) {
               result.add(dispensedForSamePrescription);
            }
         }
      }

      return Collections.unmodifiableList(result);
   }

   public List<PharmaceuticalCareEventType.DispensedWithoutPrescription> getAllDispensedWithoutPrescriptions() {
      List<PharmaceuticalCareEventType.DispensedWithoutPrescription> result = new LinkedList<>();

      for (PharmaceuticalCareEventType pharmaceuticalCareEventType : this.getAllEventsOfType(PharmaceuticalCareEventType.class)) {
         PharmaceuticalCareEventType.DispensedWithoutPrescription dispensedWithoutPrescription = pharmaceuticalCareEventType.getDispensedWithoutPrescription();
         if (dispensedWithoutPrescription != null) {
            result.add(dispensedWithoutPrescription);
         }
      }

      return Collections.unmodifiableList(result);
   }

   public HashMap<String, MaxSetProductType> getAllDGUIDsAndProducts() {
      HashMap<String, MaxSetProductType> result = new HashMap<>();
      for(PharmaceuticalCareEventType.DispensedForSamePrescription dispensedForSamePrescription:this.getAllDispensedForSamePrescriptions()) {
         for(PharmaceuticalCareEventType.DispensedForSamePrescription.Product product : dispensedForSamePrescription.getProduct()) {
            if (product != null) {
               result.put(product.getDispensationGUID(), product);
            }
         }
      }

      for (PharmaceuticalCareEventType.DispensedWithoutPrescription dispensedWithoutPrescription : this.getAllDispensedWithoutPrescriptions()) {
         for (MaxSetProductType product : dispensedWithoutPrescription.getProduct()) {
            if (product != null) {
               result.put(product.getDispensationGUID(), product);
            }
         }
      }

      return result;
   }

   public List<PharmaceuticalCareEventType.DispensedForSamePrescription.Product> getAllDispensedForSamePrescriptionProducts() {
      List<PharmaceuticalCareEventType.DispensedForSamePrescription.Product> result = new LinkedList<>();

      for (PharmaceuticalCareEventType.DispensedForSamePrescription dispensedForSamePrescription : this.getAllDispensedForSamePrescriptions()) {
         for (PharmaceuticalCareEventType.DispensedForSamePrescription.Product product : dispensedForSamePrescription.getProduct()) {
            if (product != null) {
               result.add(product);
            }
         }
      }

      return Collections.unmodifiableList(result);
   }

   public List<MaxSetProductType> getAllDispensedWithoutPrescriptionProducts() {
      List<MaxSetProductType> result = new LinkedList<>();

      for (PharmaceuticalCareEventType.DispensedWithoutPrescription dispensedWithoutPrescription : this.getAllDispensedWithoutPrescriptions()) {
         for (MaxSetProductType product : dispensedWithoutPrescription.getProduct()) {
            if (product != null) {
               result.add(product);
            }
         }
      }

      return Collections.unmodifiableList(result);
   }

   public String getPharmaceuticalCareEventTypePharmacyId(PharmaceuticalCareEventType event) {
      NihiiIdType nihiiIdType = event.getPharmacyId();
      return nihiiIdType != null ? nihiiIdType.getNihiiPharmacyNumber() : null;
   }

   public String getArchivePrescriptionEventTypePharmacyId(ArchivePrescriptionEventType event) {
      ID nihiiIdType = event.getExecutorId();
      return nihiiIdType != null ? nihiiIdType.getValue() : null;
   }

   public String getArchivePrescriptionCommentEventTypePharmacyId(ArchivePrescriptionCommentEventType event) {
      ID nihiiIdType = event.getExecutorId();
      return nihiiIdType != null ? nihiiIdType.getValue() : null;
   }

   public String getBvacEventTypePharmacyId(BvacEventType event) {
      String nihiiPharmacy = null;
      if (event.getPharmacyList() != null && event.getPharmacyList().getPharmacy().size() > 0 && event.getPharmacyList().getPharmacy().get(0).getIdentification() != null && StringUtils.isNotBlank(event.getPharmacyList().getPharmacy().get(0).getIdentification().getId())) {
         nihiiPharmacy = event.getPharmacyList().getPharmacy().get(0).getIdentification().getId();
      }

      return nihiiPharmacy;
   }

   public MinSetPatient getMedicationHistoryTypePatient(MedicationHistoryType medicationHistoryType) {
      return medicationHistoryType.getMinPatient();
   }

   public String getDGUIDMinSetProductType(PharmaceuticalCareEventType.DispensedForSamePrescription.Product product) {
      return product.getDispensationGUID();
   }

   public String getDGUIDMaxSetProductType(MaxSetProductType product) {
      return product.getDispensationGUID();
   }

   public List<StatusMessageType> getAllStatusMessages() {
      return this.getEntities().stream().filter(entity -> entity instanceof StatusMessageType).map(entity -> (StatusMessageType) entity).collect(Collectors.toCollection(LinkedList::new));
   }

   public String getHeaderPharmacyId() {
      if (this.getHeader() != null && this.getHeader().getSender() != null && this.getHeader().getSender().getAbstractPharmacy() != null && this.getHeader().getSender().getAbstractPharmacy().getValue() != null) {
         AbstractPharmacyIdType pharmacyId = ((ReferencePharmacyType)this.getHeader().getSender().getAbstractPharmacy().getValue()).getPharmacyId();
         NihiiIdType nihiiIdType = (NihiiIdType)pharmacyId;
         return nihiiIdType.getNihiiPharmacyNumber();
      } else {
         return null;
      }
   }

   public String getSmoaId() {
      HeaderType header = this.getHeader();
      String id = null;
      if (header != null) {
         id = header.getMessageID();
      }

      return id;
   }

   public boolean containProducts() {
      List<PharmaceuticalCareEventType.DispensedForSamePrescription> dispensedForSamePrescriptions = new ArrayList<>();

      int i;
      for(i = 0; i < this.getAllDispensedForSamePrescriptions().size(); ++i) {
         if (this.getAllDispensedForSamePrescriptions().get(i).getProduct().isEmpty()) {
            dispensedForSamePrescriptions.add(this.getAllDispensedForSamePrescriptions().get(i));
            LOG.info("a dispensed for same prescriptions list has been removed because it doesn't containt any product");
         }
      }

      for(i = 0; i < this.getAllDispensedWithoutPrescriptions().size(); ++i) {
         if (this.getAllDispensedWithoutPrescriptions().get(i).getProduct().isEmpty()) {
            this.getAllEventsOfType(PharmaceuticalCareEventType.class).get(0).setDispensedWithoutPrescription(null);
            LOG.info("a dispensed without prescription list has been removed because it doesn't containt any product");
         }
      }

      this.getAllEventsOfType(PharmaceuticalCareEventType.class).get(0).getDispensedForSamePrescription().removeAll(dispensedForSamePrescriptions);
      return !this.getAllDispensedForSamePrescriptions().isEmpty() || !this.getAllDispensedWithoutPrescriptions().isEmpty();
   }

   public boolean containEvents() {
      return !this.getEvents().isEmpty();
   }
}
