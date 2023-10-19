package xml;

import javax.xml.bind.annotation.XmlElement;

public class Espectadores {
    private Meses meses;

    @XmlElement
    public Meses getMeses() {
        return meses;
    }

    public void setMeses(Meses meses) {
        this.meses = meses;
    }
}
