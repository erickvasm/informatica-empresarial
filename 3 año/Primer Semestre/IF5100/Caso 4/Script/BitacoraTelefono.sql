--[Telefono]
	
CREATE TABLE public.bitacora_telefono_junio2021 PARTITION OF public."BitacoraTelefono"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarTelefono() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacoraTelefono" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_telefono",NEW."descripcion");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacoraTelefono_IA AFTER INSERT ON public."telefono"
FOR EACH ROW EXECUTE PROCEDURE InsertarTelefono();

-- INSERT
INSERT INTO "telefono" VALUES (16,'88221122');
SELECT * FROM "BitacoraTelefono";


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarTelefono() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacoraTelefono" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_telefono",OLD."descripcion");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacoraTelefono_DB BEFORE DELETE ON public."telefono"
FOR EACH ROW EXECUTE PROCEDURE EliminarTelefono();

-- Eliminar
DELETE FROM "telefono" WHERE "id_telefono"=16;
SELECT * FROM "BitacoraTelefono";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarTelefono() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacoraTelefono" VALUES (OLD."id_telefono",OLD."descripcion",CURRENT_USER,tipo_accion,NOW(),NEW."id_telefono",NEW."descripcion");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacoraTelefono_UB BEFORE UPDATE ON public."telefono"
FOR EACH ROW EXECUTE PROCEDURE ActualizarTelefono();

-- Actualizar
UPDATE "telefono" SET "descripcion" = '23452210' WHERE "id_telefono"=1;
SELECT * FROM "BitacoraTelefono";