package modelo;

//Clase paciente hereda de persona
public class Paciente extends Persona{
	
	//propiedades
	private String condicion;
	private int diasH;
	
	//Constructor vacio
	public Paciente() {
		
	}

	
	@Override
	public String mostrarInformacion() {
		return "\t\tInformacion del Paciente:\nIdentificacion:"+super.getIdentificacion()+"\nNombre:"+super.getNombre()+
				"\nApellido:"+super.getApellido()+"\nDireccion:"+super.getDireccion()+"\nCondicion:"+getCondicion()+"\nGenero:"
				+((super.getGenero()=='F')?"Femenino":"Masculino")+"\nDias Hospitalizado:"+getDiasH();
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
