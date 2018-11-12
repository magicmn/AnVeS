package de.anves;

import java.util.Date;

public class Reservierung {

    private long reservierungsId;
    private Anhaenger anhaenger;
    private Date vertragBeginn;
    private Date vertragsEnde;
    private Kunde kunde;

    public long getReservierungsId() {
        return reservierungsId;
    }
    public void setReservierungsId(long reservierungsId) {
        this.reservierungsId = reservierungsId;
    }
    public Anhaenger getAnhaenger() {
        return anhaenger;
    }
    public void setAnhaenger(Anhaenger anhaenger) {
        this.anhaenger = anhaenger;
    }
    public Date getVertragBeginn() {
        return vertragBeginn;
    }
    public void setVertragBeginn(Date vertragBeginn) {
        this.vertragBeginn = vertragBeginn;
    }
    public Date getVertragsEnde() {
        return vertragsEnde;
    }
    public void setVertragsEnde(Date vertragsEnde) {
        this.vertragsEnde = vertragsEnde;
    }
    public Kunde getKunde() {
        return kunde;
    }
    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

}
