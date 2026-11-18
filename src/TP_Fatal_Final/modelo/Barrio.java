package TP_Fatal_Final.modelo;

import java.util.ArrayList;

import javax.swing.JTextField;

public abstract class Barrio {

	
	private String nombreBarrio;
	private int superficie;
	private int numeroBarrio;
	private String tipoDeBarrio;
	
	private ArrayList<Persona> personas = new ArrayList<>();
	
	public Barrio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Barrio(String nombreBarrio, int superficie, int numeroBarrio) {
		super();
		this.numeroBarrio= numeroBarrio;
		this.nombreBarrio = nombreBarrio;
		this.superficie = superficie;
	}

	public String getNombreBarrio() {
		return nombreBarrio;
	}
	public void setNombreBarrio(String string) {
		this.nombreBarrio = string;
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

	public int getNumeroBarrio() {
		return numeroBarrio;
	}

	public void setNumeroBarrio(int numeroBarrio) {
		this.numeroBarrio = numeroBarrio;
	}

	public String getTipoDeBarrio() {
		return tipoDeBarrio;
	}

	public void setTipoDeBarrio(String tipoDeBarrio) {
		this.tipoDeBarrio = tipoDeBarrio;
	}
}