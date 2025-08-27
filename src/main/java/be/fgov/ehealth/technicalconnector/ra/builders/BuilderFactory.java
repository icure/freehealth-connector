package be.fgov.ehealth.technicalconnector.ra.builders;

import org.taktik.connector.technical.service.sts.security.Credential;

public class BuilderFactory {
   public static EncryptionTokenBuilder.EncryptionTokenBuilderStep newEncryptionTokenBuilder(Credential cred) {
      return new EncryptionTokenBuilder.EncryptionTokenBuilderSteps(cred);
   }
}
