package controlador;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class CrearXml {

    public static void marshalizarBiblioteca(Biblioteca biblioteca, String rutaArchivo) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Biblioteca.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Marshaliza la biblioteca y guarda el resultado en el archivo
            marshaller.marshal(biblioteca, new File(rutaArchivo));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Biblioteca desmarshalizarBiblioteca(String rutaArchivo) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Biblioteca.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Desmarshaliza el archivo y devuelve la biblioteca
            return (Biblioteca) unmarshaller.unmarshal(new File(rutaArchivo));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}