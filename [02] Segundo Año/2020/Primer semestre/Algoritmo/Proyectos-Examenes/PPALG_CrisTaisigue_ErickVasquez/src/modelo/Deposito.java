package modelo;

//Transaccion deposito
public class Deposito extends Transaccion{

	
	//Atributos
	private double montoDepositado;
	private double nuevoSaldo;
	
	
	//Constructor
	public Deposito() {
		
	}

	
	//Set & Get
	public double getMontoDepositado() {
		return montoDepositado;
	}

	public void setMontoDepositado(double montoDepositado) {
		this.montoDepositado = montoDepositado;
	}

	public double getNuevoSaldo() {
		return nuevoSaldo;
	}

	public void setNuevoSaldo(double nuevoSaldo) {
		this.nuevoSaldo = nuevoSaldo;
	}	
	
	//Mostrar informacion
	public String mostrarInformacion() {
		return "Transaccion Deposito:\n"+super.mostrarInformacion()+"\nMonto depositado:"+this.getMontoDepositado()+"\nNuevo saldo:"+this.getNuevoSaldo();
	}
	
	
	//Transformar para guardar
	public String transformacion() {
		return super.transformar()+","+this.getMontoDepositado()+","+this.getNuevoSaldo();
	}
	
	
}
