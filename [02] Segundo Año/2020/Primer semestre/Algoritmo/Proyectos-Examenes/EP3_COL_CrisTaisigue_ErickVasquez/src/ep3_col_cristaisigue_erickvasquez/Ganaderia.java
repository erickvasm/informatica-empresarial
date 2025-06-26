package ep3_col_cristaisigue_erickvasquez;

//Clase ganaderia
public class Ganaderia {

	//Atributos
	private String nombreGanaderia;	
	private ColaCabezaRes corral;//Corral asignado en la subasta
	private int numeroPresentado=0;
	private int numeroComprado=0;
	private double montoInvertido=0;
	
	//Constructor
	public Ganaderia() {
		corral=new ColaCabezaRes();
	}

	
	//Set & Get
	public String getNombreGanaderia() {
		return nombreGanaderia;
	}

	public void setNombreGanaderia(String nombreGanaderia) {
		this.nombreGanaderia = nombreGanaderia;
	}

	public ColaCabezaRes getCorral() {
		return corral;
	}

	public void setCorral(ColaCabezaRes corral) {
		this.corral = corral;
	}


	public int getNumeroPresentado() {
		return numeroPresentado;
	}


	public void setNumeroPresentado(int numeroPresentado) {
		this.numeroPresentado = numeroPresentado;
	}


	public int getNumeroComprado() {
		return numeroComprado;
	}


	public void setNumeroComprado(int numeroComprado) {
		this.numeroComprado = numeroComprado;
	}


	public double getMontoInvertido() {
		return montoInvertido;
	}


	public void setMontoInvertido(double montoInvertido) {
		this.montoInvertido = montoInvertido;
	}
	
	
	
}
