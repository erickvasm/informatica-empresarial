<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
       //TECER CONECION A LA BASE DE DATOS
       // Por favor, cambiar estos datos con los de la base de datos
       // en la que se esta ejecutando
       Connection con;       
       String url="jdbc:mysql://localhost:3306/pacientes_covid";
       String Driver="com.mysql.jdbc.Driver";
       String user="root";
       String clave="Lakers07";
       Class.forName(Driver);
       con=DriverManager.getConnection(url,user,clave);
       //Empezamos Listando los Datos de la Tabla pacientes pero de la fila seleccionada
       PreparedStatement ps;       
       String identificacion=request.getParameter("Identificacion");
       ps=con.prepareStatement("delete from pacientes where Identificacion="+identificacion);
       ps.executeUpdate();
       //response.sendRedirect("index.jsp");
       
       %>
    </body>
</html>