package intro;

import java.net.*;
import java.io.*;

public class Ejemplo2URL {

    public static void main(String[] args) {
        URL url = null;
        BufferedReader in = null;
        try {
            url = new URL("http://www.cebem.net");
            InputStream inputStream = url.openStream();
            in = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
