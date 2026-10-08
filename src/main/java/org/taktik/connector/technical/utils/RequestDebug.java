package org.taktik.connector.technical.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Per-request debug switch, fed by the X-FHC-debug header in MdcInterceptor.
 * Lets a single caller get the full eHealth connector tracing without raising the log level for everyone.
 * Output goes to a dedicated logger so logback can route it independently of the package levels.
 */
public final class RequestDebug {

   public static final String MDC_KEY = "debug";
   public static final String TRACE_LOGGER = "fhc.trace";

   private static final Logger TRACE = LoggerFactory.getLogger(TRACE_LOGGER);

   private RequestDebug() {
   }

   public static boolean isEnabled() {
      return "true".equalsIgnoreCase(MDC.get(MDC_KEY));
   }

   public static void trace(String format, Object... args) {
      if (isEnabled()) {
         TRACE.debug(format, args);
      }
   }
}
