package Clases;

import java.util.Iterator;
import java.util.LinkedList;

public class GestorUML {
	private LinkedList<Diagrama> diagramas;
	private Diagrama diagramaSeleccionado;
	private static GestorUML gestor;


	private GestorUML () {
		this.diagramas = new LinkedList<Diagrama>();
	}


	public LinkedList<Diagrama> getDiagramas() {
		return diagramas;
	}


	public void setDiagramas(LinkedList<Diagrama> diagramas) {
		this.diagramas = diagramas;
	}


	public static void setGestor(GestorUML gestor) {
		GestorUML.gestor = gestor;
	}

	public static GestorUML getInstancie () {
		if (gestor == null)
			gestor = new GestorUML();

		return gestor;
	}


	public Diagrama getDiagramaSeleccionado() {
		return diagramaSeleccionado;
	}


	public void setDiagramaSeleccionado(Diagrama diagramaSeleccionado) {
		this.diagramaSeleccionado = diagramaSeleccionado;
	}

	//OPERACIONES
	public void addDiagrama (Diagrama diagrama) {
		this.diagramas.add(diagrama);
		this.setDiagramaSeleccionado(diagrama); // se asigna como diagrama seleccionado al diagrama añadido
	}

	public void deleteDiagrama (Diagrama diagramaEliminar) {
		this.diagramas.remove(diagramaEliminar);

		if (this.diagramas.size() != 0)
			this.diagramaSeleccionado = this.diagramas.getFirst(); // se asigna el primer diagrama despues de la eliminacion
		else
			this.diagramaSeleccionado = null;
	}

	public boolean comprobarExistenciaDiagrama (Diagrama diagramaCargado) {
		boolean encontrado = false;

		Iterator<Diagrama> iter = this.diagramas.iterator();

		while (iter.hasNext() && !encontrado) {
			Diagrama diagramaAux = iter.next();

			if (diagramaAux.equals(diagramaCargado))
				encontrado = true;
		}

		return encontrado;
	}

	//FIN OPERACIONES

}
