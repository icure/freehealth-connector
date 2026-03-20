package org.taktik.freehealth.middleware.domain.memberdata

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
@JsonIgnoreProperties(ignoreUnknown = true)
class Facet(var id:String? = null, var dimensions:List<Dimension>? = null)
