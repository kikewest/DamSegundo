package xml;

import java.io.File;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CrearXML {

	public static void crearXML() throws ParserConfigurationException {
		
		 try {
			    // Crea un objeto DocumentBuilderFactory
			    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			    
			    // Crea un objeto DocumentBuilder
			    DocumentBuilder builder = factory.newDocumentBuilder();
			    
			    // Crea un nuevo documento XML
			    Document document = builder.newDocument();
			    
			    // Crea el elemento raíz
			    Element raiz = document.createElement("crunchyroll");
			    
			    // Agrega el elemento raíz al documento
			    document.appendChild(raiz);

			    // Crea elementos y atributos
			    Element categ1 = document.createElement("categoria");
			    raiz.appendChild(categ1); // Elemento categoría
			    
			    Element shonen = document.createElement("shonen");
			    categ1.appendChild(shonen); // División entre géneros diferentes de la industria
			    Element gore = document.createElement("gore");
			    categ1.appendChild(gore);
			    
			    Element anime1 = document.createElement("anime");
			    Element titulo1 = document.createElement("titulo");
			    titulo1.appendChild(document.createTextNode("Shingeki no Kyojin"));
			    Element autor1 = document.createElement("autor");
			    autor1.appendChild(document.createTextNode("Hajime Isayama"));
			    Element codigo1 = document.createElement("codigo");
			    codigo1.appendChild(document.createTextNode("01"));
			    Element espect1 = document.createElement("espectadores");
			    Element meses1 = document.createElement("meses");
			    espect1.appendChild(meses1);
			    Element mes1 = document.createElement("enero");
			    mes1.appendChild(document.createTextNode("300"));
			    Element mes2 = document.createElement("febrero");
			    mes2.appendChild(document.createTextNode("400"));
			    Element mes3 = document.createElement("marzo");
			    mes3.appendChild(document.createTextNode("270"));
			    Element mes4 = document.createElement("abril");
			    mes4.appendChild(document.createTextNode("500"));
			    Element mes5 = document.createElement("mayo");
			    mes5.appendChild(document.createTextNode("840"));
			    Element mes6 = document.createElement("junio");
			    mes6.appendChild(document.createTextNode("700"));
			    Element mes7 = document.createElement("julio");
			    mes7.appendChild(document.createTextNode("600"));
			    Element mes8 = document.createElement("agosto");
			    mes8.appendChild(document.createTextNode("900"));
			    Element mes9 = document.createElement("septiembre");
	    		mes9.appendChild(document.createTextNode("750"));
			    Element mes10 = document.createElement("octubre");
			    mes10.appendChild(document.createTextNode("550"));
			    Element mes11 = document.createElement("noviembre");
	    		mes11.appendChild(document.createTextNode("400"));
			    Element mes12 = document.createElement("diciembre");
			    mes12.appendChild(document.createTextNode("450"));

			    anime1.appendChild(titulo1);
			    anime1.appendChild(autor1);
			    anime1.appendChild(codigo1);
			    anime1.appendChild(espect1);
			    meses1.appendChild(mes1);
			    meses1.appendChild(mes2);
			    meses1.appendChild(mes3);
			    meses1.appendChild(mes4);
			    meses1.appendChild(mes5);
			    meses1.appendChild(mes6);
			    meses1.appendChild(mes7);
			    meses1.appendChild(mes8);
			    meses1.appendChild(mes9);
			    meses1.appendChild(mes10);
			    meses1.appendChild(mes11);
			    meses1.appendChild(mes12);
			    shonen.appendChild(anime1);
			    
			    Element anime2 = document.createElement("anime");
			    Element titulo2 = document.createElement("titulo");
			    titulo2.appendChild(document.createTextNode("One Piece"));
			    Element autor2 = document.createElement("autor");
			    autor2.appendChild(document.createTextNode("Eiichiro Oda"));
			    Element codigo2 = document.createElement("codigo");
			    codigo2.appendChild(document.createTextNode("02"));
			    Element espect2 = document.createElement("espectadores");
			    Element meses2 = document.createElement("meses");
			    espect2.appendChild(meses2);
			    Element mes2_1 = document.createElement("enero");
			    mes2_1.appendChild(document.createTextNode("1000"));
			    Element mes2_2 = document.createElement("febrero");
			    mes2_2.appendChild(document.createTextNode("900"));
			    Element mes2_3 = document.createElement("marzo");
			    mes2_3.appendChild(document.createTextNode("750"));
			    Element mes2_4 = document.createElement("abril");
			    mes2_4.appendChild(document.createTextNode("600"));
			    Element mes2_5 = document.createElement("mayo");
			    mes2_5.appendChild(document.createTextNode("800"));
			    Element mes2_6 = document.createElement("junio");
			    mes2_6.appendChild(document.createTextNode("650"));
			    Element mes2_7 = document.createElement("julio");
			    mes2_7.appendChild(document.createTextNode("550"));
			    Element mes2_8 = document.createElement("agosto");
			    mes2_8.appendChild(document.createTextNode("800"));
			    Element mes2_9 = document.createElement("septiembre");
			    mes2_9.appendChild(document.createTextNode("450"));
			    Element mes2_10 = document.createElement("octubre");
			    mes2_10.appendChild(document.createTextNode("400"));
			    Element mes2_11 = document.createElement("noviembre");
			    mes2_11.appendChild(document.createTextNode("500"));
			    Element mes2_12 = document.createElement("diciembre");
			    mes2_12.appendChild(document.createTextNode("600"));
			    
			    anime2.appendChild(titulo2);
			    anime2.appendChild(autor2);
			    anime2.appendChild(codigo2);
			    anime2.appendChild(espect2);
			    meses2.appendChild(mes2_1);
			    meses2.appendChild(mes2_2);
			    meses2.appendChild(mes2_3);
			    meses2.appendChild(mes2_4);
			    meses2.appendChild(mes2_5);
			    meses2.appendChild(mes2_6);
			    meses2.appendChild(mes2_7);
			    meses2.appendChild(mes2_8);
			    meses2.appendChild(mes2_9);
			    meses2.appendChild(mes2_10);
			    meses2.appendChild(mes2_11);
			    meses2.appendChild(mes2_12);
			    shonen.appendChild(anime2);
			    
			    Element anime5 = document.createElement("anime");
			    Element titulo5 = document.createElement("titulo");
			    titulo5.appendChild(document.createTextNode("Kimetsu no Yaiba"));
			    Element autor5 = document.createElement("autor");
			    autor5.appendChild(document.createTextNode("Koyoharu Gotouge"));
			    Element codigo5 = document.createElement("codigo");
			    codigo5.appendChild(document.createTextNode("05"));
			    Element espect5 = document.createElement("espectadores");
			    Element meses5 = document.createElement("meses");
			    espect5.appendChild(meses5);
			    Element mes5_1 = document.createElement("enero");
			    mes5_1.appendChild(document.createTextNode("1000"));
			    Element mes5_2 = document.createElement("febrero");
			    mes5_2.appendChild(document.createTextNode("900"));
			    Element mes5_3 = document.createElement("marzo");
			    mes5_3.appendChild(document.createTextNode("650"));
			    Element mes5_4 = document.createElement("abril");
			    mes5_4.appendChild(document.createTextNode("760"));
			    Element mes5_5 = document.createElement("mayo");
			    mes5_5.appendChild(document.createTextNode("800"));
			    Element mes5_6 = document.createElement("junio");
			    mes5_6.appendChild(document.createTextNode("600"));
			    Element mes5_7 = document.createElement("julio");
			    mes5_7.appendChild(document.createTextNode("450"));
			    Element mes5_8 = document.createElement("agosto");
			    mes5_8.appendChild(document.createTextNode("700"));
			    Element mes5_9 = document.createElement("septiembre");
	    		mes5_9.appendChild(document.createTextNode("850"));
			    Element mes5_10 = document.createElement("octubre");
			    mes5_10.appendChild(document.createTextNode("900"));
			    Element mes5_11 = document.createElement("noviembre");
	    		mes5_11.appendChild(document.createTextNode("1000"));
			    Element mes5_12 = document.createElement("diciembre");
			    mes5_12.appendChild(document.createTextNode("700"));

			    anime5.appendChild(titulo5);
			    anime5.appendChild(autor5);
			    anime5.appendChild(codigo5);
			    anime5.appendChild(espect5);
			    meses5.appendChild(mes5_1);
			    meses5.appendChild(mes5_2);
			    meses5.appendChild(mes5_3);
			    meses5.appendChild(mes5_4);
			    meses5.appendChild(mes5_5);
			    meses5.appendChild(mes5_6);
			    meses5.appendChild(mes5_7);
			    meses5.appendChild(mes5_8);
			    meses5.appendChild(mes5_9);
			    meses5.appendChild(mes5_10);
			    meses5.appendChild(mes5_11);
			    meses5.appendChild(mes5_12);
			    shonen.appendChild(anime5);
			    
			    Element anime3 = document.createElement("anime");
			    Element titulo3 = document.createElement("titulo");
			    titulo3.appendChild(document.createTextNode("Elfen Lied"));
			    Element autor3 = document.createElement("autor");
			    autor3.appendChild(document.createTextNode("Lynn Okamoto"));
			    Element codigo3 = document.createElement("codigo");
			    codigo3.appendChild(document.createTextNode("03"));
			    Element espect3 = document.createElement("espectadores");
			    Element meses3 = document.createElement("meses");
			    espect3.appendChild(meses3);
			    Element mes3_1 = document.createElement("enero");
			    mes3_1.appendChild(document.createTextNode("500"));
			    Element mes3_2 = document.createElement("febrero");
			    mes3_2.appendChild(document.createTextNode("400"));
			    Element mes3_3 = document.createElement("marzo");
			    mes3_3.appendChild(document.createTextNode("300"));
			    Element mes3_4 = document.createElement("abril");
			    mes3_4.appendChild(document.createTextNode("500"));
			    Element mes3_5 = document.createElement("mayo");
			    mes3_5.appendChild(document.createTextNode("650"));
			    Element mes3_6 = document.createElement("junio");
			    mes3_6.appendChild(document.createTextNode("700"));
			    Element mes3_7 = document.createElement("julio");
			    mes3_7.appendChild(document.createTextNode("560"));
			    Element mes3_8 = document.createElement("agosto");
			    mes3_8.appendChild(document.createTextNode("430"));
			    Element mes3_9 = document.createElement("septiembre");
			    mes3_9.appendChild(document.createTextNode("650"));
			    Element mes3_10 = document.createElement("octubre");
			    mes3_10.appendChild(document.createTextNode("704"));
			    Element mes3_11 = document.createElement("noviembre");
			    mes3_11.appendChild(document.createTextNode("340"));
			    Element mes3_12 = document.createElement("diciembre");
			    mes3_12.appendChild(document.createTextNode("800"));
			    
			    anime3.appendChild(titulo3);
			    anime3.appendChild(autor3);
			    anime3.appendChild(codigo3);
			    anime3.appendChild(espect3);
			    meses3.appendChild(mes3_1);
			    meses3.appendChild(mes3_2);
			    meses3.appendChild(mes3_3);
			    meses3.appendChild(mes3_4);
			    meses3.appendChild(mes3_5);
			    meses3.appendChild(mes3_6);
			    meses3.appendChild(mes3_7);
			    meses3.appendChild(mes3_8);
			    meses3.appendChild(mes3_9);
			    meses3.appendChild(mes3_10);
			    meses3.appendChild(mes3_11);
			    meses3.appendChild(mes3_12);
			    gore.appendChild(anime3);
			    
			    Element anime4 = document.createElement("anime");
			    Element titulo4 = document.createElement("titulo");
			    titulo4.appendChild(document.createTextNode("Chainsaw Man"));
			    Element autor4 = document.createElement("autor");
			    autor4.appendChild(document.createTextNode("Tatsuya Endo"));
			    Element codigo4 = document.createElement("codigo");
			    codigo4.appendChild(document.createTextNode("04"));
			    Element espect4 = document.createElement("espectadores");
			    Element meses4 = document.createElement("meses");
			    espect4.appendChild(meses4);
			    Element mes4_1 = document.createElement("enero");
			    mes4_1.appendChild(document.createTextNode("800"));
			    Element mes4_2 = document.createElement("febrero");
			    mes4_2.appendChild(document.createTextNode("600"));
			    Element mes4_3 = document.createElement("marzo");
			    mes4_3.appendChild(document.createTextNode("500"));
			    Element mes4_4 = document.createElement("abril");
			    mes4_4.appendChild(document.createTextNode("670"));
			    Element mes4_5 = document.createElement("mayo");
			    mes4_5.appendChild(document.createTextNode("500"));
			    Element mes4_6 = document.createElement("junio");
			    mes4_6.appendChild(document.createTextNode("600"));
			    Element mes4_7 = document.createElement("julio");
			    mes4_7.appendChild(document.createTextNode("650"));
			    Element mes4_8 = document.createElement("agosto");
			    mes4_8.appendChild(document.createTextNode("550"));
			    Element mes4_9 = document.createElement("septiembre");
			    mes4_9.appendChild(document.createTextNode("670"));
			    Element mes4_10 = document.createElement("octubre");
			    mes4_10.appendChild(document.createTextNode("900"));
			    Element mes4_11 = document.createElement("noviembre");
			    mes4_11.appendChild(document.createTextNode("1000"));
			    Element mes4_12 = document.createElement("diciembre");
			    mes4_12.appendChild(document.createTextNode("980"));
			    
			    anime4.appendChild(titulo4);
			    anime4.appendChild(autor4);
			    anime4.appendChild(codigo4);
			    anime4.appendChild(espect4);
			    meses4.appendChild(mes4_1);
			    meses4.appendChild(mes4_2);
			    meses4.appendChild(mes4_3);
			    meses4.appendChild(mes4_4);
			    meses4.appendChild(mes4_5);
			    meses4.appendChild(mes4_6);
			    meses4.appendChild(mes4_7);
			    meses4.appendChild(mes4_8);
			    meses4.appendChild(mes4_9);
			    meses4.appendChild(mes4_10);
			    meses4.appendChild(mes4_11);
			    meses4.appendChild(mes4_12);
			    gore.appendChild(anime4);
			    
			    Element anime6 = document.createElement("anime");
			    Element titulo6 = document.createElement("titulo");
			    titulo6.appendChild(document.createTextNode("Gantz"));
			    Element autor6 = document.createElement("autor");
			    autor6.appendChild(document.createTextNode("Hiroya Oku"));
			    Element codigo6 = document.createElement("codigo");
			    codigo6.appendChild(document.createTextNode("06"));
			    Element espect6 = document.createElement("espectadores");
			    Element meses6 = document.createElement("meses");
			    espect6.appendChild(meses6);
			    Element mes6_1 = document.createElement("enero");
			    mes6_1.appendChild(document.createTextNode("500"));
			    Element mes6_2 = document.createElement("febrero");
			    mes6_2.appendChild(document.createTextNode("300"));
			    Element mes6_3 = document.createElement("marzo");
			    mes6_3.appendChild(document.createTextNode("450"));
			    Element mes6_4 = document.createElement("abril");
			    mes6_4.appendChild(document.createTextNode("550"));
			    Element mes6_5 = document.createElement("mayo");
			    mes6_5.appendChild(document.createTextNode("350"));
			    Element mes6_6 = document.createElement("junio");
			    mes6_6.appendChild(document.createTextNode("650"));
			    Element mes6_7 = document.createElement("julio");
			    mes6_7.appendChild(document.createTextNode("450"));
			    Element mes6_8 = document.createElement("agosto");
			    mes6_8.appendChild(document.createTextNode("700"));
			    Element mes6_9 = document.createElement("septiembre");
			    mes6_9.appendChild(document.createTextNode("500"));
			    Element mes6_10 = document.createElement("octubre");
			    mes6_10.appendChild(document.createTextNode("650"));
			    Element mes6_11 = document.createElement("noviembre");
			    mes6_11.appendChild(document.createTextNode("400"));
			    Element mes6_12 = document.createElement("diciembre");
			    mes6_12.appendChild(document.createTextNode("300"));
			    
			    anime6.appendChild(titulo6);
			    anime6.appendChild(autor6);
			    anime6.appendChild(codigo6);
			    anime6.appendChild(espect6);
			    meses6.appendChild(mes6_1);
			    meses6.appendChild(mes6_2);
			    meses6.appendChild(mes6_3);
			    meses6.appendChild(mes6_4);
			    meses6.appendChild(mes6_5);
			    meses6.appendChild(mes6_6);
			    meses6.appendChild(mes6_7);
			    meses6.appendChild(mes6_8);
			    meses6.appendChild(mes6_9);
			    meses6.appendChild(mes6_10);
			    meses6.appendChild(mes6_11);
			    meses6.appendChild(mes6_12);
			    gore.appendChild(anime6);

			    // Crea un objeto Transformer para escribir el archivo XML
			    TransformerFactory transformerFactory = TransformerFactory.newInstance();
			    Transformer transformer = transformerFactory.newTransformer();
			    
			    // Crea un objeto DOMSource para el documento
			    DOMSource source = new DOMSource(document);
			    
			    // Crea un objeto StreamResult para el archivo XML de salida
			    File archivoXML = new File("nuevo_ejemplo.xml");
			    StreamResult result = new StreamResult(archivoXML);
			    
			    // Transforma el documento a un archivo XML
			    transformer.transform(source, result);
			    
			    System.out.println("Archivo XML creado correctamente en " + archivoXML.getAbsolutePath());
			    
			    
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}
	
}
