/**
 *@author Cris
 * @author Erick
 */
package modelo;

//Clase abstracta persona
public abstract class  Persona {

	
	//Atributos
    private String nombre, apellido, identificacion;
    private char genero;

    //Constructor
    public Persona(){

    }
    
    
    //metodo para recolectar atributos de persona
    public String recolectarInformacion(){    
        return "Nombre: "+getNombre()+" \nApellido: "+getApellido()+"\nIdentificacion: "+getIdentificacion()+"\nGenero: "+getGenero(); 
    }
    
    //metodo que se sobre-escribira para mostrar la información del empleado
    public abstract String mostrarInformacion();

    
    
    //Set & Get
    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setCedula(String cedula) {
        this.identificacion = cedula;
    }
    
    
}
