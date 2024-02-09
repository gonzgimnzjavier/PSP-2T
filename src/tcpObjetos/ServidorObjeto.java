package tcpObjetos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorObjeto {

	static Scanner sc;
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		String host = "localhost";
		int puerto = 6000; // puerto remoto
		System.out.println("PROGRAMA CLIENTE INICIADO....");
		Socket cliente = new Socket(host, puerto);

		// Flujo de entrada para objetos
		ObjectInputStream perEnt = new ObjectInputStream(cliente.getInputStream());

		// Se recibe un objeto
		Persona dato = (Persona) perEnt.readObject();
		// recibo objeto
		System.out.println("Recibo: " + dato.getNombre() + "*" + dato.getEdad());

		// Modifico el objeto
		dato.setNombre("Juan Ramos");
		dato.setEdad(22);

		// FLUJO DE salida para objetos
		ObjectOutputStream perSal = new ObjectOutputStream(cliente.getOutputStream());

		// Se envía el objeto
		perSal.writeObject(dato);
		System.out.println("Envio: " + dato.getNombre() + "*" + dato.getEdad());

		// CERRAR STREAMS Y SOCKETS

		perEnt.close();
		perSal.close();
		cliente.close();
	} // Fin de ServidorObjeto

	public static void modificarPersona(Persona p) {
		System.out.println("modifica datos de la persona recibida");
		System.out.println(p.toString());
		System.out.println("modificar nombre: ");
		String s=sc.nextLine();
		System.out.println("modificar  la edad");
		int e = sc.nextInt();
		p.setEdad(e);
		p.setNombre(s);


	}
}
