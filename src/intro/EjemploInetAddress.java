package intro;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EjemploInetAddress {

    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("\tMetodo getByName(): " + dir);
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println("\tMetodo getHostName(): " + dir.getHostName());
        System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMetodo toString(): " + dir.toString());
        System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }

    public static void main(String[] args) {
        System.out.println("-------------------------");
        System.out.println("SALIDA PARA LOCALHOST:");
        InetAddress dir = null;
        try {
            // LOCALHOST
            dir = InetAddress.getByName("JAVIERGONZL6F9C");
            pruebaMetodos(dir);

            // URL www.google.es
            System.out.println("\nSALIDA PARA UNA URL:");
            dir = InetAddress.getByName("www.google.es");
            pruebaMetodos(dir);

            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            for (int i = 0; i < direcciones.length; i++) {
                System.out.println("\t\t" + direcciones[i].toString());
            }
            System.out.println("====================");
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
    }
}
