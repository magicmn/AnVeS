package de.anves;

import java.util.Date;

public class Reservierung extends DBManaged {

    private long reservierungsId;
    private Anhaenger anhaenger;
    private Date vertragBeginn;
    private Date vertragsEnde;
    private Kunde kunde;

    public long getReservierungsId() {
        return reservierungsId;
    }
    public Reservierung setReservierungsId(long reservierungsId) {
        this.reservierungsId = reservierungsId;
        return this;
    }
    public Anhaenger getAnhaenger() {
        return anhaenger;
    }
    public Reservierung setAnhaenger(Anhaenger anhaenger) {
        this.anhaenger = anhaenger;
        return this;
    }
    public Date getVertragBeginn() {
        return vertragBeginn;
    }
    public Reservierung setVertragBeginn(Date vertragBeginn) {
        this.vertragBeginn = vertragBeginn;
        return this;
    }
    public Date getVertragsEnde() {
        return vertragsEnde;
    }
    public Reservierung setVertragsEnde(Date vertragsEnde) {
        this.vertragsEnde = vertragsEnde;
        return this;
    }
    public Kunde getKunde() {
        return kunde;
    }
    public Reservierung setKunde(Kunde kunde) {
        this.kunde = kunde;
        return this;
    }

}
