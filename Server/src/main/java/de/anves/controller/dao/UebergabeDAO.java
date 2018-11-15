package de.anves.controller.dao;

import de.anves.Uebergabe;
import de.anves.Uebergabe;
import de.anves.controller.db.DBController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UebergabeDAO implements CRUDInterface<Uebergabe> {

    private DBController db = DBController.getInstance();
    private Uebergabe uebergabe = new Uebergabe();
    private MitarbeiterDAO mitarbeiterDAO = new MitarbeiterDAO();


    @Override
    public Uebergabe create(Uebergabe value) {

        String createsql = "INSERT INTO rückgabe (vertragsid, mitarbeiterid, datum) VALUES ("
                +value.getVertragid()+", "+value.getMitarbeiter().getId()+", "+value.getDatum().getTime();
        String selectsql = "SELECT * FROM rückgabe WHERE vertragsid = "+value.getVertragid();
        db.connect();
        try{
            db.executeUpdate(createsql);
            ResultSet rs = db.executeQuery(selectsql);
            uebergabe = convertRsToUebergabe(rs);
            db.closeConnection();
        }catch(SQLException e){
            System.err.println("Fehler Create Uebergabe");
            e.printStackTrace();
        }


        return uebergabe;
    }

    @Override
    public Uebergabe read(long id) {
        String selectsql = "SELECT * FROM rückgabe WHERE vertragsid = " + id;

        db.connect();
        try{
            ResultSet rs = db.executeQuery(selectsql);
            uebergabe = convertRsToUebergabe(rs);

            db.closeConnection();
        }catch(SQLException e){
            System.err.println("Fehler Read Uebergabe");
            e.printStackTrace();
        }

        return uebergabe;
    }

    @Override
    public Uebergabe update(Uebergabe value) {

        String updatesql = "UPDATE SET mitarbeiterid = "+value.getMitarbeiter().getId()+", datum = "+value.getDatum()+
                "WHERE vertragsid = "+value.getVertragid();
        String selectsql = "SELECT * FROM vertragsid = "+value.getVertragid();
        db.connect();
        try{
            db.executeUpdate(updatesql);
        }catch(SQLException e){
            System.err.println("Fehler Update Uebergabe");
            e.printStackTrace();
        }

        return uebergabe;
    }

    @Override
    public boolean delete(Uebergabe value) {

        String deletesql ="DELETE * FROM Uebergabe WHERE vertragsid = "+value.getVertragid();

        db.connect();
        try{
            db.executeUpdate(deletesql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }


    private Uebergabe convertRsToUebergabe(ResultSet rs) throws SQLException {
        List<Uebergabe> result = new ArrayList<Uebergabe>();
        while(rs.next()){
            uebergabe = new Uebergabe();
            uebergabe.setDatum(new Date(rs.getLong("datum")));
            uebergabe.setMitarbeiter(mitarbeiterDAO.read(rs.getLong("mitarbeiterid")));
            uebergabe.setVertragid(rs.getLong("vertragsid"));

        }
        return uebergabe;
    }




}
