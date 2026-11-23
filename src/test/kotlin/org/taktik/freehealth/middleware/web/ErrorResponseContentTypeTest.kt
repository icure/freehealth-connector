package org.taktik.freehealth.middleware.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.smile.MappingJackson2SmileHttpMessageConverter
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.taktik.freehealth.middleware.WebMvcConfigurer

/**
 * Offline check that responses are JSON even though the Smile and CBOR Jackson converters are
 * auto-registered (jackson-dataformat-smile/cbor are on the classpath via Elasticsearch).
 * On an error the endpoint's `produces` constraint no longer applies, so with `Accept: any type`
 * the first converter able to write the body wins.
 */
class ErrorResponseContentTypeTest {
    @RestController
    class FailingController {
        @PostMapping("/fail", produces = [MediaType.APPLICATION_JSON_VALUE])
        fun fail(): Map<String, String> = throw IllegalStateException("[ERR200018] A technical error occured.")

        @DeleteMapping("/noProduces")
        fun noProduces(): Map<String, String> = mapOf("status" to "ok")
    }

    // Same order as Spring's default converters when both binary dataformats are on the classpath
    private val converters: Array<HttpMessageConverter<*>> = mutableListOf<HttpMessageConverter<*>>(
        StringHttpMessageConverter(),
        MappingJackson2HttpMessageConverter(),
        MappingJackson2SmileHttpMessageConverter(),
        MappingJackson2CborHttpMessageConverter()
    ).also { WebMvcConfigurer(WebProperties(), ObjectMapper()).extendMessageConverters(it) }.toTypedArray()

    private val mockMvc = MockMvcBuilders
        .standaloneSetup(FailingController())
        .setControllerAdvice(ExceptionHandlers())
        .setMessageConverters(*converters)
        .build()

    @Test
    fun errorIsReturnedAsJsonWhenClientAcceptsAnything() {
        mockMvc.perform(post("/fail").servletPath("/fail").accept(MediaType.ALL))
            .andExpect(status().isInternalServerError)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status").value(500))
            .andExpect(jsonPath("$.message").value("[ERR200018] A technical error occured."))
            .andExpect(jsonPath("$.path").value("/fail"))
    }

    @Test
    fun endpointWithoutProducesReturnsJsonWhenClientAcceptsAnything() {
        mockMvc.perform(delete("/noProduces").accept(MediaType.ALL))
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status").value("ok"))
    }
}
