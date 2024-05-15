package org.taktik.connector.business.agreement.domain;

import be.fgov.ehealth.agreement.protocol.v1.AskAgreementRequest;

public class AskAgreementBuilderRequest extends AgreementBuilderRequest<AskAgreementRequest> {
   public AskAgreementBuilderRequest() {
      super(new AskAgreementRequest());
   }
}
