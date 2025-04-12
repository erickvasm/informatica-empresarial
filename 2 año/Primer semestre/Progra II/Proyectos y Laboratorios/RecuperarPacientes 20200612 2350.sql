-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.18-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema controlpacientes
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ controlpacientes;
USE controlpacientes;

--
-- Table structure for table `controlpacientes`.`pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
CREATE TABLE `pacientes` (
  `Identificacion` varchar(9) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Apellido` varchar(20) NOT NULL,
  `Genero` char(1) NOT NULL,
  `Direccion` varchar(145) NOT NULL,
  `Telefono` int(11) NOT NULL,
  `Condicion` varchar(10) NOT NULL,
  `Dias` int(11) NOT NULL,
  PRIMARY KEY (`Identificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `controlpacientes`.`pacientes`
--

/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` (`Identificacion`,`Nombre`,`Apellido`,`Genero`,`Direccion`,`Telefono`,`Condicion`,`Dias`) VALUES 
 ('123456789','Maria','Maria','F','100 mts',12345678,'Estable',2),
 ('208210061','Cris','Taisigue','M','100 mts sur',12345678,'Regular',2);
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
