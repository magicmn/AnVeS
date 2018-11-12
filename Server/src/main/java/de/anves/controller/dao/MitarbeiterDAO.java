package de.anves.controller.dao;

import de.anves.Mitarbeiter;
import de.anves.controller.db.DBController;

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

        //String sqlStatement = "INSERT ";
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
}
