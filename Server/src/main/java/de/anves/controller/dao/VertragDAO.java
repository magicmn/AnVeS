package de.anves.controller.dao;

import de.anves.*;
import de.anves.controller.db.DBController;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VertragDAO implements CRUDInterface<Vertrag> {


    private DBController db = DBController.getInstance();
    private Vertrag vertrag = new Vertrag();
    private List<Vertrag> result = new ArrayList<Vertrag>();
    private MitarbeiterDAO mitarbeiterdao = new MitarbeiterDAO();
    private TarifDAO tarifdao = new TarifDAO();
    private RueckgabeDAO rueckgabeDAO = new RueckgabeDAO();
    private UebergabeDAO uebergabeDAO = new UebergabeDAO();
    private ReservierungDAO reservierungdao = new ReservierungDAO();
    private SchadensberichtDAO schadensberichtdao = new SchadensberichtDAO();
    private Reservierung reser = new Reservierung();


    public VertragDAO() {
    }

    /**
     * Schreibt die Attribute des Objekts in die Tabellen: Vertrag, Rückgabe und Übergabe
     *
     * @return der erstellte Vertrag
     * @author Leon
     * @version 1.0
     */
    @Override
    public Vertrag create(Vertrag value) {
        db.connect();
        String createsql = "INSERT INTO vertrag (reservierungsid, tarifid) VALUES ("

                + value.getReservierung().getReservierungsId()+ ", "
                + value.getTarif().getTarifNummer() + ");";

        String selectsql = "SELECT * FROM vertrag WHERE vertragsid IN (SELECT MAX(vertragsid) in vertrag)";



        try {
            db.executeUpdate(createsql);
            ResultSet rs = db.executeQuery(selectsql);
            Vertrag result = convertRsToVertrag(rs).get(0);

            return null;
        } catch (SQLException e) {
            System.err.println("Fehler VertragDAO create");
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
     * Liest die Datenbankeinträge aus den Tabellen Vertrag, Rückgabe und übergabe anhand der übergebenen ID aus
     * und erstellt ein Objekt der Klasse Vertrag
     *
     * @return der Vertrag mit der angegebenen ID
     * @author Leon
     */
    public Vertrag read(long id) {
        String selectsql = "SELECT * FROM vertrag WHERE vertragsid = " + id;
        db.connect();
        try {
            ResultSet rs = db.executeQuery(selectsql);
            result = convertRsToVertrag(rs);

            db.closeConnection();
        } catch (SQLException e) {
            System.err.println("Fehler VertragDAO read");
            e.printStackTrace();
        }
        return result.get(0);
    }

    /**
     * Ist in der Lage die reservierungsid, die tarifid, das rück- und übergabedatum zu bearbeiten
     *
     * @author Leon
     * @version 1.0
     */
    @Override
    public Vertrag update(Vertrag value) {
        String updatesql = "UPDATE vertrag SET reservierungsid = " + value.getReservierung().getReservierungsId() +
                ", tarifid =" + value.getTarif().getTarifNummer() + " WHERE vertragsid = " + value.getId();

        String selectsql = "SELECT * FROM vertrag WHERE vertragsid = " + vertrag.getId();
        try {
            db.connect();
            db.executeUpdate(updatesql);
            ResultSet rs = db.executeQuery(selectsql);
            result = convertRsToVertrag(rs);
            db.closeConnection();
        } catch (SQLException e) {
            System.err.println("Fehler VertragDAO update");
            e.printStackTrace();
        }
        return result.get(0);
    }

    /**
     * @return ein boolean der zeigt ob es gelöscht worden ist oder nicht
     * @author Leon
     */
    @Override
    public boolean delete(Vertrag value) {
        String deletesql = "DELETE FROM vertrag WHERE vertragsid = " + value.getId();
        String selectsql = "SELECT * FROM vertrag WHERE vertragsid = " + value.getId();
        db.connect();
        try {
            db.executeUpdate(deletesql);
            ResultSet rs = db.executeQuery(selectsql);
        } catch (SQLException e) {
            System.err.println("Fehler VertragDAO delete");
            e.printStackTrace();
        }
        if (result.isEmpty()) {
            return true;
        }
        return false;
    }

    public List<Vertrag> convertRsToVertrag(ResultSet rs) throws SQLException {
        List<Vertrag> result = new ArrayList<Vertrag>();
       Vertrag vertrag = new Vertrag();

        while (rs.next()) {



            vertrag.setTarif(tarifdao.read(rs.getLong("tarifid")));
            vertrag.setReservierung(reservierungdao.read(rs.getLong("reservierungsid")));
          //  vertrag.setRueckgabe(rueckgabeDAO.read(vertrag.getId()));
            //vertrag.setUebergabe(uebergabeDAO.read(vertrag.getId()));
           // vertrag.setSchadensbericht(schadensberichtdao.read(vertrag.getReservierung().getAnhaenger().getId(),
             //         vertrag.getRueckgabe().getDatum()));

            result.add(vertrag);
        }
        return result;
    }


}
