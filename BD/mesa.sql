CREATE TABLE `mesa` (
  `idMesa` int(11) NOT NULL AUTO_INCREMENT,
  `capacidad` int(11) NOT NULL COMMENT 'Cuantas personas admite la mesa',
  `estado` varchar(1) NOT NULL COMMENT 'Estado de la mesa: L libre, O ocupada, A atendida.\n',
  `idmesero` int(11) DEFAULT NULL COMMENT 'Es el mesero que está atendiendo la mesa. Corresponde al idServicio que sea tipo M\n',
  PRIMARY KEY (`idMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4

/*
-- Query: SELECT * FROM restaurante.mesa
LIMIT 0, 5000

-- Date: 2023-10-18 16:37
*/
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (1,4,'L',6);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (2,6,'L',8);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (3,1,'L',8);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (4,2,'A',6);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (5,7,'A',5);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (6,5,'A',5);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (7,4,'L',5);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (8,2,'L',NULL);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (9,10,'O',5);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (10,6,'L',6);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (11,4,'L',7);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (12,8,'L',8);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (13,5,'L',NULL);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (27,5,'L',8);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (28,3,'L',8);
INSERT INTO `mesa` (`idMesa`,`capacidad`,`estado`,`idmesero`) VALUES (29,6,'L',6);
