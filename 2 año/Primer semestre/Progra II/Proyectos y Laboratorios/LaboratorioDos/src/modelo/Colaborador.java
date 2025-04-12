package modelo;

//Interfaz colaborador
public interface Colaborador {
	//Nivel Operativo
	int operativeHour=1500;
	int operativeExtra=2500;
	
	//Calcular Pago
	public abstract void calcularPago();
}
