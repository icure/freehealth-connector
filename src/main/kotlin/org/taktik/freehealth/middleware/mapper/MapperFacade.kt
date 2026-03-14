/*
 *
 * Copyright (C) 2018 Taktik SA
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

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature

/**
 * Simple mapper facade that replaces Orika (which is incompatible with Java 21).
 * Uses Jackson ObjectMapper.convertValue() for bean property copying and supports
 * explicit custom converters for type pairs that need special handling.
 */
class MapperFacade(objectMapper: ObjectMapper? = null) {
    private val objectMapper = (objectMapper?.copy() ?: ObjectMapper()).apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    }

    private data class TypePair(val sourceType: Class<*>, val destType: Class<*>)

    private val converters = mutableMapOf<TypePair, (Any) -> Any?>()

    fun <S : Any, D : Any> registerConverter(sourceType: Class<S>, destType: Class<D>, converter: (S) -> D?) {
        @Suppress("UNCHECKED_CAST")
        converters[TypePair(sourceType, destType)] = converter as (Any) -> Any?
    }

    fun <D : Any> map(source: Any?, destType: Class<D>): D {
        requireNotNull(source) { "Cannot map null source to ${destType.name}" }
        return doMap(source, destType)
    }

    fun <D : Any> mapOrNull(source: Any?, destType: Class<D>): D? {
        if (source == null) return null
        return doMap(source, destType)
    }

    private fun <D : Any> doMap(source: Any, destType: Class<D>): D {
        val converter = converters[TypePair(source::class.java, destType)]
        if (converter != null) {
            @Suppress("UNCHECKED_CAST")
            return converter(source) as D
        }

        // Walk up the class hierarchy and interfaces to find a matching converter
        val match = findConverter(source::class.java, destType)
        if (match != null) {
            @Suppress("UNCHECKED_CAST")
            return match(source) as D
        }

        return objectMapper.convertValue(source, destType)
    }

    private fun findConverter(sourceType: Class<*>, destType: Class<*>): ((Any) -> Any?)? {
        // Check direct match first
        converters[TypePair(sourceType, destType)]?.let { return it }

        // Check superclasses
        var current: Class<*>? = sourceType.superclass
        while (current != null) {
            converters[TypePair(current, destType)]?.let { return it }
            current = current.superclass
        }

        // Check interfaces
        for (iface in sourceType.interfaces) {
            converters[TypePair(iface, destType)]?.let { return it }
        }

        return null
    }
}