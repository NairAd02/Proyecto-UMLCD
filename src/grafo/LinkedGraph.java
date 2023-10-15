package grafo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedGraph implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Vertex> vertices;


	public LinkedGraph () {
		this.vertices = new LinkedList<Vertex>();
	}
	//public LinkedGraph () {} // Constructor json



	public LinkedList<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(LinkedList<Vertex> vertices) {
		this.vertices = vertices;
	}

	// OPERACIONES

	public void addVertice (Object info) { // Metodo para añadir un vertice al grafo
		this.vertices.add(new Vertex(info));
	}

	public void addArista (Vertex verticeOrigen, Vertex verticeDestino, Object ponderacion) { // Metodo para añadir una arista al grafo
		verticeOrigen.addArista(new Edge(ponderacion, verticeDestino));
	}

	public Vertex buscarVertice (Object info) { 
		Vertex vertice = null;
		Iterator<Vertex> iter = this.vertices.iterator();

		while (iter.hasNext() && vertice == null) {
			Vertex vertexAux = iter.next();

			if (vertexAux.getInfo().equals(info))
				vertice = vertexAux;
		}

		return vertice;
	}

	//ELIMINACIONES

	public void deleteVertice (Object infoEliminar) {
		Vertex verticeEliminar = this.deleteListaVertices(infoEliminar); // Eliminamos el vertice de la lista de vertice y lo obtenemos para eliminar las aristas que lo referencien
		eliminarAristasVertice(verticeEliminar); // se elimina las aristas que referencien a ese vertice
	}

	private Vertex deleteListaVertices (Object infoEliminar) { // Metodo para eliminar un vertice de la lista de vertices
		Vertex verticeEliminar = null;
		Iterator<Vertex> iter = this.vertices.iterator();

		while (iter.hasNext() && verticeEliminar == null) {
			Vertex vertexAux = iter.next();
			if (vertexAux.getInfo().equals(infoEliminar)){
				verticeEliminar = vertexAux;
				iter.remove(); // se elimina el vertice actual
			}
		}

		return verticeEliminar;
	}

	private void eliminarAristasVertice (Vertex vertice) { // Metodo para eliinar las aristas que referencien a un vertice
		Iterator<Vertex> iter = this.vertices.iterator();

		while (iter.hasNext()){
			iter.next().eliminarAristasVertice(vertice);

		}
	}

	public void eliminarArista (Vertex verticeInicial, Edge arista) { // Metodo para eliminar la arista de un grafo
		verticeInicial.eliminarArista(arista);
	}

	// FIN DE ELIMINACIONES

	// FIN OPERACIONES


}
