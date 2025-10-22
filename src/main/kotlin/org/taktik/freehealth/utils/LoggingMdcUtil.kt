package org.taktik.freehealth.utils

import org.apache.log4j.MDC
import org.apache.logging.log4j.ThreadContext
import java.util.UUID

/**
 * Utility class for logging
 */
object LoggingMdcUtil {
    /**
     * Set the MDC for logging
     * @param keystoreId The keystore id
     * @param company The company
     * @param debug The debug flag
     */
    fun setMDC(keystoreId: UUID, company: String, debug: Boolean) {
        MDC.put("keystoreId", keystoreId.toString())
        MDC.put("company", company)
        MDC.put("debug", debug.toString())
        ThreadContext.put("debug", debug.toString())
    }

    /**
     * Clear the MDC for logging
     */
    fun clearMDC() {
        MDC.clear()
        ThreadContext.remove("debug")
    }

    /**
     * Get the MDC for logging
     * @param key The key
     * @return The value
     */
    fun getMDC(key: String): String {
        return MDC.get(key) as String
    }

    /**
     * Get the MDC for logging as a boolean
     * @param key The key
     * @return The value
     */
    fun getBooleanMDC(key: String): Boolean {
        return getMDC(key).toBoolean()
    }
}
