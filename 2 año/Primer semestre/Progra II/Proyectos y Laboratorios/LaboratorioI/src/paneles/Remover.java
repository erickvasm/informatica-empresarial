package paneles;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

import laboratorio.Paciente;

//Panel de Remover
public class Remover extends JPanel{

	//Donde se guardara el arreglo de pacientes
	private ArrayList<Paciente> pacient;
	

	private boolean condition=false;
	private int cell=0;//Celda a remover "fila en la Tabla"

	
	//Constructor del Panel Remover
	public Remover(ArrayList<Paciente> pacent) {
		
		//Obtención del ArrayList "pacientes"
		this.pacient=pacent;

		//Componentes
		JLabel showFour = new JLabel("N\u00FAmero:");
		showFour.setBounds(34, 497, 191, 14);
		this.add(showFour);
		
		JLabel showTwo = new JLabel("Nombre:");
		showTwo.setBounds(34, 532, 214, 14);
		this.add(showTwo);
		
		JLabel showThree = new JLabel("Identificación:");
		showThree.setBounds(34, 567, 214, 14);
		this.add(showThree);
		
		JScrollPane scrollEliminar = new JScrollPane();
		scrollEliminar.setBounds(10, 66, 949, 383);
		
		JButton deleteButton = new JButton("Eliminar");
		
		//Tabla donde se proporciona la información de los pacientes
		Tabla tablaEliminar = new Tabla();
		scrollEliminar.setViewportView(tablaEliminar);
		tablaEliminar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Captar eventos de la Tabla, proporciona el índice que el administrador desea remover
		tablaEliminar.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	if(!e.getValueIsAdjusting()) {
		    		cell = tablaEliminar.getSelectedRow();
		            deleteButton.setEnabled(true);
		            if(cell>=0) {
		            	showFour.setText("N\u00FAmero: "+(cell+1));
		            	showTwo.setText("N\u00FAmero: "+pacent.get(cell).getNombre());
		            	showThree.setText("Identificación: "+pacent.get(cell).getIdentificacion());
		            }
		    	}
		    }
		    
		});
		
		
		//Se elimina el índice en el ArrayList
	    deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pacient.remove(cell);
				tablaEliminar.actualizarTabla(pacient);
				deleteButton.setEnabled(false);
				showFour.setText("N\u00FAmero:");
	            showTwo.setText("N\u00FAmero: ");
	            showThree.setText("Identificación: ");
	            
		}});
		
		//Capatar eventos de este panel
		this.addComponentListener(new ComponentAdapter() {
			//Se muestra
			@Override
			public void componentShown(ComponentEvent arg0) {
				tablaEliminar.actualizarTabla(pacent);
			}
			//Se oculta
			@Override
			public void componentHidden(ComponentEvent e) {
				deleteButton.setEnabled(false);
			}
		});
		
		
		//Componentes y características
		this.setLayout(null);
		this.add(scrollEliminar);

		
		deleteButton.setEnabled(false);
		
		
		deleteButton.setBounds(831, 520, 128, 57);
		this.add(deleteButton);
		
		JLabel Instruccion = new JLabel(">Instrucciones: debe clicar la fila de información del paciente y posterior a esto apretar el botón eliminar");
		Instruccion.setBounds(50, 11, 655, 31);
		this.add(Instruccion);
		
		JLabel showOne = new JLabel("Paciente:");
		showOne.setBounds(62, 460, 128, 14);
		this.add(showOne);
		
	}
	
}
