package tcpObjetos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClienteObjeto {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		int puerto = 6000; // Puerto
		ServerSocket servidor = new ServerSocket(puerto);
		System.out.println("Esperando al cliente.....");
		Socket cliente = servidor.accept();

		// Se prepara un flujo de salida para objetos
		ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream());

		// Se prepara un objeto y se envía
		// Persona per = new Persona("Juan", 20);
		

		// Se obtiene un stream para leer objetos
		ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
		Persona dato = (Persona) inObjeto.readObject();
		System.out.println("Recibo: " + dato.getNombre() + dato.getEdad());

		// CERRAR STREAMS Y SOCKETS
		outObjeto.close();
		inObjeto.close();
		cliente.close();
		servidor.close();
	} // Fin de main
	
	public static Persona crearPersona() {
		Scanner sc = new Scanner(System.in);
		System.out.println("introduce datos de la nueva persona");
		Persona p = new Persona();
		System.out.println("Dame el nombre");
		String s=sc.nextLine();
		System.out.println("Dame la edad");
		int e = sc.nextInt();
		p.setEdad(e);
		p.setNombre(s);
		
		return p;

	}
} // Fin de ClienteObjeto
