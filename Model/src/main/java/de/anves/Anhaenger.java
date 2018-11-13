package de.anves;

import java.util.Date;
import java.util.List;

public class Anhaenger extends DBManaged {

    private long id;
    private AnhaengerTypEnum anhaengerTyp;
    private String kennzeichen;
    private Date naechsteHU;
    private List<Schadensbericht> schadensBerichte;

//Getter and Setter-------------------------------

    public long getId() {
        return id;
    }
    public Anhaenger setId(long id) {
        this.id = id;
        return this;
    }
    public AnhaengerTypEnum getAnhaengerTyp() {
        return anhaengerTyp;
    }
    public Anhaenger setAnhaengerTyp(AnhaengerTypEnum anhaengerTyp) {
        this.anhaengerTyp = anhaengerTyp;
        return this;
    }
    public String getKennzeichen() {
        return kennzeichen;
    }
    public Anhaenger setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
        return this;
    }
    public Date getNaechsteHU() {
        return naechsteHU;
    }
    public Anhaenger setNaechsteHU(Date naechsteHU) {
        this.naechsteHU = naechsteHU;
        return this;
    }
    public List<Schadensbericht> getSchadensBerichte() {
        return schadensBerichte;
    }
    public Anhaenger setSchadensBerichte(List<Schadensbericht> schadensBerichte) {
        this.schadensBerichte = schadensBerichte;
        return this;
    }

}
