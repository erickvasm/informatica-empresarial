--[canton]
	
CREATE TABLE public.bitacoras_canton_junio2021 PARTITION OF public."BitacorasCanton"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarCanton() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacorasCanton" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_canton",NEW."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasCanton_IA AFTER INSERT ON public."canton"
FOR EACH ROW EXECUTE PROCEDURE InsertarCanton();

-- INSERT
INSERT INTO "canton" VALUES (16,'Peralta');
SELECT * FROM "BitacorasCanton";


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarCanton() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacorasCanton" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_canton",OLD."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasCanton_DB BEFORE DELETE ON public."canton"
FOR EACH ROW EXECUTE PROCEDURE EliminarCanton();

-- Eliminar
DELETE FROM "canton" WHERE "id_canton"=16;
SELECT * FROM "BitacorasCanton";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarCanton() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacorasCanton" VALUES (OLD."id_canton",OLD."nombre",CURRENT_USER,tipo_accion,NOW(),NEW."id_canton",NEW."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasCanton_UB BEFORE UPDATE ON public."canton"
FOR EACH ROW EXECUTE PROCEDURE ActualizarCanton();

-- Actualizar
UPDATE "canton" SET "nombre" = 'Orlando' WHERE "id_canton"=1;
SELECT * FROM "BitacorasCanton";