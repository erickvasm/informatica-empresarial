--[Email]
	
CREATE TABLE public.bitacoras_email_junio2021 PARTITION OF public."BitacorasEmail"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarEmail() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacorasEmail" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_email",NEW."descripción");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasEmail_IA AFTER INSERT ON public."email"
FOR EACH ROW EXECUTE PROCEDURE InsertarEmail();

-- INSERT
INSERT INTO "email" VALUES (16,'Postal');
SELECT * FROM "BitacorasEmail";


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarEmail() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacorasEmail" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_email",OLD."descripción");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasEmail_DB BEFORE DELETE ON public."email"
FOR EACH ROW EXECUTE PROCEDURE EliminarEmail();

-- Eliminar
DELETE FROM "email" WHERE "id_email"=1;
SELECT * FROM "BitacorasEmail";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarEmail() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacorasEmail" VALUES (OLD."id_email",OLD."descripción",CURRENT_USER,tipo_accion,NOW(),NEW."id_email",NEW."descripción");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasEmail_UB BEFORE UPDATE ON public."email"
FOR EACH ROW EXECUTE PROCEDURE ActualizarEmail();

-- Actualizar
UPDATE "email" SET "descripción" = 'Correo' WHERE "id_email"=2;
SELECT * FROM "BitacorasEmail";