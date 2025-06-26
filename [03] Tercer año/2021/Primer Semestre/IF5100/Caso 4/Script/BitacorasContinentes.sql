--[Continentes]
	
CREATE TABLE public.bitacoras_continentes_junio2021 PARTITION OF public."BitacorasContinentes"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarContinente() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacorasContinentes" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_continentes",NEW."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasContinentes_IA AFTER INSERT ON public."continentes"
FOR EACH ROW EXECUTE PROCEDURE InsertarContinente();

-- INSERT
INSERT INTO "continentes" VALUES (7,'Pangea');
SELECT * FROM "BitacorasContinentes";


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarContinente() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacorasContinentes" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_continentes",OLD."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasContinentes_DB BEFORE DELETE ON public."continentes"
FOR EACH ROW EXECUTE PROCEDURE EliminarContinente();

-- Eliminar
DELETE FROM "continentes" WHERE "id_continentes"=1;
SELECT * FROM "BitacorasContinentes";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarContinentes() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacorasContinentes" VALUES (OLD."id_continentes",OLD."nombre",CURRENT_USER,tipo_accion,NOW(),NEW."id_continentes",NEW."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasContinentes_UB BEFORE UPDATE ON public."continentes"
FOR EACH ROW EXECUTE PROCEDURE ActualizarContinentes();

-- Actualizar
UPDATE "continentes" SET "nombre" = 'Caronte' WHERE "id_continentes"=1;
SELECT * FROM "BitacorasContinentes";