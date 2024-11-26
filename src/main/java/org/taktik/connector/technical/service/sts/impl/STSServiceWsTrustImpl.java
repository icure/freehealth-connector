package org.taktik.connector.technical.service.sts.impl;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.idgenerator.IdGeneratorFactory;
import org.taktik.connector.technical.service.sts.STSService;
import org.taktik.connector.technical.service.sts.domain.SAMLAttribute;
import org.taktik.connector.technical.service.sts.domain.SAMLAttributeDesignator;
import org.taktik.connector.technical.service.sts.domain.SAMLNameIdentifier;
import org.taktik.connector.technical.service.sts.security.Credential;
import org.taktik.connector.technical.service.ws.ServiceFactory;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import org.taktik.connector.technical.utils.ConnectorXmlUtils;
import org.taktik.connector.technical.utils.DateUtils;
import org.taktik.connector.technical.ws.domain.GenericRequest;
import org.taktik.connector.technical.ws.domain.GenericResponse;

import java.security.cert.CertificateEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.xml.soap.SOAPException;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.joda.time.DateTime;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class STSServiceWsTrustImpl extends AbstractSTSService implements STSService {
    public Element getToken(@NotNull Credential headerCredentials, @NotNull Credential bodyCredentials, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, String authenticationMethod, String nameQualifier, String value, String subjectConfirmationMethod, int validity) throws TechnicalConnectorException {
        try {
            Element issuePayload;
            if ("urn:oasis:names:tc:SAML:1.0:cm:holder-of-key".equals(subjectConfirmationMethod)) {
                issuePayload = this.issueHokToken(bodyCredentials, attributes, designators, validity);
            } else {
                if (!"urn:oasis:names:tc:SAML:1.0:cm:sender-vouches".equals(subjectConfirmationMethod)) {
                    throw new UnsupportedOperationException("SubjectConfirmationMethod [" + subjectConfirmationMethod + "] not supported.");
                }

                SAMLNameIdentifier nameId = this.generateNameIdentifier(bodyCredentials, nameQualifier, value);
                issuePayload = this.issueSVToken(nameId, authenticationMethod, attributes, designators, validity);
            }

            return this.processRequest(headerCredentials, bodyCredentials, issuePayload);
        } catch (SOAPException | DOMException exception) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, exception, exception.getMessage());
        }
    }

    public Element getToken(@NotNull Credential headerCredentials, @NotNull Credential bodyCredentials, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, String subjectConfirmationMethod, int validity) throws TechnicalConnectorException {
        return this.getToken(headerCredentials, bodyCredentials, attributes, designators, (String) null, (String) null, (String) null, subjectConfirmationMethod, validity);
    }

    private Element issueHokToken(Credential bodyCredentials, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, int validity) throws TechnicalConnectorException {
        try {
            Element issuePayload = ConnectorXmlUtils.toElement(ConnectorXmlUtils.flatten(ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream("/wstrust/issue.samlv11.hok.template.xml"))).getBytes());
            issuePayload.setAttributeNS("http://docs.oasis-open.org/ws-sx/ws-trust/200512", "Context", IdGeneratorFactory.getIdGenerator("uuid").generateId());
            Document doc = issuePayload.getOwnerDocument();
            Element claims = (Element) issuePayload.getElementsByTagNameNS("http://docs.oasis-open.org/ws-sx/ws-trust/200512", "Claims").item(0);
            Set<String> claimsURI = new HashSet<>();

            Element x509Cert;

            for (SAMLAttribute attr : attributes) {
                if (!claimsURI.contains(attr.getName())) {
                    Element claim = doc.createElementNS("http://docs.oasis-open.org/wsfed/authorization/200706", "auth:ClaimType");
                    claim.setAttribute("Uri", attr.getName());
                    x509Cert = doc.createElementNS("http://docs.oasis-open.org/wsfed/authorization/200706", "auth:Value");
                    x509Cert.setTextContent(StringUtils.join(attr.getValues(), ";"));
                    claim.appendChild(x509Cert);
                    claims.appendChild(claim);
                    claimsURI.add(attr.getName());
                }
            }

            for (SAMLAttributeDesignator attr : designators) {
                if (!claimsURI.contains(attr.getName())) {
                    Element claim = doc.createElementNS("http://docs.oasis-open.org/wsfed/authorization/200706", "auth:ClaimType");
                    claim.setAttribute("Uri", attr.getName());
                    claims.appendChild(claim);
                }
            }

            DateTime now = new DateTime();
            Element lifetimeCreated = (Element) issuePayload.getElementsByTagNameNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Created").item(0);
            lifetimeCreated.setTextContent(DateUtils.printDateTime(now));
            Element claim = (Element) issuePayload.getElementsByTagNameNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Expires").item(0);
            claim.setTextContent(DateUtils.printDateTime(now.plusHours(validity)));
            x509Cert = (Element) issuePayload.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "X509Certificate").item(0);
            x509Cert.setTextContent(new String(Base64.encode(bodyCredentials.getCertificate().getEncoded())));
            return issuePayload;
        } catch (CertificateEncodingException var13) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var13, var13.getMessage());
        }
    }

    private Element issueSVToken(SAMLNameIdentifier nameIdentifier, String authmethod, List<SAMLAttribute> attributes, List<SAMLAttributeDesignator> designators, int validity) throws TechnicalConnectorException {
        String requestTemplate = "";
        if (StringUtils.isEmpty(authmethod)) {
            requestTemplate = ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream("/wstrust/issue.samlv11.sv.template.xml"));
        } else {
            requestTemplate = ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream("/wstrust/issue.samlv11.sv.authmethod.template.xml"));
        }

        String flattenRequest = ConnectorXmlUtils.flatten(requestTemplate);
        flattenRequest = this.processDefaultFields(flattenRequest, validity, nameIdentifier);
        flattenRequest = StringUtils.replace(flattenRequest, "${authenticationMethod}", authmethod);
        Element issuePayload = ConnectorXmlUtils.toElement(flattenRequest.getBytes());
        Document doc = issuePayload.getOwnerDocument();
        Element claims = (Element) issuePayload.getElementsByTagNameNS("http://docs.oasis-open.org/ws-sx/ws-trust/200512", "Claims").item(0);

        for (SAMLAttributeDesignator attr : designators) {
            Element claim = doc.createElementNS("http://docs.oasis-open.org/wsfed/authorization/200706", "auth:ClaimType");
            claim.setAttribute("Uri", attr.getName());
            claims.appendChild(claim);
        }

        Element attributesStatement = (Element) issuePayload.getElementsByTagNameNS("urn:oasis:names:tc:SAML:1.0:assertion", "AttributeStatement").item(0);

        for (SAMLAttribute attr : attributes) {
            Element attrEl = doc.createElementNS("urn:oasis:names:tc:SAML:1.0:assertion", "saml:Attribute");
            attrEl.setAttribute("AttributeName", attr.getName());
            attrEl.setAttribute("AttributeNamespace", attr.getNamespace());
            Element attrVal = doc.createElementNS("urn:oasis:names:tc:SAML:1.0:assertion", "saml:AttributeValue");
            if (!ArrayUtils.isEmpty(attr.getValues())) {
                attrVal.setTextContent(attr.getValues()[0]);
            }

            attrEl.appendChild(attrVal);
            attributesStatement.appendChild(attrEl);
        }

        return issuePayload;
    }

    public Element renewToken(Credential headerCredentials, Credential bodyCredentials, Element samlToken, int validity) throws TechnicalConnectorException {
        try {
            Element renewPayload = ConnectorXmlUtils.toElement(ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream("/wstrust/renew.samlv11.template.xml")).getBytes());
            renewPayload.setAttributeNS("http://docs.oasis-open.org/ws-sx/ws-trust/200512", "Context", IdGeneratorFactory.getIdGenerator("uuid").generateId());
            NodeList embeddedList = renewPayload.getElementsByTagNameNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Embedded");
            Element embedded = (Element) embeddedList.item(0);
            embedded.setAttributeNS("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "wsu:Id", "token-" + IdGeneratorFactory.getIdGenerator("uuid").generateId());
            Node samlTokenNode = renewPayload.getOwnerDocument().importNode(samlToken, true);
            embedded.appendChild(samlTokenNode);
            return this.processRequest(headerCredentials, bodyCredentials, renewPayload);
        } catch (SOAPException var9) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, var9, var9.getMessage());
        }
    }

    private Element processRequest(Credential headerCredentials, Credential bodyCredentials, Element payload) throws TechnicalConnectorException, SOAPException {
        GenericRequest request = ServiceFactory.getSTSService(headerCredentials.getCertificate(), headerCredentials.getPrivateKey());
        request.setSoapAction("urn:be:fgov:ehealth:sts:protocol:v1:RequestSecurityToken");
        request.setPayload(payload.getOwnerDocument());
        GenericResponse response = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(request);
        Element wsTrustElement = (Element) response.asNode();
        NodeList nodeChallenge = wsTrustElement.getElementsByTagNameNS("http://docs.oasis-open.org/ws-sx/ws-trust/200512", "Challenge");
        if (nodeChallenge != null && nodeChallenge.getLength() >= 1) {
            GenericRequest requestChallenge = ServiceFactory.getSTSService(bodyCredentials.getCertificate(), bodyCredentials.getPrivateKey());
            requestChallenge.setSoapAction("urn:be:fgov:ehealth:sts:protocol:v1:Challenge");
            String requestTemplate = ConnectorIOUtils.convertStreamToString(ConnectorIOUtils.getResourceAsStream("/wstrust/signchallenge.template.xml"));
            String flattenRequest = ConnectorXmlUtils.flatten(requestTemplate);
            flattenRequest = StringUtils.replaceEach(flattenRequest, new String[]{"${context}", "${challenge}"}, new String[]{wsTrustElement.getAttribute("Context"), nodeChallenge.item(0).getTextContent()});
            Element issuePayload = ConnectorXmlUtils.toElement(flattenRequest.getBytes());
            Document doc = issuePayload.getOwnerDocument();
            requestChallenge.setPayload(doc);
            response = org.taktik.connector.technical.ws.ServiceFactory.getGenericWsSender().send(requestChallenge);
            wsTrustElement = (Element) response.asNode();
        }

        NodeList nodeRequestedSecurityToken = wsTrustElement.getElementsByTagNameNS("http://docs.oasis-open.org/ws-sx/ws-trust/200512", "RequestedSecurityToken");
        if (nodeRequestedSecurityToken != null && nodeRequestedSecurityToken.getLength() >= 1) {
            return ConnectorXmlUtils.getFirstChildElement(nodeRequestedSecurityToken.item(0));
        } else {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_WS, "Unable to obtain token: reason unkown.");
        }
    }
}
