package chatGrafico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClienteChat extends JFrame implements ActionListener {

	private static final long serialVersionUlD = 1;
	Socket socket = null;
	// streams
	DataInputStream fentrada;
	// para leer mensajes de todos
	DataOutputStream fsalida;
	// para escribir sus mensajes
	String nombre;
	
	static JTextField mensaje = new JTextField();
	private JScrollPane scrollpanel;
	static JTextArea textarea1;
	JButton boton = new JButton("Enviar");
	JButton desconectar = new JButton("Salir");
	
	//controla la salida del bucle de comunicaion
	boolean repetir = true;
	
	//constructor
	public ClienteChat(Socket s, String nombre) {
		super(" CONEXIÓN DEL CLIENTE CHAT: " + nombre);
		setLayout(null);
		mensaje.setBounds(10, 10, 400, 30);add(mensaje);
		textarea1 = new JTextArea();
		scrollpanel = new JScrollPane(textarea1);
		scrollpanel.setBounds(10, 50, 400, 300); 
		add(scrollpanel); 
		boton.setBounds(420, 10, 100, 30);
		add(boton); 
		desconectar.setBounds(420, 50, 100, 30);
		add(desconectar); 
		textarea1.setEditable(false);
		boton.addActionListener(this);
		desconectar.addActionListener(this); 
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		socket = s;
		this.nombre= nombre;
		
		try{
			fentrada = new DataInputStream(socket.getInputStream());
			fsalida = new DataOutputStream(socket.getOutputStream()); 
			String texto = " > Entra en el Chat " +nombre;
			fsalida.writeUTF(texto); 
			//escribe mensaje de entrada
			} catch (IOException e) {
				System.out.println("ERROR DE E/S");
				e.printStackTrace();
				System.exit(0);
			}
		}// fin constructor

	    // acción Realizada cuando pulsamos botones
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == boton) {
				// SE PULSA botón ENVIAR
				String texto = nombre + "> " + mensaje.getText();
				try {
					mensaje.setText(""); // limpio area de mensaje
					fsalida.writeUTF(texto);
				} catch (IOException el) {
					el.printStackTrace();
				}
			}

			if (e.getSource() == desconectar) {
				// SE PULSA botón SALIR
				String texto = " > Abandona el Chat " + nombre;
				try {
					fsalida.writeUTF(texto);
					fsalida.writeUTF("*");
					repetir = false;
					// para salir del bucle
				} catch (IOException el) {
					el.printStackTrace();
				}
			}
		}// fin ap

	//funcionamiento
		public void ejecutar() {
			String texto = "";
			while (repetir) {
				try {
					texto = fentrada.readUTF();
					// leer mensajes
					textarea1.setText(texto);
					// visualizarlos
				} catch (IOException e) {
					// este error sale cuando el servidor se cierra
					JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
							"<<MENSAJE DE ERROR: 2>>", JOptionPane.ERROR_MESSAGE);
					repetir = false;
				} // salir del bucle
			} // while

			try {
				socket.close();// cerrar socket
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}// ejecutar
	
	//main grafico
		public static void main(String args[]) {
			int puerto = 44444;
			String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick:");
			Socket s = null;
			try {
				// cliente y servidor se ejecutan en la máquina local
				// InetAddress.getByName("10.101.14.116"); 
				InetAddress ip = InetAddress.getByName("10.101.14.116");
				//EN EL CLIENTE USE la de arriba
				s = new Socket(ip, puerto);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" + e.getMessage(),
						"<<MENSAJE DE ERROR:1>>", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			if (!nombre.trim().equals("")) {
				// hay que escribir algo
				ClienteChat cliente = new ClienteChat(s, nombre);
				cliente.setBounds(0, 0, 540, 400);
				cliente.setVisible(true);
				cliente.ejecutar();
			} else

				System.out.println("El nombre está vacío....");
		}// main	
		
}