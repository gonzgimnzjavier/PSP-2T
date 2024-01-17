package intro;

import java.io.*;
import java.net.*;

public class Ejemplo2urlCon {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost/DAM2PSP/vernombre.php");
			URLConnection conexion = url.openConnection();
			conexion.setDoOutput(true);
			String cadena = "nombre=Maria Jesús&apellidos=Ramos Martin";
//ESCRIBIR EN LA URL
			PrintWriter output = new PrintWriter(conexion.getOutputStream());
			output.write(cadena);
			output.close(); // cerrar flujo
//LEER DE LA URL
			BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			String linea;
			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
			}
			reader.close();// cerrar flujo
		} catch (MalformedURLException me) {
			System.err.println("MalformedURLException: " + me);
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe);
		}
	}// fin de main
}// Ejemplo2urlCon