package de.anves.controller.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


import de.anves.Schadensbericht;
import de.anves.controller.db.DBController;

public class SchadensberichtDAO implements CRUDInterface<Schadensbericht>
{
    //Instance des DBControllers holen und als globale Variable speichern, um
    //	unnötige Methodenaufrufe zu vermeiden
    DBController db = DBController.getInstance();
    /**
     * persistiert einen Schadensbericht in der Datenbank
     * @param value	: Schadensberichtobjekt, dass persistiert werden soll
     * @return		: gibt den erstellten Schadensbericht zurück, wie er
     * 				  in der Datenbank steht
     */
    @Override
    public Schadensbericht create(Schadensbericht value) {
        //Rückgabevariable initialisieren
        Schadensbericht result = null;

        //SQL-Statements bauen
        String insertSQL = "INSERT INTO schadensbericht(anhängerid, datum, text, anmerkungk) VALUES("
                + value.getId() + ", "
                + value.getDatum().getTime() + ", '"
                + value.getBeschreibung() + "', '"
                + value.getAnmerkungKunde() + "');";

        String selectSQL = "SELECT * FROM schadensbericht WHERE anhängerid=+"
                + value.getId() + " AND datum="
                + value.getDatum().getTime() + ";";

        //Datenbankverbindung öffnen
        db.connect();

        try {
            //Datensatz einfügen
            db.executeUpdate(insertSQL);

            //eingefügten Datensatz auslesen
            ResultSet rs = db.executeQuery(selectSQL);

            //Resultset convertieren
            result = convertRsToSchadensbericht(rs).get(0);

        } catch (SQLException e) {
            System.err.println("UserDao: CreatUser: Fehler");
            e.printStackTrace();
        } finally {
            try {
                //Verbindung zur Datenbank schließen
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Rückgabe des Ergebnisses
        return result;
    }

    /**
     * Muss überschrieben werden, da es eine Methode aus dem Interface ist, kann
     * 	jedoch für dieses DAO nicht gebraucht werden und schmeist eine
     * 	UnsupportedOperationException
     * @throws	: wirft !immer! eine UnsupportedOperationException
     */
    @Deprecated
    @Override
    public Schadensbericht read(long id) {
        System.err.println("SchadensverichtDAO: read(long): Not implemented");
        throw new UnsupportedOperationException();
    }

    /**
     * Liest einen einzelnen Schadensbericht aus
     * @param id	:	id, des Anhängers, dessen Schadensbericht abgefragt werden soll
     * @param datum :	Datum, an dem der Schadensbericht erzeugt wurde
     * @return	: Schadensbericht, der zu der übergebenen Anhängerid und
     * 			  dem Übergebenen Datum passt
     */
    public Schadensbericht read(long id, Date datum) {
        //Rückgabevariable initialisieren
        Schadensbericht result = null;

        //Select SQL-Statement bauen
        String selectSQL = "SELECT * FROM schadensbericht WHERE anhängerid=+"
                + id + " AND datum="
                + datum.getTime() + ";";

        //Datenbankverbindung öffnen
        db.connect();

        try {
            //Datensatz auslesen
            ResultSet rs = db.executeQuery(selectSQL);

            //Resultset konvertieren
            result = convertRsToSchadensbericht(rs).get(0);

        } catch (SQLException e) {
            System.err.println("UserDao: CreatUser: Fehler");
            e.printStackTrace();
        } finally {
            try {
                //Verbindung zur Datenbank schließen
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Rückgabe des Ergebnisses
        return result;
    }

    @Override
    public Schadensbericht update(Schadensbericht value) {
        //Rückgabevariable initialisieren
        Schadensbericht result = null;

        //Update-SQL
        String updatesql = "UPDATE schadensbericht SET "
                + "anhängerid=" + value.getId() + ","
                + "datum=" + value.getDatum().getTime() + ","
                + "text='" + value.getBeschreibung() + "', "
                + "anmerkungk='" + value.getAnmerkungKunde() + "' "
                + "WHERE anhängerid =" + value.getId();

        //Select-SQL um aktualisierten Datensatz aus der DB zu holen
        String selectsql = "SELECT * FROM schadensbericht WHERE anhängerid = " + value.getId();

        //Verbindung zur Datenbank aufbauen
        db.connect();

        try {
            //Update ausführen
            db.executeUpdate(updatesql);
            //aktualisierten Datensatz lesen
            ResultSet rs = db.executeQuery(selectsql);
            result = convertRsToSchadensbericht(rs).get(0);

            return result;
        } catch (SQLException e) {
            System.err.println("SchadensberichtDao: UpdateSchadensbericht: Fehler");
            e.printStackTrace();
        } finally {
            try {
                //Datenbankverbindung schließen
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Löscht einen einzelnen Schadensbericht aus der Datenbank
     * @param value	: Schadensbericht, der aus der DB gelöscht werden soll
     * 			!Achtung!: Schadensbericht wird vor dem Löschen nur auf
     * 					   AnhängerID und Datum geprüft, nicht auf den Inhlat
     * @return : boolischer Wert, ob das löschen erfolgreich war oder nicht
     */
    @Override
    public boolean delete(Schadensbericht value) {
        //Delete-SQL
        String deletesql = "DELETE FROM schadensbericht WHERE anhängerid=" + value.getId()
                + " AND datum= " + value.getDatum().getTime() + ";";

        //Datenbankverbindung aufbauen
        db.connect();

        try {
            //Delete auf der DB ausführen
            if (db.executeUpdate(deletesql) == 0)
            {
                return true;	//falls erfolgreich gelöscht, true zurückgeben
            }

        } catch (SQLException e) {
            System.err.println("SchadensberichtDao: DeleteSchadensbericht: Fehler");
            e.printStackTrace();
        } finally {
            try {
                //Datenbankverbindung schließen
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;	//Wenn irgendetwas nicht funktioniert hat false zurückgeben
    }

    /**
     * Gibt alle Schadensberichte eines Anhängers zurück
     * @param 	anhängerid:	Anhänger des Anhängers,
     * 						dessen Schadensberichte zurückgegeben werden
     * @return	ArrayListe mit den Schadensberichten
     */
    public ArrayList<Schadensbericht> readList(long anhängerid)
    {
        //SQL-Statement aufbauen
        String listssql = "SELECT * FROM schadensbericht WHERE anhängerid="
                + anhängerid;

        //Verbindung zur DB eingehen
        db.connect();
        try {
            //Ausführen des
            ResultSet rs = db.executeQuery(listssql);
            //Ergebnisse formatieren
            ArrayList<Schadensbericht> result = convertRsToSchadensbericht(rs);

            return result;
        } catch (SQLException e) {
            System.err.println("SchadensberichtDAO: readList: Fehler");
            e.printStackTrace();
        } finally {
            try {
                //Datenbakverbindung schließen
                db.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Wertet das Resultset aus, dass übergeben wird und
     *  gibt eine Liste mit Schadensberichten zurück
     * @param rs	: Resultset, dass ausgewertet werden soll
     * @return		: ArrayList<Schadensbericht> mit Schadensberichten, die
     * 					in der Datenbank hinterlegt sind und zurückgegeben wurden
     * @throws SQLException
     */
    private ArrayList<Schadensbericht> convertRsToSchadensbericht(ResultSet rs) throws SQLException {
        //Ergebnisvariable initialisieren
        ArrayList<Schadensbericht> result = new ArrayList<Schadensbericht>();

        //Abarbeitung des ResultSets
        while(rs.next()) {
            Schadensbericht schadensbericht = new Schadensbericht();

            //Mappen der einzelnen Felder
            schadensbericht.setId(rs.getLong("anhängerid"));
            schadensbericht.setBeschreibung(rs.getString("text"));
            schadensbericht.setDatum(new Date(rs.getLong("datum")));
            schadensbericht.setAnmerkungKunde(rs.getString("AnmerkungK"));
            result.add(schadensbericht);
        }
        //Rückgabe des Ergebnisses
        return result;
    }
}