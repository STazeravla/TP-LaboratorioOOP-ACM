package TP_Fatal_Final.modelo;

import java.util.ArrayList;

public class BTrazadoFormal extends Barrio {

	private int cantidadDeEdificiosHorizontales;
	private int porAsfaltoEnCalles;
	
	ArrayList<Persona> personas = new ArrayList<>();
	
	
	
	public BTrazadoFormal(int cantidadDeEdificiosHorizontales, int porAsfaltoEnCalles, ArrayList<Persona> personas) {
		super();
		this.cantidadDeEdificiosHorizontales = cantidadDeEdificiosHorizontales;
		this.porAsfaltoEnCalles = porAsfaltoEnCalles;
		this.personas = personas;
	}

	public int calcularDensidad() {
		return personas.size() / getCantidadDeEdificiosHorizontales();
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
