package de.anves.controller.dao;


/**
import de.anves.Mitarbeiter;
import de.anves.Vertrag;
import de.anves.controller.db.DBController;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VertragDAO implements CRUDInterface<Vertrag> {

    private DBController db = DBController.getInstance();
    private Vertrag vertrag = new Vertrag();
    private List<Vertrag> result = new ArrayList<Vertrag>();
    private MitarbeiterDAO mitarbeiterdao = MitarbeiterDAO.getInstance();
    private TarifDAO tarifdao = TarifDAO.getInstance();
    private VertragDAO instance;

    public VertragDAO getInstance() {
        if(instance == null) {
            instance = new VertragDAO();
        }
        return instance;
    }

    private VertragDAO(){}


    /**
     * Inserts the Vertrag object in to the Database
     * @author Leon
     * @version 1.0
     * @return the created Vertrag object in DB
     */
/*
    @Override
    public Vertrag create(Vertrag value) {
        String createsql ="INSERT INTO vertrag (mitarbeiterid, tarifid) VALUES ("+value.getMitarbeiter().getId()+
                ", "+value.getTarif().getTarifNummer()+")";
        String selectsql ="SELECT * FROM vertrag WHERE vertrags id IN (SELECT MAX(vertragsid) in vertrag";
        db.connect();
        try {
            db.executeUpdate(createsql);
            ResultSet rs = db.executeQuery(selectsql);

            result = convertRsToVertrag(rs);
            db.closeConnection();
        }catch(SQLException e){
            System.err.println("Fehler VertragDAO create");
            e.printStackTrace();
        }



        return result.get(0);
    }

    /**
     * @author Leon
     * @return the Vertrag object from DB
     */
    /*
    public Vertrag read(long id){
        String selectsql ="SELECT * FROM vertrag WHERE vertragsid = "+id;

        db.connect();
        try{
            ResultSet rs = db.executeQuery("selectsql");
            result = convertRsToVertrag(rs);

            db.closeConnection();
        }catch(SQLException e){
            System.err.println("Fehler VertragDAO read");
            e.printStackTrace();

        }
        return result.get(0);
    }

    /**
     *update allows to edit the "mitarbeiterid" and the "tarifid"in the vertrag table
     *@author: Leon
     *@version: 1.0
     */
    /*
    @Override
    public Vertrag update(Vertrag value) {
        String updatesql = "UPDATE vertrag SET mitarbeiterid = "+value.getMitarbeiter().getId()+
                ", tarifid ="+value.getTarif().getTarifNummer()+" WHERE vertragsid = "+value.getId();
        String selectsql = "SELECT * FROM vertrag WHERE vertragsid = "+vertrag.getId();
        try {
            db.connect();
            db.executeUpdate(updatesql);

            ResultSet rs = db.executeQuery(selectsql);
            result = convertRsToVertrag(rs);

            db.closeConnection();
        }catch(SQLException e){
            System.err.println("Fehler VertragDAO update");
            e.printStackTrace();
        }
        return result.get(0);
    }

    /**
     * author Leon
     * @param value: the Vertrag object you want to delete in DB
     * @return a boolean to show if it worked or not
     */
/*
    @Override
    public boolean delete(Vertrag value) {

        String deletesql ="DELETE FROM vertrag WHERE vertragsid = "+value.getId();
        String selectsql ="SELECT * FROM vertrag WHERE vertragsid = "+value.getId();

        db.connect();
        try{
            db.executeUpdate(deletesql);
            ResultSet rs = db.executeQuery(selectsql);
        }catch(SQLException e){
            System.err.println("Fehler VertragDAO delete");
            e.printStackTrace();
        }
        if(result.isEmpty()){
            return true;
        }
        return false;
    }

    public List<Vertrag> convertRsToVertrag(ResultSet rs) throws SQLException{
        List<Vertrag> result = new ArrayList<Vertrag>();

        while(rs.next()){

            vertrag = new Vertrag();
            vertrag.setId(rs.getLong("vertragsid"));
            vertrag.setMitarbeiter(mitarbeiterdao.read(rs.getLong("mitarbeiterid")));
            vertrag.setTarif(tarifdao.read(rs.getLong("tarifid")));
            result.add(vertrag);
        }

        return result;
    }
}
*/