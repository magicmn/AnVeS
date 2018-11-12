package de.anves.controller.dao;

import de.anves.Mitarbeiter;
import de.anves.controller.db.DBController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterDAO implements CRUDInterface<Mitarbeiter> {

    private DBController dbController = DBController.getInstance();

    /**
     * Singleton Pattern
     */
    private static MitarbeiterDAO instance;

    public static MitarbeiterDAO getInstance() {
        if (instance == null) {
            instance = new MitarbeiterDAO();
        }
        return instance;
    }

    private MitarbeiterDAO() {

    }


    @Override
    public Mitarbeiter create(Mitarbeiter value) {
        dbController.connect();
        Mitarbeiter mitarbeiter = new Mitarbeiter();

        String sqlStatementCreate = "INSERT INTO Mitarbeiter(Vorname, Nachname, Geschlecht) VALUES ("
                + value.getVorname() + ", "
                + value.getNachname() + ", "
                + value.isGeschlecht() + ")";

        try {
            ResultSet result = dbController.executeQuery(sqlStatementCreate);
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

    @Override
    public Mitarbeiter read(long id) {
        return null;
    }

    @Override
    public Mitarbeiter update(Mitarbeiter value) {
        return null;
    }

    @Override
    public boolean delete(Mitarbeiter value) {
        return false;
    }

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
