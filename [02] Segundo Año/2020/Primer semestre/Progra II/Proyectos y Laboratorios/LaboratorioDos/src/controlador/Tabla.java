package controlador;

import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import modelo.Persona;

//Clase Tabla para hacer posible la gestión de empleados
public class Tabla extends JTable{
	
	//Modelo de la tabla
	private DefaultTableModel modeloTabla;
	
	//Constructor
	public Tabla(){
		
	
		//Características del modelo de tabla empleado
		modeloTabla=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"#","Nombre", "Apellido","Genero","Identificacion"
			}
		);
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setDefaultEditor(Object.class,null);
		this.setModel(modeloTabla);

	}

	
	//Método actualizar tabla con datos del LinkedList empleados
	public void actualizarTabla(LinkedList<Persona> emp) {
		int index=0;
		//Se resetea la tabla
		modeloTabla.setRowCount(0);
		
		for(Persona e:emp) {
			index++;
			modeloTabla.addRow(new Object[] {index,e.getNombre(),e.getApellido(),e.getGenero(),e.getIdentificacion()});
		}
	}

	//Retornar el modelo de tabla
	public DefaultTableModel obtenerModelo() {
		return modeloTabla;
	}
}
