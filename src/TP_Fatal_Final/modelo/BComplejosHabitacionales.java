package TP_Fatal_Final.modelo;

import java.util.ArrayList;

public class BComplejosHabitacionales extends Barrio {

	private int cantidadDeMonoblocks;
	private boolean tieneAdministracionActiva;
	
	ArrayList<Persona> personas = new ArrayList<>();
	
	
	
	public BComplejosHabitacionales(int cantidadDeMonoblocks, boolean tieneAdministracionActiva, ArrayList<Persona> personas) {
		super();
		this.cantidadDeMonoblocks = cantidadDeMonoblocks;
		this.tieneAdministracionActiva= tieneAdministracionActiva;
		this.personas = personas;
	}

	public int calcularDensidad() {
		return personas.size() / getCantidadDeMonoblocks() ;
	}
	
	public boolean tieneCriticidadHabitacional() {
		return isTieneAdministracionActiva() == false;
	}

	

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}

	public int getCantidadDeMonoblocks() {
		return cantidadDeMonoblocks;
	}

	public void setCantidadDeMonoblocks(int cantidadDeMonoblocks) {
		this.cantidadDeMonoblocks = cantidadDeMonoblocks;
	}

	public boolean isTieneAdministracionActiva() {
		return tieneAdministracionActiva;
	}

	public void setTieneAdministracionActiva(boolean tieneAdministracionActiva) {
		this.tieneAdministracionActiva = tieneAdministracionActiva;
	}
}
