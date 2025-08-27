package org.taktik.connector.business.recipe.utils;

import java.util.Calendar;
import jakarta.xml.bind.DatatypeConverter;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

@XmlTransient
public class CalendarAdapter extends XmlAdapter<String, Calendar> {
   public Calendar unmarshal(String dateStr) {
      return DatatypeConverter.parseDateTime(dateStr);
   }

   public String marshal(Calendar dateTime) {
      return DatatypeConverter.printDateTime(dateTime);
   }
}
