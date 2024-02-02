package udpin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException {

		int port = 12345; // puerto al que envio
		InetAddress destino = InetAddress.getLocalHost();// IP a la que envío
		
		byte[] mensaje = new byte[1024]; // matriz de bytes
		String saludo = "Enviando Saludos !!";
		mensaje = saludo.getBytes(); // codificarlo a bytes para enviarlo
		// construyo el datagrama a enviar
		DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
				
		try {
			DatagramSocket socket = new DatagramSocket(34567);//del cliente
			socket.send(envio);
			System.out.println("datagram enviado...");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

}