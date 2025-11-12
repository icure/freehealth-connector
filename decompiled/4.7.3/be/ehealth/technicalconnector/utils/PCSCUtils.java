package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.beid.BeIDFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;

public final class PCSCUtils {
   private PCSCUtils() {
      throw new UnsupportedOperationException();
   }

   public static void verifyPin(char[] pin) throws TechnicalConnectorException {
      BeIDFactory.verify(pin);
   }
}
