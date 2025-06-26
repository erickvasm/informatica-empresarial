package paneles;

import java.awt.event.*;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

import examen.Paciente;

//Panel de Remover
public class Remover extends JPanel{

	//Donde se guardara el arreglo de pacientes
	private ArrayList<Paciente> pacient;
	

	private boolean condition=false;
	private int cell=0;//Celda a remover "fila en la Tabla"
	private JLabel showFour;
	private JLabel showTwo;
	private JLabel showThree;
	private JButton botonBorrar;
	
	//Constructor del Panel Remover
	public Remover(ArrayList<Paciente> pacent) {
		
		//Obtención del ArrayList "pacientes"
		this.pacient=pacent;
		//Componentes y características
		this.setLayout(null);
		
		//Componentes
		showFour = new JLabel("N\u00FAmero:");
		showFour.setBounds(34, 280, 191, 14);
		this.add(showFour);
		
		showTwo = new JLabel("Nombre:");
		showTwo.setBounds(34, 302, 214, 14);
		this.add(showTwo);
		
		showThree = new JLabel("Identificación:");
		showThree.setBounds(34, 320, 214, 14);
		this.add(showThree);
		
		JScrollPane scrollEliminar = new JScrollPane();
		scrollEliminar.setBounds(8, 50, 835, 200);
		this.add(scrollEliminar);
		
		botonBorrar = new JButton("Eliminar");
		botonBorrar.setEnabled(false);
		botonBorrar.setBounds(670, 280, 128, 57);
		
		//Tabla donde se proporciona la información de los pacientes
		Tabla tablaEliminar = new Tabla();
		scrollEliminar.setViewportView(tablaEliminar);
		tablaEliminar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Captar eventos de la Tabla, proporciona el índice que el administrador desea remover
		tablaEliminar.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	if(!e.getValueIsAdjusting()) {
		    		cell = tablaEliminar.getSelectedRow();
		            
		            if(cell>=0) {
		            	botonBorrar.setEnabled(true);
		            	showFour.setText("N\u00FAmero: "+(cell+1));
		            	showTwo.setText("N\u00FAmero: "+pacent.get(cell).getNombre());
		            	showThree.setText("Identificación: "+pacent.get(cell).getIdentificacion());
		            }
		    	}
		    }
		    
		});
		
		
		//Se elimina el índice en el ArrayList
	    botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					pacient.remove(cell);
				}catch(Exception e) {
					//pass
				}
				tablaEliminar.actualizarTabla(pacient);
				resetear(botonBorrar);
	            
		}});
		
	    //Componentes
		JLabel Instruccion = new JLabel(">Instrucciones: debe clicar la fila de información del paciente y posterior a esto apretar el botón eliminar");
		Instruccion.setBounds(50, 11, 655, 31);
		this.add(Instruccion);
		
		JLabel showOne = new JLabel("Paciente:");
		showOne.setBounds(62, 260, 128, 14);
		this.add(showOne);
		this.add(botonBorrar);
		
		//Captar eventos de este panel
		this.addComponentListener(new ComponentAdapter() {
			//Se muestra
				
			@Override
			public void componentShown(ComponentEvent arg0) {
				tablaEliminar.actualizarTabla(pacent);
			}
			
			//Se oculta		
			@Override
			public void componentHidden(ComponentEvent arg0) {
				resetear(botonBorrar);
			}
		});
				
		
		
	}
	
	//Resetea los detalles mostrados y desactiva el boton de "Eliminar"
	public void resetear(JButton componente) {
		componente.setEnabled(false);
		showFour.setText("N\u00FAmero:");
        showTwo.setText("Nombre: ");
        showThree.setText("Identificación: ");
	}
	
}
