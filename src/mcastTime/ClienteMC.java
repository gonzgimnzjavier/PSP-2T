package mcastTime;

import java.net.*;

public class ClienteMC {
	public static void main(String args[]) throws Exception {
//Se crea el socket multicast
		int puerto = 12345;// Puerto multicast
		MulticastSocket ms = new MulticastSocket(puerto);
		InetAddress grupo = InetAddress.getByName("225.0.0.1");// Grupo
//Nos unimos al grupo 
		ms.joinGroup(grupo);
		String msg = "";
		while (!msg.trim().equals("quit")) {
//Recibe el paquete del servidor multicast
			byte[] buf = new byte[1024]; // Genero dentro elbuffer para que se sobreescriba al enviar un nuevo mensaje
			DatagramPacket paquete = new DatagramPacket(buf, buf.length);
			ms.receive(paquete);
			msg = new String(paquete.getData());
			System.out.println("Recibo: " + msg.trim());
		} // Fin de while
		ms.leaveGroup(grupo); // abandonamos grupo
//cierra socket ms. close ();
		System.out.println("Socket cerrado...");
	}// Fin de main
}// Fin de ClienteMC