package modelo;

//Interfaz para nivel administrativo
public interface Jefatura {
	//Nivel Administrativo
	int perHour=2500;
	int extra=3200;
	
	//Metodo abstracto calcular pago
	public abstract void calcularPago();
}
