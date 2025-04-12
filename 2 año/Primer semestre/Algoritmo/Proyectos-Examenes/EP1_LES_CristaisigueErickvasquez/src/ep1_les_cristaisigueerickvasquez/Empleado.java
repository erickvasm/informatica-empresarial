package ep1_les_cristaisigueerickvasquez;

//Clase empleado con implementacion de Pago
public class Empleado implements Pago{

	//Atributos
	private String nombre;
	private String numeroEmpleado;
	private int horas;
	private int nLlamadas;
	private int tiempoLlamadas;
	private double salario;
	private Lista llamadas;
	
	
	//Constructor
	public Empleado() {
		llamadas=new Lista();
	}
	
	
	//Calcular pago del empleado
	@Override
	public double calcularPago() {
		double general=0;
		if((getnLlamadas()>200) && (getTiempoLlamadas()>=300)) {
			general=pagoUno*getnLlamadas();
		}else {
			general=pagoDos*getnLlamadas();
		}
		return general;
	}
	
	
	
	//Actualizar la informacion del empleado
	public void actualizarInformacion() {
		setTiempoLlamadas(llamadas.totalTiempo());
		setnLlamadas(llamadas.totalLlamadas());
		setSalario(calcularPago());
	}
	
	//Mostrar la informacion del empleado (Con,Sin) llamadas
	public String mostrarInformacion(boolean type) {
		actualizarInformacion();
		return "\tEmpleado:\nNumero Empleado: "+getNumeroEmpleado()+"\nNombre: "+getNombre()+"\nHoras trabajadas: "+getHoras()+
				"\nDuracion Llamadas: "+getTiempoLlamadas()+"\nN° Llamadas recibidas: "+getnLlamadas()
				+"\nSalario: "+getSalario()+((type)?((getnLlamadas()==0)?"\n No se han ingresado llamadas":llamadas.mostrarLLamadas()):"");
						//"\n No se han ingresado llamadas":llamadas.mostrarLLamadas());
	}

	
	
	//Set & Get

	//Obtener la lista de llamadas
	public Lista obtenerLlamadas() {
		return llamadas;
	}
	
	
	public String getNumeroEmpleado() {
		return numeroEmpleado;
	}


	public void setNumeroEmpleado(String numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getHoras() {
		return horas;
	}


	public void setHoras(int horas) {
		this.horas = horas;
	}


	public int getnLlamadas() {
		return nLlamadas;
	}


	public void setnLlamadas(int nLlamadas) {
		this.nLlamadas = nLlamadas;
	}


	public int getTiempoLlamadas() {
		return tiempoLlamadas;
	}


	public void setTiempoLlamadas(int tiempoLlamadas) {
		this.tiempoLlamadas = tiempoLlamadas;
	}


	public double getSalario() {
		return salario;
	}


	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	
	
	
	
	
}
