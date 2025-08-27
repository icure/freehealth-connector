package be.fgov.ehealth.technicalconnector.signature.transformers;

import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.apache.xml.security.signature.XMLSignatureByteInput;
import org.taktik.connector.technical.enumeration.Charset;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.transforms.Transform;
import org.apache.xml.security.transforms.TransformSpi;
import org.apache.xml.security.transforms.TransformationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class NippinOptionalDeflateTransformer extends TransformSpi {
   public static final String TRANSFORM_URI = "urn:nippin:xml:sig:transform:optional-deflate";

   protected XMLSignatureInput enginePerformTransform(XMLSignatureInput input, OutputStream os, Element element, String s, boolean b) throws IOException, CanonicalizationException, InvalidCanonicalizerException, TransformationException, ParserConfigurationException, SAXException {
      try {
         if (input.isElement()) {
            return this.processElement(input, os);
         } else if (os == null) {
            byte[] base64Bytes = input.getBytes();
            byte[] decodedBytes = ConnectorIOUtils.decompress(base64Bytes);
            return new XMLSignatureByteInput(decodedBytes);
         } else {
            os.write(ConnectorIOUtils.decompress(input.getBytes()));

            XMLSignatureInput output = new XMLSignatureByteInput((byte[])null);
            output.setOutputStream(os);
            return output;
         }
      } catch (TechnicalConnectorException var11) {
         throw new TransformationException("DeflateException", var11);
      }
   }

   protected String engineGetURI() {
      return "urn:nippin:xml:sig:transform:optional-deflate";
   }

   private XMLSignatureInput processElement(XMLSignatureInput input, OutputStream os) throws TechnicalConnectorException, IOException {
      Node el = input.getSubNode();
      if (input.getSubNode().getNodeType() == 3) {
         el = el.getParentNode();
      }

      StringBuilder sb = new StringBuilder();
      this.traverseElement((Element)el, sb);
      if (os == null) {
         byte[] decodedBytes = ConnectorIOUtils.decompress(ConnectorIOUtils.toBytes(sb.toString(), Charset.UTF_8));
         return new XMLSignatureByteInput(decodedBytes);
      } else {
         os.write(ConnectorIOUtils.decompress(ConnectorIOUtils.toBytes(sb.toString(), Charset.UTF_8)));
         XMLSignatureInput output = new XMLSignatureByteInput((byte[])null);
         output.setOutputStream(os);
         return output;
      }
   }

   private void traverseElement(Element node, StringBuilder sb) {
      for(Node sibling = node.getFirstChild(); sibling != null; sibling = sibling.getNextSibling()) {
         switch(sibling.getNodeType()) {
         case 1:
            this.traverseElement((Element)sibling, sb);
            break;
         case 3:
            sb.append(((Text)sibling).getData());
         }
      }

   }
}
