package modelo;

public class Transaccion {

	//Atributos de transaccion
	private int cajero=0;
	private String cliente;
	private String fecha;
	private String hora;
	private int tipo;
	private int nTransaccion;
	
	//
	public Transaccion() {
		
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getCajero() {
		return cajero;
	}

	public void setCajero(int cajero) {
		this.cajero = cajero;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public int getnTransaccion() {
		return nTransaccion;
	}

	public void setnTransaccion(int nTransaccion) {
		this.nTransaccion = nTransaccion;
	}

	//Mostrar iformacion
	public String mostrarInformacion() {
		return "Número transaccion:"+this.getnTransaccion()+"\nFecha:"+this.getFecha()+
				"\nHora:"+this.getHora()+"\nCodigo de cajero:"+this.getCajero();
	}
	
	
	//Transformar para guardar
	public String transformar() {
		return "/"+this.getTipo()+","+this.getFecha()+","+this.getHora()+","+this.getCajero()+","+this.getnTransaccion();
	}
	
}
