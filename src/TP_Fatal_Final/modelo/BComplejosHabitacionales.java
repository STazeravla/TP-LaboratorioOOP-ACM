package TP_Fatal_Final.modelo;

import java.util.ArrayList;

public class BComplejosHabitacionales extends Barrio {

	private int cantidadDeMonoblocks;
	private boolean tieneAdministracionActiva;
	
	public BComplejosHabitacionales() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BComplejosHabitacionales(String nombreBarrio, int superficie, int numeroBarrio, String comuna) {
		super(nombreBarrio, superficie, numeroBarrio, comuna);
		// TODO Auto-generated constructor stub
	}
	
	
	public BComplejosHabitacionales(String nombreBarrio, int superficie, int cantidadDeMonoblocks, boolean tieneAdministracionActiva, ArrayList<Persona> personas) {
		super();
		this.cantidadDeMonoblocks = cantidadDeMonoblocks;
		this.tieneAdministracionActiva= tieneAdministracionActiva;
	}

	public void agregarPersona(Persona p) {
		getPersonas().add(p);
	}
	
	public int calcularDensidad() {
		return getPersonas().size() / getCantidadDeMonoblocks() ;
	}
	
	public boolean tieneCriticidadHabitacional() {
		return isTieneAdministracionActiva() == false;
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