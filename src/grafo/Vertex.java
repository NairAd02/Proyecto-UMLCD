package grafo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class Vertex implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object info;
	private LinkedList<Edge> aristas;






	public Vertex(Object info) {
		this.info = info;
		this.aristas = new LinkedList<Edge>();
	}

	public Vertex () {}; // Constructor json


	public Object getInfo() {
		return info;
	}
	public void setInfo(Object info) {
		this.info = info;
	}
	public LinkedList<Edge> getAristas() {
		return aristas;
	}
	public void setAristas(LinkedList<Edge> aristas) {
		this.aristas = aristas;
	}

	// OPERACIONES
	public void addArista (Edge arista){
		this.aristas.add(arista);
	}

	// ELIMINACIONES

	public void eliminarAristasVertice (Vertex vertice) { // Metodo que elimina todas las aristas que referencien a ese vertice
		Iterator<Edge> iter = this.aristas.iterator();

		while (iter.hasNext()) { // Se itera sobre la lista de aristas
			Edge edgeAux = iter.next();

			if (edgeAux.getVertice().equals(vertice)) 
				iter.remove(); // se elimina la arista si esta referencia al vertice

		}

	}

	public boolean eliminarArista (Edge aristaEliminar) { // Metodo para eliminar una arista de la lista de aristas del grafo
		boolean eliminado = false;

		Iterator<Edge> iter = this.aristas.iterator();

		while (iter.hasNext()) { // Se itera sobre la lista de aristas
			Edge edgeAux = iter.next();

			if (edgeAux.equals(aristaEliminar)){
				iter.remove(); // se elimina la arista si esta referencia al vertice
				eliminado = true;
			}
		}

		return eliminado;

	}

	// FIN ELIMINACIONES
	// FIN OPERACIONES
}
