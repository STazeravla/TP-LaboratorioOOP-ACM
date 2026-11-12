package TP_Fatal_Final.modelo;

public class Comuna {
	
	private int numeroComuna;
	private String nombreDeSede;
	private String juntaComunal;
	
	//public Comuna(int numeroComuna, String nombreDeSede, String juntaComunal){
	//	super();
	//	this.numeroComuna = numeroComuna;
	//	this.nombreDeSede = nombreDeSede;
	//	this.juntaComunal = juntaComunal;
	//}
	
	
	//public atencionPrioritaria() {
	//	
	//}

	public int getNumeroComuna() {
		return numeroComuna;
	}

	public void setNumeroComuna(int numeroComuna) {
		this.numeroComuna = numeroComuna;
	}

	public String getNombreDeSede() {
		return nombreDeSede;
	}

	public void setNombreDeSede(String nombreDeSede) {
		this.nombreDeSede = nombreDeSede;
	}

	public String getJuntaComunal() {
		return juntaComunal;
	}

	public void setJuntaComunal(String juntaComunal) {
		this.juntaComunal = juntaComunal;
	}
	
	
	@Override
	public String toString() {
		return "Comuna [Numero=" + numeroComuna + ", nombre=" + nombreDeSede + ", juntaComunal="
				+ juntaComunal + "]";
	}
}
