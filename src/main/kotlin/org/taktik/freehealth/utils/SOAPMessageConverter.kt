package org.taktik.freehealth.utils

import org.w3c.dom.Document
import java.io.StringWriter
import javax.xml.soap.SOAPMessage
import javax.xml.transform.OutputKeys
import javax.xml.transform.Source
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

/**
 * CF: Converts a SOAPMessagea to a xml-string.
 *
 * !! THIS IS MEANT FOR LOGGING !!
 */
object SOAPMessageConverter {
    fun SOAPMessage?.toXmlString() : String {
        if(this == null){
            return ""
        }
        // Create a TransformerFactory instance
        val transformerFactory = TransformerFactory.newInstance()

        // Create a Transformer object
        val transformer = transformerFactory.newTransformer()

        // Set output properties for pretty - printing
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no")
        transformer.setOutputProperty(OutputKeys.METHOD, "xml")
        transformer.setOutputProperty(OutputKeys.INDENT, "no")
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent - amount", "2")

        // Get the Source object from the SOAPMessage
        val sourceContent: Source? = soapPart.content

        // Create a StringWriter to hold the output
        val stringWriter = StringWriter()
        val result = StreamResult(stringWriter)


        // Transform the Source to the StreamResult
        transformer.transform(sourceContent, result)

        // Return the string representation of the SOAPMessage
        return stringWriter.toString()
    }

    fun Document?.toXmlString() : String {
        if(this == null){
            return ""
        }

        val transformerFactory = TransformerFactory.newInstance()
        val transformer: javax.xml.transform.Transformer = transformerFactory.newTransformer()
        val stringWriter = StringWriter()
        transformer.transform(DOMSource(this), StreamResult(stringWriter))
        return stringWriter.toString()
    }
}
