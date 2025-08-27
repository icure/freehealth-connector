package org.taktik.connector.business.domain.common;

import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by aduchate on 8/11/13, 16:16
 */
public class Addressee implements Serializable {
    private String id;
    private IdentifierType identifierType;
    private String quality;
    private String applicationId;
    private String lastName;
    private String firstName;
    private String organizationName;
    private String personInOrganisation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IdentifierType getIdentifierType() {
        return identifierType;
    }

    public void setIdentifierType(IdentifierType identifierType) {
        this.identifierType = identifierType;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPersonInOrganisation() {
        return personInOrganisation;
    }

    public void setPersonInOrganisation(String personInOrganisation) {
        this.personInOrganisation = personInOrganisation;
    }

    @Override
    public String toString() {
        return isEmpty(organizationName) ? String.format("%s %s %s",defaultString(quality.toString()),defaultString(firstName),defaultString(lastName)) :
                String.format("%s %s - %s %s %s",defaultString(organizationName),defaultString(personInOrganisation),defaultString(quality.toString()),defaultString(firstName),defaultString(lastName));
    }
}
