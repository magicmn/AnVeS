package de.anves.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.anves.Anhaenger;

import de.anves.AnhaengerTypEnum;
import de.anves.Reservierung;
import de.anves.model.transfer.TransferAction;
import de.anves.model.transfer.TransferObject;


import java.io.*;
import java.net.Socket;

public class ClientController {

    /**
     * @author Leon
     * @param SERVERADRESS ist die IP Adresse des Servers
     */

    private static final String SERVERADRESS ="172.25.13.231";
    private static final int PORT = 50000;
    private Socket socket;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private TransferObject transObject;

    public ClientController(){}

    /**
     * stellt die Verbindung zum Server her und baut die Streams auf
     *
     * @author Leon
     */
    private void connectToServer() {
        try {
            System.out.println("Try to connect");
            socket = new Socket(SERVERADRESS, PORT);
            System.out.println("conncetion status: " + socket.isConnected());
            outStream = new ObjectOutputStream(socket.getOutputStream());
            inStream = new ObjectInputStream(socket.getInputStream());
        }catch(IOException e){
            System.err.println("Fehler ClientController connectToServer connection failed");
            e.printStackTrace();
        }
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
        public List<Anhaenger> sucheAnhaenger(AnhaengerTypEnum anhaengerTyp, Date anfang, Date ende){
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

}
