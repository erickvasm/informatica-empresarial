package modelo;

//Transaccion consulta
public class Consulta extends Transaccion{

	//Atributo
	private double saldo;
	
	
	//Constructor
	public Consulta() {
		
	}

	
	//Set & Get
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	//Mostrar infomacion
	public String mostrarInformacion() {
		return "Transaccion consulta:\n"+super.mostrarInformacion()+"\nSaldo:"+this.getSaldo();
	}
	
	
	//Transformacion para guardar
	public String transformacion() {
		return super.transformar()+","+this.getSaldo();
	}
	
}