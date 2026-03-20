package org.taktik.freehealth.middleware.dto.consultrn

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class PersonMid(
    var lastName: String? = null,
    var firstName: String? = null,
    var nationalityCode: String? = null,
    var dateOfBirth: Int? = null,
    var gender: String? = null,
    var birthPlace: BirthPlace? = null,
    var residentialAddress: Address? = null
               ) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    class BirthPlace(var countryCode: String? = null, var cityCode: String? = null, var cityName: String? = null)
    @JsonIgnoreProperties(ignoreUnknown = true)
    class Address(var countryCode: String? = null, var cityCode: String? = null, var cityName: String? = null, var postalCode: String? = null, var streetName: String? = null)
}
