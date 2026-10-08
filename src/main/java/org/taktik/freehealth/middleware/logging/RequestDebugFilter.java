package org.taktik.freehealth.middleware.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import java.util.Map;
import org.taktik.connector.technical.utils.RequestDebug;

/**
 * Keeps the per-keystore debug file limited to the requests that carried X-FHC-debug: true.
 * Reads the MDC snapshot of the event rather than the current thread, so it stays correct behind an async appender.
 */
public class RequestDebugFilter extends Filter<ILoggingEvent> {

   @Override
   public FilterReply decide(ILoggingEvent event) {
      Map<String, String> mdc = event.getMDCPropertyMap();
      if (mdc != null && "true".equalsIgnoreCase(mdc.get(RequestDebug.MDC_KEY))) {
         return FilterReply.ACCEPT;
      }
      return FilterReply.DENY;
   }
}
