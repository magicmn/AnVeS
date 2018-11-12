package de.anves.controller.dao;

import de.anves.Mitarbeiter;
import de.anves.controller.db.DBController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object für den Mitarbeiter
 * @author: Jannik Stark
 * @version 1.0
 * @see Mitarbeiter
 * @see DBController
 */
public class MitarbeiterDAO implements CRUDInterface<Mitarbeiter> {

    private DBController dbController = DBController.getInstance();

    public MitarbeiterDAO() {

    }

    /**
     * Mitarbeiter erstellen <p>
     * @param value Objekt vom Typ Mitarbeiter <br>
     * @return Der letzte Eintrag der Datenbank <br>
     * executeUpdate gibt ein boolean zurück, wenn dieser true ist wird
     * getLastEntry() ausgeführt.
     */
    @Override
    public Mitarbeiter create(Mitarbeiter value) {
        dbController.connect();
        Mitarbeiter mitarbeiter = new Mitarbeiter();

        boolean geschlecht = value.isGeschlecht();

        String sqlStatementCreate = "INSERT INTO Mitarbeiter(Vorname, Nachname, Geschlecht) VALUES ('"
                + value.getVorname() + "', '"
                + value.getNachname() + "', "
                + value.isGeschlecht() + ")";

        try {
            Integer result = dbController.executeUpdate(sqlStatementCreate);
            if (result == 1) {
                return getLastEntry();
            }
        } catch (SQLException e) {
            System.out.println("MitarbeiterDAO: Create User failed");
            e.printStackTrace();
        } finally {
            try {
                dbController.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * Mitarbeiter anhand der ID auslesen <p>
     * @param id Mitarbeiterid <p>
     * @return Mitarbeiter mit der übergebenen ID <br>
     * executeQuery gibt ein ResultSet zurück, welches dann convertRsToMitarbeiter(ResultSet) übergeben wird. <br>
     */
    @Override
    public Mitarbeiter read(long id) {
        dbController.connect();

        String sqlRead = "SELECT * FROM Mitarbeiter WHERE Mitarbeiterid = " + id + "";

        try {
            ResultSet result = dbController.executeQuery(sqlRead);
            return convertRsToMitarbeiter(result);
        } catch (SQLException e) {
            System.out.println("MitarbeiterDAO: read failed");
            e.printStackTrace();
        } finally {
            try {
                dbController.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Liste aller Mitarbeiter ausgeben <p>
     * executeQuery() gibt ein ResultSet zurück, welches dann an convertRsToMitarbeiterList(ResultSet) übergeben wird.
     * @return Eine Liste mit allen Mitarbeitern
     */
    public List<Mitarbeiter> readAllMitarbeiter(){
        List<Mitarbeiter> mitarbeiterListe = new ArrayList<Mitarbeiter>();
        dbController.connect();
        String sql = "SELECT * FROM Mitarbeiter";
        try {
            ResultSet result = dbController.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("MitarbeiterDAO: readAllMitarbeiter failed");
            e.printStackTrace();
        } finally {
            try {
                dbController.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * Mitarbeiter updaten <p>
     * @param value Ein Objekt vom Typ Mitarbeiter
     * @return Den bearbeiteten Mitarbeiter wenn erfolgreich, null wenn nicht erfolgreich
     */
    @Override
    public Mitarbeiter update(Mitarbeiter value) {
        dbController.connect();

        String sqlUpdate = "UPDATE Mitarbeiter " +
                "SET Vorname = '" + value.getVorname() + "', " +
                "Nachname = '" + value.getNachname() +"', " +
                "Geschlecht = " + (value.isGeschlecht() ? 1 : 0) +
                " WHERE Mitarbeiterid = " + value.getId();
        try {
            dbController.executeUpdate(sqlUpdate);
            return value;
        } catch (SQLException e) {
            System.out.println("MitarbeiterDAO: update failed");
            e.printStackTrace();
        } finally {
            try {
                dbController.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Mitarbeiter löschen <p>
     * @param value ist ein Objekt vom Typ Mitarbeiter
     * @return true bei Erfolg, false bei misserfolg
     */
    @Override
    public boolean delete(Mitarbeiter value) {
        dbController.connect();

        String sql = "DELETE FROM Mitarbeiter WHERE Mitarbeiterid = " + value.getId();
        try {
            dbController.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println("MitarbeiterDAO: delete failed");
            e.printStackTrace();
        } finally {
            try {
                dbController.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Letzten Eintrag der Datenbank auslesen <p>
     * @return Objekt vom Typ Mitarbeiter
     * @see ResultSet
     */
    public Mitarbeiter getLastEntry() {
        dbController.connect();

        String sqlStatement = "SELECT * FROM Mitarbeiter WHERE Mitarbeiterid IN (SELECT max(Mitarbeiterid) FROM mitarbeiter)";

        try {
            ResultSet result = dbController.executeQuery(sqlStatement);
            return convertRsToMitarbeiter(result);
        } catch (SQLException e) {
            System.out.println("MitarbeiterDAO: getLastEntry failed");
            e.printStackTrace();
        } finally {
            try {
                dbController.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Konvertiert ein ResultSet zu einer Mitarbeiterliste <p>
     * @param rs ResultSet zum konvertieren
     * @return Liste mit Mitarbeitern
     * @throws SQLException -
     * @see ResultSet
     */
    private List<Mitarbeiter> convertRsToMitarbeiterList(ResultSet rs) throws SQLException {
        List<Mitarbeiter> result = new ArrayList<Mitarbeiter>();

        while (rs.next()) {
            Mitarbeiter mitarbeiter = new Mitarbeiter();
            mitarbeiter.setId(rs.getLong("Mitarbeiterid"));
            mitarbeiter.setNachname(rs.getString("Nachname"));
            mitarbeiter.setVorname(rs.getString("Vorname"));
            mitarbeiter.setGeschlecht(rs.getBoolean("Geschlecht"));
            result.add(mitarbeiter);
        }
        return result;
    }

    /**
     * Konvertiert ein ResultSet zu einem Mitarbeiter <p>
     * @param rs Das ResultSet das konvertiert werden soll
     * @return Letzter Mitarbeiter des ResultSets
     * @throws SQLException -
     * @see ResultSet
     */
    private Mitarbeiter convertRsToMitarbeiter(ResultSet rs) throws SQLException {
        Mitarbeiter mitarbeiter = new Mitarbeiter();

        while (rs.next()) {
            mitarbeiter.setId(rs.getLong("Mitarbeiterid"));
            mitarbeiter.setVorname(rs.getString("Vorname"));
            mitarbeiter.setNachname(rs.getString("Nachname"));
            mitarbeiter.setGeschlecht(rs.getBoolean("Geschlecht"));
        }
        return mitarbeiter;
    }
}
