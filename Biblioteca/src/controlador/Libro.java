package controlador;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Libro")
public class Libro {
    private String Titulo;
    private String Autor;
    private Long isbn;
    private String Editorial;
    private String FechaDePublicacion;
    private String NumeroPaginas;
    private String Sinopsis;
    private Double Precio;

    // Constructor por defecto necesario para JAXB
    public Libro() {
    }

    // Constructor con parámetros
    public Libro(String titulo, String autor, Long isbn, String editorial, String fechaDePublicacion,
                String numeroPaginas, String sinopsis, Double precio) {
        this.Titulo = titulo;
        this.Autor = autor;
        this.isbn = isbn;
        this.Editorial = editorial;
        this.FechaDePublicacion = fechaDePublicacion;
        this.NumeroPaginas = numeroPaginas;
        this.Sinopsis = sinopsis;
        this.Precio = precio;
    }

    @XmlElement(name = "Titulo")
    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        this.Titulo = titulo;
    }

    @XmlElement(name = "Autor")
    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        this.Autor = autor;
    }

    @XmlElement(name = "Isbn")
    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    @XmlElement(name = "Editorial")
    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String editorial) {
        this.Editorial = editorial;
    }

    @XmlElement(name = "FechaDePublicacion")
    public String getFechaDePublicacion() {
        return FechaDePublicacion;
    }

    public void setFechaDePublicacion(String fechaDePublicacion) {
        this.FechaDePublicacion = fechaDePublicacion;
    }

    @XmlElement(name = "NumeroPaginas")
    public String getNumeroPaginas() {
        return NumeroPaginas;
    }

    public void setNumeroPaginas(String numeroPaginas) {
        this.NumeroPaginas = numeroPaginas;
    }

    @XmlElement(name = "Sinopsis")
    public String getSinopsis() {
        return Sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.Sinopsis = sinopsis;
    }

    @XmlElement(name = "Precio")
    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        this.Precio = precio;
    }
}