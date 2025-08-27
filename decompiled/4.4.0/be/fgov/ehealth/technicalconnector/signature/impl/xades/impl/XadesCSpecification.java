package be.fgov.ehealth.technicalconnector.signature.impl.xades.impl;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.etee.crypto.ocsp.OCSPChecker;
import be.fgov.ehealth.etee.crypto.ocsp.OCSPCheckerBuilder;
import be.fgov.ehealth.etee.crypto.ocsp.OCSPData;
import be.fgov.ehealth.etee.crypto.ocsp.RevocationValues;
import be.fgov.ehealth.etee.crypto.policies.OCSPPolicy;
import be.fgov.ehealth.etee.crypto.status.CryptoResult;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationError;
import be.fgov.ehealth.technicalconnector.signature.domain.SignatureVerificationResult;
import be.fgov.ehealth.technicalconnector.signature.impl.DomUtils;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.SignedPropertiesBuilder;
import be.fgov.ehealth.technicalconnector.signature.impl.xades.domain.UnsignedPropertiesBuilder;
import java.io.IOException;
import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import org.apache.geronimo.mail.util.Base64;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.content.X509Data;
import org.apache.xml.security.signature.XMLSignature;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.cert.ocsp.OCSPResp;
import org.bouncycastle.cert.ocsp.OCSPRespBuilder;
import org.bouncycastle.cert.ocsp.SingleResp;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XadesCSpecification implements be.fgov.ehealth.technicalconnector.signature.impl.xades.XadesSpecification {
   public XadesCSpecification() {
   }

   public static boolean isSelfSigned(X509Certificate cert) {
      try {
         PublicKey key = cert.getPublicKey();
         cert.verify(key);
         return true;
      } catch (Exception var2) {
         return false;
      }
   }

   public void addOptionalBeforeSignatureParts(SignedPropertiesBuilder signedProps, XMLSignature sig, Credential signing, String uuid, Map<String, Object> options) throws TechnicalConnectorException {
   }

   public void addOptionalAfterSignatureParts(UnsignedPropertiesBuilder unsignedProps, XMLSignature sig, String uuid, Map<String, Object> options) throws TechnicalConnectorException {
      try {
         OCSPChecker ocspChecker = OCSPCheckerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.RECEIVER_MANDATORY).build();
         KeyInfo keyInfo = sig.getKeyInfo();

         for(int i = 0; i < keyInfo.lengthX509Data(); ++i) {
            X509Data x509Data = keyInfo.itemX509Data(i);

            for(int j = 0; j < x509Data.lengthCertificate(); ++j) {
               X509Certificate x509Certificate = x509Data.itemCertificate(j).getX509Certificate();
               OCSPData ocsp = (OCSPData)ocspChecker.validate(x509Certificate).getData();

               for(X509CRL crl : ocsp.getCrls()) {
                  unsignedProps.addCrlRef(crl);
               }

               unsignedProps.addOCSPRef(this.convertToOCSPResp(ocsp));
            }
         }

      } catch (Exception e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, e, new Object[]{"Unable to add optional Signature parts"});
      }
   }

   private byte[] convertToOCSPResp(OCSPData data) throws IOException, OCSPException {
      BasicOCSPResp basicResp = new BasicOCSPResp(BasicOCSPResponse.getInstance(ASN1Primitive.fromByteArray(data.getOcspResponse())));
      return (new OCSPRespBuilder()).build(0, basicResp).getEncoded();
   }

   public void verify(SignatureVerificationResult result, Element sigElement) {
      try {
         OCSPChecker ocspChecker = OCSPCheckerBuilder.newBuilder().addOCSPPolicy(OCSPPolicy.SENDER_MANDATORY).build();
         Map<BigInteger, RevocationValues> ocspCache = new HashMap();
         NodeList encapsulatedOCSPValues = DomUtils.getMatchingChilds(sigElement, "http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedOCSPValue");
         if (encapsulatedOCSPValues.getLength() == 0) {
            result.addError(SignatureVerificationError.XADES_ENCAPSULATED_OCSP_NOT_FOUND);
         }

         for(int i = 0; i < encapsulatedOCSPValues.getLength(); ++i) {
            Node encapsulatedOCSPValue = encapsulatedOCSPValues.item(i);
            byte[] rawOCSP = Base64.decode(encapsulatedOCSPValue.getTextContent());
            OCSPResp ocspResp = new OCSPResp(rawOCSP);
            BasicOCSPResp basicOCSPResp = (BasicOCSPResp)ocspResp.getResponseObject();

            for(SingleResp response : basicOCSPResp.getResponses()) {
               ocspCache.put(response.getCertID().getSerialNumber(), this.toRevocationValues(basicOCSPResp.getEncoded()));
            }
         }

         for(X509Certificate x509 : result.getCertChain()) {
            if (!isSelfSigned(x509)) {
               CryptoResult<OCSPData> validate = ocspChecker.validate(x509, result.getSigningTime().toDate(), (RevocationValues)ocspCache.get(x509.getSerialNumber()));
               if (validate.hasErrors()) {
                  result.addError(SignatureVerificationError.XADES_ENCAPSULATED_OCSP_NOT_VALID);
               }
            }
         }
      } catch (Exception var15) {
         result.addError(SignatureVerificationError.XADES_ENCAPSULATED_OCSP_NOT_VERIFIED);
      }

   }

   private RevocationValues toRevocationValues(byte[] rawOCSP) {
      RevocationValues revocationValues = new RevocationValues();
      revocationValues.getOcspVals().add(rawOCSP);
      return revocationValues;
   }
}
