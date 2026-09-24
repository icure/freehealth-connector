package org.taktik.freehealth.middleware.web

import com.hazelcast.map.IMap
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.mock.web.MockFilterChain
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.taktik.freehealth.middleware.RateLimitProperties
import org.taktik.freehealth.middleware.domain.RateLimitEntry
import org.taktik.freehealth.middleware.domain.RateLimitResult
import org.taktik.freehealth.middleware.hazelcast.RateLimitEntryProcessor

class RateLimitFilterKeyTest {
    private val keys = mutableListOf<String>()

    @Suppress("UNCHECKED_CAST")
    private fun filter(fallbackKeyHeader: String?): RateLimitFilter {
        val map = mock(IMap::class.java) as IMap<String, RateLimitEntry>
        `when`(map.executeOnKey(anyString(), any(RateLimitEntryProcessor::class.java))).thenAnswer {
            keys.add(it.getArgument(0))
            RateLimitResult(allowed = true, remaining = 1)
        }
        val properties = RateLimitProperties().apply {
            enabled = true
            keyHeader = "X-FHC-keystoreId"
            this.fallbackKeyHeader = fallbackKeyHeader
        }
        return RateLimitFilter(properties, map, SimpleMeterRegistry())
    }

    private fun request(vararg headers: Pair<String, String>) = MockHttpServletRequest("GET", "/genins/123").apply {
        remoteAddr = "10.0.0.1"
        headers.forEach { (k, v) -> addHeader(k, v) }
    }

    @Test
    fun primaryHeaderIsUsedWhenPresent() {
        filter("X-FHC-tokenId").doFilter(request("X-FHC-keystoreId" to "ks", "X-FHC-tokenId" to "tk"), MockHttpServletResponse(), MockFilterChain())
        assertThat(keys).containsExactly("default:X-FHC-keystoreId:ks")
    }

    @Test
    fun fallbackHeaderIsUsedWhenPrimaryIsMissing() {
        filter("X-FHC-tokenId").doFilter(request("X-FHC-tokenId" to "tk"), MockHttpServletResponse(), MockFilterChain())
        assertThat(keys).containsExactly("default:X-FHC-tokenId:tk")
    }

    @Test
    fun ipIsUsedWhenNoHeaderIsPresent() {
        filter("X-FHC-tokenId").doFilter(request(), MockHttpServletResponse(), MockFilterChain())
        assertThat(keys).containsExactly("default:ip:10.0.0.1")
    }

    @Test
    fun ipIsUsedWhenNoFallbackIsConfigured() {
        filter(null).doFilter(request("X-FHC-tokenId" to "tk"), MockHttpServletResponse(), MockFilterChain())
        assertThat(keys).containsExactly("default:ip:10.0.0.1")
    }
}
