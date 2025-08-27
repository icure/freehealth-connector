package be.ehealth.technicalconnector.service.timestamp;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.IOException;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.tsp.TSPException;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TimestampUtil {
   private static final Logger LOG = LoggerFactory.getLogger(TimestampUtil.class);

   private TimestampUtil() {
   }

   public static TimeStampResponse getTimestampResponse(byte[] tsTokenResponse) throws TechnicalConnectorException {
      try {
         byte[] clonetsTokenResponse = ArrayUtils.clone(tsTokenResponse);
         clonetsTokenResponse = ConnectorIOUtils.base64Decode(clonetsTokenResponse, true);
         TimeStampResponse tsResp = new TimeStampResponse(clonetsTokenResponse);
         if (tsResp.getTimeStampToken() == null) {
            throw new TSPException("no response for the RFC3161 token");
         } else {
            return tsResp;
         }
      } catch (TSPException e) {
         LOG.error(e.getClass().getSimpleName() + ": " + e.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, e, new Object[]{e.getMessage()});
      } catch (IOException e) {
         LOG.error(e.getClass().getSimpleName() + ": " + e.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, e, new Object[]{e.getMessage()});
      }
   }

   public static TimeStampToken getTimestamp(byte[] tsTokenResponse) throws TechnicalConnectorException {
      try {
         LOG.debug("Trying to generate unwrapped TimeStampToken");
         return getTimeStampToken(tsTokenResponse);
      } catch (TechnicalConnectorException var2) {
         LOG.debug("Trying to generate wrapped TimeStampToken");
         return getTimestampResponse(tsTokenResponse).getTimeStampToken();
      }
   }

   public static TimeStampToken getTimeStampToken(byte[] tsToken) throws TechnicalConnectorException {
      byte[] cloneTsToken = ArrayUtils.clone(tsToken);

      try {
         cloneTsToken = ConnectorIOUtils.base64Decode(cloneTsToken, true);
         return new TimeStampToken(new CMSSignedData(cloneTsToken));
      } catch (TSPException e) {
         LOG.error(e.getClass().getSimpleName() + ": " + e.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, e, new Object[]{e.getMessage()});
      } catch (IOException e) {
         LOG.error(e.getClass().getSimpleName() + ": " + e.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, e, new Object[]{e.getMessage()});
      } catch (CMSException e) {
         LOG.error(e.getClass().getSimpleName() + ": " + e.getMessage());
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, e, new Object[]{e.getMessage()});
      }
   }
}
