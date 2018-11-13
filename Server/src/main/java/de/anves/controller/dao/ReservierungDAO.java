package de.anves.controller.dao;

import de.anves.Reservierung;
import de.anves.controller.db.DBController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data Access Object für den Mitarbeiter
 * @author: Christian Karl Schumacher aka Omegasulu
 * @version 1.0
 * @see Reservierung
 * @see DBController
 */


public class ReservierungDAO implements CRUDInterface<Reservierung> {

    private static DBController db = DBController.getInstance();


    public ReservierungDAO() {

    }

    /**
     * Reservierung erstellen <p>
     * @param value Objekt vom Typ Reservierung <br>
     * @return Der letzte Eintrag der Datenbank <br>
     * executeUpdate gibt ein boolean zurück, wenn dieser true ist wird
     */
    @Override
    public Reservierung create(Reservierung value) {

        db.connect();
        String createSQL = "INSERT INTO reservierung(Reservierungsid, anhaengerid, von, bis, kundenid) VALUES ("

                + "'" + value.getReservierungsId() + "',"
                + "'" + value.getAnhaenger() + "',"
                + "'" + value.getVertragBeginn().getTime() + "',"
                + "'" + value.getVertragsEnde().getTime() + "',"
                + "'" + value.getKunde() + "');";


        String selectsql = "SELECT * FROM reservierung  WHERE reservierungsid=(select max(reservierungsid) from reservierung)";


        try {
            ResultSet rs = db.executeQuery(selectsql);

   return convertRsToReservierung(rs);

        } catch (SQLException e) {
            System.out.println("ReservierungsDAO: Create User failed");
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public  Reservierung read(long reservierungid) {
        String readsql = "SELECT * FROM reservierung WHERE reservierungsId = '" + reservierungid + "'";

        db.connect();
        try {
            ResultSet rs = db.executeQuery(readsql);

        } catch (SQLException e) {
            System.err.println("ReservierungDAO: Read: Fehler");
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * Reservierung updaten <p>
     * @param value Ein Objekt vom Typ Reservierung
     * @return Den bearbeitete Reservierung wenn erfolgreich, null wenn nicht erfolgreich
     */

    @Override
    public Reservierung update(Reservierung value) {
        db.connect();
        String updatesql = "UPDATE reservierung SET "
                + "id=" + value.getReservierungsId() + ","

                + "anhaenger=" + value.getAnhaenger() + "',"
                + "Vetragsbeginn=" + value.getVertragBeginn().getTime() + "',"
                + "Vertragsende=" + value.getVertragsEnde().getTime() + "',"
                + "Kunde=" + value.getKunde() + "',"
                + ")";

        String selectsql = "SELECT * FROM reservierung WHERE reservierungsId = " + value.getReservierungsId();


        try {
            db.executeUpdate(updatesql);
            ResultSet rs = db.executeQuery(selectsql);

        } catch (SQLException e) {
            System.err.println("Reservierung: upodateReservierung: Fehler");
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Reservierung löschen <p>
     * @param value ist ein Objekt vom Typ Reservierung
     * @return true bei Erfolg, false bei misserfolg
     */

    @Override
    public boolean delete(Reservierung value) {
        String deletesql = "DELETE FROM kunde WHERE id=" + value.getReservierungsId();

        db.connect();

        String sql = "DELETE FROM Mitarbeiter WHERE Mitarbeiterid = " + value.getReservierungsId();
        try {
            db.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("ReservierungsDAO: delete failed");
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * Konvertiert ein ResultSet zu einer Reservierung  <p>
     * @param rs Das ResultSet das konvertiert werden soll
     * @return Letzte Reservierung des ResultSets
     * @throws SQLException -
     * @see ResultSet
     */

    private Reservierung convertRsToReservierung(ResultSet rs) throws SQLException {
        List<Reservierung> result = new ArrayList<>();
        Reservierung reservierung = new Reservierung();
        while (rs.next()) {

            reservierung.setReservierungsId(rs.getLong("reservierungsid"));
           // reservierung.setAnhaenger(new AnhaengerDAO().read(id));
            reservierung.setVertragBeginn(new Date(rs.getLong("Vertragsbeginn")));
            reservierung.setVertragsEnde(new Date(rs.getLong("Vetragsende")));
            //reservierung.setKunde(new KundeDAO().read());

        }
        return reservierung;
    }
}