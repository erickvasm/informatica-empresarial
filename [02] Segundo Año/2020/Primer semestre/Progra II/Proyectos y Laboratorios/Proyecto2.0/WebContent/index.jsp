<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <!-- Paquetes importados --> 
<%@page import="java.sql.*" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<!-- Diseño css --> 
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<title>COVID-19</title>
<!-- Icono para personalización --> 
<link rel="icon" type="image/png" href="imagenes/coronavirus.png"/>
</head>
<body>
	
	<%
	//SEGUNDA CONEXION LA BASE DE DATOS
    // Por favor, cambiar estos datos con los de la base de datos
    // en la que se esta ejecutando
	Connection con;
	String url="jdbc:mysql://localhost:3306/pacientes_covid";
	String Driver= "com.mysql.jdbc.Driver";
	String user="root";
	String clave="Lakers07";
	Class.forName(Driver);
	con=DriverManager.getConnection(url,user,clave);
	
	//listado de la tabla
	PreparedStatement ps;
	ResultSet rs;
	ps=con.prepareStatement("select * from pacientes");
	rs=ps.executeQuery();
	
    Statement stmt = null;

	//creamos la tabla
	%>
	<div class="container">
	<img src="imagenes/hospital.png" style="position:absolute;left:3%;top:3%;width:5%;">
		<h1 >Registro de pacientes</h1>
		<hr>
			<a class="btn btn-success btn-lg" href="Agregar.jsp">Nuevo paciente</a>
			<br><br>
			<!--Se manda la petición al servler "Servidor.java" --> 
			<form action="Servidor" method="POST" target="relTable">
			
				<input type="hidden" name="tipo" value="tablaRecargar"/>
				<input type="submit" class="btn btn-success btn-lg" value="Recargar Tabla"/>
			</form>
			
				&emsp;&emsp;&emsp;&emsp;&emsp;
				Si desea consultar la tabla, presione el botón "Recargar Tabla", si desea eliminar presione el boton de la fila del paciente que desea eliminar, tome en cuenta que debera recargar la tabla despues de esta acción.	
		<br>
	</div>
	
	<div id="forM">
		<!-- Se agrega los datos del servlet a la tabla--> 
		<iframe id="relTable" name="relTable" style="position:absolute;left:10%;width:80%;height:63%;border:solid 1px black;overflow:auto;">
		</iframe>
	
	</div>


</body>
</html>