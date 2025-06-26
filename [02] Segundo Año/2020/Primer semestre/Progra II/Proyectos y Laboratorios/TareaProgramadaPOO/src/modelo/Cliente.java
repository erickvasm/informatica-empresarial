package modelo;

public class Cliente extends Persona{

	private String contacto;
	private String funcAtendedor;
	private Prestamo prestamo;
	
	//Constructor
	public Cliente() {
		super();
		prestamo=null;
	}

	
	//Metodo Abstracto Sobre-escrito (Polimorfismo)
	@Override
	public String desplegarInformacion() {
		return "\t\tDetalles Cliente:\n"+super.mostrarPersona()+"\n"+mostrarCliente()+"\n"+prestamo.mostrarRecurso();
	}
	

	//Mostrar informacion del Cliente
	public String mostrarCliente() {
		return " Contacto: "+getContacto()+" \nCarnet Funcionario Atendedor: "+getFuncAtendedor();
	}
	
	
	
	// SET & GET
	public String getContacto() {
		return contacto;
	}


	public void setContacto(String contacto) {
		this.contacto = contacto;
	}


	public String getFuncAtendedor() {
		return funcAtendedor;
	}


	public void setFuncAtendedor(String funcAtendedor) {
		this.funcAtendedor = funcAtendedor;
	}


	public Prestamo getPrestamo() {
		return prestamo;
	}


	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	
	
	
	
}
