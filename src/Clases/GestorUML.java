package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import Logica.ManejoDirectorios;

public class GestorUML {
	private LinkedList<Proyecto> proyectos;
	private Proyecto proyectoSeleccionado;
	private static GestorUML gestor;


	private GestorUML () {
		this.proyectos = new LinkedList<Proyecto>();
	}


	public LinkedList<Proyecto> getProyectos() {
		return proyectos;
	}


	public void setProyectos(LinkedList<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}


	public static void setGestor(GestorUML gestor) {
		GestorUML.gestor = gestor;
	}

	public static GestorUML getInstancie () {
		if (gestor == null)
			gestor = new GestorUML();

		return gestor;
	}


	public Proyecto getProyectoSeleccionado() {
		return proyectoSeleccionado;
	}


	public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
		this.proyectoSeleccionado = proyectoSeleccionado;
	}


	//OPERACIONES

	public void mostrarProyecto (Proyecto proyectoCargado) {
		// Lo primero es verificar si el proyecto ya fue cargado
		Proyecto proyecto = buscarProyecto(proyectoCargado);

		if (proyecto != null) // si ya fue cargado, entonces solo lo actualizamos como nuevo seleccionado
			this.setProyectoSeleccionado(proyecto);

		else  // si no, lo añadimos como nuevo proyecto cargado
			this.addProyecto(proyectoCargado); 
	}

	public void addProyecto (Proyecto proyecto) { // Metodo para añadir Proyecto
		this.proyectos.add(proyecto);
		this.setProyectoSeleccionado(proyecto); // se asigna como proyecto seleccionado al proyecto añadido
	}

	public void deleteProyecto (Proyecto proyectoEliminar) {
		this.proyectos.remove(proyectoEliminar);

		if (this.proyectos.size() != 0)
			this.proyectoSeleccionado = this.proyectos.getLast(); // se asigna al ultimo proyecto despues de la eliminacion
		else
			this.proyectoSeleccionado = null;
	}

	private Proyecto buscarProyecto (Proyecto proyectoCargado) {
		Proyecto proyecto = null;	
		Iterator<Proyecto> iter = this.proyectos.iterator();

		while (iter.hasNext() && proyecto == null) {
			Proyecto proyectoAux = iter.next();

			if (proyectoAux.getNombre().equalsIgnoreCase(proyectoCargado.getNombre())) // buscamos por el nombre, ya que el nombre es el identificador único
				proyecto = proyectoAux;
		}

		return proyecto;
	}

	// SISTEMA DE GUARDADO

	public boolean comprobarEstadoModificacion() { // Metodo para comprobar si existió alguna modificación
		boolean estadoModificacion = false;
		Iterator<Proyecto> iterProyectos = this.proyectos.iterator();

		while (iterProyectos.hasNext() && !estadoModificacion) {
			if (iterProyectos.next().isModificado()) // si al menos un proyecto fue modificado
				estadoModificacion = true;
		}

		return estadoModificacion;

	}

	
	public void guardarAllProyectos () throws FileNotFoundException, IOException { // Metodo para guardar todos los proyectos que fueron modificados
		Iterator<Proyecto> iterProyectos = this.proyectos.iterator();

		// Se recorren todos los proyectos y se guardan los que han sido modificados
		while (iterProyectos.hasNext() ) {
			Proyecto proyectoAux = iterProyectos.next();
			
			if (proyectoAux.isModificado()) // si el proyecto fue modificado
				ManejoDirectorios.guardarArchivo(proyectoAux); // se guarda
		}
	}
	// FIN DE SISTEMA DE GUARDADO

	//FIN OPERACIONES

}
