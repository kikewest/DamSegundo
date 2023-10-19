package xml;

import javax.xml.bind.annotation.XmlElement;

public class Categoria {
    private Shonen shonen;
    private Gore gore;

    @XmlElement
    public Shonen getShonen() {
        return shonen;
    }

    public void setShonen(Shonen shonen) {
        this.shonen = shonen;
    }

    @XmlElement
    public Gore getGore() {
        return gore;
    }

    public void setGore(Gore gore) {
        this.gore = gore;
    }
}
