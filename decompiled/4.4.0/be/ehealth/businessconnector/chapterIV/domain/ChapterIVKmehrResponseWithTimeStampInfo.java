package be.ehealth.businessconnector.chapterIV.domain;

import be.ehealth.businessconnector.chapterIV.builders.impl.ResponseBuilderImpl;
import be.ehealth.technicalconnector.service.timestamp.TimestampUtil;
import be.ehealth.technicalconnector.utils.MarshallerHelper;
import be.fgov.ehealth.medicalagreement.core.v1.Kmehrresponse;
import java.io.Serializable;
import org.apache.commons.lang.ArrayUtils;
import org.bouncycastle.tsp.TimeStampResponse;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ChapterIVKmehrResponseWithTimeStampInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   private static final Logger LOG = LoggerFactory.getLogger(ResponseBuilderImpl.class);
   private byte[] timeStampBytes;
   private byte[] kmehrResponseBytes;

   /** @deprecated */
   @Deprecated
   public ChapterIVKmehrResponseWithTimeStampInfo(Kmehrresponse kmehrresponse, TimeStampResponse timeStampResponse, byte[] timeStampBytes, byte[] kmehrResponseBytes) {
      this(timeStampBytes, kmehrResponseBytes);
   }

   public ChapterIVKmehrResponseWithTimeStampInfo(byte[] timeStampBytes, byte[] kmehrResponseBytes) {
      this.timeStampBytes = ArrayUtils.clone(timeStampBytes);
      this.kmehrResponseBytes = ArrayUtils.clone(kmehrResponseBytes);
   }

   public Kmehrresponse getKmehrresponse() {
      MarshallerHelper<Kmehrresponse, Kmehrresponse> helper = new MarshallerHelper<Kmehrresponse, Kmehrresponse>(Kmehrresponse.class, Kmehrresponse.class);
      return (Kmehrresponse)helper.toObject(this.kmehrResponseBytes);
   }

   public TimeStampToken getTimeStampToken() {
      TimeStampToken timeStampToken = null;

      try {
         timeStampToken = TimestampUtil.getTimestampResponse(Arrays.clone(this.timeStampBytes)).getTimeStampToken();
      } catch (Exception var5) {
         try {
            timeStampToken = TimestampUtil.getTimeStampToken(Arrays.clone(this.timeStampBytes));
         } catch (Exception e1) {
            LOG.error(e1.getClass().getSimpleName() + ":" + e1.getMessage(), e1);
         }
      }

      return timeStampToken;
   }

   public byte[] getTimeStampBytes() {
      return Arrays.clone(this.timeStampBytes);
   }

   public byte[] getKmehrResponseBytes() {
      return Arrays.clone(this.kmehrResponseBytes);
   }
}
