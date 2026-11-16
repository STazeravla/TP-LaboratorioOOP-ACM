package TP_Fatal_Final.modelo;

public class Comuna {
	
	private int numeroComuna;
	private String nombreDeSede;
	private int numeroBarrios;
	private String tipoDeBarrio;
	private String numeroBarrio;
	
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

	public int getNumeroBarrios() {
		return numeroBarrios;
	}

	public void setNumeroBarrios(int numeroBarrios) {
		this.numeroBarrios = numeroBarrios;
	}

	public String getTipoDeBarrio() {
		return tipoDeBarrio;
	}

	public void setTipoDeBarrio(String tipoDeBarrio) {
		this.tipoDeBarrio = tipoDeBarrio;
	}
	
	
	@Override
	public String toString() {
		return "Comuna [Numero=" + numeroComuna + ", nombre=" + nombreDeSede + ", numeroBarrio="
				+ numeroBarrio +", tipoDeBarrio="
						+ tipoDeBarrio + "]";
	}
}
