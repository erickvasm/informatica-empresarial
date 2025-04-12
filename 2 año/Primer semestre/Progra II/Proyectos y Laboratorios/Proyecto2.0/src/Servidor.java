/**
 * @author Erick Vasquez Murillo B983344
 */

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/Servidor")
public class Servidor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @return este metodo devuelve la conexion exitosa
	 * de lo contrario salta el try cath 
	 * Se utiliza el doPost por sus caracteristicas positivas
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Creando el objeto PrintWriter
		PrintWriter salida=response.getWriter();
		response.setContentType("text/html");
		
		//Creando la conexion con la base de datos
		Connection miConexion=null;
		Statement miStatement=null;
		ResultSet miResultSet=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			/*
			 * cambiar estos parametros por los de la base de datos donde se este ejecutando.
			 * De igual forma en los archivos delete, agregar y index, cambiar los datos por los del
			 * la base de datos donde se este ejecuntando. 
			 */
			miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pacientes_covid", "root", "Lakers07");
			miConexion.setAutoCommit(true);
			
			//Se crea la conexion
			miStatement=miConexion.createStatement();
			
			
			//devuelve todo los pacientes de la tabla de pacientes
			String misql="select * from pacientes"; 
			miStatement=miConexion.createStatement();
			miResultSet=miStatement.executeQuery(misql);
			
			
			salida.println("<html><head><link rel='stylesheet' type='text/css' href='css/bootstrap.min.css'></head><body><table class='table table-bordered'>"
					+"<tr><th>Identificacion</th><th>Nombre</th><th>Apellido</th><th>Genero</th><th>Telefono</th><th>Condicion</th><th>Dias</th><th>Eliminar</th></tr>");
			
			while (miResultSet.next()) {
				
				/*
				 * manda al archivo index.jsp el mensaje que la conexion la conexion principa fue
				 * exitosa
				 */
				
				salida.println("<tr>");
				salida.println("<td>"+miResultSet.getString("Identificacion")+"</td>");
				salida.println("<td>"+miResultSet.getString("Nombre")+"</td>");
				salida.println("<td>"+miResultSet.getString("Apellido")+"</td>");
				salida.println("<td>"+miResultSet.getString("Genero")+"</td>");
				salida.println("<td>"+miResultSet.getInt("Telefono")+"</td>");
				salida.println("<td>"+miResultSet.getString("Condición")+"</td>");
				salida.println("<td>"+miResultSet.getInt("Días")+"</td>");
				//<%=rs.getString(\"Identificacion\")%>
				salida.println("<td><a href='Delete.jsp?Identificacion="+miResultSet.getString("Identificacion")+"' class='btn btn-danger btn-sm'>Eliminar</a></td>");
				salida.println("</tr>");
					
			}
			
			
			//Se cierra la conexion
			salida.println("</table></body></html>");
			salida.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
            System.out.println("Error de seguimiento en getConnection() : " + e.getMessage());
		}
		
	}

}
