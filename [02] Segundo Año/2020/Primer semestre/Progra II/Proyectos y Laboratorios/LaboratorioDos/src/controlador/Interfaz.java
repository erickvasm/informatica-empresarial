package controlador;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Persona;

import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JEditorPane;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import java.awt.event.ItemEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

//Clase Visual Primaria
public class Interfaz{
	
	//Componentes
	private JFrame ventana;
	private final int X=900,Y=700;
	private JPanel firstPanel;
	private Barra barra;
	private General generalView;
	private AddFunc addFunc;
	private Remove remove;
	private Consulta consulta;
	private Actualizar update;
	private JPanel colorPa;
	private Tabla tablaEmpleados;
	private Instrucciones instruccionesOut;
	
	//ListaEnlazada principal
	private LinkedList<Persona> empleados=new LinkedList<Persona>();
	
	//Constructor
	public Interfaz() {
		
		//Caracteristicas de la ventana
		ventana=new JFrame();
		ventana.setSize(X,Y);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setUndecorated(true);
		
		
		//Añadir icono
		try {
		    Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/recursos/Employee.png"));
		    ventana.setIconImage(iconoPropio);
		}catch(Exception e) {
			//Pass
		}
		
		
		
		
		//Añadimiento del panel primario
		firstPanel=new JPanel();
		firstPanel.setLayout(null);
		ventana.getContentPane().add(firstPanel);
		
		//Incorporar una barra dinamica
		barra=new Barra(ventana);
		firstPanel.add(barra.obtenerBarra());
		
		
		
		//Panel de Pestanas
		JTabbedPane PanelPestanas = new JTabbedPane(JTabbedPane.TOP);
		PanelPestanas.setBounds(0, 68, 900, 632);
		firstPanel.add(PanelPestanas);
		
		
		
		
		//Pestana General
		JPanel General = new JPanel();
		General.setLayout(null);
		PanelPestanas.addTab("General", null, General, null);
			
		colorPa = new JPanel();
		colorPa.setLayout(null);
		colorPa.setBounds(10, 11, 590, 215);
		General.add(colorPa);
		
		//Tabla central donde se previsualizacion los empleados
		tablaEmpleados=new Tabla();
		//Enviar el indice seleccionado a las opciones pertinentes
		tablaEmpleados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	if(!e.getValueIsAdjusting()) {
		    		if(tablaEmpleados.getSelectedRow()>=0) {
		    			sendIndex(tablaEmpleados.getSelectedRow());
		    		}
		    	}
		    }
		});
		    
		JScrollPane scrollTabla = new JScrollPane();
		scrollTabla.setBounds(10, 11, 570, 193);
		colorPa.add(scrollTabla);
		scrollTabla.setViewportView(tablaEmpleados);
		
		JPanel operation = new JPanel();
		operation.setLayout(null);
		operation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		operation.setBackground(new Color(220,232,223));
		operation.setBounds(10, 237, 590, 356);
		General.add(operation);
		

		//Panel de Vista General
		generalView=new General();
		colorPa.setBackground(generalView.getColor());
		operation.add(generalView);	
		
		
		//Panel añadir
		addFunc=new AddFunc();
		addFunc.obtenerGuardar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if(addFunc.save(empleados)) {
					tablaEmpleados.actualizarTabla(empleados);
				}
			}
		});
		addFunc.setVisible(false);
		operation.add(addFunc);
		
	
		
		//Actualizar empleado
		update=new Actualizar(empleados);
		update.setVisible(false);
		update.obtenerBoton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if(update.update()) {
					tablaEmpleados.actualizarTabla(empleados);
					update.reset(false);
					update.obtenerAviso().setVisible(true);
					update.obtenerAviso().setText("Se actualizado correctamente");
				}else {
					update.obtenerAviso().setVisible(true);
					update.obtenerAviso().setText("Verifique los campos");
				}
			}
		});
		operation.add(update);

		
		
		
		
		//Panel de eliminar
		remove=new Remove();
		remove.setVisible(false);
		remove.obtenerBoton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(remove.deleteEmploye(empleados)) {
					tablaEmpleados.actualizarTabla(empleados);
					remove.reset(false);
					remove.obtenerAnuncio().setText("Se ha eliminado exitosamente");
				}
			}
		});
		operation.add(remove);

		
		
		
		
		//Panel de consulta
		consulta=new Consulta();
		consulta.setVisible(false);
		consulta.obtenerSearch().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					consulta.searchEmployee(empleados);
				}
			}
		);
		operation.add(consulta);
				
		
		
		
		
		
		//BOTONES DE OPCIONES
		Color buttonColor=new Color(205,232,247);
        ActionListener listener = actionEvent -> optionChanger((JToggleButton) actionEvent.getSource());
		JToggleButton generalBoton = new JToggleButton("General");
		generalBoton.setBounds(648, 81, 205, 64);
		generalBoton.setBackground(buttonColor);
		generalBoton.setSelected(true);
		generalBoton.addActionListener(listener);
		General.add(generalBoton);
		
		JToggleButton addBoton = new JToggleButton("Agregar");
		addBoton.setBackground(buttonColor);
		addBoton.setBounds(648, 188, 205, 64);
		addBoton.addActionListener(listener);
		General.add(addBoton);
		
		JToggleButton updateButton = new JToggleButton("Actualizar");
		updateButton.setBackground(buttonColor);
		updateButton.setBounds(648, 289, 205, 64);
		updateButton.addActionListener(listener);
		General.add(updateButton);
		
		JToggleButton deleteBoton = new JToggleButton("Eliminar");
		deleteBoton.setBackground(buttonColor);
		deleteBoton.setBounds(648, 385, 205, 64);
		deleteBoton.addActionListener(listener);
		General.add(deleteBoton);
		
		JToggleButton searchButton = new JToggleButton("Consultar");
		searchButton.setBackground(buttonColor);
		searchButton.setBounds(648, 480, 205, 64);
		searchButton.addActionListener(listener);
		General.add(searchButton);
			//Grupo de botones
		ButtonGroup botones=new ButtonGroup();
		botones.add(addBoton);
		botones.add(generalBoton);
		botones.add(updateButton);
		botones.add(deleteBoton);
		botones.add(searchButton);
		
		//Pestana de instrucciones
		instruccionesOut=new Instrucciones();
		PanelPestanas.addTab("Instrucciones", null, instruccionesOut, null);
		
		
		
		
		ventana.setVisible(true);
		ventana.toFront();
		ventana.requestFocus();
	}
	
	
	//Enviar el indice del empleado en la tabla
	public void sendIndex(int index) {
		if(index>=0) {
			switch(getShowedPane()) {
				case 1:{
					generalView.showEmployee(empleados, index);
				
				}break;
				
				case 3:{
					remove.showEmployee(empleados, index);
				}break;
				
				case 5:{
					update.displayEmployee(index);
				}break;
			}
		}
	}
	
	
	
	//Este método se encargara de intercambiar la vista en el area de opciones
	public void optionChanger(JToggleButton getButton) {
		String value=getButton.getActionCommand();
		switch(value) {
		
			case "General":{
				if(getShowedPane()!=1) {
					resetAll();
					generalView.setVisible(true);
					colorPa.setBackground(generalView.getColor());

				}
			}break;
			
			
			case "Agregar":{
				if(getShowedPane()!=2) {
					resetAll();
					addFunc.setVisible(true);
					colorPa.setBackground(addFunc.getColor());
				}
			}break;
			
			case "Eliminar":{
				if(getShowedPane()!=3) {
					resetAll();
					colorPa.setBackground(remove.getColor());
					remove.setVisible(true);
				}
			}break;
			
			
			case "Consultar":{
				if(getShowedPane()!=4) {
					resetAll();
					colorPa.setBackground(consulta.getColor());
					consulta.setVisible(true);
				}
			}break;
			
			case "Actualizar":{
				if(getShowedPane()!=5) {
					resetAll();
					colorPa.setBackground(update.getColor());
					update.setVisible(true);
				}
			}break;
			
			
		}
	}
	
	
	//Obtener el panel mostrado
	public int getShowedPane() {
		int show=0;
		
		if(generalView.isShowing()) {
			show=1;
		}else if(addFunc.isShowing()) {
			show=2;
		}else if(remove.isShowing()) {
			show=3;
		}else if(consulta.isShowing()) {
			show=4;
		}else if(update.isShowing()) {
			show=5;
		}
		return show;
	}
	
	//Resetear todas las opciones
	public void resetAll() {
		update.reset(true);update.setVisible(false);
		consulta.reset();consulta.setVisible(false);
		remove.reset(true);remove.setVisible(false);
		tablaEmpleados.actualizarTabla(empleados);
		generalView.reset();generalView.setVisible(false);
		addFunc.reset(true);addFunc.setVisible(false);
	}
}
