package Main;

import java.awt.EventQueue;

import Clases.Diagrama;
import Interfaz.Principal;

public class Main {



	public static void main(String[] args) {
		//Diagrama.getInstance("Prueba");

		EventQueue.invokeLater(new Runnable() {		
			public void run() {
				try {
					Principal frame = Principal.getInstancie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}