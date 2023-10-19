package xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ModificarTitulo {

	public static void modificarNombreAnimePorCodigo(String codigo, String nuevoNombre) {
        try {
            File archivoXML = new File("nuevo_ejemplo.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXML);
            doc.getDocumentElement().normalize();

            NodeList listaAnimes = doc.getElementsByTagName("anime");

            for (int i = 0; i < listaAnimes.getLength(); i++) {
                Node nodoAnime = listaAnimes.item(i);

                if (nodoAnime.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoAnime = (Element) nodoAnime;
                    String animeCodigo = elementoAnime.getElementsByTagName("codigo").item(0).getTextContent();

                    if (animeCodigo.trim().equals(codigo.trim())) {
                        NodeList listaNombres = elementoAnime.getElementsByTagName("titulo");
                        Element elementoNombre = (Element) listaNombres.item(0);
                        elementoNombre.setTextContent(nuevoNombre);
                        break;
                    }
                }
            }

            // Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(archivoXML);
            transformer.transform(source, result);

            System.out.println("Nombre del anime modificado correctamente en el archivo XML.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}