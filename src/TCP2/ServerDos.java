package TCP2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDos {

    public static void main(String[] args) {
        int numPort = 6000; // Puerto
        ServerSocket servidor = null;
        Socket cliente = null;
        PrintWriter salida = null;
        BufferedReader entrada = null;

        try {
            servidor = new ServerSocket(numPort);
            System.out.println("Esperando al cliente...");

            cliente = servidor.accept();

            salida = new PrintWriter(cliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            String cad;
            while ((cad = entrada.readLine()) != null) {
                salida.println(cad);
                System.out.println("Recibiendo: " + cad);

                if (cad.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        } finally {
            try {
                if (entrada != null) entrada.close();
                if (salida != null) salida.close();
                if (cliente != null) cliente.close();
                if (servidor != null) servidor.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
}
