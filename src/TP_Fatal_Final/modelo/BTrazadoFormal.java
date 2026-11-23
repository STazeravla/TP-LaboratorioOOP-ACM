package TP_Fatal_Final.modelo;

import java.util.ArrayList;

public class BTrazadoFormal extends Barrio {

	private int cantidadDeEdificiosHorizontales;
	private int porAsfaltoEnCalles;
	
	
	
	
	public BTrazadoFormal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BTrazadoFormal(String nombreBarrio, int superficie, int numeroBarrio, String comuna) {
		super(nombreBarrio, superficie, numeroBarrio, comuna);
		// TODO Auto-generated constructor stub
	}

	public BTrazadoFormal(String nombreBarrio, int superficie, int cantidadDeEdificiosHorizontales, int porAsfaltoEnCalles, ArrayList<Persona> personas, int numeroBarrio, String comuna) {
		super(nombreBarrio, superficie, numeroBarrio, comuna);
		this.cantidadDeEdificiosHorizontales = cantidadDeEdificiosHorizontales;
		this.porAsfaltoEnCalles = porAsfaltoEnCalles;

	}

	public void agregarPersona(Persona p) {
		getPersonas().add(p);
	}
	
	public int calcularDensidad() {
		return getPersonas().size() / getCantidadDeEdificiosHorizontales();
	}
	
	public boolean tieneCriticidadHabitacional() {
		return getPorAsfaltoEnCalles() < 95;
	}

	public int getCantidadDeEdificiosHorizontales() {
		return cantidadDeEdificiosHorizontales;
	}

	public void setCantidadDeEdificiosHorizontales(int cantidadDeEdificiosHorizontales) {
		this.cantidadDeEdificiosHorizontales = cantidadDeEdificiosHorizontales;
	}

	public int getPorAsfaltoEnCalles() {
		return porAsfaltoEnCalles;
	}

	public void setPorAsfaltoEnCalles(int porAsfaltoEnCalles) {
		this.porAsfaltoEnCalles = porAsfaltoEnCalles;
	}
}