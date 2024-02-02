package TCP2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerDos {

    private static class ReadThread implements Runnable {
        private BufferedReader reader;

        public ReadThread(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("Cliente: " + message);
                    if (message.equals("exit")) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error leyendo del cliente: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        int numPort = 6000;
        ServerSocket servidor = null;
        Socket cliente = null;
        PrintWriter salida = null;
        BufferedReader entrada = null;
        Scanner scanner = new Scanner(System.in);

        try {
            servidor = new ServerSocket(numPort);
            System.out.println("Esperando al cliente...");
            cliente = servidor.accept();

            salida = new PrintWriter(cliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            Thread leerThread = new Thread(new ReadThread(entrada));
            leerThread.start();

            String mensaje;
            System.out.println("Cliente conectado. Escribe 'exit' para salir.");
            while (!(mensaje = scanner.nextLine()).equals("exit")) {
                salida.println(mensaje);
            }
            salida.println("exit");

            leerThread.join();
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Thread interrumpido: " + e.getMessage());
        } finally {
            try {
                if (scanner != null) scanner.close();
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
