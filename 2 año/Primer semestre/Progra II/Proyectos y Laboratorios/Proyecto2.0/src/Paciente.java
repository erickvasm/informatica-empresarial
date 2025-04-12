/**
 * @author Erick Vasquez Murillo B983344
 */

//Clase paciente hereda de persona
public class Paciente extends Persona{
	
	//Encapsulamiento y ocultación
	private String condicion;
	private int dias;
	
	//primer constructor
	public Paciente(String identificacion, String nombre, String apellido, String genero, int telefono,
			String condicion, int dias) {

		this.condicion = condicion;
		this.dias = dias;
	}

	/**
	 * Metodo proveniente de clase Persona que se sobre-escribe 
	 * para cumplir con el polimorfismo
	 */
	@Override
	public String mostrarInformacion() {
		return "\t\tInformacion del Paciente:\nIdentificacion:"+super.getIdentificacion()+"\nNombre:"+super.getNombre()+
				"\nApellido:"+super.getApellido()+"\nDireccion:"+super.getDireccion()+"\nCondicion:"+getCondicion()+"\nGenero:"
				+((super.getGenero()=='F')?"Femenino":"Masculino")+"\nDias Hospitalizado:"+getDias();
	} 
	
	//Métodos SET Y GET de los atributos
	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int diasH) {
		this.dias = diasH;
	}
	
	
}
