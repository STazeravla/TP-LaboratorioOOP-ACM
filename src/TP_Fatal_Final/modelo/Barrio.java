package TP_Fatal_Final.modelo;

import java.util.ArrayList;

public abstract class Barrio {

	
	private String nombreBarrio;
	private int superficie;
	
	private ArrayList<Persona> personas = new ArrayList<>();
	
	public Barrio(String nombreBarrio, int superficie) {
		super();
		this.nombreBarrio = nombreBarrio;
		this.superficie = superficie;
	}

	public String getNombreBarrio() {
		return nombreBarrio;
	}
	public void setNombreBarrio(String nombreBarrio) {
		this.nombreBarrio = nombreBarrio;
	}
	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public abstract boolean tieneCriticidadHabitacional();

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
}
