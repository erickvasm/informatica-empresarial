package controlador;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Funcionario;

//Clase Tabla para hacer posible la gestión de los prestamos y funcionarios
public class Tabla extends JTable{
	
	//Modelo de la tabla
	private DefaultTableModel modeloTabla;
	private ArrayList<Cliente> pres;//Puntero del ArrayList central prestamos
	private ArrayList<Funcionario> func;//Puntero del ArrayList central funcionarios
	
	//Constructor
	public Tabla(ArrayList<Cliente> pres, ArrayList<Funcionario> func){
		//Se determinan los punteros
		this.pres=pres;
		this.func=func;
	
		//Características del modelo de tabla Cliente/Funcionario
		modeloTabla=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#","Nombre", "Apellido","Identificacion","Edad"
			}
		);
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setDefaultEditor(Object.class,null);
		this.setModel(modeloTabla);

	}

	
	//Método actualizar tabla con datos (Funcionario/Cliente) dependiendo del modo de gestion solicitado
	public void actualizarTabla(boolean switcher) {
		int index=0;
		modeloTabla.setRowCount(0);
		//Se determina el modo de gestion
		if(switcher) {
			//Mostrar funcionarios
			for(Funcionario fun:func) {
				index++;
				modeloTabla.addRow(new Object[] {index,fun.getNombre(),fun.getApellido(),fun.getIdentificacion(),fun.getEdad()});
			}
		}else {
			//Mostrar prestamos
			for(Cliente pre:pres) {
				index++;
				modeloTabla.addRow(new Object[] {index,pre.getNombre(),pre.getApellido(),pre.getIdentificacion(),pre.getEdad()});
			}
		}
	}

	//Retornar el modelo de tabla
	public DefaultTableModel obtenerModelo() {
		return modeloTabla;
	}
}
