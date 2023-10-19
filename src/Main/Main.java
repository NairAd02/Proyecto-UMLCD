package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Clases.Diagrama;
import Interfaz.Principal;

public class Main {



	public static void main(String[] args) {
		//Diagrama.getInstance("Prueba");

		// Habilitar lookAndFeel
		JFrame.setDefaultLookAndFeelDecorated(true);

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {  
			
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			
			e1.printStackTrace();
		}
		
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