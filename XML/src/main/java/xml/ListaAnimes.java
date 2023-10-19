package xml;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name = "crunchyroll")
public class ListaAnimes {
	private List<Crunchyroll> animes;

    public ListaAnimes() {
    	animes = new ArrayList<>();
    }

    @XmlElement(name = "anime")
    public List<Crunchyroll> getObjetos() {
        return animes;
    }

    public void setObjetos(List<Crunchyroll> objetos) {
        this.animes = objetos;
    }
}

