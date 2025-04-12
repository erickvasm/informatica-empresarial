package paneles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import examen.Paciente;

public class Consultar extends JPanel{

	private ArrayList<Paciente> referencia;
	private JTextField buscado;
	private JTextArea mostrarPaciente;
	
	public Consultar(ArrayList<Paciente> referenciaDada) {
		
		this.referencia=referenciaDada;//Referencia al arrayList de pacientes
		
		
		//Componentes y caracteristicas
		this.setLayout(null);
		this.setBorder(new TitledBorder(null, "Consultar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblIdentificacionDelPaciente = new JLabel("Identificacion del Paciente");
		lblIdentificacionDelPaciente.setBounds(69, 323, 170, 14);
		this.add(lblIdentificacionDelPaciente);
		
		buscado = new JTextField();
		buscado.setBounds(251, 320, 152, 20);
		this.add(buscado);
		buscado.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(424, 319, 89, 23);
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarPaciente();
			}
		});
		
		this.add(btnConsultar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 35, 463, 260);
		this.add(scrollPane);
		
		mostrarPaciente = new JTextArea();
		mostrarPaciente.setEditable(false);
		mostrarPaciente.setLineWrap(true);
		scrollPane.setViewportView(mostrarPaciente);
		
		JLabel lblI = new JLabel("<html><body>Instrucciones: si desea consultar un paciente en particular debera proveer la identificacion de este en la barra de texto inferior y luego presionar el boton 'Consultar'</body></html>");
		lblI.setBounds(523, 35, 265, 124);
		this.add(lblI);
		
		
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				reset();
			}
		});
		

		
	}
	
	//Resetea los campos de consultar
	public void reset() {
		mostrarPaciente.setText("");
		buscado.setText("");
	}

	
	//Se busca un paciente por medio de su identificacion
	public void consultarPaciente() {
		//Comprobar que existan pacientes en el sistema
		if(referencia.size()==0) {
			reset();
			mostrarPaciente.setText("Todavia no se han ingresado pacientes al sistema ");
		}else {
			if(buscado.getText().length()<=5) {
				mostrarPaciente.setText("Ingrese una identificacion de al menos 6 caracteres");
			}else {		
				
				Paciente temporal=null;
				for(int cont=0;cont<referencia.size();cont++) {
					if((referencia.get(cont).getIdentificacion().equalsIgnoreCase(buscado.getText())) && (temporal==null)) {
						temporal=referencia.get(cont);
					}
				}
				
				reset();
				if(temporal==null) {
					mostrarPaciente.setText("No se encontro el paciente");
				}else {
					mostrarPaciente.setText(temporal.mostrarInformacion());
					mostrarPaciente.setCaretPosition(0);
				}
		
				
			}		
		}
	}
	
	
	
}