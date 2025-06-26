package conexionBD;
import java.sql.*;
import java.util.ArrayList;

import modelo.Paciente;

public class Conexion{

	private Connection conexion = null;
	private Statement sentencia = null;
    private ResultSet resultOut = null;
    
    private final String PORT="3306";
	private final String DBN="controlpacientes";
	private final String HOST="localhost";
	private final String USER="root";
	private final String PASS="Lakers07";
	
	
	//Constructor
	public Conexion() {
	}
	
	
	//Intentar conectarse con la base de datos
	public Statement TryConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(
					"jdbc:mysql://"+HOST+":"+PORT+"/"+DBN,
					USER, PASS);
			conexion.setAutoCommit(true);
			sentencia = conexion.createStatement();
			
			return sentencia;
			
		}catch (Exception e){
			CloseAll();
			return null;
		}
	}
	
	
	
	
	//
	public boolean UpdateQueryFormer(Statement Sent,String Query) {
		boolean Response=false;
		try {
			Sent.executeUpdate(Query);
			CloseAll();
			Response=true;
		}catch(Exception e) {
			CloseAll();
			Response=false;
		}
		
		return Response;
	}
	
	
	
	
	//
	public ArrayList<Paciente> ResultQueryFormer(Statement Sent,String Query) {
		
		ArrayList<Paciente> obtenidos=new ArrayList<Paciente>();
		
		try {
			resultOut=Sent.executeQuery(Query);
			while(resultOut.next()) {
				Paciente pacentBD=new Paciente();
				
				pacentBD.setApellido(resultOut.getString("Apellido"));
				pacentBD.setNombre(resultOut.getString("Nombre"));
				pacentBD.setIdentificacion(resultOut.getString("Identificacion"));
				pacentBD.setDiasH(resultOut.getInt("Dias"));
				pacentBD.setTelefono(Integer.toString(resultOut.getInt("Telefono")));
				pacentBD.setGenero(resultOut.getString("Genero").charAt(0));
				pacentBD.setCondicion(resultOut.getString("Condicion"));
				pacentBD.setDireccion(resultOut.getString("Direccion"));
				
				obtenidos.add(pacentBD);
			}
			CloseAll();
		}catch(Exception e) {
			CloseAll();
			return null;
		}
		
		return obtenidos;
	}
	
	
	//
	public void CloseAll() {
		if (conexion!=null)try{conexion.close();}catch(Exception e){}
        if (sentencia!=null)try{sentencia.close();}catch(Exception e){}
        if (resultOut!=null)try{resultOut.close();}catch(Exception e){}
	}
	
	
}
