package de.anves.controller.dao;

import de.anves.Mitarbeiter;
import de.anves.controller.db.DBController;

public class Start {

    public static void main(String[] args) {

        /**
         * Nur zum testen
         */
        MitarbeiterDAO mitarbeiterDAO = new MitarbeiterDAO();

        /**
         * mitarbeiter = Letzter Mitarbeiter
         * mitarbeiter1 = Mitarbeiter mit der ID 1
         * mitarbeiter2 = Create new Mitarbeiter
         */
        Mitarbeiter mitarbeiter = mitarbeiterDAO.getLastEntry();
        Mitarbeiter mitarbeiter1 = mitarbeiterDAO.read(1);

        Mitarbeiter mitarbeiter2 = new Mitarbeiter();
        mitarbeiter2.setNachname("NachnameMann");
        mitarbeiter2.setVorname("MännlicherVorname");
        mitarbeiter2.setGeschlecht(true);
        //mitarbeiterDAO.create(mitarbeiter2);

        Mitarbeiter mitarbeiter3 = new Mitarbeiter();
        mitarbeiter3.setNachname("NachnameFrau");
        mitarbeiter3.setVorname("WeiblicherVorname");
        mitarbeiter3.setGeschlecht(false);
        //mitarbeiterDAO.create(mitarbeiter3);

        Mitarbeiter mitarbeiterToUpdate = new Mitarbeiter();
        System.out.println("Mitarbeiter vor dem Update: " + mitarbeiterDAO.read(4).getVorname());
        mitarbeiterToUpdate.setId(4);
        mitarbeiterToUpdate.setVorname("Jetzt Frau");
        mitarbeiterToUpdate.setNachname("Frau");
        mitarbeiterToUpdate.setGeschlecht(false);
        mitarbeiterDAO.update(mitarbeiterToUpdate);
        System.out.println("Mitarbeiter nach dem Update: " + mitarbeiterDAO.read(4).getVorname());

        System.out.println("getLastEntry: " + mitarbeiter.getNachname() + " mit der ID: " + mitarbeiter.getId());
        System.out.println("read(1): IST: " + mitarbeiter1.getNachname() + " SOLL: Hans");

        Mitarbeiter mitarbeiterToDelete = new Mitarbeiter();
        mitarbeiterToDelete.setId(29);
        mitarbeiterDAO.delete(mitarbeiterToDelete);

    }

}
