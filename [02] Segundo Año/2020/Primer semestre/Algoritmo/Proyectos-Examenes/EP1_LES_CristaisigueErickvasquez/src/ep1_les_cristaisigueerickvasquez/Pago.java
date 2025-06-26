package ep1_les_cristaisigueerickvasquez;

//Interfaz de pago para el empleado
public interface Pago {

	final int condicionalLlamadas=200;//Condicional uno
	final int condicionalTiempo=300;//Condicional dos
	final int pagoUno=275;//Pago uno
	final int pagoDos=125;//Pago dos

	//Metodo abstracto para calcular pago
	public abstract double calcularPago();
	
}
