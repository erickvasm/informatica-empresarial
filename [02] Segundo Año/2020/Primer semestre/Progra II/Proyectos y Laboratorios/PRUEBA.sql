USE ucr;

SELECT * FROM estudiantes;

INSERT INTO estudiantes VALUE("B98334", "Erick", "Vasquez", "Murillo");
INSERT INTO estudiantes VALUE("B98335", "Pedro", "Vasquez", "Lopez");
DELETE FROM estudiantes WHERE Carne="B98334";
UPDATE estudiantes set Nombre= "Maria" WHERE Carne="B98335";
