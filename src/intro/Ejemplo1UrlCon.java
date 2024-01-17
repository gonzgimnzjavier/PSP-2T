package intro;

import java.net.*;
import java.io.*;

public class Ejemplo1UrlCon {

	public static void main(String[] args) {
		URL url = null;
		URLConnection urlCon = null;

		try {
			url = new URL("http://www.elaltozano.es");
			urlCon = url.openConnection();

			// Usando try-with-resources para asegurar que el BufferedReader se cierre
			// automáticamente
			try (BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
