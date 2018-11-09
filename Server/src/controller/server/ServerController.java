package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

	private static final int PORT = 50000;
	private ServerSocket serverSocket;

	public void work() throws IOException {
		serverSocket = new ServerSocket(PORT);
		
		while(true) {
			System.out.println("Server waiting");
			Socket socket = serverSocket.accept();
			System.out.println("zur Verarbeitung");
			VerarbeitungsController.getInstance().getQueue().addLast(socket);
		}
	}
	
	public static void main(String[] args) {
		VerarbeitungsController.getInstance().start();
		try {
			new ServerController().work();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
