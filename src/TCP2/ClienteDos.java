package TCP2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteDos {

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
                    System.out.println("Servidor: " + message);
                    if (message.equals("exit")) {
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error leyendo del servidor: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 6000;
        System.out.println("PROGRAMA CLIENTE INICIADO...");
        Socket cliente = new Socket(host, port);

        PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        Thread leerThread = new Thread(new ReadThread(fentrada));
        leerThread.start();

        Scanner sc = new Scanner(System.in);
        String cadena;

        System.out.print("Introduce cadena: ");
        while (!(cadena = sc.nextLine()).equals("exit")) {
            fsalida.println(cadena);
            System.out.print("Introduce cadena: ");
        }
        fsalida.println("exit");

        try {
			leerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        sc.close();
        fentrada.close();
        fsalida.close();
        cliente.close();
    }
}
