CREATE TABLE `servicio` (
  `idservicio` int(11) NOT NULL AUTO_INCREMENT,
  `nombreServicio` varchar(45) NOT NULL COMMENT 'Nombre del servicio o mozo, ej: Administracion, Cocina, Bar, Mesero Juan, Mesera Ana',
  `host` varchar(45) NOT NULL COMMENT 'Host de la máquina.',
  `puerto` int(11) NOT NULL COMMENT 'puerto en el que escucha esta máquina',
  `tipo` varchar(1) NOT NULL DEFAULT 'M' COMMENT 'Es el tipo de servicio que se presta: A administracion, M mesero, S servicio (cocina, bar, etc), R recepcionista',
  `clave` varchar(30) DEFAULT NULL COMMENT 'La clave para loguerarse al servicio. Puede estar en blanco para ingresar sin clave',
  PRIMARY KEY (`idservicio`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8


/*
-- Query: SELECT * FROM restaurante.servicio
LIMIT 0, 5000

-- Date: 2023-10-18 16:35
*/
INSERT INTO `servicio` (`idservicio`,`nombreServicio`,`host`,`puerto`,`tipo`,`clave`) VALUES (1,'Administración','localhost',20000,'A','12345');
INSERT INTO `servicio` (`idservicio`,`nombreServicio`,`host`,`puerto`,`tipo`,`clave`) VALUES (2,'Cocina','localhost',20001,'S',NULL);
INSERT INTO `servicio` (`idservicio`,`nombreServicio`,`host`,`puerto`,`tipo`,`clave`) VALUES (3,'Bar','localhost',20002,'S',NULL);
INSERT INTO `servicio` (`idservicio`,`nombreServicio`,`host`,`puerto`,`tipo`,`clave`) VALUES (4,'Recepcion','localhost',20003,'R',NULL);
INSERT INTO `servicio` (`idservicio`,`nombreServicio`,`host`,`puerto`,`tipo`,`clave`) VALUES (5,'Leticia Mores','localhost',20004,'M','12345');
INSERT INTO `servicio` (`idservicio`,`nombreServicio`,`host`,`puerto`,`tipo`,`clave`) VALUES (6,'Enrique Martinez','localhost',20005,'M','12345');
INSERT INTO `servicio` (`idservicio`,`nombreServicio`,`host`,`puerto`,`tipo`,`clave`) VALUES (7,'Eduardo Beltran','localhost',20006,'M','12345');
INSERT INTO `servicio` (`idservicio`,`nombreServicio`,`host`,`puerto`,`tipo`,`clave`) VALUES (8,'John David Molina','localhost',20007,'M','12345');
INSERT INTO `servicio` (`idservicio`,`nombreServicio`,`host`,`puerto`,`tipo`,`clave`) VALUES (9,'Jorge González','localhost',20008,'R','12345');
INSERT INTO `servicio` (`idservicio`,`nombreServicio`,`host`,`puerto`,`tipo`,`clave`) VALUES (10,'Cafeteria','localhost',20009,'S',NULL);
