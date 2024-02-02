package Palindromo;

import java.io.DataInputStream;

import java.io.DataOutputStream;

import java.net.Socket;

import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws Exception {

		String Host = "localhost";

		int Puerto = 6000; // puerto remoto

		System.out.println("PROGRAMA CLIENTE INICIADO....");

		Socket cliente = new Socket(Host, Puerto);

		Scanner scanner = new Scanner(System.in);

		System.out.print("Introduce una frase: ");

		String frase = scanner.nextLine();

		// CREO FLUJO DE SALIDA AL SERVIDOR

		DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

		// ENVIO LA FRASE AL SERVIDOR

		flujoSalida.writeUTF(frase);

		// CREO FLUJO DE ENTRADA AL SERVIDOR

		DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());

		// EL SERVIDOR ME ENVIA UN MENSAJE

		System.out.println("Recibiendo del SERVIDOR: \n" + flujoEntrada.readUTF());

		// CERRAR STREAMS Y SOCKETS

		flujoEntrada.close();

		flujoSalida.close();

		cliente.close();

		scanner.close();

	}

}
