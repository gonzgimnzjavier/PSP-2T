package udpuUno;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUno {

	public static void main(String[] args) {
		try {
			InetAddress destino = InetAddress.getLocalHost();
			int port = 12345; // puerto al que envio el datagrama
			byte[] mensaje;
			String saludo = "Enviando Saludos !!";
			mensaje = saludo.getBytes(); // codifico String a bytes
			// CONSTRUYO EL DATAGRAMA A ENVIAR
			DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
			DatagramSocket socket = new DatagramSocket(); // Puerto local asignado automáticamente
			System.out.println("Enviando Datagrama de longitud: " + mensaje.length);
			System.out.println("Host destino: " + destino.getHostName());
			System.out.println("IP Destino: " + destino.getHostAddress());
			System.out.println("Puerto local del socket: " + socket.getLocalPort());
			System.out.println("Puerto al que envio: " + envio.getPort());
			// ENVIO DATAGRAMA
			socket.send(envio);
			socket.close(); // cierro el socket
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
