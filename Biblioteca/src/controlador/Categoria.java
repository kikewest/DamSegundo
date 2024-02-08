package controlador;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"nombre", "libros"})
public class Categoria {
    private CategoriasEnum nombre;
    private List<Libro> libros;

    // Constructores, getters y setters

    @XmlElement(name = "nombre")
    public CategoriasEnum getNombre() {
        return nombre;
    }

    public void setNombre(CategoriasEnum nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "Libro")
    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}