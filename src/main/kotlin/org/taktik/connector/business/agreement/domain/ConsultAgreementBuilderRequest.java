package org.taktik.connector.business.agreement.domain;


import be.fgov.ehealth.agreement.protocol.v1.ConsultAgreementRequest;

public class ConsultAgreementBuilderRequest extends AgreementBuilderRequest<ConsultAgreementRequest> {
   public ConsultAgreementBuilderRequest() {
      super(new ConsultAgreementRequest());
   }
}
