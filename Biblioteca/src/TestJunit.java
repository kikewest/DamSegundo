
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controlador.Biblioteca;
import controlador.Categoria;
import controlador.CategoriasEnum;
import controlador.CrearXml;
import controlador.Libro;
import controlador.Metodos;
import vista.Interfaz;
class TestJunit {
	private final String archivoXml = "biblioteca.xml";
	@Test
    void testVerificarYCrearXML() {
        String nombreArchivo = "biblioteca.xml"; 
        File archivo = new File(nombreArchivo);

        if (archivo.exists()) {
            System.out.println("El archivo ya existe. No se realizará ninguna acción.");
            assertTrue(archivo.exists());
        } else {
            Metodos.verificarYCrearXML();
            assertTrue(archivo.exists());
        }
    }
	
	@Test
    void testCrearBiblioteca() {
        Biblioteca biblioteca = Metodos.crearBiblioteca();
        assertNotNull(biblioteca);
        assertNotNull(biblioteca.getCategorias());
    }

	@Test
    void testObtenerCategoriaPorNombre() {
        List<Categoria> categorias = obtenerListaDeCategorias();
        Categoria resultado = Metodos.obtenerCategoriaPorNombre("FiccionLiteraria", categorias);
        assertNotNull(resultado);
        assertEquals(CategoriasEnum.FiccionLiteraria, resultado.getNombre());
    }

    @Test
    void testGuardarYObtenerBiblioteca() {
        Biblioteca bibliotecaOriginal = crearBibliotecaDePrueba();
        Metodos.guardarBiblioteca();

        Biblioteca bibliotecaRecuperada = Metodos.obtenerBiblioteca();

        assertNotNull(bibliotecaRecuperada);
        assertEquals(bibliotecaOriginal.getCategorias().size(), bibliotecaRecuperada.getCategorias().size());
        // Puedes realizar más aserciones según tus necesidades
    }

    private Biblioteca crearBibliotecaDePrueba() {
    	List<Categoria> categorias = new ArrayList<>();

        // Crear categoría de Ficción Literaria
        Categoria ficcionLiteraria = new Categoria();
        ficcionLiteraria.setNombre(CategoriasEnum.FiccionLiteraria);

        List<Libro> librosFiccionLiteraria = new ArrayList<>();
        librosFiccionLiteraria.add(Metodos.crearLibro("Libro1", "Autor1", 1234567890L, "Editorial1", "01-01-2022", "300", "Sinopsis1", 19.99));
        librosFiccionLiteraria.add(Metodos.crearLibro("Libro2", "Autor2", 1234567891L, "Editorial2", "02-01-2022", "320", "Sinopsis2", 24.99));

        ficcionLiteraria.setLibros(librosFiccionLiteraria);
        categorias.add(ficcionLiteraria);

        // Crear categoría de No Ficción
        Categoria noFiccion = new Categoria();
        noFiccion.setNombre(CategoriasEnum.NoFiccion);

        List<Libro> librosNoFiccion = new ArrayList<>();
        librosNoFiccion.add(Metodos.crearLibro("Libro3", "Autor3", 1234567892L, "Editorial3", "03-01-2022", "280", "Sinopsis3", 29.99));
        librosNoFiccion.add(Metodos.crearLibro("Libro4", "Autor4", 1234567893L, "Editorial4", "04-01-2022", "350", "Sinopsis4", 22.99));

        noFiccion.setLibros(librosNoFiccion);
        categorias.add(noFiccion);

        // Crear instancia de Biblioteca
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setCategorias(categorias);

        return biblioteca;
    }

    @Test
    void testCrearLibro() {
        Libro libro = Metodos.crearLibro("Título", "Autor", 1234567890L, "Editorial", "01-01-2022", "300", "Sinopsis", 19.99);
        assertNotNull(libro);
        assertEquals("Título", libro.getTitulo());
        assertEquals("Autor", libro.getAutor());
        assertEquals(1234567890L, libro.getIsbn());
        // Puedes realizar más aserciones según tus necesidades
    }

    private List<Categoria> obtenerListaDeCategorias() {
    	List<Categoria> categorias = new ArrayList<>();

        // Crear categoría de Misterio Thriller
        Categoria misterioThriller = new Categoria();
        misterioThriller.setNombre(CategoriasEnum.MisterioThriller);

        List<Libro> librosMisterioThriller = new ArrayList<>();
        librosMisterioThriller.add(Metodos.crearLibro("Libro5", "Autor5", 1234567894L, "Editorial5", "05-01-2022", "400", "Sinopsis5", 18.99));
        librosMisterioThriller.add(Metodos.crearLibro("Libro6", "Autor6", 1234567895L, "Editorial6", "06-01-2022", "380", "Sinopsis6", 21.50));

        misterioThriller.setLibros(librosMisterioThriller);
        categorias.add(misterioThriller);

        // Crear categoría de Ciencia Ficción
        Categoria cienciaFiccion = new Categoria();
        cienciaFiccion.setNombre(CategoriasEnum.CienciaFiccion);

        List<Libro> librosCienciaFiccion = new ArrayList<>();
        librosCienciaFiccion.add(Metodos.crearLibro("Libro7", "Autor7", 1234567896L, "Editorial7", "07-01-2022", "420", "Sinopsis7", 25.99));
        librosCienciaFiccion.add(Metodos.crearLibro("Libro8", "Autor8", 1234567897L, "Editorial8", "08-01-2022", "360", "Sinopsis8", 19.75));

        cienciaFiccion.setLibros(librosCienciaFiccion);
        categorias.add(cienciaFiccion);

        // Puedes agregar más categorías según tus necesidades

        return categorias;
    }
    @Test
    void testObtenerCategorias() {
        // Crear una biblioteca de prueba con datos conocidos o utilizar métodos de la clase interfaz
        Biblioteca biblioteca = crearBibliotecaDePrueba();
        // Guardar la biblioteca en el archivo XML
        CrearXml.marshalizarBiblioteca(biblioteca, archivoXml);

        // Llamar al método obtenerCategorias
        // Suponiendo que tienes un objeto de la clase que contiene el método obtenerCategorias
        Interfaz claseInterfaz = new Interfaz();

        // Obtener las categorías del archivo XML
        List<String> categorias = claseInterfaz.obtenerCategorias();

        // Verificar que las categorías se han obtenido correctamente
        // Puedes adaptar esto según cómo estructures tus clases y datos
        assertNotNull(categorias);
        assertFalse(categorias.isEmpty()); // Asegurarse de que la lista no está vacía

        // Puedes agregar más aserciones según sea necesario
    }

    
}
