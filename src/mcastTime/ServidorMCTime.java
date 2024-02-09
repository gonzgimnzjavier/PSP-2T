package mcastTime;

import java.net.*;
import java.util.Date;
import java.util.Scanner;

public class ServidorMCTime {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int puerto = 12345; // Puerto multicast
        InetAddress grupo = InetAddress.getByName("225.0.0.1"); // Grupo multicast

        // Se crea el socket multicast y se une al grupo
        MulticastSocket ms = new MulticastSocket(puerto);
        ms.joinGroup(grupo);

        String cadena = "";
        while (!cadena.trim().equals("quit")) {
            //System.out.print("Datos a enviar al grupo: ");
            
            cadena = mostratTiempo();
            Thread.sleep(10000);            
            // ENVIANDO AL GRUPO
            DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), grupo, puerto);
            ms.send(paquete);
        }

        // Cerrar el socket fuera del bucle
       // Opcional, para salir del grupo multicast
        ms.close();
        System.out.println("Socket cerrado...");
    }
    public static String mostratTiempo() {
    	
    	Date date = new Date();
    	System.out.println(date.toString());
    	return date.toString();
    	
    }
}
