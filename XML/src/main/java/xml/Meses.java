package xml;

import javax.xml.bind.annotation.XmlElement;


public class Meses {
    private int enero;
    private int febrero;
    private int marzo;
    private int abril;
    private int mayo;
    private int junio;
    private int julio;
    private int agosto;
    private int septiembre;
    private int octubre;
    private int noviembre;
    private int diciembre;

    @XmlElement
    public int getEnero() {
        return enero;
    }

    public void setEnero(int enero) {
        this.enero = enero;
    }

    @XmlElement
    public int getFebrero() {
        return febrero;
    }

    public void setFebrero(int febrero) {
        this.febrero = febrero;
    }

    @XmlElement
    public int getMarzo() {
        return marzo;
    }

    public void setMarzo(int marzo) {
        this.marzo = marzo;
    }

    @XmlElement
    public int getAbril() {
        return abril;
    }

    public void setAbril(int abril) {
        this.abril = abril;
    }

    @XmlElement
    public int getMayo() {
        return mayo;
    }

    public void setMayo(int mayo) {
        this.mayo = mayo;
    }

    @XmlElement
    public int getJunio() {
        return junio;
    }

    public void setJunio(int junio) {
        this.junio = junio;
    }

    @XmlElement
    public int getJulio() {
        return julio;
    }

    public void setJulio(int julio) {
        this.julio = julio;
    }

    @XmlElement
    public int getAgosto() {
        return agosto;
    }

    public void setAgosto(int agosto) {
        this.agosto = agosto;
    }

    @XmlElement
    public int getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(int septiembre) {
        this.septiembre = septiembre;
    }

    @XmlElement
    public int getOctubre() {
        return octubre;
    }

    public void setOctubre(int octubre) {
        this.octubre = octubre;
    }

    @XmlElement
    public int getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(int noviembre) {
        this.noviembre = noviembre;
    }

    @XmlElement
    public int getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(int diciembre) {
        this.diciembre = diciembre;
    }
}