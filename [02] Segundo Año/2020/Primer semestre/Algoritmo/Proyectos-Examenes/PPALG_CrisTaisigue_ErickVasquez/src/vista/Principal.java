package vista;

import java.awt.EventQueue;
import controlador.Interfaz;

//Clase Principal
public class Principal {


	
					//CONSTRUIR LA VENTANA
	//METODO MAIN
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz ventana = new Interfaz();//Crear la ventana
					ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
