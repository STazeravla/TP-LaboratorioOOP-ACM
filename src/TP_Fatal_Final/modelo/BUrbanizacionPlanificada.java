package TP_Fatal_Final.modelo;

import java.util.ArrayList;

public class BUrbanizacionPlanificada extends Barrio {
	
		private int numeroDeLey;
		private boolean tieneAguaPotable;
		
		ArrayList<Persona> personas = new ArrayList<>();
		
		
		
		public BUrbanizacionPlanificada(int numeroDeLey, boolean tieneAguaPotable, ArrayList<Persona> personas) {
			super();
			this.numeroDeLey = numeroDeLey;
			this.tieneAguaPotable = tieneAguaPotable;
			this.personas = personas;
		}

		public int calcularDensidad() {
			return personas.size() / getSuperficie();
		}
		
		public boolean tieneCriticidadHabitacional() {
			return isTieneAguaPotable() == false;
		}

		public int getNumeroDeLey() {
			return numeroDeLey;
		}

		public void setNumeroDeLey(int numeroDeLey) {
			this.numeroDeLey = numeroDeLey;
		}

		public boolean isTieneAguaPotable() {
			return tieneAguaPotable;
		}

		public void setTieneAguaPotable(boolean tieneAguaPotable) {
			this.tieneAguaPotable = tieneAguaPotable;
		}

		public ArrayList<Persona> getPersonas() {
			return personas;
		}

		public void setPersonas(ArrayList<Persona> personas) {
			this.personas = personas;
		}
	}
