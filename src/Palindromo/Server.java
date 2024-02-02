package Palindromo;

import java.io.DataInputStream;

import java.io.DataOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import java.net.ServerSocket;

import java.net.Socket;

public class Server {

	public static void main(String[] arg) throws IOException {

		int numeroPuerto = 6000; // Puerto

		ServerSocket servidor = new ServerSocket(numeroPuerto);

		Socket clienteConectado = null;

		System.out.println("Esperando al cliente.....");

		clienteConectado = servidor.accept();

		// CREO FLUJO DE ENTRADA DEL CLIENTE

		InputStream entrada = null;

		entrada = clienteConectado.getInputStream();

		DataInputStream flujoEntrada = new DataInputStream(entrada);

		// EL CLIENTE ME ENVIA UN MENSAJE

		String mensajeCliente = flujoEntrada.readUTF();

		System.out.println("Recibiendo del CLIENTE: \n\t" + mensajeCliente);

		// Verificando si el mensaje es un palíndromo

		String respuesta = esPalindromo(mensajeCliente) ?

				"Es un palíndromo" :

				"No es un palíndromo";

		// CREO FLUJO DE SALIDA AL CLIENTE

		OutputStream salida = clienteConectado.getOutputStream();

		DataOutputStream flujoSalida = new DataOutputStream(salida);

		// ENVIO UN SALUDO AL CLIENTE

		flujoSalida.writeUTF(respuesta);

		// CERRAR STREAMS Y SOCKETS

		entrada.close();

		flujoEntrada.close();

		salida.close();

		flujoSalida.close();

		clienteConectado.close();

		servidor.close();

	}

	// Método para verificar si una cadena es un palíndromo

	public static boolean esPalindromo(String str) {

		// Eliminar espacios

		str = str.replaceAll(" ", "");

		int izquierda = 0;

		int derecha = str.length() - 1;

		while (izquierda < derecha) {

			if (str.charAt(izquierda) != str.charAt(derecha)) {

				return false;

			}

			izquierda++;

			derecha--;

		}

		return true;

	}

}