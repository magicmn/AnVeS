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
    public void setId(long id) {
        this.id = id;
    }
    public String getNachname() {
        return nachname;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    public String getVorname() {
        return vorname;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    public boolean isGeschlecht() {
        return geschlecht;
    }
    public void setGeschlecht(boolean geschlecht) {
        this.geschlecht = geschlecht;
    }
    public List<RollenEnum> getRollenliste() {
        return rollenliste;
    }
    public void setRollenliste(List<RollenEnum> rollenliste) {
        this.rollenliste = rollenliste;
    }

}
