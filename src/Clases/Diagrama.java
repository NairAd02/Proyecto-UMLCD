package Clases;

import grafo.LinkedGraph;
import grafo.Vertex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import util.Flecha;



public class Diagrama implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedGraph grafoClases; // HACER IMPLEMENTACION PROPIA
	private String nombre;
	private ArrayList<Flecha> flechasHerencia;
	private boolean estadoModificacion; // false si no se ha modificado, true si fue modificado




	public Diagrama(String nombre) {

		this.grafoClases = new LinkedGraph();
		this.nombre = nombre;
		this.estadoModificacion = false;
		this.flechasHerencia = new ArrayList<Flecha>();
	}

	public Diagrama(){} // Constructor json



	public boolean isEstadoModificacion() {
		return estadoModificacion;
	}

	public void setEstadoModificacion(boolean estadoModificacion) {
		this.estadoModificacion = estadoModificacion;
	}

	public void addFlechaHerencia(Flecha flecha){
		this.flechasHerencia.add(flecha);
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


	public LinkedGraph getGrafoClases() {
		return grafoClases;
	}

	public void setGrafoClases(LinkedGraph grafoClases) {
		this.grafoClases = grafoClases;
	}


	// OPERACIONES SOBRE EL DIAGRAMA
	public void addClase(Clase clase){

		if(this.validar(clase)){
			this.grafoClases.addVertice(clase); // se inserta la clase como vertice del grafo
			this.estadoModificacion = true; // se indica que el diagrama fue modificado
		}
		else
			throw new IllegalArgumentException();
	}



	public boolean validar(Clase clase) {
		boolean validator = true;
		Iterator<Vertex> iter = this.grafoClases.getVertices().iterator();

		while (iter.hasNext() && validator){
			Clase claseAux = (Clase) iter.next().getInfo(); // se obtiene la clase del vertice correspodiente
			if(clase.getNombre().equalsIgnoreCase(claseAux.getNombre()))
				validator = false;
		}

		return validator;
	}


	public Clase buscarClase(String nombre){ // Buscar una clase en el grafo segun su nombre
		Clase clase = null;
		Iterator<Vertex> iter = this.grafoClases.getVertices().iterator();

		while (iter.hasNext() && clase == null) {
			Clase claseAux = (Clase) iter.next().getInfo();
			if (claseAux.getNombre().equalsIgnoreCase(nombre))
				clase = claseAux;
		}

		return clase;
	}


	public int posClase(Clase clase){ // Buscar una clase en el grafo segun su referencia
		int pos = -1;
		Iterator<Vertex> iter = this.grafoClases.getVertices().iterator();

		while (iter.hasNext() && pos == -1) {
			Clase claseAux = (Clase) iter.next().getInfo();
			if (claseAux.equals(clase))
				clase = claseAux;
		}

		return pos;
	}

	public void eliminarClase (Clase clase) { // Metodo para eliminar una clase del grafo
		this.grafoClases.deleteVertice(clase); // implementacion temporal
		this.estadoModificacion = true; // se indica que el diagrama fue modificado
	}



	// FIN OPERACIONES SOBRE EL DIAGRAMA
	// OPERACIONES SOBRE LAS CLASES

	// Insercciones
	public void addAtributoClase (Clase clase, Atributo atributo) throws Exception { // Metodo para añadir un atributo a una clase
		clase.addAtributo(atributo);
		this.estadoModificacion = true; // se indica que el diagrama fue modificado
	}

	public void generarConstructorConTodosAtributos (Clase clase) {
		clase.generarConstructorAtributos();
		this.estadoModificacion = true;
	}

	public void addMetodoClase (Clase clase, Metodo metodo) throws Exception { // Metodo para añadir un metodo a una clase
		clase.addMetodo(metodo);
		this.estadoModificacion = true; // se indica que el diagrama fue modificado
	}

	public void generarGetAndSetAtributos (Clase clase) { // Metodo para generar los metodos get y set de todos los atributos de una clase
		clase.generarGetsAndSets();
		this.estadoModificacion = true;
	}

	public void generarGetAndSetAtributo (Clase clase, Atributo atributo) { // Metodo para generar los get y set de un atributo de una clase en especifico
		clase.generarGetAndSet(atributo);
		this.estadoModificacion = true;
	}
	// Fin de insercciones

	// Eliminaciones

	public void deleteAtributoClase (Clase clase, Atributo atributo) {
		clase.eliminarAtributo(atributo);
		this.estadoModificacion = true; // se indica que el diagrama fue modificado
	}

	public void deleteMetodoClase (Clase clase, Metodo metodo) {
		clase.elminarMetodo(metodo);
		this.estadoModificacion = true; // se indica que el diagrama fue modificado
	}

	// Fin de eliminaciones

	// Modificaciones

	public void modificarNombreClase (Clase clase, String nombre) throws Exception {
		clase.setNombre(nombre);
		this.estadoModificacion = true; // se indica que el diagrama fue modificado
	}

	public void modificarAtributo (Atributo atributo, String nombre, String tipoDato, String acceso) {
		atributo.setNombre(nombre);
		atributo.setTipoDato(tipoDato);
		atributo.setVisibilidad(acceso);
		this.estadoModificacion = true; // se indica que el diagrama fue modificado
	}

	public void modificarMetodoOrdinario (Metodo metodo, String nombre, String tipoDato, String acceso, boolean isAbstracto, ArrayList<Parametro> parametros) {
		metodo.setNombre(nombre);
		((MetodoOrdinario) metodo).setTipoRetorno(tipoDato);
		metodo.setModificadorAcceso(acceso);
		((MetodoOrdinario) metodo).setAbstracto(isAbstracto);
		metodo.setParametros(parametros);
		this.estadoModificacion = true; // se indica que el diagrama fue modificado
	}

	public void modificarColorClase (Clase clase, String color) {
		clase.setColor(color);
		this.estadoModificacion = true; // se indica que el diagrama fue modificado
	}

	// Fin de modificaciones


	// OPERACIONES RELACIONES
	// OPERACIONES HERENCIA
	public Clase obtenerPadreClase(String nombreClase){
		return null;
	}
	// AKIIIII DARIOOOOOOOOOOOO
	public void addRelacionHerencia(Clase clasePadre, Clase claseHija) throws Exception{
		this.grafoClases.addArista(this.grafoClases.buscarVertice(claseHija), this.grafoClases.buscarVertice(clasePadre), determinarBoundsRelacionHerencia(clasePadre, claseHija));;
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
		return this.grafoClases.getVertices().size();
	}

	public int cantClasesConcretas(){ // Metodo para obtener la cantidad de clases concretas del diagrama
		int count = 0;
		Iterator<Vertex> iter = this.grafoClases.getVertices().iterator();

		while (iter.hasNext() ) {
			Clase claseAux = (Clase) iter.next().getInfo();
			if (claseAux instanceof Concreta)
				count++;
		}

		return count;
	}

	public int cantClasesAbstractas(){ // Metodo para obtener la cantidad de clases abstractas del diagrama
		int count = 0;
		Iterator<Vertex> iter = this.grafoClases.getVertices().iterator();

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
