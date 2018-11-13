
package de.anves.controller.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import de.anves.Anhaenger;
import de.anves.AnhaengerTypEnum;
import de.anves.Reservierung;
import de.anves.controller.db.DBController;

/**
 * @author Konstantin
 * @version 0.2 Fertig uncommented/untested
 */
public class AnhaengerDAO implements CRUDInterface<Anhaenger> {
    private DBController db = DBController.getInstance();

    @Override
    public Anhaenger create(Anhaenger value) {
        ArrayList<Anhaenger> result;
        String createsql = "INSERT INTO anhänger(AnhängerID, AnhängerTYP, Kennzeichen, nächsteHU) VALUES " + "("
                + value.getId() + ", "
                //Anhängettyp als INT an die datenbank übergeben
                + value.getAnhaengerTyp().ordinal() + ", '"
                + value.getKennzeichen() + "',"
                + value.getNaechsteHU().getTime() + " )";
        String selectsql = "SELECT * FROM anhänger WHERE AnhängerID IN (SELECT max(AnhängerID) FROM Anhänger)";
        db.connect();
        try {
            db.executeUpdate(createsql);
            ResultSet rs = db.executeQuery(selectsql);

            result = convertRStoAnhaenger(rs);
            if (!result.isEmpty()) {
                return result.get(0);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (SQLException e) {
                System.out.println(e + " in line " + this.getClass().getSimpleName());
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public Anhaenger read(long id) {
        ArrayList<Anhaenger> result;
        String selectsql = "SELECT * FROM anhänger WHERE AnhängerID = " + id;
        db.connect();
        try {
            ResultSet rs = db.executeQuery(selectsql);

            result = convertRStoAnhaenger(rs);
            if (!result.isEmpty()) {
                return result.get(0);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (SQLException e) {
                System.out.println(e + " in line " + this.getClass().getSimpleName());
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public Anhaenger update(Anhaenger value) {
        ArrayList<Anhaenger> result;
        String updatesql = "UPDATE anhänger SET AnhängerID = "
                + value.getId() + ", Kennzeichen = '"
                + value.getKennzeichen() + "' , AnhängerTYP = " +
                +value.getAnhaengerTyp().ordinal() + " , nächsteHU = "
                + value.getNaechsteHU().getTime();
        String selectsql = "SELECT * FROM anhänger WHERE AnhängerID  = " + value.getId();
        db.connect();
        try {
            db.executeUpdate(updatesql);
            ResultSet rs = db.executeQuery(selectsql);

            result = convertRStoAnhaenger(rs);
            if (!result.isEmpty()) {
                return result.get(0);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (SQLException e) {
                System.out.println(e + " in line " + this.getClass().getSimpleName());
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public boolean delete(Anhaenger value) {
        String deletesql = "DELETE FROM anhänger WHERE AnhängerID = " + value.getId();
        db.connect();
        try {
            int result = db.executeUpdate(deletesql);
            if (result == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e + " in line " + this.getClass().getSimpleName());
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (SQLException e) {
                System.out.println(e + " in line " + this.getClass().getSimpleName());
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     *
     * @param start Start des Zeitraum
     * @param end Ende des Zeitraums
     * @param anhaengerTyp Typ des Anhängers
     * @return Liste verfügbarer Anhänger
     */

    private ArrayList<Anhaenger> readList(Date start, Date end, AnhaengerTypEnum anhaengerTyp){
        ArrayList<Anhaenger> result;
        String selectsql = "SELECT * anhänger WHERE anhängetyp = "+anhaengerTyp.ordinal()+" AND anhaengerid NOT IN " +
                "( SELECT anhaengerid FROM `reservierung` WHERE bis < "+start+" OR von > "+end+")";
        db.connect();
        try {
            ResultSet rs = db.executeQuery(selectsql);

            result = convertRStoAnhaenger(rs);
            if (!result.isEmpty()) {
                return result;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        } finally {
            try {
                db.closeConnection();
            } catch (SQLException e) {
                System.out.println(e + " in line " + this.getClass().getSimpleName());
                e.printStackTrace();
            }

        }
        return null;
    }
    private ArrayList<Anhaenger> convertRStoAnhaenger(ResultSet rs) throws SQLException {
        ArrayList<Anhaenger> result = new ArrayList<Anhaenger>();

        while (rs.next()) {
            Anhaenger anhaenger = new Anhaenger();
            //Enum zum INT konvertieren
            anhaenger.setAnhaengerTyp((AnhaengerTypEnum.values()[rs.getInt("AnhängerTYP")]));
            anhaenger.setId(rs.getLong("AnhängerID"));
            anhaenger.setKennzeichen(rs.getString("Kennzeichen"));
            anhaenger.setNaechsteHU(new Date(rs.getLong("nächsteHU")));
            result.add(anhaenger);
        }

        return result;
    }

}
