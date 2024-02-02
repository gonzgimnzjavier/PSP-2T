package udpChat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerChat {

    public static void main(String[] args) throws Exception {

        // Puerto por el que escucha el servidor: 9876
        try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
            byte[] recibidos;
            byte[] enviados;

            while (true) {
                System.out.println("Esperando datagrama.....");
                // RECIBO DATAGRAMA
                recibidos = new byte[1024];
                DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
                serverSocket.receive(paqRecibido);
                String cadena = new String(paqRecibido.getData(), 0, paqRecibido.getLength());

                // DIRECCION ORIGEN
                InetAddress IPOrigen = paqRecibido.getAddress();
                int puerto = paqRecibido.getPort();
                System.out.println("\tOrigen: " + IPOrigen + ":" + puerto);
                System.out.println("\tMensaje recibido: " + cadena.trim());

                // CONVERTIR CADENA A MAYÚSCULAS
                String mayuscula = cadena.trim().toUpperCase();
                enviados = mayuscula.getBytes();

                // ENVIO DATAGRAMA AL CLIENTE
                DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
                serverSocket.send(paqEnviado);

                // Para terminar
                if (cadena.trim().equals("*")) break;
            }
            System.out.println("Socket cerrado...");
        }
    }
}
