

											-- II PARCIAL. FUNDAMENTOS DE BASES DE DATOS
                                            
-- Estudiantes: Erick Vasquez Murillo & Cris Taisigue Alvarez

-- CREAR LA BASE DE DATOS PARA LA CADENA DE HOTELES DREAMS
CREATE DATABASE dreams_hotels;


-- USAR LA BASE DE DATOS ANTERIORMENTE CREADA
USE dreams_hotels;



-- CREAR LA TABLA DE PERSONA
CREATE TABLE IF NOT EXISTS persona(

	cedula INT PRIMARY KEY NOT NULL,
    nombre1 VARCHAR(15),
    nombre2 VARCHAR(15),
    apellido1 VARCHAR(15),
    apellido2 VARCHAR(15),
    fecha_nacimiento VARCHAR(15)
    
);


-- CREAR TABLA AREA
CREATE TABLE IF NOT EXISTS area(

	id_area INT AUTO_INCREMENT,
    servicio VARCHAR(25),
    PRIMARY KEY(id_area)
    
);

-- CREAR TABLA PROVINCIA
CREATE TABLE IF NOT EXISTS provincia(

	n_provincia INT AUTO_INCREMENT,
    nombre VARCHAR(15),
    PRIMARY KEY (n_provincia)
    
);

-- CREAR TABLA CANTON
CREATE TABLE IF NOT EXISTS canton(

	n_canton INT AUTO_INCREMENT,
    nombre VARCHAR(15),
    PRIMARY KEY (n_canton)
    
);

-- CREAR LA TABLA HOTELES
CREATE TABLE IF NOT EXISTS hoteles(
	
    id_hotel INT AUTO_INCREMENT PRIMARY KEY,
	nombre_hotel VARCHAR (35),
    telefono_contacto INT NOT NULL,
	n_provincia INT NOT NULL,
    n_canton INT NOT NULL,
    FOREIGN KEY(n_provincia) REFERENCES provincia (n_provincia),
	FOREIGN KEY(n_canton) REFERENCES canton (n_canton),
	otras_senas VARCHAR (120)
    
); 


-- CREAR LA TABLA DE EMPLEADO
CREATE TABLE IF NOT EXISTS empleado(
	
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    cedula INT NOT NULL,
    id_area INT NOT NULL,
    salario INT DEFAULT 0,
    hora_entrada VARCHAR(9),
    hora_salida VARCHAR(9),
    id_hotel INT NOT NULL,
    
    FOREIGN KEY(cedula) REFERENCES persona (cedula),
    FOREIGN KEY(id_area) REFERENCES area (id_area),
    FOREIGN KEY(id_hotel) REFERENCES hoteles (id_hotel)
    
);



									-- INSERTAR EN LAS TABLAS CREADAS

-- INSERTAR 5 REGISTROS A PERSONA
INSERT INTO persona (cedula,nombre1,nombre2,apellido1,apellido2,fecha_nacimiento) VALUES (504390958,'Cristian','Eriksen	','Vasquez','Murillo','7/8/2001');
INSERT INTO persona (cedula,nombre1,nombre2,apellido1,apellido2,fecha_nacimiento) VALUES (102340258,'Rosa','Maria','Perez','Lopez','10/2/2000');
INSERT INTO persona (cedula,nombre1,nombre2,apellido1,apellido2,fecha_nacimiento) VALUES (203578952,'Andrea','Elizabeth','Jara','Ortiz','23/10/2001');
INSERT INTO persona (cedula,nombre1,nombre2,apellido1,apellido2,fecha_nacimiento) VALUES (301240658,'Carlos','Jose','Quintana','Quintana','18/5/1998');
INSERT INTO persona (cedula,nombre1,nombre2,apellido1,apellido2,fecha_nacimiento) VALUES (401370585	,'Maria','Magdalena','Torres','Silva','25/3/1995');
SELECT * FROM persona;

--  INSERTAR 5 REGISTROS A AREA
INSERT INTO area (servicio) VALUE ('Servicio al Cliente');
INSERT INTO area (servicio) VALUE ('Mantenimiento');
INSERT INTO area (servicio) VALUE ('Administrador');
INSERT INTO area (servicio) VALUE ('Recursos Humanos');
INSERT INTO area (servicio) VALUE ('Marketing');
SELECT * FROM area;

-- INSERTAR 5 REGISTROS A PROVINCIA
INSERT INTO provincia (nombre) VALUE ('Guanacaste');
INSERT INTO provincia (nombre) VALUE ('Limon');
INSERT INTO provincia (nombre) VALUE ('Puntarenas');
INSERT INTO provincia (nombre) VALUE ('Alajuela');
INSERT INTO provincia (nombre) VALUE ('Heredia');
SELECT * FROM provincia;

-- INSERTAR 5 REGISTROS A CANTON
INSERT INTO canton (nombre) VALUE ('Liberia');
INSERT INTO canton (nombre) VALUE ('Siquirres');
INSERT INTO canton (nombre) VALUE ('Esparza');
INSERT INTO canton (nombre) VALUE ('San Carlos');
INSERT INTO canton (nombre) VALUE ('San Rafael');
SELECT * FROM canton;

-- INSERTAR 5 REGISTROS A HOTELES
INSERT INTO hoteles (n_provincia,n_canton,nombre_hotel,telefono_contacto,otras_senas) VALUES (1,3,'Dreams Las Mareas',26798081,'Ruta 07');
INSERT INTO hoteles (n_provincia,n_canton,nombre_hotel,telefono_contacto,otras_senas) VALUES (4,4,'Dreams Gold',24238081,'Avenida 17');
INSERT INTO hoteles (n_provincia,n_canton,nombre_hotel,telefono_contacto,otras_senas) VALUES (3,2,'Dreams Sky',24032180,'Ruta 01');
INSERT INTO hoteles (n_provincia,n_canton,nombre_hotel,telefono_contacto,otras_senas) VALUES (2,1,'Dreams Las Brisas',20135874,'Avenida 334');
INSERT INTO hoteles (n_provincia,n_canton,nombre_hotel,telefono_contacto,otras_senas) VALUES (5,5,'Dreams Ocean',21357840,'Ruta 64');
SELECT * FROM hoteles;


-- INSERTAR 5 REGISTROS EMPLEADO
INSERT INTO empleado (cedula,id_hotel,id_area,salario,hora_entrada,hora_salida) VALUES (504390958,1,4,850000,'08:00:PM' ,'02:00:AM');
INSERT INTO empleado (cedula,id_hotel,id_area,salario,hora_entrada,hora_salida) VALUES (102340258,2,2,750000,'07:00:AM','05:00:PM');
INSERT INTO empleado (cedula,id_hotel,id_area,salario,hora_entrada,hora_salida) VALUES (203578952,3,5,655000,'02:00:PM','12:00:PM');
INSERT INTO empleado (cedula,id_hotel,id_area,salario,hora_entrada,hora_salida) VALUES (301240658,4,3,1150000,'05:00:AM','2:00:PM');
INSERT INTO empleado (cedula,id_hotel,id_area,salario,hora_entrada,hora_salida) VALUES (401370585,5,1,800000,'10:00:PM','05:00:AM');
SELECT * FROM empleado;







										-- ELIMINAR REGISTROS

SET FOREIGN_KEY_CHECKS=OFF;
                                        
-- ELIMINAR 2 REGISTROS DE PERSONA
DELETE FROM persona WHERE cedula=401370585;
DELETE FROM persona WHERE cedula=301240658;
SELECT * FROM persona;

-- ELIMINAR 2 REGISTROS DE AREA
DELETE FROM area WHERE id_area=4;
DELETE FROM area WHERE id_area=5;
SELECT * FROM area;

-- ELIMINAR 2 REGISTROS DE PROVINCIA
DELETE FROM provincia WHERE n_provincia=4;
DELETE FROM provincia WHERE n_provincia=5;
SELECT * FROM provincia;

-- ELIMINAR 2 REGISTROS DE CANTON
DELETE FROM canton WHERE n_canton=4;
DELETE FROM canton WHERE n_canton=5;
SELECT * FROM canton;

-- ELIMINAR 2 REGISTROS DE HOTELES
DELETE FROM hoteles WHERE id_hotel=4;
DELETE FROM hoteles WHERE id_hotel=5;
SELECT * FROM hoteles;

-- ELIMINAR 2 REGISTROS DE EMPLEADOS
DELETE FROM empleado WHERE cedula=401370585;
DELETE FROM empleado WHERE cedula=301240658;
SELECT * FROM empleado;

SET FOREIGN_KEY_CHECKS=ON;

								-- ACTUALIZAR VALORES EN LAS TABLAS

-- ACTUALIZAR LA TABLA DE PERSONA
UPDATE persona SET nombre1='Cristopher' WHERE cedula=504390958;
UPDATE persona SET nombre1='Margarita' WHERE cedula=102340258;
SELECT * FROM persona;

-- ACTUALIZAR LA TABLA DE AREA
UPDATE area SET servicio='Limpieza' WHERE id_area=1;
UPDATE area SET servicio='Fontaneria' WHERE id_area=2;
SELECT * FROM area;

-- ACTUALIZAR LA TABLA DE PROVINCIA
UPDATE provincia SET nombre='San Jose' WHERE n_provincia=1;
UPDATE provincia SET nombre='Cartago' WHERE n_provincia=2;
SELECT * FROM provincia;

-- ACTUALIZAR LA TABLA DE CANTON
UPDATE canton SET nombre='Montes de Oca' WHERE n_canton=1;
UPDATE canton SET nombre='Turrialba' WHERE n_canton=2;
SELECT * FROM canton;

-- ACTUALIZAR LA TABLA DE HOTELES
UPDATE hoteles SET telefono_contacto=24021605 WHERE id_hotel=1;
UPDATE hoteles SET telefono_contacto=24809029 WHERE id_hotel=2;
SELECT * FROM  hoteles;

-- ACTUALIZAR LA TABLA DE EMPLEADO
UPDATE empleado SET salario=84000 WHERE id_empleado=1;
UPDATE empleado SET salario=155000 WHERE id_empleado=2;
SELECT * FROM empleado;





										-- ELIMINAR LA BASE DE DATOS

-- ELIMINAR LA BASE DE DATOS
DROP DATABASE dreams_hotels;