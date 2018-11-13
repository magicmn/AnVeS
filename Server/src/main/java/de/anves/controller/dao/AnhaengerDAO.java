
package de.anves.controller.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import de.anves.Anhaenger;
import de.anves.AnhaengerTypEnum;
import de.anves.controller.db.DBController;

/**
 * @author Konstantin
 * @version 0.1 Entwurf
 * @version 0.2 Fertig uncommented/untested
 *
 */
public class AnhaengerDAO implements CRUDInterface<Anhaenger>{
    private DBController db = DBController.getInstance();
    public static void main(String[] args) {
/*        AnhaengerDAO dao = new AnhaengerDAO();
        Anhaenger anhaenger = new Anhaenger();
        anhaenger.setAnhaengerTyp(AnhaengerTypEnum.EINACHSER);
        anhaenger.setId(13);
        anhaenger.setNaechsteHU(new Date());
        anhaenger.setKennzeichen();*/


    }

    @Override
    public Anhaenger create(Anhaenger value) {
        ArrayList<Anhaenger> result;
        String createsql = "INSERT INTO anhänger(AnhängerID, AnhängerTYP, Kennzeichen, nächsteHU) VALUES "+ "("
                + value.getId() + " '"
                //Anhängettyp als INT an die datenbank übergeben
                + value.getAnhaengerTyp().ordinal()+ " '"
                + value.getKennzeichen() + "' "
                + value.getNaechsteHU() + " )";
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
        String selectsql = "SELECT * FROM anhänger WHERE AnhängerID = "+ id;
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
        String updatesql = "UPDATE user SET AnhängerID = "
                + value.getId() + ", Kennzeichen = '"
                + value.getKennzeichen() + "' , AnhängerTYP = " +
                + value.getAnhaengerTyp().ordinal() + " , nächsteHU = "
                + value.getNaechsteHU().getTime();
        String selectsql = "SELECT * FROM anhänger WHERE AnhängerID  = "+ value.getId();
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

    private ArrayList<Anhaenger> convertRStoAnhaenger(ResultSet rs) throws SQLException {
        ArrayList<Anhaenger> result = new ArrayList<Anhaenger>();

        while (rs.next()) {
            Anhaenger anhaenger = new Anhaenger();
            //Enum zum INT konvertieren
            anhaenger.setAnhaengerTyp((AnhaengerTypEnum.values()[rs.getInt("AnhängerTYP")]));
            anhaenger.setId(rs.getLong("AnhängerID"));
            anhaenger.setKennzeichen(rs.getString("AnhängerTYP"));
            anhaenger.setNaechsteHU(new Date(rs.getLong("naechsteHU")));
        }

        return result;
    }

}
