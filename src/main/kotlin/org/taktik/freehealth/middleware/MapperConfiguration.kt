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

package org.taktik.freehealth.middleware

import be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTY
import be.fgov.ehealth.standards.kmehr.id.v1.IDHCPARTYschemes
import be.fgov.ehealth.standards.kmehr.schema.v1.AddressType
import be.fgov.ehealth.standards.kmehr.schema.v1.CountryType
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializerProvider
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.taktik.freehealth.middleware.dto.Address
import org.taktik.freehealth.middleware.dto.common.KmehrCd
import org.taktik.freehealth.middleware.dto.common.KmehrId
import org.taktik.freehealth.middleware.mapper.MapperFacade
import org.w3._2005._05.xmlmime.Base64Binary
import java.time.Instant
import java.util.*

@Configuration
class MapperConfiguration {
    @Bean
    fun customJson(): Jackson2ObjectMapperBuilderCustomizer {
        return (Jackson2ObjectMapperBuilderCustomizer { builder ->
            builder?.serializerByType(LocalDate::class.java, object : JsonSerializer<LocalDate>() {
                override fun serialize(value: LocalDate?, gen: JsonGenerator?, serializers: SerializerProvider?) {
                    gen?.let { if (value != null) it.writeNumber(value.year * 10000L + value.monthOfYear * 100 + value.dayOfMonth) else it.writeNull() }
                }
            })?.serializerByType(LocalDateTime::class.java, object : JsonSerializer<LocalDateTime>() {
                override fun serialize(value: LocalDateTime?, gen: JsonGenerator?, serializers: SerializerProvider?) {
                    gen?.let { if (value != null) it.writeNumber((value.year * 10000L + value.monthOfYear * 100 + value.dayOfMonth) * 1000000L + value.hourOfDay * 10000 + value.minuteOfHour * 100 + value.secondOfMinute) else it.writeNull() }
                }
            })?.serializerByType(java.time.LocalDate::class.java, object : JsonSerializer<java.time.LocalDate>() {
                override fun serialize(
                    value: java.time.LocalDate?,
                    gen: JsonGenerator?,
                    serializers: SerializerProvider?
                ) {
                    gen?.let { if (value != null) it.writeNumber(value.year * 10000L + value.monthValue * 100 + value.dayOfMonth) else it.writeNull() }
                }
            })?.serializerByType(
                java.time.LocalDateTime::class.java,
                object : JsonSerializer<java.time.LocalDateTime>() {
                    override fun serialize(
                        value: java.time.LocalDateTime?,
                        gen: JsonGenerator?,
                        serializers: SerializerProvider?
                    ) {
                        gen?.let { if (value != null) it.writeNumber((value.year * 10000L + value.monthValue * 100 + value.dayOfMonth) * 1000000L + value.hour * 10000 + value.minute * 100 + value.second) else it.writeNull() }
                    }
                })?.serializerByType(DateTime::class.java, object : JsonSerializer<DateTime>() {
                override fun serialize(value: DateTime?, gen: JsonGenerator?, serializers: SerializerProvider?) {
                    gen?.let { if (value != null) it.writeNumber((value.year * 10000L + value.monthOfYear * 100 + value.dayOfMonth) * 1000000L + value.hourOfDay * 10000 + value.minuteOfHour * 100 + value.secondOfMinute) else it.writeNull() }
                }
            })?.deserializerByType(LocalDate::class.java, object : JsonDeserializer<LocalDate>() {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDate {
                    val v = p.longValue
                    return LocalDate((v / 10000).toInt(), ((v / 100) % 100).toInt(), (v % 100).toInt())
                }
            })?.deserializerByType(LocalDateTime::class.java, object : JsonDeserializer<LocalDateTime>() {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext): LocalDateTime {
                    val v = p.longValue
                    val date = v / 1000000L
                    val time = v % 1000000L
                    return LocalDateTime(
                        (date / 10000).toInt(), ((date / 100) % 100).toInt(), (date % 100).toInt(),
                        (time / 10000).toInt(), ((time / 100) % 100).toInt(), (time % 100).toInt()
                    )
                }
            })?.deserializerByType(DateTime::class.java, object : JsonDeserializer<DateTime>() {
                override fun deserialize(p: JsonParser, ctxt: DeserializationContext): DateTime {
                    val v = p.longValue
                    val date = v / 1000000L
                    val time = v % 1000000L
                    return DateTime(
                        (date / 10000).toInt(), ((date / 100) % 100).toInt(), (date % 100).toInt(),
                        (time / 10000).toInt(), ((time / 100) % 100).toInt(), (time % 100).toInt()
                    )
                }
            })
        })
    }

    @Bean
    fun mapper(objectMapper: ObjectMapper): MapperFacade {
        val facade = MapperFacade(objectMapper)

        facade.registerConverter(IDHCPARTY::class.java, KmehrId::class.java) { source ->
            KmehrId().apply {
                s = source.s.value()
                sl = source.sl
                sv = source.sv
                value = source.value
            }
        }

        facade.registerConverter(KmehrId::class.java, IDHCPARTY::class.java) { source ->
            IDHCPARTY().apply {
                s = IDHCPARTYschemes.fromValue(source.s)
                sl = source.sl
                sv = source.sv
                value = source.value
            }
        }

        facade.registerConverter(be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY::class.java, KmehrCd::class.java) { source ->
            KmehrCd().apply {
                s = source.s.value()
                sl = source.sl
                sv = source.sv
                value = source.value
            }
        }

        facade.registerConverter(KmehrCd::class.java, be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY::class.java) { source ->
            be.fgov.ehealth.standards.kmehr.cd.v1.CDHCPARTY().apply {
                s = CDHCPARTYschemes.fromValue(source.s)
                sl = source.sl
                sv = source.sv
                value = source.value
            }
        }

        facade.registerConverter(AddressType::class.java, Address::class.java) { source ->
            Address(
                addressType = source.cds.firstOrNull()?.value?.let {
                    org.taktik.freehealth.middleware.dto.AddressType.valueOf(it.lowercase())
                } ?: org.taktik.freehealth.middleware.dto.AddressType.home,
                street = source.street,
                houseNumber = source.housenumber,
                postboxNumber = source.postboxnumber,
                postalCode = source.zip,
                city = source.city,
                country = source.country?.cd?.value
            )
        }

        facade.registerConverter(CountryType::class.java, String::class.java) { source ->
            source.cd.value
        }

        facade.registerConverter(Base64Binary::class.java, String::class.java) { source ->
            Base64.getEncoder().encodeToString(source.value)
        }

        return facade
    }
}