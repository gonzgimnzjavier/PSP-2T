package udpChat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientChat {

	public static void main(String[] args) throws Exception {
		try (Scanner sc = new Scanner(System.in); DatagramSocket clientSocket = new DatagramSocket()) { // Utiliza
																										// try-with-resources
																										// para el
																										// manejo																							// automático de
																										// recurso
			byte[] enviados = new byte[1024];
			byte[] recibidos = new byte[1024];

			// DATOS DEL SERVIDOR al que enviar mensaje
			InetAddress IPServidor = InetAddress.getLocalHost();
			int puerto = 9876;
			System.out.println("Cliente iniciado."); // Corregido para reflejar correctamente el lado del cliente

			// INTRODUCIR DATOS POR TECLADO
			System.out.print("Introduce mensaje: ");
			String cadena = sc.nextLine(); // Leer la entrada del usuario
			enviados = cadena.getBytes();

			// ENVIANDO DATAGRAMA AL SERVIDOR
			System.out.println("Enviando " + enviados.length + " bytes al servidor.");
			DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
			clientSocket.send(envio);

			// RECIBIENDO DATAGRAMA DEL SERVIDOR
			DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
			System.out.println("Esperando datagrama...");
			clientSocket.receive(recibo);
			String mayuscula = new String(recibo.getData(), 0, recibo.getLength());

			// OBTENIENDO INFORMACIÓN DEL DATAGRAMA
			InetAddress IPOrigen = recibo.getAddress();
			int puertoOrigen = recibo.getPort(); // Corregido a puertoOrigen
			System.out.println("\tProcedente de: " + IPOrigen + ":" + puertoOrigen);
			System.out.println("\tDatos: " + mayuscula.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
