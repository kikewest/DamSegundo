package controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Metodos {

	public static void verificarYCrearXML() {
		String nombreArchivo = "biblioteca.xml"; 
		File archivo = new File(nombreArchivo);

		if (archivo.exists()) {
			System.out.println("El archivo ya existe. No se realizará ninguna acción.");
		} else {
			Biblioteca biblioteca = crearBiblioteca();
			CrearXml.marshalizarBiblioteca(biblioteca, nombreArchivo);
			System.out.println("Se ha creado el archivo " + nombreArchivo);
		}
	}

	
	public static Biblioteca crearBiblioteca() {
		List<Categoria> categorias = new ArrayList<>();

		// FiccionLiteraria
		Categoria ficcionLiteraria = new Categoria();
		ficcionLiteraria.setNombre(CategoriasEnum.FiccionLiteraria);

		List<Libro> librosFiccionLiteraria = new ArrayList<>();
		librosFiccionLiteraria.add(crearLibro("El Hijo Olvidado", "MIKEL SANTIAGO", 9788466677318L, "EDICIONES B", "25-01-2024", "576", "Hay personas a las que dejamos atrás, hay deudas que nunca terminamos de pagar.", 21.75));
		librosFiccionLiteraria.add(crearLibro("Tres Enigmas", "EDUARDO MENDOZA", 9788432242823L, "SEIX BARRAL", "24-01-2024", "408", "No vuelve con un detective chiflado, sino con nueve. No vuelve con un caso, sino con tres.Vuelve el mejor Eduardo Mendoza.", 20.80));
		librosFiccionLiteraria.add(crearLibro("El Barracon", "FERMINA CAÑAVERAS", 9788467071764L, "ESPASA", "10-01-2024", "504", "La novela que nos cuenta la dura realidad de las mujeres españolas obligadas a prostituirse en los campos de concentración.", 19.85));
		librosFiccionLiteraria.add(crearLibro("Scarred", "EMILY MCINTIRE", 9788419650351L, "MONTENA", "01-02-2024", "448", "Después de Hooked, llega Scarred: la esperadísima secuela de la saga  Nunca Jamás que ha conquistado a TikTok, donde los villanos de cuento hallan su final feliz.", 19.90));
		librosFiccionLiteraria.add(crearLibro("Paris Despierta", "EDUARDO MENDOZA", 9788432242823L, "SEIX BARRAL", "24-01-2024", "408", "No vuelve con un detective chiflado, sino con nueve. No vuelve con un caso, sino con tres.Vuelve el mejor Eduardo Mendoza.", 20.80));

		ficcionLiteraria.setLibros(librosFiccionLiteraria);
		categorias.add(ficcionLiteraria);


		Categoria noFiccion = new Categoria();
		noFiccion.setNombre(CategoriasEnum.NoFiccion);

		List<Libro> librosNoFiccion = new ArrayList<>();
		librosNoFiccion.add(crearLibro("Sapiens: De animales a dioses", "Yuval Noah Harari", 9788499924217L, "Debate", "15-04-2014", "512", "Una breve historia de la humanidad que explora el desarrollo de la sociedad.", 25.00));
		librosNoFiccion.add(crearLibro("Elon Musk: Tesla, SpaceX y la búsqueda de un futuro fantástico", "Ashlee Vance", 9780062301253L, "Ecco", "19-05-2015", "400", "Una biografía autorizada del visionario empresario Elon Musk.", 30.00));
		librosNoFiccion.add(crearLibro("Breves respuestas a las grandes preguntas", "Stephen Hawking", 9788498389621L, "Crítica", "16-10-2018", "256", "El renombrado físico Stephen Hawking aborda preguntas fundamentales sobre el universo y la existencia humana.", 22.50));
		librosNoFiccion.add(crearLibro("Educated", "Tara Westover", 9780399590504L, "Random House", "20-02-2018", "352", "La autora relata su viaje desde una educación hogareña en una familia disfuncional hasta obtener un doctorado en Cambridge.", 18.99));
		librosNoFiccion.add(crearLibro("Sapiens: Breve historia de la humanidad", "Yuval Noah Harari", 9788499923999L, "Debate", "11-02-2011", "464", "Una exploración fascinante de la evolución y el impacto de Homo sapiens en el mundo.", 28.50));

		noFiccion.setLibros(librosNoFiccion);
		categorias.add(noFiccion);

		Categoria MisterioThriller = new Categoria();
		MisterioThriller.setNombre(CategoriasEnum.MisterioThriller);

		List<Libro> librosMisterioThriller = new ArrayList<>();
		librosMisterioThriller.add(crearLibro("El código Da Vinci", "Dan Brown", 9780307474278L, "Vintage", "18-03-2003", "592", "Un thriller que mezcla misterio, simbología y conspiraciones en torno a un oscuro secreto religioso.", 14.99));
		librosMisterioThriller.add(crearLibro("La chica del tren", "Paula Hawkins", 9780735212169L, "Riverhead Books", "13-01-2015", "336", "Un emocionante thriller psicológico que sigue la vida de una mujer obsesionada con una pareja aparentemente perfecta.", 22.95));
		librosMisterioThriller.add(crearLibro("El silencio de los corderos", "Thomas Harris", 9788497592252L, "DeBolsillo", "15-05-1988", "448", "Un clásico del thriller que presenta al famoso Hannibal Lecter, un brillante pero perturbador psiquiatra.", 18.00));
		librosMisterioThriller.add(crearLibro("Perdida", "Gillian Flynn", 9788490328278L, "Penguin Books", "05-05-2012", "496", "Un thriller psicológico que sigue la desaparición de una mujer y los oscuros secretos que se revelan.", 20.50));
		librosMisterioThriller.add(crearLibro("El juego de Gerald", "Stephen King", 9781501144202L, "Scribner", "01-05-1992", "512", "Un thriller psicológico que sigue los eventos aterradores después de un juego sexual que sale mal.", 25.99));

		MisterioThriller.setLibros(librosMisterioThriller);
		categorias.add(MisterioThriller);

		Categoria CienciaFiccion = new Categoria();
		CienciaFiccion.setNombre(CategoriasEnum.CienciaFiccion);

		List<Libro> librosCienciaFiccion = new ArrayList<>();
		librosCienciaFiccion.add(crearLibro("La espiral y la sombra", "Rafael Jara", 9788415148043L, "Vintage", "18-03-2003", "592", "Un thriller que mezcla misterio, simbología y conspiraciones en torno a un oscuro secreto religioso.", 14.99));
		librosCienciaFiccion.add(crearLibro("Abraxas en una aventura en la II Guerra Mundial", "Paula Hawkins", 9780735212169L, "Riverhead Books", "13-01-2015", "336", "Un emocionante thriller psicológico que sigue la vida de una mujer obsesionada con una pareja aparentemente perfecta.", 22.95));
		librosCienciaFiccion.add(crearLibro("VI.VI.D", "Thomas Harris", 9788497592252L, "DeBolsillo", "15-05-1988", "448", "Un clásico del thriller que presenta al famoso Hannibal Lecter, un brillante pero perturbador psiquiatra.", 18.00));
		librosCienciaFiccion.add(crearLibro("En el umbral del futuro", "Gillian Flynn", 9788490328278L, "Penguin Books", "05-05-2012", "496", "Un thriller psicológico que sigue la desaparición de una mujer y los oscuros secretos que se revelan.", 20.50));
		librosCienciaFiccion.add(crearLibro("Sueños al otro lado de la pared", "Stephen King", 9781501144202L, "Scribner", "01-05-1992", "512", "Un thriller psicológico que sigue los eventos aterradores después de un juego sexual que sale mal.", 25.99));

		CienciaFiccion.setLibros(librosCienciaFiccion);
		categorias.add(CienciaFiccion);

		Categoria Fantasia = new Categoria();
		Fantasia.setNombre(CategoriasEnum.Fantasia);

		List<Libro> librosFantasia= new ArrayList<>();
		librosFantasia.add(crearLibro("El Señor de los Anillos: La Comunidad del Anillo", "J.R.R. Tolkien", 9788445000666L, "Minotauro", "29-07-1954", "464", "La primera entrega de la épica trilogía que sigue la odisea de Frodo para destruir el Anillo Único.", 28.99));
		librosFantasia.add(crearLibro("Harry Potter y la piedra filosofal", "J.K. Rowling", 9788478884454L, "Salamandra", "26-06-1997", "256", "El inicio de la serie que narra las aventuras del joven mago Harry Potter y sus amigos en la escuela de magia Hogwarts.", 19.95));
		librosFantasia.add(crearLibro("Canción de hielo y fuego: Juego de tronos", "George R.R. Martin", 9788496208908L, "Gigamesh", "06-08-1996", "800", "El primer libro de la serie que inspiró la popular serie de televisión Game of Thrones, ambientada en un mundo de fantasía épica.", 32.50));
		librosFantasia.add(crearLibro("El nombre del viento", "Patrick Rothfuss", 9788499082479L, "Plaza & Janés", "01-04-2007", "880", "La primera entrega de la Crónica del Asesino de Reyes, que sigue la vida del músico y mago Kvothe.", 23.80));
		librosFantasia.add(crearLibro("El Hobbit", "J.R.R. Tolkien", 9788445071407L, "Minotauro", "21-09-1937", "320", "La clásica novela que sigue las aventuras del hobbit Bilbo Bolsón en su búsqueda para recuperar el tesoro guardado por el dragón Smaug.", 15.75));

		Fantasia.setLibros(librosFantasia);
		categorias.add(Fantasia);


		Categoria Romance = new Categoria();
		Romance.setNombre(CategoriasEnum.Romance);

		List<Libro> librosRomance= new ArrayList<>();
		librosRomance.add(crearLibro("Cuando volvamos a vernos", "J.R.R. Tolkien", 9788445000666L, "Minotauro", "29-07-1954", "464", "La primera entrega de la épica trilogía que sigue la odisea de Frodo para destruir el Anillo Único.", 28.99));
		librosRomance.add(crearLibro("Cuentame esta noche", "J.K. Rowling", 9788478884454L, "Salamandra", "26-06-1997", "256", "El inicio de la serie que narra las aventuras del joven mago Harry Potter y sus amigos en la escuela de magia Hogwarts.", 19.95));
		librosRomance.add(crearLibro("Paris despertaba tarde", "George R.R. Martin", 9788496208908L, "Gigamesh", "06-08-1996", "800", "El primer libro de la serie que inspiró la popular serie de televisión Game of Thrones, ambientada en un mundo de fantasía épica.", 32.50));
		librosRomance.add(crearLibro("Arriba las manos", "Patrick Rothfuss", 9788499082479L, "Plaza & Janés", "01-04-2007", "880", "La primera entrega de la Crónica del Asesino de Reyes, que sigue la vida del músico y mago Kvothe.", 23.80));
		librosRomance.add(crearLibro("¿Tu lo harias?", "J.R.R. Tolkien", 9788445071407L, "Minotauro", "21-09-1937", "320", "La clásica novela que sigue las aventuras del hobbit Bilbo Bolsón en su búsqueda para recuperar el tesoro guardado por el dragón Smaug.", 15.75));

		Romance.setLibros(librosRomance);
		categorias.add(Romance);

		Biblioteca biblioteca = new Biblioteca();
		biblioteca.setCategorias(categorias);

		return biblioteca;
	}
	public static Categoria obtenerCategoriaPorNombre(String nombre, List<Categoria> categorias) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().toString().equals(nombre)) {
                return categoria;
            }
        }
        return null;
    }
	public static void guardarBiblioteca() {
	    try {
	        // Configurar el contexto de JAXB para la clase Biblioteca
	        JAXBContext context = JAXBContext.newInstance(Biblioteca.class);

	        // Crear el objeto Marshaller
	        Marshaller marshaller = context.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

	        // Obtener la biblioteca actualizada
	        Biblioteca biblioteca = obtenerBiblioteca();

	        // Guardar la biblioteca en el archivo XML
	        marshaller.marshal(biblioteca, new File("biblioteca.xml"));
	    } catch (JAXBException e) {
	        e.printStackTrace();
	        System.out.println("no se pudo guardar la biblioteca");
	    }
	}
	public static Biblioteca obtenerBiblioteca() {
	    try {
	        JAXBContext context = JAXBContext.newInstance(Biblioteca.class);

	        Unmarshaller unmarshaller = context.createUnmarshaller();

	        Biblioteca biblioteca = (Biblioteca) unmarshaller.unmarshal(new File("biblioteca.xml"));

	        return biblioteca;
	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public static Libro crearLibro(String titulo, String autor, Long isbn, String editorial,
			String fechaDePublicacion, String numeroPaginas, String sinopsis, Double precio) {
		Libro libro = new Libro();
		libro.setTitulo(titulo);
		libro.setAutor(autor);
		libro.setIsbn(isbn);
		libro.setEditorial(editorial);
		libro.setFechaDePublicacion(fechaDePublicacion);
		libro.setNumeroPaginas(numeroPaginas);
		libro.setSinopsis(sinopsis);
		libro.setPrecio(precio);
		return libro;
	}
}