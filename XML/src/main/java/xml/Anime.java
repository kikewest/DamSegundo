package xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


// Indica que esta clase es el elemento raíz en el XML
@XmlRootElement
@XmlType(propOrder = { "titulo", "autor", "codigo", "espectadores" })
public class Anime {
    private String titulo;
    private String autor;
    private String codigo;
    private Espectadores espectadores;

    @XmlElement
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @XmlElement
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @XmlElement
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @XmlElement
    public Espectadores getEspectadores() {
        return espectadores;
    }

    public void setEspectadores(Espectadores espectadores) {
        this.espectadores = espectadores;
    }
}