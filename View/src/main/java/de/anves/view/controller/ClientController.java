package de.anves.view.controller;

import de.anves.Anhaenger;
import de.anves.AnhaengerTyp;
import de.anves.Kunde;
import de.anves.Reservierung;
import de.anves.model.transfer.TransferAction;
import de.anves.model.transfer.TransferObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientController {

    /**
     * @author Leon
     * @param SERVERADRESS ist die IP Adresse des Servers
     */

    private static final String SERVERADRESS ="127.0.0.1";
    private static final int PORT = 50000;
    private Socket socket;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private TransferObject transObject;

    public ClientController(){}


    public List<Anhaenger> sucheAnhaenger(AnhaengerTyp anhaengerTyp, Date anfang, Date ende){
        Reservierung reservierung = new Reservierung();

        List<Anhaenger> anhaengerListe = new ArrayList<Anhaenger>();

        Anhaenger anhaenger = new Anhaenger();
        anhaenger.setAnhaengerTyp(anhaengerTyp);

        reservierung.setVertragBeginn(anfang);
        reservierung.setVertragsEnde(ende);
        reservierung.setAnhaenger(anhaenger);

        TransferObject transObject = new TransferObject(reservierung, TransferAction.READLIST);

        try{
            connectToServer();
            outStream.writeObject(transObject);
            outStream.flush();

            TransferObject resultObj = (TransferObject) inStream.readObject();
            anhaengerListe = (List<Anhaenger>) resultObj.getObject();

            closeConnection();

        }catch(IOException e){
            System.err.println("Fehler Stream sucheAnhaenger");
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            System.err.println("Fehler InputObject kann nicht zugeordnet werden");
            e.printStackTrace();
        }

        return anhaengerListe;
    }

    /**
     * Sucht Kunden in der Datenbank, nach übergebenene Parametern
     * @param id    : Kundenid, nachder gesucht werden soll
     * @return      : List<Kunde> mit Kunden aus der Datenbank die gefunden wurden
     *
     * @author Joern Felling
     */
    public Kunde kundenSuchenID(long id){
        Kunde result = null; //Rückgabeliste initialisieren

        try {
            //Verbindung zum Server aufbauen
            connectToServer();

            //Transferobjekt erstellen
            TransferObject tobj = new TransferObject(new Kunde().setId(id), TransferAction.READ);

            //Transferobjekt an Server schicken
            outStream.writeObject(tobj);
            outStream.close();

            //Ergebnis vom Server holen
            result = (Kunde)((TransferObject)inStream.readObject()).getObject();

            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * Sucht Kunden in der Datenbank, nach übergebenene Parametern
     * @param Nachname  : Nachname des Kunden der gesucht wird
     * @param gebDat    : Geburtsdatum des Kunden der gesucht wird
     * @return          : List<Kunde> mit Kunden aus der Datenbank die gefunden wurden
     *
     * @author Joern Felling
     */
    public List<Kunde> kundenSuchenNachname(String Nachname, String gebDat){
        List<Kunde> result = null; //Rückgabeliste initialisieren

        try {
            //Verbindung zum Server aufbauen
            connectToServer();

            //Transferobjekt erstellen
            TransferObject tobj = new TransferObject(new Kunde().setNachname(Nachname).setGeburtsdatum(gebDat), TransferAction.READLIST);

            //Transferobjekt an Server schicken
            outStream.writeObject(tobj);
            outStream.flush();

            //Ergebnis vom Server holen
            result = (List<Kunde>)((TransferObject)inStream.readObject()).getObject();

            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * Sucht Kunden in der Datenbank, nach übergebenene Parametern
     * @param Vorname   : Vorname des Kunden, der gesucht wird
     * @param gebDat    : Geburtsdatum des Kunden, der gesucht wird
     * @return          : List<Kunde> mit Kunden aus der Datenbank die gefunden wurden
     *
     * @author Joern Felling
     */
    public List<Kunde> kundenSuchenVorname(String Vorname, String gebDat){
        List<Kunde> result = null; //Rückgabeliste initialisieren

        try {
            //Verbindung zum Server aufbauen
            connectToServer();

            //Transferobjekt erstellen
            TransferObject tobj = new TransferObject(new Kunde().setVorname(Vorname).setGeburtsdatum(gebDat), TransferAction.READ);

            //Transferobjekt an Server schicken
            outStream.writeObject(tobj);
            outStream.flush();

            //Ergebnis vom Server holen
            result = (List<Kunde>)((TransferObject)inStream.readObject()).getObject();

            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * Methode die verbindung zum Server herstellt, ein TransferObject an diesen schickt und ein Objekt das vom Server
     * geschickt wurde zurück gibt.
     *
     * @param transferObject {@link TransferObject} für den Server.
     * @return Unbekanntes Objekt vom Server
     */
    private Object sendRequest(TransferObject transferObject) {
        Object serverObject = null;
        try {
            connectToServer();
            outStream.writeObject(transferObject);
            serverObject = inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return serverObject;
    }


    /**
     * stellt die Verbindung zum Server her und baut die Streams auf
     *
     * @author Leon
     */
    private void connectToServer() throws IOException {
        System.out.println("Try to connect");
        socket = new Socket(SERVERADRESS, PORT);
        System.out.println("conncetion status: " + socket.isConnected());
        outStream = new ObjectOutputStream(socket.getOutputStream());
        inStream = new ObjectInputStream(socket.getInputStream());
    }

    private void closeConnection(){
        try {
            if(outStream != null)outStream.close();
            if(inStream != null)inStream.close();
            if(socket != null)socket.close();
        } catch (IOException e) {
            System.err.println("Fehler ClientController closeConnection");
            e.printStackTrace();
        }

    }
}
