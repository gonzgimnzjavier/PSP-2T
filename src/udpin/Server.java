package udpin;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

	public static void main(String[] args) throws IOException {

		 byte[] bufer = new byte[1024];
		
		 System.out.println("esperando datagrama...");
		 
		 DatagramSocket socket = new DatagramSocket (12345);//local , ahora del servidor
		 //construyo datagrama a recibir
		 
		 DatagramPacket recibo = new DatagramPacket (bufer, bufer.length);
		 socket.receive(recibo) ;//recibo datagrama

		
		 int bytesRec = recibo.getLength();// obtengo longitud del mensaje
		 String paquete = new String(recibo.getData());// obtengo mensaje
		
		 System. out .println ("Número de Bytes recibidos: "+ bytesRec);
		 System. out .println ("Contenido del Paquete: " + paquete.trim());
		 System. out.println ("Puerto origen del mensaje: " + recibo.getPort());//del cliente
		 System. out.println ("IP de origen:" + recibo. getAddress() .getHostAddress ());
		 System. out.println ("Puerto destino del mensaje:" + socket.getLocalPort());//al que llega
		 socket. close(); //cierro el socket


	}

}
