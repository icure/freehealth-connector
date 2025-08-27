package be.apb.gfddpp.assuralia.batch;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
   public BvacList createBvacList() {
      return new BvacList();
   }

   public Bvac createBvac() {
      return new Bvac();
   }
}
