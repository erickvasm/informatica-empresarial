package modelo;

//Clase funcionario
public class Funcionario extends Persona{
	
	
	//Atributos (Encapsulacion)
	private int horasSemanales;
	private String funcion;
	private String carnetFuncionario;
	
	
	//Constructor
	public Funcionario() {
		super();
	}

	
	
	//Metodo Abstracto Sobre-escrito (Polimorfismo)
	@Override
	public String desplegarInformacion() {
		return "\t\tDetalles Funcionario:\n"+super.mostrarPersona()+"\n"+mostrarFuncionario();
	}
	
	
	
	//Mostrar la informacion del Funcionario
	public String mostrarFuncionario() {
		return "Carnet Funcionario: "+getCarnetFuncionario()+"\nFuncion: "+getFuncion()+"\n Horas Semanales: "+getHorasSemanales();
	}


	//SET & GET
	public int getHorasSemanales() {
		return horasSemanales;
	}



	public void setHorasSemanales(int horas) {
		this.horasSemanales = horas;
	}



	public String getFuncion() {
		return funcion;
	}



	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}



	public String getCarnetFuncionario() {
		return carnetFuncionario;
	}



	public void setCarnetFuncionario(String carnetFuncionario) {
		this.carnetFuncionario = carnetFuncionario;
	}
	
	
	
	
}
