package lesc_cristaisigueerickvasquez_2020;

//Clase Estudiante tipo de dato en los Nodos
public class Estudiante {

	//Atributos
	private String nombre;
	private String carnet;
	private double promedioPonderado;
	private char tipoBeca;
	private String carrera;
	
	//Constructor vacio
	public Estudiante() {	
	}


	//Set & Get
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCarnet() {
		return carnet;
	}


	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}


	public double getPromedioPonderado() {
		return promedioPonderado;
	}


	public void setPromedioPonderado(double promedioPonderado) {
		this.promedioPonderado = promedioPonderado;
	}


	public char getTipoBeca() {
		return tipoBeca;
	}


	public void setTipoBeca(char tipoBeca) {
		this.tipoBeca = tipoBeca;
	}


	public String getCarrera() {
		return carrera;
	}


	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
	//Desplegar toda la informacion del estudiante
	public String mostrarInformacion() {
		return "\n\tEstudiante\nCarnet:"+getCarnet()+"\nNombre:"+getNombre()+"\nCarrera:"+getCarrera()+
				"\nTipo Beca:"+((getTipoBeca()=='N')?"No posee":getTipoBeca())+"\nPromedio Ponderado:"+getPromedioPonderado();
	}
	
}
