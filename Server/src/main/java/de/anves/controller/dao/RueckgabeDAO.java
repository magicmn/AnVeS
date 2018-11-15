package de.anves.controller.dao;

import de.anves.Rueckgabe;
import de.anves.controller.db.DBController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RueckgabeDAO implements CRUDInterface<Rueckgabe> {
    private DBController db = DBController.getInstance();
    private Rueckgabe rueckgabe = new Rueckgabe();
    private MitarbeiterDAO mitarbeiterDAO = new MitarbeiterDAO();


    @Override
    public Rueckgabe create(Rueckgabe value) {

        String createsql = "INSERT INTO rückgabe (vertragsid, mitarbeiterid, datum) VALUES ("
                +value.getVertragid()+", "+value.getMitarbeiter().getId()+", "+value.getDatum().getTime();
        String selectsql = "SELECT * FROM rückgabe WHERE vertragsid = "+value.getVertragid();
        db.connect();
        try{
            db.executeUpdate(createsql);
            ResultSet rs = db.executeQuery(selectsql);
            rueckgabe = convertRsToRueckgabe(rs);
            db.closeConnection();
        }catch(SQLException e){
            System.err.println("Fehler Create Rueckgabe");
            e.printStackTrace();
        }


        return rueckgabe;
    }

    @Override
    public Rueckgabe read(long id) {
        String selectsql = "SELECT * FROM rückgabe WHERE vertragsid = " + id;

        db.connect();
        try{
            ResultSet rs = db.executeQuery(selectsql);
            rueckgabe = convertRsToRueckgabe(rs);

            db.closeConnection();
        }catch(SQLException e){
            System.err.println("Fehler Read Rueckgabe");
            e.printStackTrace();
        }

        return rueckgabe;
    }

    @Override
    public Rueckgabe update(Rueckgabe value) {

        String updatesql = "UPDATE SET mitarbeiterid = "+value.getMitarbeiter().getId()+", datum = "+value.getDatum()+
                "WHERE vertragsid = "+value.getVertragid();
        String selectsql = "SELECT * FROM vertragsid = "+value.getVertragid();
        db.connect();
        try{
            db.executeUpdate(updatesql);
        }catch(SQLException e){
            System.err.println("Fehler Update Rueckgabe");
            e.printStackTrace();
        }

        return rueckgabe;
    }

    @Override
    public boolean delete(Rueckgabe value) {

        String deletesql ="DELETE * FROM rueckgabe WHERE vertragsid = "+value.getVertragid();

        db.connect();
        try{
            db.executeUpdate(deletesql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }


    private Rueckgabe convertRsToRueckgabe(ResultSet rs) throws SQLException {
        List<Rueckgabe> result = new ArrayList<Rueckgabe>();
        while(rs.next()){
            rueckgabe = new Rueckgabe();
            rueckgabe.setDatum(new Date(rs.getLong("datum")));
            rueckgabe.setMitarbeiter(mitarbeiterDAO.read(rs.getLong("mitarbeiterid")));
            rueckgabe.setVertragid(rs.getLong("vertragsid"));

        }
        return rueckgabe;
    }




}
