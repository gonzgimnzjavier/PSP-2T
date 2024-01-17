package TCPin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serv1 {

	public static void main(String[] args) throws IOException {
		int Puerto = 6000;// Puerto
		ServerSocket Servidor = null;
		Servidor = new ServerSocket(Puerto);
		System.out.println("Escuchando en " + Servidor.getLocalPort());
		Socket clientel = Servidor.accept();// esperando a un cliente
		// realizar acciones con clientel
		Socket cliente2 = Servidor.accept();// esperando a otro cliente
		// realizar acciones con cliente2
		Servidor.close(); // cierro socket servidor
	}// fin de main
}// fin de SocketServidor
