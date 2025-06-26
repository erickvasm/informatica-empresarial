--[Clima]
	
CREATE TABLE public.bitacoras_clima_junio2021 PARTITION OF public."BitacorasClima"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarClima() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacorasClima" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_clima",NEW."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasClima_IA AFTER INSERT ON public."clima"
FOR EACH ROW EXECUTE PROCEDURE InsertarClima();

-- INSERT
INSERT INTO "clima" VALUES (16,'Veraniego');
SELECT * FROM "BitacorasClima";


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarClima() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacorasClima" VALUES (NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_clima",OLD."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasClima_DB BEFORE DELETE ON public."clima"
FOR EACH ROW EXECUTE PROCEDURE EliminarClima();

-- Eliminar
DELETE FROM "clima" WHERE "id_clima"=16;
SELECT * FROM "BitacorasClima";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarClima() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacorasClima" VALUES (OLD."id_clima",OLD."nombre",CURRENT_USER,tipo_accion,NOW(),NEW."id_clima",NEW."nombre");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasClima_UB BEFORE UPDATE ON public."clima"
FOR EACH ROW EXECUTE PROCEDURE ActualizarClima();

-- Actualizar
UPDATE "clima" SET "nombre" = 'Riego' WHERE "id_clima"=1;
SELECT * FROM "BitacorasClima";