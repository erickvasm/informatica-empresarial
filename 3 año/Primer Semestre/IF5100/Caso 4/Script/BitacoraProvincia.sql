--[PROVINCIA]
	
CREATE TABLE public.bitacora_provincia_junio2021 PARTITION OF public."BitacoraProvincia"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarProvincia() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacoraProvincia" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_provincia",NEW."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacoraProvincia_IA AFTER INSERT ON public."provincia"
FOR EACH ROW EXECUTE PROCEDURE InsertarProvincia();

-- INSERT
INSERT INTO "provincia" VALUES (16,'Ganimedes');
SELECT * FROM "BitacoraProvincia";


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarProvincia() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacoraProvincia" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_provincia",OLD."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacoraProvincia_DB BEFORE DELETE ON public."provincia"
FOR EACH ROW EXECUTE PROCEDURE EliminarProvincia();

-- Eliminar
DELETE FROM "provincia" WHERE "id_provincia"=16;
SELECT * FROM "BitacoraProvincia";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarProvincia() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacoraProvincia" VALUES (OLD."id_provincia",OLD."nombre",CURRENT_USER,tipo_accion,NOW(),NEW."id_provincia",NEW."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacoraProvincia_UB BEFORE UPDATE ON public."provincia"
FOR EACH ROW EXECUTE PROCEDURE ActualizarProvincia();

-- Actualizar
UPDATE "provincia" SET "nombre" = 'Caronte' WHERE "id_provincia"=1;
SELECT * FROM "BitacoraProvincia";