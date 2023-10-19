package xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class NumEspectadores {

	public static String NumEspec() {
        StringBuilder espectadoresContent = new StringBuilder();

        try {
            // Crear un objeto DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Crear un objeto DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document document = builder.parse("nuevo_ejemplo.xml");

            // Obtener la lista de elementos 'anime'
            NodeList listaAnime = document.getElementsByTagName("anime");

            // Iterar a través de la lista de animes
            for (int i = 0; i < listaAnime.getLength(); i++) {
                Element anime = (Element) listaAnime.item(i);
                String codigo = anime.getElementsByTagName("codigo").item(0).getTextContent();
                String titulo = anime.getElementsByTagName("titulo").item(0).getTextContent();
                String autor = anime.getElementsByTagName("autor").item(0).getTextContent();

                // Verificar si existe el nodo "espectadores"
                NodeList listaEspectadores = anime.getElementsByTagName("espectadores");
                if (listaEspectadores.getLength() > 0) {
                    Element espectadores = (Element) listaEspectadores.item(0);
                    
                    // Verificar si existe el nodo "meses" dentro de "espectadores"
                    NodeList listaMeses = espectadores.getElementsByTagName("meses");
                    if (listaMeses.getLength() > 0) {
                        Element meses = (Element) listaMeses.item(0);
                        String enero = meses.getElementsByTagName("enero").item(0).getTextContent();
                        String febrero = meses.getElementsByTagName("febrero").item(0).getTextContent();
                        String marzo = meses.getElementsByTagName("marzo").item(0).getTextContent();
                        String abril = meses.getElementsByTagName("abril").item(0).getTextContent();
                        String mayo = meses.getElementsByTagName("mayo").item(0).getTextContent();
                        String junio = meses.getElementsByTagName("junio").item(0).getTextContent();
                        String julio = meses.getElementsByTagName("julio").item(0).getTextContent();
                        String agosto = meses.getElementsByTagName("agosto").item(0).getTextContent();
                        String septiembre = meses.getElementsByTagName("septiembre").item(0).getTextContent();
                        String octubre = meses.getElementsByTagName("octubre").item(0).getTextContent();
                        String noviembre = meses.getElementsByTagName("noviembre").item(0).getTextContent();
                        String diciembre = meses.getElementsByTagName("diciembre").item(0).getTextContent();

                        int numEnero = Integer.parseInt(enero);
                        int numFebrero = Integer.parseInt(febrero);
                        int numMarzo = Integer.parseInt(marzo);
                        int numAbril = Integer.parseInt(abril);
                        int numMayo = Integer.parseInt(mayo);
                        int numJunio = Integer.parseInt(junio);
                        int numJulio = Integer.parseInt(julio);
                        int numAgosto = Integer.parseInt(agosto);
                        int numSeptiembre = Integer.parseInt(septiembre);
                        int numOctubre = Integer.parseInt(octubre);
                        int numNoviembre = Integer.parseInt(noviembre);
                        int numDiciembre = Integer.parseInt(diciembre);
                        
                        int total = numEnero + numFebrero + numMarzo + numAbril + numMayo + numJunio + numJulio + numAgosto + numSeptiembre + numOctubre + numNoviembre + numDiciembre;
                        
                        espectadoresContent.append("Código: ").append(codigo).append("\n");
                        espectadoresContent.append("Título: ").append(titulo).append("\n");
                        espectadoresContent.append("Autor: ").append(autor).append("\n");
                        espectadoresContent.append("Espectadores enero: ").append(enero).append("\n");
                        espectadoresContent.append("Espectadores febrero: ").append(febrero).append("\n");
                        espectadoresContent.append("Espectadores marzo: ").append(marzo).append("\n");
                        espectadoresContent.append("Espectadores abril: ").append(abril).append("\n");
                        espectadoresContent.append("Espectadores mayo: ").append(mayo).append("\n");
                        espectadoresContent.append("Espectadores junio: ").append(junio).append("\n");
                        espectadoresContent.append("Espectadores julio: ").append(julio).append("\n");
                        espectadoresContent.append("Espectadores agosto: ").append(agosto).append("\n");
                        espectadoresContent.append("Espectadores septiembre: ").append(septiembre).append("\n");
                        espectadoresContent.append("Espectadores octubre: ").append(octubre).append("\n");
                        espectadoresContent.append("Espectadores noviembre: ").append(noviembre).append("\n");
                        espectadoresContent.append("Espectadores diciembre: ").append(diciembre).append("\n");
                        espectadoresContent.append("Espectadores totales al año: ").append(total).append("\n");
                        espectadoresContent.append("----------------------").append("\n");
                    } else {
                        espectadoresContent.append("No hay datos de meses para este anime").append("\n");
                    }
                } else {
                    espectadoresContent.append("No hay datos de espectadores para este anime").append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return espectadoresContent.toString();
    }
}