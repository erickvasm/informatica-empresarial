package modelo;

//Transaccion Retiro
public class Retiro extends Transaccion{

	
	//Atributos
	private double montoDebitado;
	private double saldoCorrespondiente;
	
	
	//Constructor
	public Retiro() {
		
	}


	
	//Set & get
	public double getMontoDebitado() {
		return montoDebitado;
	}


	public void setMontoDebitado(double montoDebitado) {
		this.montoDebitado = montoDebitado;
	}


	public double getSaldoCorrespondiente() {
		return saldoCorrespondiente;
	}


	public void setSaldoCorrespondiente(double saldoCorrespondiente) {
		this.saldoCorrespondiente = saldoCorrespondiente;
	}
	
	
	//Mostrar informacion
	public String mostrarInformacion() {
		return "Transaccion Retiro:\n"+super.mostrarInformacion()+"\nMonto debitado:"+this.getMontoDebitado()+"\nSaldo correspondiente:"+this.getSaldoCorrespondiente();
	}
	
	
	//Transformar para guardar
	public String transformacion() {
		return super.transformar()+","+this.getMontoDebitado()+","+this.getSaldoCorrespondiente();
	}
	
}
