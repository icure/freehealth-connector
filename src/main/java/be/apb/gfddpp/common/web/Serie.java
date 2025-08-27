package be.apb.gfddpp.common.web;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Serie"
)
public class Serie {
   @XmlElement(
      name = "name"
   )
   private String name;
   @XmlElementWrapper(
      name = "data"
   )
   @XmlElement(
      name = "point"
   )
   private List<Point> points;

   public Serie() {
      this.points = new ArrayList();
   }

   public Serie(String name, List<Point> points) {
      this.name = name;
      this.points = points;
   }

   public Serie(String name) {
      this();
      this.name = name;
   }

   public void addPoint(Point point) {
      this.points.add(point);
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Point> getPoints() {
      return this.points;
   }

   public void setPoints(List<Point> points) {
      this.points = points;
   }
}
