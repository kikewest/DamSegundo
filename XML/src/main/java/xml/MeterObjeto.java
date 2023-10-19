package xml;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class MeterObjeto {
    
    private static final String XML_FILE_PATH = "nuevo_ejemplo.xml";

    public static Crunchyroll leerArchivo() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Crunchyroll.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File xmlFile = new File(XML_FILE_PATH);
            return (Crunchyroll) unmarshaller.unmarshal(xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void escribirArchivo(Crunchyroll objeto) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Crunchyroll.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            File xmlFile = new File(XML_FILE_PATH);
            marshaller.marshal(objeto, xmlFile);

            System.out.println("Objeto Java añadido y almacenado en el archivo XML (archivo existente actualizado).");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void agregarAnime() throws JAXBException {
        String categoriaElegida = JOptionPane.showInputDialog("¿Qué categoría es el anime (shonen/gore)?").toLowerCase();
		if ("shonen".equals(categoriaElegida)) {
		} else if ("gore".equals(categoriaElegida)) {
		} else {
		    JOptionPane.showMessageDialog(null, "Categoría no válida.", "Error", JOptionPane.ERROR_MESSAGE);
		    return;
		}

		Anime nuevoAnime = new Anime();
		nuevoAnime.setTitulo(JOptionPane.showInputDialog("Introduce el título del anime:"));
		nuevoAnime.setAutor(JOptionPane.showInputDialog("Introduce el autor del anime:"));
		nuevoAnime.setCodigo(JOptionPane.showInputDialog("Introduce el código del anime:"));

		Meses meses = new Meses();
		Espectadores espectadores = new Espectadores();

		for (int i = 1; i <= 12; i++) {
		    int espectadoresMes = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad de espectadores para el mes " + i + ":"));
		    switch (i) {
		        case 1:
		            meses.setEnero(espectadoresMes);
		            break;
		        case 2:
		            meses.setFebrero(espectadoresMes);
		            break;
		        case 3:
		            meses.setMarzo(espectadoresMes);
		            break;
		        case 4:
		            meses.setAbril(espectadoresMes);
		            break;
		        case 5:
		            meses.setMayo(espectadoresMes);
		            break;
		        case 6:
		            meses.setJunio(espectadoresMes);
		            break;
		        case 7:
		            meses.setJulio(espectadoresMes);
		            break;
		        case 8:
		            meses.setAgosto(espectadoresMes);
		            break;
		        case 9:
		            meses.setSeptiembre(espectadoresMes);
		            break;
		        case 10:
		            meses.setOctubre(espectadoresMes);
		            break;
		        case 11:
		            meses.setNoviembre(espectadoresMes);
		            break;
		        case 12:
		            meses.setDiciembre(espectadoresMes);
		            break;
		        default:
		            break;
		    }
		}

		// Luego, estableces los espectadores en la clase Espectadores.
		espectadores.setMeses(meses);

		JOptionPane.showMessageDialog(null, "Anime añadido y almacenado en el archivo XML.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}