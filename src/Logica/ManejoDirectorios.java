package Logica;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Clases.Diagrama;

public class ManejoDirectorios {

	public static void guardarArchivo(Object objeto) throws FileNotFoundException, IOException{
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("Salvas Diagrama/" + ((Diagrama) objeto).getNombre()));
		stream.writeObject(objeto);
		stream.close();
		
		
	}

	public static Object recuperarArchivo(String ruta) throws FileNotFoundException, IOException, ClassNotFoundException{
		Object objetoArecuperar = null;
		
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream("Salvas Diagrama/" + ruta));
		objetoArecuperar = stream.readObject();
		stream.close();

		return objetoArecuperar;
	}
	
	
	
	public static boolean isSameName(String name){
	       File rutaSalvas = new File("Salvas Diagrama");
	       boolean issameName = false;
			
			String[] list = rutaSalvas.list();
			
			for (String s : list) {
				if(s.equals(name))
					issameName = true;
			}
			
			return issameName;
		}
	
	public static boolean comprobarEstadoDeGuardado(Diagrama diagramaActual){
		boolean isGuardado = false;
		if(diagramaActual != null){
		
			
			Diagrama d;
			try {
				d = (Diagrama) ManejoDirectorios.recuperarArchivo(diagramaActual.getNombre());
				if(d.equals(diagramaActual))
					isGuardado = true;
			} catch (FileNotFoundException e) {
				isGuardado = false;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		}
		else
			isGuardado = true;
		
		return isGuardado;	
	}

}
