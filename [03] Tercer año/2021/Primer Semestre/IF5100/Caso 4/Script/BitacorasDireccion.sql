--[Direccion]
	
CREATE TABLE public.bitacoras_direccion_junio2021 PARTITION OF public."BitacorasDireccion"
FOR VALUES FROM ('2021-06-01') TO ('2021-06-30');


	--- [INSERTAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION InsertarDireccion() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'INSERT';
		
	BEGIN
	
		INSERT INTO "BitacorasDireccion" VALUES (NULL,NULL,NULL,NULL,NULL,NULL,CURRENT_USER,tipo_accion,NOW(),NEW."id_direccion",NEW."id_pais_fk",NEW."id_provincia_fk",NEW."id_canton_fk",NEW."id_distrito_fk",NEW."id_barrio_fk");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasDireccion_IA AFTER INSERT ON public."direccion"
FOR EACH ROW EXECUTE PROCEDURE InsertarDireccion();

-- INSERT
INSERT INTO "direccion" VALUES (17,1,1,1,1,1);
SELECT * FROM "BitacorasDireccion";


				--- [ELIMINAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION EliminarDireccion() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'DELETE';
		
	BEGIN
	
		INSERT INTO "BitacorasDireccion" VALUES (NULL,NULL,NULL,NULL,NULL,NULL,CURRENT_USER,tipo_accion,NOW(),OLD."id_direccion",OLD."id_pais_fk",OLD."id_provincia_fk",OLD."id_canton_fk",OLD."id_distrito_fk",OLD."id_barrio_fk");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasDireccion_DB BEFORE DELETE ON public."direccion"
FOR EACH ROW EXECUTE PROCEDURE EliminarDireccion();

-- Eliminar
DELETE FROM "direccion" WHERE "id_direccion"=1;
SELECT * FROM "BitacorasDireccion";


				--- [ACTUALIZAR]

-- [FUNCTION]
CREATE OR REPLACE FUNCTION ActualizarDireccion() RETURNS TRIGGER LANGUAGE 'plpgsql'
AS
$$
	DECLARE
		
		tipo_accion character(30) := 'UPDATE';
		
	BEGIN
	
		INSERT INTO "BitacorasDireccion" VALUES (OLD."id_direccion",OLD."id_pais_fk",OLD."id_provincia_fk",OLD."id_canton_fk",OLD."id_distrito_fk",OLD."id_barrio_fk",CURRENT_USER,tipo_accion,NOW(),NEW."id_direccion",NEW."id_pais_fk",NEW."id_provincia_fk",NEW."id_canton_fk",NEW."id_distrito_fk",NEW."id_barrio_fk");
		RETURN NULL;
		
	END
$$

-- [ TRIGGER ]
CREATE TRIGGER BitacorasDireccion_UB BEFORE UPDATE ON public."direccion"
FOR EACH ROW EXECUTE PROCEDURE ActualizarDireccion();

-- Actualizar
UPDATE "direccion" SET "id_pais_fk" = 2 WHERE "id_direccion"=1;
SELECT * FROM "BitacorasDireccion";