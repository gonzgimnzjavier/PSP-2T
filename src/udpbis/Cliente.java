package udpbis;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in); DatagramSocket clientSocket = new DatagramSocket()) {
            byte[] enviados;
            byte[] recibidos = new byte[1024];

            // DATOS DEL SERVIDOR al que enviar mensaje
            InetAddress IPServidor = InetAddress.getLocalHost();
            int puerto = 9876;
            System.out.println("Cliente de comprobación de año bisiesto iniciado.");

            while (true) {
                System.out.print("Introduce un año para comprobar si es bisiesto (o '*' para salir): ");
                String cadena = sc.nextLine(); // Leer la entrada del usuario
                if (cadena.trim().equals("*")) break; // Salir del bucle si el usuario introduce "*"
                
                enviados = cadena.getBytes();

                // ENVIANDO DATAGRAMA AL SERVIDOR
                DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
                clientSocket.send(envio);
                System.out.println("Solicitud enviada al servidor.");

                // RECIBIENDO DATAGRAMA DEL SERVIDOR
                DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
                clientSocket.receive(recibo);
                String respuesta = new String(recibo.getData(), 0, recibo.getLength());

                // OBTENIENDO INFORMACIÓN DEL DATAGRAMA
                InetAddress IPOrigen = recibo.getAddress();
                int puertoOrigen = recibo.getPort();
                System.out.println("\tRespuesta del servidor " + IPOrigen + ":" + puertoOrigen + " - " + respuesta.trim());
            }
            System.out.println("Cliente cerrado.");
        } catch (Exception e) {
            System.err.println("Error en cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
