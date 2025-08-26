package be.fgov.ehealth.technicalconnector.services.daas;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.util.List;

public class DaasServiceException extends TechnicalConnectorException {
   private List<String> statusLevels;

   public DaasServiceException(List<String> statusLevels, Object... params) {
      super(TechnicalConnectorExceptionValues.ERROR_DAAS, params);
      this.statusLevels = statusLevels;
   }

   public String getStatusLevel(int level) {
      --level;
      return (String)this.statusLevels.get(level);
   }

   public List<String> getStatusLevels() {
      return this.statusLevels;
   }
}
