//package de.anves.controller.dao;
//
//import de.anves.Mitarbeiter;
//import de.anves.Reservierung;
//import de.anves.Schadensbericht;
//import de.anves.Vertrag;
//import de.anves.controller.db.DBController;
//
//import java.util.Date;
//import java.util.List;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class VertragDAO implements CRUDInterface<Vertrag> {
//    private DBController db = DBController.getInstance();
//    private Vertrag vertrag = new Vertrag();
//    private List<Vertrag> result = new ArrayList<Vertrag>();
//    private MitarbeiterDAO mitarbeiterdao = new MitarbeiterDAO();
//    private TarifDAO tarifdao = new TarifDAO();
//    private VertragDAO instance;
//    private ReservierungDAO reservierungdao = new ReservierungDAO();
//    private SchadensberichtDAO schadensberichtdao = new SchadensberichtDAO();
//
//    public VertragDAO() {
//    }
//
//    /**
//     * Schreibt die Attribute des Objekts in die Tabellen: Vertrag, Rückgabe und Übergabe
//     *
//     * @return der erstellte Vertrag
//     * @author Leon
//     * @version 1.0
//     */
//    @Override
//    public Vertrag create(Vertrag value) {
//        String createsql = "INSERT INTO vertrag (reservierungsid, tarifid) VALUES (" +
//                value.getReservierung().getReservierungsId() +
//                ", " + value.getTarif().getTarifNummer() + ")";
//        String createRueckgabesql = "INSERT INTO rückgabe (Vertragsid, mitarbeiterid, datum) VALUES (" +
//                value.getId() + ", " + value.getRueckgabeMitarbeiter().getId() + ", " + value.getRueckgabe();
//        String createUebergabesql = "INSERT INTO übergabe (vertragsid, Mitarbeiterid, datum) VALUES (" +
//                value.getId() + ", " + value.getUebergabeMitarbeiter().getId() + ", " + value.getUebergabe();
//        String selectsql = "SELECT * FROM vertrag WHERE vertragsid IN (SELECT MAX(vertragsid) in vertrag)";
//        String selectRueckgabesql = "SELECT * FROM rückgabe WHERE vertragsid = " + value.getId();
//        String selectUebergabesql = "SELECT * FROM übergabe WHERE vertragsid = " + value.getId();
//        db.connect();
//        try {
//            db.executeUpdate(createsql);
//            db.executeUpdate(createRueckgabesql);
//            db.executeUpdate(createUebergabesql);
//            ResultSet rs = db.executeQuery(selectsql);
//            ResultSet rs2 = db.executeQuery(selectRueckgabesql);
//            ResultSet rs3 = db.executeQuery(selectUebergabesql);
//            result = convertRsToVertrag(rs, rs2, rs3);
//            db.closeConnection();
//        } catch (SQLException e) {
//            System.err.println("Fehler VertragDAO create");
//            e.printStackTrace();
//        }
//        return result.get(0);
//    }
//
//    /**
//     * Liest die Datenbankeinträge aus den Tabellen Vertrag, Rückgabe und übergabe anhand der übergebenen ID aus
//     * und erstellt ein Objekt der Klasse Vertrag
//     *
//     * @return der Vertrag mit der angegebenen ID
//     * @author Leon
//     */
//    public Vertrag read(long id) {
//        String selectsql = "SELECT * FROM vertrag WHERE vertragsid = " + id;
//        String selectuebergabe = "SELECT * FROM übergabe = " + id;
//        String selectrueckgabe = "SELECT * FROM rückgabe = " + id;
//        db.connect();
//        try {
//            ResultSet rs = db.executeQuery(selectsql);
//            ResultSet rs2 = db.executeQuery(selectuebergabe);
//            ResultSet rs3 = db.executeQuery(selectrueckgabe);
//            result = convertRsToVertrag(rs, rs2, rs3);
//            db.closeConnection();
//        } catch (SQLException e) {
//            System.err.println("Fehler VertragDAO read");
//            e.printStackTrace();
//        }
//        return result.get(0);
//    }
//
//    /**
//     * Ist in der Lage die reservierungsid, die tarifid, das rück- und übergabedatum zu bearbeiten
//     *
//     * @author: Leon
//     * @version: 1.0
//     */
//    @Override
//    public Vertrag update(Vertrag value) {
//        String updatesql = "UPDATE vertrag SET reservierungsid = " + value.getReservierung().getReservierungsId() +
//                ", tarifid =" + value.getTarif().getTarifNummer() + " WHERE vertragsid = " + value.getId();
//        String updateRueckgabesql = "UPDATE rückgabe SET mitarbeiterid = " + value.getRueckgabeMitarbeiter().getId() +
//                ", datum = " + value.getRueckgabe().getTime() + "WHERE vertragsid = " + value.getId();
//        String updateuebergabesql = "UPDATE übergabe SET mitarbeiterid = " + value.getUebergabeMitarbeiter().getId() +
//                ", datum = " + value.getUebergabe().getTime() + "WHERE vertragsid = " + value.getId();
//        String selectsql = "SELECT * FROM vertrag WHERE vertragsid = " + vertrag.getId();
//        String selectUebergabesql = "SELECT * FROM übergabe WHERE vertragsid = " + vertrag.getId();
//        String selectRueckgabesql = "SELECT * FROM rückgabe WHERE vertragsid = " + vertrag.getId();
//        try {
//            db.connect();
//            db.executeUpdate(updatesql);
//            db.executeUpdate(updateRueckgabesql);
//            db.executeUpdate(updateuebergabesql);
//            ResultSet rs = db.executeQuery(selectsql);
//            ResultSet rs2 = db.executeQuery(selectRueckgabesql);
//            ResultSet rs3 = db.executeQuery(selectUebergabesql);
//            result = convertRsToVertrag(rs, rs2, rs3);
//            db.closeConnection();
//        } catch (SQLException e) {
//            System.err.println("Fehler VertragDAO update");
//            e.printStackTrace();
//        }
//        return result.get(0);
//    }
//
//    /**
//     * @return ein boolean der zeigt ob es gelöscht worden ist oder nicht
//     * @author Leon
//     */
//    @Override
//    public boolean delete(Vertrag value) {
//        String deletesql = "DELETE FROM vertrag WHERE vertragsid = " + value.getId();
//        String selectsql = "SELECT * FROM vertrag WHERE vertragsid = " + value.getId();
//        db.connect();
//        try {
//            db.executeUpdate(deletesql);
//            ResultSet rs = db.executeQuery(selectsql);
//        } catch (SQLException e) {
//            System.err.println("Fehler VertragDAO delete");
//            e.printStackTrace();
//        }
//        if (result.isEmpty()) {
//            return true;
//        }
//        return false;
//    }
//
//    public List<Vertrag> convertRsToVertrag(ResultSet rs, ResultSet rs2, ResultSet rs3) throws SQLException {
//        List<Vertrag> result = new ArrayList<Vertrag>();
//        while (rs.next()) {
//            vertrag = new Vertrag();
//            vertrag.setId(rs.getLong("vertragsid"));
//            vertrag.setTarif(tarifdao.read(rs.getLong("tarifid")));
//            vertrag.setReservierung(reservierungdao.read(rs.getLong("Reservierungsid")));
//            vertrag.setUebergabe(new Date(rs3.getLong("datum")));
//            vertrag.setRueckgabe(new Date(rs2.getLong("datum")));
//            vertrag.setSchadensbericht(schadensberichtdao.read(vertrag.getReservierung().getAnhaenger().getId()),
//                    vertrag.getRueckgabe());
//            vertrag.setRueckgabeMitarbeiter(mitarbeiterdao.read(rs2.getLong("mitarbeiterid")));
//            vertrag.setUebergabeMitarbeiter(mitarbeiterdao.read(rs3.getLong("mitarbeiterid")));
//            result.add(vertrag);
//        }
//        return result;
//    }
//}
