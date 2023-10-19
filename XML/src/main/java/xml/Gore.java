package xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Gore {
    private List<Anime> animeList;

    @XmlElement(name = "anime")
    public List<Anime> getAnimeList() {
        return animeList;
    }

    public void setAnimeList(List<Anime> animeList) {
        this.animeList = animeList;
    }
}
