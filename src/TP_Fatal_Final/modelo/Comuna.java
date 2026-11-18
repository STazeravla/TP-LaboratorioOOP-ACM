package TP_Fatal_Final.modelo;

public class Comuna {
	
	private int numeroComuna;
	private String nombreDeSede;
	private int cantidadBarrios;
	
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

	public int getCantidadBarrios() {
		return cantidadBarrios;
	}

	public void setCantidadBarrios(int numeroBarrios) {
		this.cantidadBarrios = numeroBarrios;
	}
	
	
	@Override
	public String toString() {
		return "Comuna [Numero=" + numeroComuna + ", nombre=" + nombreDeSede + ", cantidadBarrios="
				+ cantidadBarrios + "]";
	}
}
