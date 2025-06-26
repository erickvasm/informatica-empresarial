<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*" %>   <!-- Paquetes importados --> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<!-- Titulo de la pagína principal --> 
<title>Agregar un paciente</title>

<!-- Personalización en la pagína web con css --> 
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link rel="icon" type="image/png" href="imagenes/coronavirus.png"/>
</head>
<body>
	
	<div class="container">
		<h1>Agregar pacientes</h1>
		
		<hr>
		<!-- El Form el cual guarda lo ingresado en las cajas de  texto en la base de datos --> 
		<form action="" method="post" class="form-control" style="width:500px; height:400px" autocomplete="off">
		
			<!-- Por favor, no ingresar una identificación en uso por que la base de datos cae --> 
			Identificacion:
			<input type="text" name="txID" maxlength="9" minlength="9" placeholder="No utilizar un id en uso, si no caera el sistema" class="form-control" required/>
			Nombre:
			<input type="text" name="txNom" maxlength="20" minlength="3"placeholder="Nombre"class="form-control" required/>
			Apellido:
			<input type="text" name="txAp" maxlength="40" minlength="3"placeholder="Apellidos"class="form-control" required/>
			Genero:
			<input type="text" name="txGe" maxlength="9" minlength="8"placeholder="Genero"class="form-control" required/>
			Telefono:
			<input type="text" name="txTe" maxlength="15" minlength="8"placeholder="Telefono"class="form-control" required/>
			Condición:
			<input type="text" name="txCo" maxlength="15" minlength="3"placeholder="Condión"class="form-control" required/>
			Días:
			<input type="text" name="txDi" maxlength="3" minlength="1"placeholder="Días"class="form-control" required/>
			<br>
			<br>
			<!-- Se guarda en la base de datos y regresa a la pagina principal --> 
			<input type="submit" value="Guardar" class="btn btn-primary btn-lg"/>
			<a href="index.jsp">Regresar</a> 
			
		</form>


	</div>
	
</body>
</html>
<%
	//CUARTA conección con la base de datos
	Connection con;
	String url="jdbc:mysql://localhost:3306/pacientes_covid";
	String Driver= "com.mysql.jdbc.Driver";
	String user="root";
	String clave="Lakers07";
	Class.forName(Driver);
	con=DriverManager.getConnection(url,user,clave);
	
	//capturar los datos de la caja de texto
	PreparedStatement ps;
	
	String identificacion;//campo clave
	identificacion=request.getParameter("txID");
	
	String nombre;
	nombre=request.getParameter("txNom");
	
	String apellido;
	apellido=request.getParameter("txAp");
	
	String genero;
	genero=request.getParameter("txGe");
	
	String telefono;
	telefono=request.getParameter("txTe");
	
	String condicion;
	condicion=request.getParameter("txCo");
	
	String dias;
	dias=request.getParameter("txDi");
	
	//Si todos los campos son diferentes a null, se realiza la conección con 
	//la base de datos y se guardan. 
	if(identificacion!=null && nombre!=null && apellido!=null && genero!=null && telefono!=null  && condicion!= null && dias!=null){
		
		ps=con.prepareStatement("insert into pacientes values('"+identificacion+"','"+nombre+"','"+apellido+"','"+genero+"',"+telefono+",'"+condicion+"',"+dias+")");
		ps.executeUpdate();
		
		//Se redirecciona a la pagina principal
		response.sendRedirect("index.jsp");
	}
		
	
%>