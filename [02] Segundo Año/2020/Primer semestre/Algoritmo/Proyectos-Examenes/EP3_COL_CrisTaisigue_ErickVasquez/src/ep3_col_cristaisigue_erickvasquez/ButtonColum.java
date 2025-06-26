package ep3_col_cristaisigue_erickvasquez;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

//Esta clase permite obtener el indice de la fila en el cual se ubica el boton
public class ButtonColum extends AbstractCellEditor implements TableCellRenderer, TableCellEditor{

	private JButton button;//Boton
	private int value;//Indice
	
	//Constructor
	public ButtonColum(){
	    button = new JButton("Descargar");
	}

	
	//Se define la fila en la tabla
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1,
	        boolean arg2, boolean arg3, int arg4, int arg5) {
	    value =arg4;
	    return button;
	}
	
	
	@Override
	public Object getCellEditorValue() {
	    return null;
	}
	
	@Override
	public Component getTableCellEditorComponent(JTable arg0, Object arg1,
	        boolean arg2, int arg3, int arg4) {
	    value = arg3;
	    return button;
	}
	
	//Obtener la fila
	public int obtenerIndex() {
		return value;
	}
	
	
	//Obtener el boton
	public JButton obtenerBoton() {
		return button;
	}
}