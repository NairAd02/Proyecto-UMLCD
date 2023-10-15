package grafo;

import java.io.Serializable;

public class Edge implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object ponderacion;
	private Vertex vertice;


	public Edge(Object ponderacion, Vertex vertice){

		this.ponderacion = ponderacion;
		this.vertice = vertice;
	}

	public Edge () {} // Constructor json



	public Object getPonderacion() {
		return ponderacion;
	}
	public void setPonderacion(Object ponderacion) {
		this.ponderacion = ponderacion;
	}
	public Vertex getVertice() {
		return vertice;
	}
	public void setVertice(Vertex vertice) {
		this.vertice = vertice;
	}




}
