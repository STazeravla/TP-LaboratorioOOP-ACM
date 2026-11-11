package TP_Fatal_Final.modelo;

public class Persona {

	private int DNI;
	private String nombreCompleto;
	private String fechaDeNacimiento;
	private String situacionLaboral;
	private Barrio barrio;
	
	public Persona(int dNI, String nombreCompleto, String fechaDeNacimiento, String situacionLaboral, Barrio barrio) {
		super();
		DNI = dNI;
		this.nombreCompleto = nombreCompleto;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.situacionLaboral = situacionLaboral;
		this.barrio = barrio;
	}
	
	
	public int getDNI() {
		return DNI;
	}
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public String getSituacionLaboral() {
		return situacionLaboral;
	}
	public void setSituacionLaboral(String situacionLaboral) {
		this.situacionLaboral = situacionLaboral;
	}
	
	
}
