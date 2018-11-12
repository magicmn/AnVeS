package de.anves.controller.dao;

import de.anves.Mitarbeiter;
import de.anves.controller.db.DBController;

public class Start {

    public static void main(String[] args) {
        MitarbeiterDAO mitarbeiterDAO = MitarbeiterDAO.getInstance();
        Mitarbeiter mitarbeiter = mitarbeiterDAO.getLastEntry();
        System.out.println(mitarbeiter.getNachname());

    }

}
