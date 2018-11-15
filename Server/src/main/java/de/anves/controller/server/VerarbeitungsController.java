package de.anves.controller.server;

import de.anves.Anhaenger;
import de.anves.Kunde;
import de.anves.Reservierung;
import de.anves.controller.dao.*;
import de.anves.model.transfer.TransferObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class VerarbeitungsController extends Thread {

	private static LinkedList<Socket> queue = new LinkedList<Socket>();
	private ObjectOutputStream outStream;
	private ObjectInputStream inStream;

	//DAOs
	private AnhaengerDAO anhaengerDAO = new AnhaengerDAO();
	private KundeDAO kundeDAO = new KundeDAO();
	private MitarbeiterDAO mitarbeiterDAO = new MitarbeiterDAO();
	private ReservierungDAO reservierungDAO = new ReservierungDAO();
	private RueckgabeDAO rueckgabeDAO = new RueckgabeDAO();
	private SchadensberichtDAO schadensberichtDAO = new SchadensberichtDAO();
	private TarifDAO tarifDAO = new TarifDAO();
	private UebergabeDAO uebergabeDAO = new UebergabeDAO();
	private VertragDAO vertragDAO = new VertragDAO();

	
	private static VerarbeitungsController instance;
	
	public static VerarbeitungsController getInstance() {
		if(instance == null) {
			instance = new VerarbeitungsController();
		}
		return instance;
	}
	
	private VerarbeitungsController() {}
	
	public void run() {
		while(true) {
			if(!queue.isEmpty()) {
				Socket socket = queue.removeFirst();
				try {
					System.out.println("Verarbeite: " + socket.getLocalAddress());
					verarbeite(socket);
				} catch (IOException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					System.err.println("Verarbeitung fehlgeschlagen!");
					e.printStackTrace();
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				try {
					System.out.print(".");
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void verarbeite(Socket socket) throws IOException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		outStream = new ObjectOutputStream(socket.getOutputStream());
		inStream = new ObjectInputStream(socket.getInputStream());
		
		TransferObject tobj = (TransferObject) inStream.readObject();
		
		String methodenname = "get" + tobj.getAction() + tobj.getObjectClassName();
		System.out.println(methodenname + " wird aufgerufen");
		Method methode = this.getClass().getMethod(methodenname, TransferObject.class);
		methode.invoke(this, tobj);
		
	}
	
	
	//Beispiel Methode zum Nachimplementieren
	public void getREADLISTChatroom(TransferObject obj) throws IOException {
		
		//messageDAO = new MessageDAO();
	//	Chatroom chatroom = (Chatroom) obj.getObject();
	//	List<Message> messageList = messageDAO.getAllChatMessanges(chatroom.getId());
	//	outStream.writeObject(messageList);
	//	outStream.flush();
	}
		
	public void getREADLISTReservierung(TransferObject obj)throws IOException{
		List<Anhaenger> anhaengerList = new ArrayList<Anhaenger>();
		anhaengerDAO = new AnhaengerDAO();

		Reservierung reservierung = (Reservierung) obj.getObject();
		anhaengerList = anhaengerDAO.readList(reservierung.getVertragBeginn(), reservierung.getVertragsEnde(),
				reservierung.getAnhaenger().getAnhaengerTyp());

		outStream.writeObject(anhaengerList);
		outStream.flush();

	}

	/**
	 * liest eine Liste mit Kunden aus der Datenbank
	 *
	 * @param tobj	: transferobjekt, das übergeben wurde
	 *
	 * @author Joern Felling
	 */
	public void getREADLISTKunde(TransferObject tobj) throws IOException {
		List<Kunde> result = new ArrayList<>();	//Liste für die Rückgabe initialisieren

		//Wenn der Nachname im Übergeben tobj gesetzt wurde wird mit Nachname und gebdat gesucht
		if(((Kunde)tobj.getObject()).getNachname() != null){
			result = kundeDAO.searchNachname((Kunde)tobj.getObject());
		}else if(((Kunde)tobj.getObject()).getVorname() != null){		//Wenn Vorname gesetzt ist wird mit
			result = kundeDAO.searchVorname((Kunde)tobj.getObject());	//	Vorname und Gebdat gesucht
		}

		//Ergebnis an Client zurückgeben
		outStream.writeObject(result);
		outStream.flush();
	}

	/**
	 * liest einen Kunden anhand der ID aus der Datenbank
	 *
	 * @param tobj	: transferobjekt, das übergeben wurde
	 *
	 * @author Joern Felling
	 */
	public void getREADKunde(TransferObject tobj) throws IOException {
		//Kunden aus Datenbank selektieren
		Kunde result = kundeDAO.read(((Kunde)tobj.getObject()).getId());

		//Ergebnis an Client zurückgeben
		outStream.writeObject(result);
		outStream.flush();
	}
	
	
	public LinkedList<Socket> getQueue(){
		return queue;
	}
	
}
