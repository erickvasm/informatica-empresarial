--[Distrito]
	
CREATE TABLE public.bitacora_distrito_junio2021 PARTITION OF public."BitacorasDistrito"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarDistrito() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacorasDistrito" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_distrito",NEW."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasDistrito_IA AFTER INSERT ON public."distrito"
FOR EACH ROW EXECUTE PROCEDURE InsertarDistrito();

-- INSERT
INSERT INTO "distrito" VALUES (16,'Carrillo');
SELECT * FROM "BitacorasDistrito";

				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarDistritos() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacorasDistrito" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_distrito",OLD."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasDistritos_DB BEFORE DELETE ON public."distrito"
FOR EACH ROW EXECUTE PROCEDURE EliminarDistritos();

-- Eliminar
DELETE FROM "distrito" WHERE "id_distrito"=15;
SELECT * FROM "BitacorasDistrito";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarDistrito() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacorasDistrito" VALUES (OLD."id_distrito",OLD."nombre",CURRENT_USER,tipo_accion,NOW(),NEW."id_distrito",NEW."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasDistritos_UB BEFORE UPDATE ON public."distrito"
FOR EACH ROW EXECUTE PROCEDURE ActualizarDistrito();

-- Actualizar
UPDATE "distrito" SET "nombre" = 'Caronte' WHERE "id_distrito"=1;
SELECT * FROM "BitacorasDistrito";