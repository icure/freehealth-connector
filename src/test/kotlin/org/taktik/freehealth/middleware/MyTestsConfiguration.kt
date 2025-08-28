package org.taktik.freehealth.middleware

import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.client.RestTemplateCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.AnnotationAwareOrderComparator
import org.springframework.util.CollectionUtils
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@Configuration
class MyTestsConfiguration {
    @Bean fun restTemplateBuilder(messageConverters: ObjectProvider<HttpMessageConverters>,
        restTemplateCustomizers: ObjectProvider<List<RestTemplateCustomizer>>): RestTemplateBuilder {
        var builder = RestTemplateBuilder()
        messageConverters.ifUnique?.let {builder = builder.messageConverters(it.converters)}
        builder = restTemplateCustomizers
            .ifAvailable.let {
            if (it.isNullOrEmpty()) builder else builder.customizers(it)
        }.connectTimeout(120.seconds.toJavaDuration()).readTimeout(120.seconds.toJavaDuration())
        return builder
    }
}

