package be.recipe.services;

import java.io.Serializable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "uploadFileResponse"
)
@XmlRootElement(
   name = "uploadFileResponse"
)
public class UploadFileResponse implements Serializable {
   private static final long serialVersionUID = 1L;
}
