--[cuidadores_especies]
	
CREATE TABLE public.bitacoras_cuidadores_especies_junio2021 PARTITION OF public."BitacorasCuidadores_especie"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarCuidadoresEspecies() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacorasCuidadores_especie" VALUES (NULL,NULL,NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_cuidadores_especies",NEW."id_cuidadores_fk",NEW."id_especies_fk",NEW."fecha_de_encargo");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasCuidadoresEspecies_IA AFTER INSERT ON public."cuidadores_especies"
FOR EACH ROW EXECUTE PROCEDURE InsertarCuidadoresEspecies();

-- INSERT
INSERT INTO "cuidadores_especies" VALUES (17,14,14,'2021-06-22');
SELECT * FROM "BitacorasCuidadores_especie";
-- DEBE INSERTAR UNA NUEVA ESPECIE, QUE RELACIONO SU ID CON ID_CUIDADODERES_FK (MISMO)
--INSERT INTO especie (id_especie,nombre,nombre_cientifico,descripcion) VALUES (17,'Matra','Matra Cat','Color Amarillo');


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarCuidadoresEspecies() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacorasCuidadores_especie" VALUES (NULL,NULL,NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_cuidadores_especies",OLD."id_cuidadores_fk",OLD."id_especies_fk",OLD."fecha_de_encargo");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasCuidadoresEspecies_DB BEFORE DELETE ON public."cuidadores_especies"
FOR EACH ROW EXECUTE PROCEDURE EliminarCuidadoresEspecies();

-- Eliminar
DELETE FROM "cuidadores_especies" WHERE "id_cuidadores_especies"=17;
SELECT * FROM "BitacorasCuidadores_especie";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarCuidadoresEspecies() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacorasCuidadores_especie" VALUES (OLD."id_cuidadores_especies",OLD."id_cuidadores_fk",OLD."id_especies_fk",OLD."fecha_de_encargo",CURRENT_USER,tipo_accion,NOW(),NEW."id_cuidadores_especies",NEW."id_cuidadores_fk",NEW."id_especies_fk",NEW."fecha_de_encargo");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasCuidadoresEspecies_UB BEFORE UPDATE ON public."cuidadores_especies"
FOR EACH ROW EXECUTE PROCEDURE ActualizarCuidadoresEspecies();

-- Actualizar
UPDATE "cuidadores_especies" SET "fecha_de_encargo" = '2021-04-04' WHERE "id_cuidadores_especies"=1;
SELECT * FROM "BitacorasCuidadores_especie";