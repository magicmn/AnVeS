package de.anves.controller.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.LinkedList;

import de.anves.model.transfer.TransferObject;

public class VerarbeitungsController extends Thread {

	private static LinkedList<Socket> queue = new LinkedList<Socket>();
	private ObjectOutputStream outStream;
	private ObjectInputStream inStream;

	
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
		

 
 
 
	
	
	public LinkedList<Socket> getQueue(){
		return queue;
	}
	
}
