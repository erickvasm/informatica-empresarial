--[cuidadores]
	
CREATE TABLE public.bitacoras_cuidadores_junio2021 PARTITION OF public."BitacorasCuidadores"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarCuidadores() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacorasCuidadores" VALUES (NULL,NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_cuidadores",NEW."cedula_fk",NEW."fecha_ingreso");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasCuidadores_IA AFTER INSERT ON public."cuidadores"
FOR EACH ROW EXECUTE PROCEDURE InsertarCuidadores();

-- INSERT
INSERT INTO "cuidadores" VALUES (16,670091888,'2021-06-22');
SELECT * FROM "BitacorasCuidadores";


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarCuidadores() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacorasCuidadores" VALUES (NULL,NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_cuidadores",OLD."cedula_fk",OLD."fecha_ingreso");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasCuidadores_DB BEFORE DELETE ON public."cuidadores"
FOR EACH ROW EXECUTE PROCEDURE EliminarCuidadores();

-- Eliminar
DELETE FROM "cuidadores" WHERE "id_cuidadores"=1;
SELECT * FROM "BitacorasCuidadores";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarCuidadores() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacorasCuidadores" VALUES (OLD."id_cuidadores",OLD."cedula_fk",OLD."fecha_ingreso",CURRENT_USER,tipo_accion,NOW(),NEW."id_cuidadores",NEW."cedula_fk",NEW."fecha_ingreso");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasCuidadores_UB BEFORE UPDATE ON public."cuidadores"
FOR EACH ROW EXECUTE PROCEDURE ActualizarCuidadores();

-- Actualizar
UPDATE "cuidadores" SET "fecha_ingreso" = '2021-04-04' WHERE "id_cuidadores"=1;
SELECT * FROM "BitacorasCuidadores";