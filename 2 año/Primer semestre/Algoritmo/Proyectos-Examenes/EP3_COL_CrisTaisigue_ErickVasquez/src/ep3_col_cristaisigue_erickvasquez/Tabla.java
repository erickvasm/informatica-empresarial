package ep3_col_cristaisigue_erickvasquez;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

//Esta clase permite las distintas opciones
public class Tabla extends JTable{

	
	//Atributos
	private DefaultTableModel modeloTabla;//Modelo de la tabla
	private int editing=-1;
	private ButtonColum botonColumna;
	
	//Constructor
	public Tabla(int cont) {
		//Se pide el tipo de tabla
		
		if(cont==1) {
			//La tabla de crear
			modeloTabla = new DefaultTableModel(new Object[][] {},new String[] {"Número","Peso(KG)","Sello"}) {
				
	            boolean[] canEdit = new boolean[]{false,true,true
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
			};
			this.setModel(modeloTabla);

			//Edicion por un click
			
			DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
		    singleclick.setClickCountToStart(1);
		
		    TableColumn colNombre=this.getColumnModel().getColumn(1);
			colNombre.setCellEditor(singleclick);
			
			TableColumn colCantidad=this.getColumnModel().getColumn(2);
			colCantidad.setCellEditor(singleclick);
			
			
			//Bloquear el drag de las columnas
			this.getTableHeader().setReorderingAllowed(false);
			
		}else if(cont==2){
			
			//La tabla de descargar
			
			modeloTabla = new DefaultTableModel(new Object[][] {},new String[] {"Número","Peso (KG)","Sello","Descargar"}) {
				
	            boolean[] canEdit = new boolean[]{false,false,false,true
	            };

	            
	            
	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
	            
	            
	            Class[] types=new Class[] {
						java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,javax.swing.JButton.class
				};
					
				@Override
			    public Class getColumnClass(int columnIndex) {
					return types[columnIndex];
			    }
	            
	            
			};
			this.setModel(modeloTabla);

			//Edicion por un click
			
			DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
		    singleclick.setClickCountToStart(1);
		
		    TableColumn colNombre=this.getColumnModel().getColumn(1);
			colNombre.setCellEditor(singleclick);
			
			TableColumn colCantidad=this.getColumnModel().getColumn(2);
			colCantidad.setCellEditor(singleclick);
			
			
			
			
			
			botonColumna=new ButtonColum();
			
			
			//Bloquear el drag de las columnas
			this.getColumnModel().getColumn(3).setCellEditor(botonColumna);
			this.getColumnModel().getColumn(3).setCellRenderer(botonColumna);
			this.getTableHeader().setReorderingAllowed(false);
		
		}else if(cont==3) {
			
			//La tabla de subastar
			
			
			modeloTabla = new DefaultTableModel(new Object[][] {},new String[] {"Número","Peso(KG)","Sello","Ganaderia"}) {
				
	            boolean[] canEdit = new boolean[]{false,false,false,false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit[columnIndex];
	            }
			};
			this.setModel(modeloTabla);

			
			//Bloquear el drag de las columnas
			this.getTableHeader().setReorderingAllowed(false);

			
			
		}
		
		
	}
	
	
	//Este metodo presentara todas las cabezas de res en la opcion de subastar
	public void presentar(ColaCabezaRes cola) {
		
		int size=cola.getLongitud();
		for(int cont=0;cont<size;cont++) {
			CabezaRes actual=cola.Leer();
			modeloTabla.addRow(new String[] {Integer.toString(cont+1),
					Double.toString(actual.getPeso()),
					actual.getSello(),
					actual.getGanaderiaPerteneciente()});
		}
	}
	
	
	
	
	//Comprobar el editado de celda
	public int comprober(int number) {
		if(this.isEditing()) {
			this.getCellEditor().stopCellEditing();
			modeloTabla.removeRow(number);
		}
		return number;
	}
	
	
	
	
	//Obtener las reses de la tabla (opcion Agregar)
	public ArrayList<CabezaRes> obtenerReses(String ganaderia){
		ArrayList<CabezaRes> reses=new <CabezaRes>ArrayList();
		if(modeloTabla.getRowCount()!=0) {
			for(int cont=0;cont<modeloTabla.getRowCount();cont++) {
				CabezaRes res=new CabezaRes();
				try{
					res.setPeso(Double.parseDouble((String)modeloTabla.getValueAt(cont, 1).toString()));
					res.setSello((String)modeloTabla.getValueAt(cont, 2));
					res.setGanaderiaPerteneciente(ganaderia);
					reses.add(res);
				}catch(Exception e) {
					//pass
				}
			}
		}
		return reses;
	}
	
	
	//Obtener la capacidad en suma de todas las reses
	public int capacidad() {
		int capacidad=0;
		try {
			if(modeloTabla.getRowCount()!=0) {
				for(int cont=0;cont<modeloTabla.getRowCount();cont++) {
					capacidad+=Integer.parseInt((String)modeloTabla.getValueAt(cont, 1).toString());
				}
			}
		}catch(Exception e) {
			capacidad=-1;
		}
		return capacidad;
	}
	
	
	
	//Visualizar las reses en la opcion de descarga
	public void visualizarDescarga(ArrayList<CabezaRes> ganado) {
		reset();
		int contador=1;
		
		for(CabezaRes res:ganado) {
			
			modeloTabla.addRow(new Object[] {contador,res.getPeso(),res.getSello()});
			contador++;
		}
		
		
	}
	
	
	//Comprobar las filas en la opcion de agregar
	public boolean[] comprobarFilas() {
		boolean response[]= {true,true};
		
		ArrayList<String> nombres=new <String>ArrayList();
		String nombrere="";
		
		
		
		if(modeloTabla.getRowCount()!=0) {
			
			for(int cont=0;cont<modeloTabla.getRowCount();cont++) {
				
				
				
				
				if(((modeloTabla.getValueAt(cont, 2).toString().length()<=2) || (modeloTabla.getValueAt(cont, 2).toString().length()>3)) && (response[0]) && (response[1])) {
					response[0]=false;
					response[1]=true;
				}else {
					nombrere=modeloTabla.getValueAt(cont, 2).toString();
					if((nombres.contains(nombrere)) && (response[0]) && (response[1])) {
						response[0]=true;
						response[1]=false;
					}
				}
				
				
				
				
				try {
					int valor=Integer.parseInt(modeloTabla.getValueAt(cont, 1).toString());
					
					if((valor<=0) && (response[0]) && (response[1])) {
						response[0]=false;
						response[1]=false;
					}
					
				}catch(Exception o) {
					if((response[0]) && (response[1])) {
						response[0]=false;
						response[1]=false;
					}
					
				}
				
				
				
				
				if((response[0]) && (response[1])) {
					nombres.add(nombrere);
				}
				
			}
			
			
		}
		
		
		
		return response;
	}
	
	
	
	
	
	//Agregar una res
	public void AgregarCelda() {
		modeloTabla.addRow(new String[] {(Integer.toString(modeloTabla.getRowCount()+1)),"","",""});
	}
	
	
	//Quitar una res
	public void EliminarCelda() {
		int last=modeloTabla.getRowCount();
		if(last!=0) {
			modeloTabla.removeRow(last-1);
		}
	}
	
	
	//Obtener el modelo de la tabla
	public DefaultTableModel obtenerModelo() {
		return this.modeloTabla;
	}
	
	
	//Resetear la tabla
	public void reset() {
		modeloTabla.setRowCount(0);
	}
	
	//Obtener el boton 'Descargar' en la opcion descargar
	public ButtonColum botonColumna() {
		return this.botonColumna;
	}
}
