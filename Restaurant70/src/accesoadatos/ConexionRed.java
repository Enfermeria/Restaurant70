/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoadatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author John David Molina
 */
public class ConexionRed implements Runnable {
	
	/**
	 * Envio un mensaje a una máquina host que está escuchando en un puerto
	 * @param mensaje
	 * @param host
	 * @param puerto 
	 */
	public void enviaMensaje(String mensaje, String host, int puerto){
		try {
            Socket socket = new Socket("localhost",9999);  // creo un puente
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream()); //creo un stream de salida
            
            salida.writeUTF(mensaje); // envio un String
            salida.close();  // cierro el puente
        } catch (IOException ex) {
            Utils.mensajeError("Error conexión de Red: " + ex + " No se pudo realizar la conexión . ");
        }
	}
	
	public String reciboMensaje(int puerto){
		String mensaje = ""; //mensaje a devolver
		
		try{
			ServerSocket servidor = new ServerSocket(9999); //crea un puente en el puerto 9999
			
			while(true){
				Socket misocket = servidor.accept();	// espera una conexion y la acepta
				DataInputStream entrada = new DataInputStream(misocket.getInputStream()); //creo un stream de entrada
				mensaje = entrada.readUTF();		// recibo un string
				System.out.println(mensaje);			//muestro el mensaje recibido.
				misocket.close();						//cierro el puente
			}
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,"Error : " +e);
		}
		
		return mensaje;
	}
	
	@Override
	public void run() { //codigo del hilo que se ejecutará. Requiere que la clase implements Runnable
		String m=reciboMensaje(9999);
	} //run
}
