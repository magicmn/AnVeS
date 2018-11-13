package de.anves;

import java.util.List;

public class Mitarbeiter extends DBManaged {

    private long id;
    private String nachname;
    private String vorname;
    private boolean geschlecht;
    private List<RollenEnum> rollenliste;

    //Getter and Setter--------------------------------
    public long getId() {
        return id;
    }
    public Mitarbeiter setId(long id) {
        this.id = id;
        return this;
    }
    public String getNachname() {
        return nachname;
    }
    public Mitarbeiter setNachname(String nachname) {
        this.nachname = nachname;
        return this;
    }
    public String getVorname() {
        return vorname;
    }
    public Mitarbeiter setVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }
    public boolean isGeschlecht() {
        return geschlecht;
    }
    public Mitarbeiter setGeschlecht(boolean geschlecht) {
        this.geschlecht = geschlecht;
        return this;
    }
    public List<RollenEnum> getRollenliste() {
        return rollenliste;
    }
    public Mitarbeiter setRollenliste(List<RollenEnum> rollenliste) {
        this.rollenliste = rollenliste;
        return this;
    }

}
