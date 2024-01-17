package TCPin;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client1 {

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000; // Puerto remoto

        try {
            // Abrir socket
            Socket cliente = new Socket(host, puerto); // Conecta
            InetAddress i = cliente.getInetAddress();

            System.out.println("Puerto local: " + cliente.getLocalPort());
            System.out.println("Puerto remoto: " + cliente.getPort());
            System.out.println("Host remoto: " + i.getHostName());
            System.out.println("IP Host remoto: " + i.getHostAddress());

            // Cierra el socket
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
