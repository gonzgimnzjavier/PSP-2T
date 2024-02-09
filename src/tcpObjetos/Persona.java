package tcpObjetos;

import java.io.Serializable;
import java.util.Scanner;



public class Persona implements Serializable {
	String nombre;
	int edad;
	
	static Scanner sc = new Scanner(System.in);

	public Persona(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}

	public Persona() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	public static Persona crearPersona() {
		System.out.println("introduce datos de la nueva persona");
		Persona p = new Persona();
		System.out.println("Dame el nombre");
		String s=sc.nextLine();
		System.out.println("Dame la edad");
		int e = sc.nextInt();
		p.setEdad(e);
		p.setNombre(s);
		
		return p;

	}
}// Fin de Persona
