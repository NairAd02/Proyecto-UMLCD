package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cu.edu.cujae.ceis.graph.LinkedGraph;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedWeightedEdgeDirectedGraph;
import cu.edu.cujae.ceis.graph.interfaces.ILinkedWeightedVertexDirectedGraph;
import cu.edu.cujae.ceis.graph.vertex.Vertex;
import util.Flecha;
import Interfaz.Lienzo;


public class Diagrama implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ILinkedWeightedEdgeDirectedGraph grafoClases; // HACER IMPLEMENTACION PROPIA
	private String nombre;
	@JsonIgnore
	private static Diagrama diagrama;
	private ArrayList<Flecha> flechasHerencia;




	private Diagrama(String nombre) {

		this.grafoClases = new LinkedGraph();
		this.nombre = nombre;

		this.flechasHerencia = new ArrayList<Flecha>();
	}

	private Diagrama(){} // Constructor json

	public static Diagrama getInstance(String nombre){
		if(diagrama == null)
			diagrama = new Diagrama(nombre);

		return diagrama;

	}

	public void addFlechaHerencia(Flecha flecha){
		this.flechasHerencia.add(flecha);
	}

	public static void setInstance(Diagrama d){
		diagrama = d;
	}

	public String getNombre() {
		return nombre;
	}



	public ArrayList<Flecha> getFlechasHerencia() {
		return flechasHerencia;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static Diagrama getInstance(){
		return diagrama;
	}


	public ILinkedWeightedEdgeDirectedGraph getGrafoClases() {
		return grafoClases;
	}

	public void setGrafoClases(ILinkedWeightedEdgeDirectedGraph grafoClases) {
		this.grafoClases = grafoClases;
	}


	// OPERACIONES SOBRE EL DIAGRAMA
	public void addClase(Clase clase){

		if(this.validar(clase))
			this.grafoClases.insertVertex(clase); // se inserta la clase como vertice del grafo
		else
			throw new IllegalArgumentException();
	}



	public boolean validar(Clase clase) {
		boolean validator = true;
		Iterator<Vertex> iter = this.grafoClases.getVerticesList().iterator();

		while (iter.hasNext() && validator){
			Clase claseAux = (Clase) iter.next().getInfo(); // se obtiene la clase del vertice correspodiente
			if(clase.getNombre().equalsIgnoreCase(claseAux.getNombre()))
				validator = false;
		}

		return validator;
	}


	public Clase buscarClase(String nombre){ // Buscar una clase en el grafo segun su nombre
		Clase clase = null;
		Iterator<Vertex> iter = this.grafoClases.getVerticesList().iterator();

		while (iter.hasNext() && clase == null) {
			Clase claseAux = (Clase) iter.next().getInfo();
			if (claseAux.getNombre().equalsIgnoreCase(nombre))
				clase = claseAux;
		}

		return clase;
	}


	public int posClase(Clase clase){ // Buscar una clase en el grafo segun su referencia
		int pos = -1;
		Iterator<Vertex> iter = this.grafoClases.getVerticesList().iterator();

		while (iter.hasNext() && pos == -1) {
			Clase claseAux = (Clase) iter.next().getInfo();
			if (claseAux.equals(clase))
				clase = claseAux;
		}

		return pos;
	}

	public void eliminarClase (Clase clase) { // Metodo para eliminar una clase del grafo
		this.grafoClases.deleteVertex(this.grafoClases.getVerticesList().indexOf(clase)); // implementacion temporal
	}

	// FIN OPERACIONES SOBRE EL DIAGRAMA

	// OPERACIONES RELACIONES
	// OPERACIONES HERENCIA
	public Clase obtenerPadreClase(String nombreClase){
		return null;
	}
// AKIIIII DARIOOOOOOOOOOOO
	public void addRelacionHerencia(Clase clasePadre, Clase claseHija) throws Exception{
		this.grafoClases.insertWEdgeDG(this.grafoClases.getVerticesList().indexOf(clasePadre), this.grafoClases.getVerticesList().indexOf(claseHija), determinarBoundsRelacionHerencia(clasePadre, claseHija));
	}

	public Relacion determinarBoundsRelacionHerencia (Clase clasePadre, Clase claseHija) {
		// DADA LA INFORMACION DE lAS DOS CLASES, O SEA SUS POSICIONES Y DIMENSIONES (MIRA LOS ATRIBUTOS DE LA CLASE CLASE)
		// TRATA DE CREAR UN ALGORITMO QUE CREE UN OBJETO RELACION YA SEA HERENCIA O COMPOSICION QUE TENGA LAS POSICIONES ADECUADAS PARA QUE SE PINTE LA RELACION DE FORMA ADECUADA
		return null;
	}

	public ArrayList<Clase> buscarHijosdeClase(String nombreClase){
		return null;

	}
	
	 
	// FIN OPERACIONES HERENCIA
	
	// PARTE ADRIAN IMPLEMENTAR TODOS LOS DEMAS METODOS BASADOS EN LAS OPERACIONES SOBRE LAS RELACIONES
	
	// FIN OPERACIONES RELACIONES



	public int totalClases(){ // Metodo para obtener la cantidad de clases del diagrama
		return this.grafoClases.getVerticesList().size();
	}

	public int cantClasesConcretas(){ // Metodo para obtener la cantidad de clases concretas del diagrama
		int count = 0;
		Iterator<Vertex> iter = this.grafoClases.getVerticesList().iterator();

		while (iter.hasNext() ) {
			Clase claseAux = (Clase) iter.next().getInfo();
			if (claseAux instanceof Concreta)
				count++;
		}

		return count;
	}

	public int cantClasesAbstractas(){ // Metodo para obtener la cantidad de clases abstractas del diagrama
		int count = 0;
		Iterator<Vertex> iter = this.grafoClases.getVerticesList().iterator();

		while (iter.hasNext() ) {
			Clase claseAux = (Clase) iter.next().getInfo();
			if (claseAux instanceof Abstracta)
				count++;
		}

		return count;

	}

	/*public int totalAtributos(){ // Metodo para obtener la cantidad de atributos
	int cant = 0;

	for (Clase a : this.clases) {

		cant+=a.cantAtributos();

	}

	return cant;

}

public int totalMetodos(){
	int cant = 0;

	for (Clase a : this.clases) {

		cant+=a.cantMetodos();

	}

	return cant;

}

public int cantMetodosConcretos(){
	int cant = 0;

	for (Clase a : this.clases) {

		cant+=a.cantMetodosConcretos();

	}

	return cant;

}

public int cantMetodosAbstractos(){
	int cant = 0;

	for (Clase a : this.clases) {

		cant+=a.cantMetodosAbstractos();

	}

	return cant;

}

public int totalParametros(){

	int cant = 0;

	for (Clase a : this.clases) {

		cant+=a.totalParametros();

	}

	return cant;

}
	 */



	//Reporte 1
	/*public ArrayList<Clase> clasesConMasSuperClases(){
	ArrayList<Clase> clases = new ArrayList<Clase>();
	int mayor = -1;

	for (Clase a : this.clases) {
		if(a.cantProgenitores() > mayor){
			clases.clear();
			clases.add(a);
			mayor = a.cantProgenitores();

		}
		else if (a.cantProgenitores() == mayor){
			clases.add(a);
		}

	}

	return clases;
}
//Reporte 2
public ArrayList<Clase> claseMasRelaciones(){
	ArrayList<Clase> clasesMasRelaciones = new ArrayList<Clase>() ;
	int cantRelaciones = 0;
	int mayor = 0;

	for (Clase a : this.clases) {
		cantRelaciones = a.cantRelaciones();
		if(cantRelaciones > mayor){
			clasesMasRelaciones.clear();
			clasesMasRelaciones.add(a);
			mayor = cantRelaciones;		
		}
		else if (cantRelaciones == mayor){
			clasesMasRelaciones.add(a);
		}
	}


	return clasesMasRelaciones;
}

//Reporte 3
public ArrayList<Clase> clasesSoloMetodosAbstractos(){
	ArrayList<Clase> clases = new ArrayList<Clase>();

	for (Clase a : this.clases) {
		if(a instanceof Abstracta && ((Abstracta) a).soloMetedosAbstractos())
			clases.add(a);
	}

	return clases;

}*/


	// METODOS PARA LA COMPARACION PARA EL SISTEMA DE GUARDADO

	public boolean equals(Diagrama d){
		boolean verificador = false;

		if(this.equalsNombre(d) && this.equalsClases(d))
			verificador = true;

		return verificador;
	}

	private boolean equalsNombre (Diagrama d){
		boolean verificador = false;

		if(this.nombre.equals(d.getNombre()))
			verificador = true;

		return verificador;
	}

	private boolean equalsClases (Diagrama d){ // TEMPORAL
		boolean verificador = true;
		Iterator<Vertex> iterD = d.getGrafoClases().getVerticesList().iterator();
		Iterator<Vertex> iter = this.grafoClases.getVerticesList().iterator();

		if(this.grafoClases.getVerticesList().size() == d.getGrafoClases().getVerticesList().size()){
			while (iterD.hasNext() && verificador) {
				Clase claseAuxD = (Clase) iterD.next().getInfo();
				Clase claseAux = (Clase) iter.next().getInfo();
				if(!claseAuxD.equals(claseAux)) // utilizamos el metodo equals de implementacion propia para comparar dos referencias distintas del objeto clase
					verificador = false;
			}
		}
		else
			verificador = false;

		return verificador;
	}

	// FIN DE METODOS PARA LA COMPARACION PARA EL SISTEMA DE GUARDADO

	public void eliminarRelaciones(String nombre){
		int i = 0;
		boolean x = false;
		if(this.flechasHerencia.size() != 0){

			while(i<this.flechasHerencia.size()){
				x = false;
				if(this.flechasHerencia.get(i).getHijo().equals(nombre)||this.flechasHerencia.get(i).getPadre().equals(nombre)){
					this.flechasHerencia.remove(i);
					x = true;
				}
				i++;
				if(x){
					i = 0;
				}
			}
		}
	}

}
