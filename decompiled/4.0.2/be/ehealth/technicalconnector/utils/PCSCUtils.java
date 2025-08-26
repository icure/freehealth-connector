package be.ehealth.technicalconnector.utils;

import be.ehealth.technicalconnector.exception.BeIDPinCodeException;
import be.ehealth.technicalconnector.exception.ResponseAPDUException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.util.Arrays;
import java.util.List;
import javax.smartcardio.ATR;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardNotPresentException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PCSCUtils {
   private static final Logger LOG = LoggerFactory.getLogger(PCSCUtils.class);
   private static final byte[] ATR_PATTERN = new byte[]{59, -104, 0, 64, 0, 0, 0, 0, 1, 1, -83, 19, 16};
   private static final byte[] ATR_MASK = new byte[]{-1, -1, 0, -1, 0, 0, 0, 0, -1, -1, -1, -1, -16};

   private PCSCUtils() {
      throw new UnsupportedOperationException();
   }

   public static void verifyPin(char[] pin) throws TechnicalConnectorException {
      try {
         ResponseAPDU responseApdu = verifyPIN(pin);
         if (36864 != responseApdu.getSW()) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("VERIFY_PIN error. SW: {}", Integer.toHexString(responseApdu.getSW()));
            }

            if (27011 == responseApdu.getSW()) {
               throw new BeIDPinCodeException(new ResponseAPDUException("eID card blocked!", responseApdu));
            } else if (99 != responseApdu.getSW1()) {
               LOG.debug("PIN verification error.");
               throw new BeIDPinCodeException(new ResponseAPDUException("PIN Verification Error", responseApdu));
            } else {
               throw new BeIDPinCodeException(new ResponseAPDUException("PIN Verification Error", responseApdu));
            }
         }
      } catch (CardNotPresentException e) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_EID_NULL, e, new Object[0]);
      } catch (CardException e) {
         throw new BeIDPinCodeException(e);
      }
   }

   private static ResponseAPDU verifyPIN(char[] pin) throws CardException {
      byte[] verifyData = new byte[]{(byte)(32 | pin.length), -1, -1, -1, -1, -1, -1, -1};

      for(int idx = 0; idx < pin.length; idx += 2) {
         char digit1 = pin[idx];
         char digit2;
         if (idx + 1 < pin.length) {
            digit2 = pin[idx + 1];
         } else {
            digit2 = '?';
         }

         byte value = (byte)((digit1 - 48 << 4) + (digit2 - 48));
         verifyData[idx / 2 + 1] = value;
      }

      Arrays.fill(pin, '\u0000');
      LOG.debug("verifying PIN...");

      ResponseAPDU var9;
      try {
         var9 = transmit(new CommandAPDU(0, 32, 0, 1, verifyData));
      } finally {
         Arrays.fill(verifyData, (byte)0);
      }

      return var9;
   }

   private static ResponseAPDU transmit(CommandAPDU commandApdu) throws CardException {
      TerminalFactory factory = TerminalFactory.getDefault();
      List<CardTerminal> terminals = factory.terminals().list();
      LOG.debug("Terminals: {}", terminals);
      Card card = null;

      for(CardTerminal terminal : terminals) {
         if (terminal.isCardPresent()) {
            card = terminal.connect("*");
            if (card != null && matchesEidAtr(card.getATR())) {
               break;
            }
         }
      }

      if (card == null) {
         throw new CardNotPresentException("EID is not present");
      } else {
         card.beginExclusive();
         LOG.debug("card: {}", card);
         CardChannel cardChannel = card.getBasicChannel();
         ResponseAPDU responseApdu = cardChannel.transmit(commandApdu);
         if (108 == responseApdu.getSW1()) {
            LOG.debug("sleeping...");

            try {
               Thread.sleep(10L);
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               throw new be.ehealth.technicalconnector.exception.InterruptedException("Cannot sleep", e);
            }

            responseApdu = cardChannel.transmit(commandApdu);
         }

         card.endExclusive();
         card.disconnect(false);
         return responseApdu;
      }
   }

   private static boolean matchesEidAtr(ATR atr) {
      byte[] atrBytes = atr.getBytes();
      if (atrBytes.length != ATR_PATTERN.length) {
         return false;
      } else {
         for(int idx = 0; idx < atrBytes.length; ++idx) {
            atrBytes[idx] &= ATR_MASK[idx];
         }

         return Arrays.equals(atrBytes, ATR_PATTERN);
      }
   }
}
