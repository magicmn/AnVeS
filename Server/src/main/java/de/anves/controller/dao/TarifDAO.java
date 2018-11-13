package de.anves.controller.dao;

import de.anves.Tarif;
import de.anves.controller.db.DBController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jannik Stark
 * @version 1.0
 * @see Tarif
 * @see DBController
 * @see CRUDInterface
 */

public class TarifDAO implements CRUDInterface<Tarif> {

    private DBController db = DBController.getInstance();

    /**
     * Einen neuen Tarif erstellen<p>
     * @param value ein Objekt des Typs Tarif
     * @return Erstellter Tarif
     */
    @Override
    public Tarif create(Tarif value) {
        db.connect();
        String sqlCreate = "INSERT INTO tarif(Anhängertyp, TarifNr, Tarifbezeichnung, Gültigab, Gültigbis, Preis) VALUES ('" +
                value.getAnhaengerTyp() + "', " +
                value.getTarifNummer() + ", '" +
                value.getTarifBezeichnung() + "', " +
                value.getGueltigAb().getTime() + ", " +
                value.getGueltigBis().getTime() + ", " +
                value.getPreis() + ") ";

        try {
            db.executeUpdate(sqlCreate);
            return value;
        } catch (SQLException e) {
            System.out.println("TarifDAO: create failed");
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
    public Tarif read(long id) {
        return null;
    }

    /**
     * Liest einen Tarif anhand der eindeutigen Kennung aus <p>
     * @param tarifNummer Die Nummer des Tarifs
     * @param anhaengerTyp Der Anhängertyp (E - Einachser | T = Tandemachser | K = Kofferanhänger | M = Motorradanhänger)
     * @return Objekt des Typs Tarif
     */
    public Tarif read(long tarifNummer, String anhaengerTyp) {
        db.connect();
        String sqlCreate = "SELECT * FROM Tarif WHERE TarifNr = " + tarifNummer + " AND Anhängertyp = '" + anhaengerTyp + "'";

        try {
            return convertRsToTarif(db.executeQuery(sqlCreate));
        } catch (SQLException e) {
            System.out.println("TarifDAO: read failed");
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
     * Updaten eines Tarifs <p>
     * @param value Objekt des Typs Tarif
     * @return Updated Objekt des Typs Tarif
     */
    @Override
    public Tarif update(Tarif value) {
        db.connect();

        String sqlUpdate = "UPDATE Tarif " +
                "SET Anhängertyp = '" + value.getAnhaengerTyp() + "', " +
                "TarifNr = " + value.getTarifNummer() +", " +
                "Tarifbezeichnung = '" + value.getTarifBezeichnung() + "', " +
                "Gültigab = " + value.getGueltigAb().getTime() + ", " +
                "Gültigbis = " + value.getGueltigBis().getTime() + " " +
                " WHERE TarifNr = " + value.getTarifNummer() + " AND Anhängertyp = '" + value.getAnhaengerTyp() + "';";
        System.out.println(sqlUpdate);
        try {
            db.executeUpdate(sqlUpdate);
            return value;
        } catch (SQLException e) {
            System.out.println("MitarbeiterDAO: update failed");
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
     * Löscht einen Tarif <p>
     * @param value Objekt des Typs Tarif
     * @return true oder false
     */
    @Override
    public boolean delete(Tarif value) {
        db.connect();

        String sql = "DELETE FROM Tarif WHERE Tarifbezeichnung = '"
                + value.getTarifBezeichnung() +
                "' AND TarifNr = " + value.getTarifNummer();
        System.out.println(sql);
        try {
            db.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("TarifDAO: delete failed");
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
     * Liest alle Tarife aus der Datenbank aus <p>
     * @return Liste mit Tarifen
     */
    public List<Tarif> readAllTarife(){
        List<Tarif> tarifListe = new ArrayList<>();

        db.connect();

        String sql = "SELECT * FROM Tarif";

        try {
            return convertRsToTarifList(db.executeQuery(sql));
        } catch (SQLException e){
            System.out.println("TarifDAO: readAllTarife failed");
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Konvertiert ein ResultSet zu einer Tarifliste <p>
     * @param rs ResultSet zum konvertieren
     * @return Liste mit Tarifen
     * @throws SQLException -
     * @see ResultSet
     */
    private List<Tarif> convertRsToTarifList(ResultSet rs) throws SQLException {
        List<Tarif> result = new ArrayList<>();

        while (rs.next()) {
            Tarif tarif = new Tarif();
            tarif.setAnhaengerTyp(rs.getString("Anhängertyp"));
            tarif.setGueltigAb(rs.getDate("Gültigab"));
            tarif.setGueltigBis(rs.getDate("Gültigbis"));
            tarif.setTarifBezeichnung(rs.getString("Tarifbezeichnung"));
            tarif.setTarifNummer(rs.getLong("TarifNr"));
            tarif.setPreis(rs.getDouble("Preis"));
            result.add(tarif);
        }
        return result;
    }

    /**
     * Konvertiert ein ResultSet zu einem Tarif <p>
     * @param rs Das ResultSet das konvertiert werden soll
     * @return Letzter Tarif des ResultSets
     * @throws SQLException -
     * @see ResultSet
     */
    private Tarif convertRsToTarif(ResultSet rs) throws SQLException {
        Tarif tarif = new Tarif();

        while (rs.next()) {
            tarif.setAnhaengerTyp(rs.getString("Anhängertyp"));
            tarif.setGueltigAb(rs.getDate("Gültigab"));
            tarif.setGueltigBis(rs.getDate("Gültigbis"));
            tarif.setTarifBezeichnung(rs.getString("Tarifbezeichnung"));
            tarif.setTarifNummer(rs.getLong("TarifNr"));
            tarif.setPreis(rs.getDouble("Preis"));
        }
        return tarif;
    }

}
