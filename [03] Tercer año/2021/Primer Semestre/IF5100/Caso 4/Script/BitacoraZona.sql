--[ZONA]
	
CREATE TABLE public.bitacora_zonas_junio2021 PARTITION OF public."BitacoraZonas"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarZonas() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacoraZonas" VALUES (NULL,NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_zonas",NEW."nombre",NEW."extención");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacoraZonas_IA AFTER INSERT ON public."zonas"
FOR EACH ROW EXECUTE PROCEDURE InsertarZonas();

-- INSERT
INSERT INTO "zonas" VALUES (16,'Matagalpa','200millas');
SELECT * FROM "BitacoraZonas";


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarZonas() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacoraZonas" VALUES (NULL,NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_zonas",OLD."nombre",OLD."extención");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacoraZonas_DB BEFORE DELETE ON public."zonas"
FOR EACH ROW EXECUTE PROCEDURE EliminarZonas();

-- Eliminar
DELETE FROM "zonas" WHERE "id_zonas"=16;
SELECT * FROM "BitacoraZonas";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarZonas() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacoraZonas" VALUES (OLD."id_zonas",OLD."nombre",OLD."extención",CURRENT_USER,tipo_accion,NOW(),NEW."id_zonas",NEW."nombre",NEW."extención");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacoraZonas_UB BEFORE UPDATE ON public."zonas"
FOR EACH ROW EXECUTE PROCEDURE ActualizarZonas();

-- Actualizar
UPDATE "zonas" SET "nombre" = 'Herrera' WHERE "id_zonas"=1;
SELECT * FROM "BitacoraZonas";