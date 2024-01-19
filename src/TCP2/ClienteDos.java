package TCP2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteDos {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 6000; // puerto remoto
        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Socket cliente = new Socket(host, port);

        // CREO FLUJO DE SALIDA AL SERVIDOR
        PrintWriter fsalida = new PrintWriter(cliente.getOutputStream(), true);

        // CREO FLUJO DE ENTRADA DEL SERVIDOR
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

        // FLUJO PARA ENTRADA ESTANDAR
        Scanner sc = new Scanner(System.in);
        String cadena, eco="";
        System.out.print("Introduce cadena: ");
        cadena = sc.nextLine(); // lectura por teclado
        while(cadena != null){
	        fsalida.println(cadena);
	        eco = fentrada.readLine();
	        System.out.println(" =>ECO:" + eco);
	        System.out.print("Introduce cadena: ");
	        cadena = sc.nextLine();
        }

        // ENVÍO UN SALUDO AL SERVIDOR
        fsalida.println("Saludos al SERVIDOR DESDE EL CLIENTE");

        // EL SERVIDOR ME ENVÍA UN MENSAJE
        eco = fentrada.readLine();
        System.out.println("Recibiendo del SERVIDOR: \n\t" + eco);

        // CERRAR STREAMS Y SOCKETS
        fentrada.close();
        fsalida.close();
        cliente.close();
    }
}
