/*
 *
 * Copyright (C) 2018 iCure SA
 *
 * This file is part of FreeHealthConnector.
 *
 * FreeHealthConnector is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation.
 *
 * FreeHealthConnector is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with FreeHealthConnector.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.taktik.freehealth.middleware.mapper

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.DeserializationConfig
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializationConfig
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier
import java.lang.reflect.Method
import java.lang.reflect.Modifier

/**
 * Maps JAXB generated enums (KMEHR schemes such as IDHCPARTYschemes.ID_HCPARTY) through their XML value ("ID-HCPARTY")
 * instead of their Java constant name, as the Orika based mapper did before. When reading, both the XML value and the
 * constant name are accepted. Other enums serialize as usual and also accept such XML values when read.
 */
class JaxbEnumModule : SimpleModule("JaxbEnumModule") {
    init {
        setSerializerModifier(object : BeanSerializerModifier() {
            override fun modifyEnumSerializer(
                config: SerializationConfig,
                valueType: JavaType,
                beanDesc: BeanDescription,
                serializer: JsonSerializer<*>
            ): JsonSerializer<*> = xmlValueGetter(valueType.rawClass)?.let { JaxbEnumSerializer(it) } ?: serializer
        })
        setDeserializerModifier(object : BeanDeserializerModifier() {
            override fun modifyEnumDeserializer(
                config: DeserializationConfig,
                type: JavaType,
                beanDesc: BeanDescription,
                deserializer: JsonDeserializer<*>
            ): JsonDeserializer<*> =
                if (xmlValueGetter(type.rawClass) != null) JaxbEnumDeserializer(type.rawClass, deserializer)
                else LenientEnumDeserializer(type.rawClass, deserializer)
        })
    }

    private class JaxbEnumSerializer(private val xmlValue: Method) : JsonSerializer<Enum<*>>() {
        override fun serialize(value: Enum<*>, gen: JsonGenerator, serializers: SerializerProvider) {
            gen.writeString(xmlValue.invoke(value) as String)
        }
    }

    private class JaxbEnumDeserializer(private val enumClass: Class<*>, private val fallback: JsonDeserializer<*>) : JsonDeserializer<Any>() {
        private val byXmlValue: Map<String, Any> = xmlValueGetter(enumClass)!!.let { getter ->
            enumClass.enumConstants.associateBy { getter.invoke(it) as String }
        }
        private val byName: Map<String, Any> = enumClass.enumConstants.associateBy { (it as Enum<*>).name }

        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Any? {
            if (p.currentToken != JsonToken.VALUE_STRING) return fallback.deserialize(p, ctxt)
            val text = p.text
            return byXmlValue[text] ?: byName[text] ?: ctxt.handleWeirdStringValue(enumClass, text, "not one of the values accepted for ${enumClass.simpleName}")
        }
    }

    /**
     * Non JAXB enums (e.g. DTO enums) keep their usual deserialization, but also accept a JAXB XML value
     * such as "ID-HCPARTY" for a constant named ID_HCPARTY, since JAXB enums are now written with their XML value.
     */
    private class LenientEnumDeserializer(private val enumClass: Class<*>, private val delegate: JsonDeserializer<*>) : JsonDeserializer<Any>() {
        private val byNormalizedName: Map<String, Any> = enumClass.enumConstants.associateBy { normalize((it as Enum<*>).name) }

        override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Any? = try {
            delegate.deserialize(p, ctxt)
        } catch (e: InvalidFormatException) {
            (if (p.currentToken == JsonToken.VALUE_STRING) byNormalizedName[normalize(p.text)] else null) ?: throw e
        }

        private fun normalize(name: String) = name.replace('-', '_').uppercase()
    }

    companion object {
        private val JAXB_ENUM_ANNOTATIONS = setOf("jakarta.xml.bind.annotation.XmlEnum", "javax.xml.bind.annotation.XmlEnum")

        /** The `value()` method JAXB generates on enums whose XML values differ from their constant names. */
        private fun xmlValueGetter(type: Class<*>): Method? {
            val enumClass = if (type.isEnum) type else type.superclass?.takeIf { it.isEnum } ?: return null
            if (enumClass.annotations.none { it.annotationClass.java.name in JAXB_ENUM_ANNOTATIONS }) return null
            return try {
                enumClass.getMethod("value").takeIf { it.returnType == String::class.java && !Modifier.isStatic(it.modifiers) }
            } catch (e: NoSuchMethodException) {
                null
            }
        }
    }
}
