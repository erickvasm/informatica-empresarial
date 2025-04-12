--[Especie]
	
CREATE TABLE public.bitacoras_especie_junio2021 PARTITION OF public."BitacorasEspecie"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarEspecie() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacorasEspecie" VALUES (NULL,NULL,NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_especie",NEW."nombre",NEW."nombre_cientifico",NEW."descripcion");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasEspecie_IA AFTER INSERT ON public."especie"
FOR EACH ROW EXECUTE PROCEDURE InsertarEspecie();

-- INSERT
INSERT INTO "especie" VALUES (16,'Perro','Caninus','El mejor amigo del hombre');
SELECT * FROM "BitacorasEspecie";


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarEspecie() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacorasEspecie" VALUES (NULL,NULL,NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_especie",OLD."nombre",OLD."nombre_cientifico",OLD."descripcion");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasEspecie_DB BEFORE DELETE ON public."especie"
FOR EACH ROW EXECUTE PROCEDURE EliminarEspecie();

-- Eliminar
DELETE FROM "especie" WHERE "id_especie"=1;
SELECT * FROM "BitacorasEspecie";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarEspecie() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacorasEspecie" VALUES (OLD."id_especie",OLD."nombre",OLD."nombre_cientifico",OLD."descripcion",CURRENT_USER,tipo_accion,NOW(),NEW."id_especie",NEW."nombre",NEW."nombre_cientifico",NEW."descripcion");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasEspecie_UB BEFORE UPDATE ON public."especie"
FOR EACH ROW EXECUTE PROCEDURE ActualizarEspecie();

-- Actualizar
UPDATE "especie" SET "nombre_cientifico" = 'color rojo' WHERE "id_especie"=2;
SELECT * FROM "BitacorasEspecie";

--CAMBIE LA EXTENSION DEL CAMPO nombreNEW nombreOLD en BitacorasEspecie a 30