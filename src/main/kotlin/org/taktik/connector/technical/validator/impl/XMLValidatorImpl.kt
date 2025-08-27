package org.taktik.connector.technical.validator.impl

import org.apache.commons.lang3.mutable.Mutable
import org.taktik.connector.technical.exception.TechnicalConnectorException
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues
import org.taktik.connector.technical.validator.ValidatorHelper
import org.taktik.connector.technical.validator.XMLValidator
import java.util.HashMap

open class XMLValidatorImpl : XMLValidator {

    @Throws(TechnicalConnectorException::class)
    override fun validate(xmlObject: Any?) {
        if (xmlObject != null) {
            ValidatorHelper.validate(xmlObject, xmlObject.javaClass, this.getXsdFileLocationForXmlObject(xmlObject))
        } else {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, "xml object had null value")
        }
    }

    @Throws(TechnicalConnectorException::class)
    private fun getXsdFileLocationForXmlObject(xmlObject: Any?): String {
        return if (xmlObject != null && XSD_FILE_LOCATION_FOR_CLASS_MAP.containsKey(xmlObject.javaClass)) {
            XSD_FILE_LOCATION_FOR_CLASS_MAP[xmlObject.javaClass] as String
        } else {
            throw TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_XML_INVALID, "no xsd source defined for xmlObject " + xmlObject!!)
        }
    }


    companion object {
        fun putXsdFileLocationForXmlObject(clazz: Class<*>, path: String) {
            XSD_FILE_LOCATION_FOR_CLASS_MAP[clazz] = path
        }

        val XSD_FILE_LOCATION_FOR_CLASS_MAP: MutableMap<Class<*>, String> = HashMap()
    }
}
