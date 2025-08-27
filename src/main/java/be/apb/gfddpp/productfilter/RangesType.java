package be.apb.gfddpp.productfilter;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "rangesType",
   propOrder = {"range"}
)
@XmlSeeAlso({MedicinesRanges.class, PreparationsRanges.class})
public class RangesType {
   @XmlElement(
      required = true
   )
   protected List<Range> range;

   public List<Range> getRange() {
      if (this.range == null) {
         this.range = new ArrayList();
      }

      return this.range;
   }
}
