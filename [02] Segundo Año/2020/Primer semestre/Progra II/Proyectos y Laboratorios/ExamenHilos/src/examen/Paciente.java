package examen;

//Clase paciente hereda de persona
public class Paciente extends Persona{
	
	//propiedades
	private String condicion;
	private String horaRegistro;
	private int diasH;
	
	//Constructor vacio
	public Paciente() {
		
	}

	@Override
	public String mostrarInformacion() {
		return "\t\tInformacion del Paciente:\nIdentificacion:"+super.getIdentificacion()+"\nNombre:"+super.getNombre()+
				"\nApellido:"+super.getApellido()+"\nDireccion:"+super.getDireccion()+"\nCondicion:"+getCondicion()+"\nGenero:"
				+((super.getGenero()=='F')?"Femenino":"Masculino")+"\nDias Hospitalizado:"+getDiasH()+"\nHora de Registro:"+getHoraRegistro();
	}
	
	
	
	//M�todos SET Y GET
	
	public void setHoraRegistro(String hour) {
		this.horaRegistro=hour;
	}
	
	
	public String getHoraRegistro() {
		return this.horaRegistro;
	}
	
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
