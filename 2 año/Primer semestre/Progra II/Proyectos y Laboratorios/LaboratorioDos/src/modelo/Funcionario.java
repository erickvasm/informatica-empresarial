/**
 *@author Cris
 * @author Erick
 */
package modelo;

//Clase funcionario
public class Funcionario extends Persona implements Colaborador{
    
	//Atributos
   private String AltaContrato,departamento;
   private double salario;
   private int horasTrabajadas;
    
   
   //Constructor
    public Funcionario(){
    	
    }
    

    //Sobreescribir el metodo calcular pago
    @Override
    public void calcularPago() {
    	if(getHorasTrabajadas()<=0) {
    		setSalario(0);
    	}else {
    		setSalario((getHorasTrabajadas()>48)?((getHorasTrabajadas()-48)*operativeExtra)+(48*operativeHour):getHorasTrabajadas()*operativeHour);
    	}
    }
    
    //metodo abstracto de clase Persona
   @Override
    public String mostrarInformacion(){
        return "\t\tFuncionario\n"+super.recolectarInformacion()+"\n"+funcionarioInfo();
    }

   	//Resumen de funcionario
   	public String funcionarioInfo() {
   		return "Alta de Contrato: "+getAltaContrato()+"\nDepartamento: "+getDepartamento()
        		+"\nSalario: "+getSalario()+"\nHoras Semanales: "+getHorasTrabajadas();
   	}
   
   	
   	//Set & Get
   
    public String getAltaContrato() {
        return AltaContrato;
    }

    public void setAltaContrato(String contrato) {
        this.AltaContrato = contrato;
    }

    
    
    
    public int getHorasTrabajadas() {
		return horasTrabajadas;
	}


	public void setHorasTrabajadas(int horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}


	public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }


    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    
}
