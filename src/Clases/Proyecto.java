package Clases;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class Proyecto implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private LinkedList<Diagrama> diagramasMostrados;
	private LinkedList<Diagrama> diagramasCargados;
	private Diagrama diagramaSeleccionado;
	private boolean estadoModificacion;
	private String rutaDeGuardado;



	public Proyecto(String nombre, String rutaDeGuardado) {
		super();
		this.nombre = nombre;
		this.rutaDeGuardado = rutaDeGuardado; // se alamcena la ruta donde será guardado el proyecto
		this.diagramasMostrados = new LinkedList<Diagrama>();
		this.diagramasCargados = new LinkedList<Diagrama>() ;
		this.diagramaSeleccionado = null ;
		this.estadoModificacion = false;
	}


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
		this.estadoModificacion = true;
	}
	
	public String getRutaDeGuardado() {
		return rutaDeGuardado;
	}
	public void setRutaDeGuardado(String rutaDeGuardado) {
		this.rutaDeGuardado = rutaDeGuardado;
	}
	
	public LinkedList<Diagrama> getDiagramasMostrados() {
		return diagramasMostrados;
	}
	public void setDiagramasMostrados(LinkedList<Diagrama> diagramasMostrados) {
		this.diagramasMostrados = diagramasMostrados;
	}
	
	public LinkedList<Diagrama> getDiagramasCargados() {
		return diagramasCargados;
	}
	public void setDiagramasCargados(LinkedList<Diagrama> diagramasCargados) {
		this.diagramasCargados = diagramasCargados;
	}
	
	public Diagrama getDiagramaSeleccionado() {
		return diagramaSeleccionado;
	}
	public void setDiagramaSeleccionado(Diagrama diagramaSeleccionado) {
		this.diagramaSeleccionado = diagramaSeleccionado;
	}

	public boolean isEstadoModificacion() {
		return estadoModificacion;
	}
	public void setEstadoModificacion(boolean estadoModificacion) {
		this.estadoModificacion = estadoModificacion;
	}

	public LinkedList<Diagrama> obtenerDiagramas () { // Metodo para obtener todos los diagramas, tanto mostrados como cargados
		LinkedList<Diagrama> allDiagramas = new LinkedList<Diagrama>();
		allDiagramas.addAll(this.diagramasCargados);
		allDiagramas.addAll(this.diagramasMostrados);
		return  allDiagramas;
	}


	// OPERACIONES

	public void addDiagrama (Diagrama diagramaInsertar) { // Metodo para insertar un diagrama
		this.diagramaSeleccionado = diagramaInsertar;
		this.diagramasMostrados.add(diagramaInsertar); // se insertar como un diagrama mostrado
		this.estadoModificacion = true; // se indica que el proyecto fue modificado
	}

	public void eliminacionFisicaDiagrama (Diagrama diagramaEliminar) {
		if (!this.diagramasCargados.remove(diagramaEliminar)) // si no se pudo eliminar de esta lista diagramas cargados
			this.diagramasMostrados.remove(diagramaEliminar); // se elimina de la lista de diagramas mostrados
		if (diagramaEliminar.equals(this.diagramaSeleccionado))
			this.actualizarDiagramaSeleccionado();

		this.estadoModificacion = true; // se indica que el proyecto fue modificado
	}

	private void actualizarDiagramaSeleccionado () {
		if (this.diagramasMostrados.size() != 0)
			this.diagramaSeleccionado = this.diagramasMostrados.getLast(); // se asigna como seleccionado al ultimo diagrama mostrado
		else
			this.diagramaSeleccionado = null;
	}

	public void eliminarSeleccionDiagrama (Diagrama diagrama) { // Metodo para eliminar un diagrama mostrado e insertarlo como cargado
		this.diagramasMostrados.remove(diagrama); // se elimina el diagrama de los mostrados
		this.diagramasCargados.add(diagrama); // se inserta como cargado

		if (this.diagramaSeleccionado.equals(diagrama)){ // si el diagrama actualmente selccionado resulta ser el que se desea  eliminar
			this.actualizarDiagramaSeleccionado();
		}
	}

	public void eliminarDiagrama () {
		// Se borra permanentemente la información del diagrama
	}


	public void mostrarDiagrama (Diagrama diagramaMostrar) { // Metodo para seleccionar un diagrama
		//Primero verificamos si el diagrama ya está siendo mostrado o no
		if (this.verificarSiEsMostrado(diagramaMostrar))  // si ya está siendo mostrado solo actualizamos al diagrama seleccionado con el diagrama que se desea mostrar
			this.diagramaSeleccionado = diagramaMostrar;
		else // si no entonces lo mostramos
			this.mostrarDiagramaCargado(diagramaMostrar);

	}

	private boolean verificarSiEsMostrado (Diagrama diagrama) { // Metodo para verificar si un diagrama ya está siendo mostrado
		boolean isMostrado = false;
		Iterator<Diagrama> iter = this.diagramasMostrados.iterator();

		while (iter.hasNext() && !isMostrado) {
			if (iter.next().equals(diagrama))
				isMostrado = true;
		}

		return isMostrado;
	}

	private void mostrarDiagramaCargado (Diagrama diagramaMostrar) {
		this.diagramasCargados.remove(diagramaMostrar); // se elimina de los cargados para evitar la duplicidad
		this.diagramasMostrados.add(diagramaMostrar); // se inserta en los diagramas mostrados
		this.diagramaSeleccionado = diagramaMostrar; // se asigna como diagrama actualmente seleccionado
	}



	// METODOS SISTEMA GUARDADO
	public void restablecerEstadoDeModificacion () {
		this.estadoModificacion = false;
		this.restablecerEstadoModificacionDiagramas();

	}

	private void restablecerEstadoModificacionDiagramas () {
		this.estadoModificacion = false;
		Iterator<Diagrama> iterDiagramas = this.obtenerDiagramas().iterator();
		// Se recorren todos los diagramas del proyecto y se les restablece su estado de modificacion a false
		while (iterDiagramas.hasNext()) {
			iterDiagramas.next().setEstadoModificacion(false);
		}	
	}

	public boolean isModificado () { // Metodo para determinar si se le realizó alguna modificación al proyecto
		boolean modificado = false;

		if (this.estadoModificacion || this.verificarExistenciaDiagramaModificado())
			modificado = true;

		return modificado;
	}

	private boolean verificarExistenciaDiagramaModificado () {
		boolean isModificado = false;
		Iterator<Diagrama> iterDiagramas = this.obtenerDiagramas().iterator();

		while (iterDiagramas.hasNext() && !isModificado) {
			if (iterDiagramas.next().isEstadoModificacion())
				isModificado = true; // se encontró un diagrama modificado
		}

		return isModificado;
	}

	// FIN DE METODOS SISTEMA GUARDADO



	// FIN DE OPERACIONES

}
