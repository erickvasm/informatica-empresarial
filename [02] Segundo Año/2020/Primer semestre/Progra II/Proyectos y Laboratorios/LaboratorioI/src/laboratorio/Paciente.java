package laboratorio;

//Clase paciente hereda de persona
public class Paciente extends Persona{
	
	//propiedades
	private String condicion;
	private int diasH;
	
	//Constructor vacio
	public Paciente() {
	
	}

	//Métodos SET Y GET
	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public int getDiasH() {
		return diasH;
	}

	public void setDiasH(int diasH) {
		this.diasH = diasH;
	}
	
	
}
