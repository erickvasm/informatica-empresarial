/**
 *@author Cris
 * @author Erick
 */
package modelo;

//Clase gerente
public class Gerente extends Funcionario implements Jefatura{
    
	//Atributo
    private String actividades;
    
    //Constructor
    public Gerente(){
        
    }

    
    //Sobreescribir el metodo para el resumen de informacion
    @Override
    public String mostrarInformacion() {
    	return "\t\tGerente\n"+super.recolectarInformacion()+"\n"+super.funcionarioInfo()+
    			"\nActividades: "+getActividades();
    }
    
    //Sobreescribir el metodo calcular pago
    @Override
    public void calcularPago() {
    	if(super.getHorasTrabajadas()<=0) {
    		super.setSalario(0);
    	}else {
    		super.setSalario((super.getHorasTrabajadas()>48)?((super.getHorasTrabajadas()-48)*extra)+(48*perHour):super.getHorasTrabajadas()*perHour);
    	}
    }
    
    
    //Set & Get
	public String getActividades() {
		return actividades;
	}

	public void setActividades(String actividades) {
		this.actividades = actividades;
	}
    
}
