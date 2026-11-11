package TP_Fatal_Final.modelo;

import java.util.ArrayList;

public class BUrbanizacionPlanificada extends Barrio {
	
		private int numeroDeLey;
		private boolean tieneAguaPotable;
		
		
		
		
		public BUrbanizacionPlanificada(String nombreBarrio, int superficie, int numeroDeLey, boolean tieneAguaPotable, ArrayList<Persona> personas) {
			super(nombreBarrio, superficie);
			this.numeroDeLey = numeroDeLey;
			this.tieneAguaPotable = tieneAguaPotable;
		}
		
		public void agregarPersona(Persona p) {
			getPersonas().add(p);
		}
		
		public int calcularDensidad() {
			return getPersonas().size() / getSuperficie();
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
	}
