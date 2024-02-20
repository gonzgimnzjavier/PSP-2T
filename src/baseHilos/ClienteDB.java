package baseHilos;

import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.awt.*;

public class ClienteDB extends JFrame implements ActionListener, Runnable {

	private static final long serialVersionUID = 1L;
	static JTextField depconsultar = new JTextField(2);
	static JLabel etiqueta = new JLabel("Departamento a consultar:");
	private JScrollPane scrollpanel;
	static JTextArea textareal;
	JButton boton = new JButton("Enviar");
	JButton desconectar = new JButton("Salir");
	boolean repetir = true;
	static Socket socket;

	// streams
	ObjectInputStream inObjeto;
	DataOutputStream salida;

	// constructor
	public ClienteDB(Socket s) {
		super("OPERACIONES CON BD");
		socket = s;
		try {
			// flujo de salida - para enviar cadena
			salida = new DataOutputStream(socket.getOutputStream());
			// flujo de entrada - para recibir objeto
			inObjeto = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		setLayout(null);
		etiqueta.setBounds(10, 10, 200, 30);
		add(etiqueta);
		depconsultar.setBounds(210, 10, 50, 30);
		add(depconsultar);
		textareal = new JTextArea();
		scrollpanel = new JScrollPane(textareal);
		scrollpanel.setBounds(10, 50, 400, 300);
		add(scrollpanel);
		boton.setBounds(420, 10, 100, 30);
		add(boton);
		desconectar.setBounds(420, 50, 100, 30);
		add(desconectar);
		textareal.setEditable(false);
		boton.addActionListener(this);
		desconectar.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}// constructor

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == boton) { // ENVIAR DEP
			try {
				salida.writeUTF(depconsultar.getText());
			} catch (IOException el) {
				el.printStackTrace();
			}
		}
		if (e.getSource() == desconectar) { // SALIR
			try {
				socket.close();
			} catch (IOException el) {
				el.printStackTrace();
				System.exit(0);
			}
		} // if
	}// actionPerformed

	// proceso repetitivo
	public void run() {
		String texto = "";
		while (repetir) {
			try {
				Departamentos d = null;
				d = (Departamentos) inObjeto.readObject();// recibo un objeto
				textareal.setText("");
				textareal.setForeground(Color.blue);
				if (d == null) {
					textareal.setForeground(Color.red);
					System.out.println(" «EL DEPARTAMENTO NO EXISTE>>");

				} else { // datos del departamento
					texto = "Número Dep: " + d.getDeptNo() + "\n " + " Nombre: " + d.getDnombre() + "\tLocalidad: "
							+ d.getLoc();
					textareal.append(texto);
					PintarEmpleados(d);// visualizar empleados Welse
				}
			} catch (SocketException s) {
				repetir = false;// se produce al cerrar socket en botón salir
			} catch (IOException e) {
				e.printStackTrace();
				repetir = false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				repetir = false;
			}
		} // fin while
		try {
			socket.close(); // CERRAR SOCKET
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		} // fin run
	}

	// PINTA LOS EMPLEADOS EN EL AREA
	private void PintarEmpleados(Departamentos d) {
		Set<Empleados> listaemple = d.getEmpleadoses();// obtenemos// empleados
		textareal.setForeground(Color.blue);
		if (listaemple == null) {
			PintaMensaje("EL DEPARTAMENTO NO TIENE EMPLEADOS");
		} else {
			PintaMensaje("EMPLEADOS DEL DEPARTAMENTO: " + listaemple.size());
			Iterator<Empleados> it = listaemple.iterator();
			while (it.hasNext()) {
				Empleados emple = new Empleados();
				emple = it.next();
				textareal.append("\n" + emple.getEmpNo() + " * " + emple.getApellido() + " * " + emple.getOficio()
						+ " * " + emple.getSalario());
			} // while
			textareal.append("\n ");
		} // else
	}
	// PAGINA 27/32 proceso repetitivo

	private void PintaMensaje(String mensaje) {
		textareal.append("\n - - - - - - - - - - - - - - - - - - - -");

		textareal.append("\n" + mensaje);
		textareal.append("\n - - - - - -");

	}// PintaMensaje

	public static void main(String[] args) throws UnknownHostException, IOException {
		int puerto = 44441;
		Socket s = new Socket("localhost", puerto);// máquina local
		ClienteDB hiloC = new ClienteDB(s);
		hiloC.setBounds(0, 0, 540, 400);
		hiloC.setVisible(true);
		new Thread(hiloC).start();
	}// fin main

}// Fin del CLIENTE