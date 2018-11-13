package de.anves.view.Application;

import de.anves.model.transfer.TransferObject;

import java.io.*;
import java.net.Socket;

public class ClientController {

    private static final String SERVERADRESS ="172.25.13.231";
    private static final int PORT = 50000;
    private Socket socket;
    private OutputStream outStream;
    private InputStream inStream;
    private TransferObject transObject;

    public ClientController(){}

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

}
