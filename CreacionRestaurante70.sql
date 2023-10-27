-- MySQL Script generated by MySQL Workbench
-- Fri Oct 27 14:11:16 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema restaurante
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `restaurante` ;

-- -----------------------------------------------------
-- Schema restaurante
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `restaurante` DEFAULT CHARACTER SET utf8 ;
USE `restaurante` ;

-- -----------------------------------------------------
-- Table `restaurante`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurante`.`categoria` ;

CREATE TABLE IF NOT EXISTS `restaurante`.`categoria` (
  `idcategoria` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL DEFAULT NULL COMMENT 'Nombre de la categoría a la que pertenece un producto. Ej: Sopas, Carnes, Pescados, Postres, Bebidas, Entradas.',
  PRIMARY KEY (`idcategoria`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `restaurante`.`servicio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurante`.`servicio` ;

CREATE TABLE IF NOT EXISTS `restaurante`.`servicio` (
  `idservicio` INT(11) NOT NULL AUTO_INCREMENT,
  `nombreServicio` VARCHAR(45) NOT NULL COMMENT 'Nombre del servicio o mozo, ej: Administracion, Cocina, Bar, Mesero Juan, Mesera Ana',
  `host` VARCHAR(45) NOT NULL COMMENT 'Host de la máquina.',
  `puerto` INT(11) NOT NULL COMMENT 'puerto en el que escucha esta máquina',
  `tipo` VARCHAR(1) NOT NULL DEFAULT 'M' COMMENT 'Es el tipo de servicio que se presta: A administracion, M mesero, S servicio (cocina, bar, etc), R recepcionista',
  `clave` VARCHAR(30) NULL DEFAULT NULL COMMENT 'La clave para loguerarse al servicio. Puede estar en blanco para ingresar sin clave',
  PRIMARY KEY (`idservicio`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `restaurante`.`mesa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurante`.`mesa` ;

CREATE TABLE IF NOT EXISTS `restaurante`.`mesa` (
  `idMesa` INT(11) NOT NULL AUTO_INCREMENT,
  `capacidad` INT(11) NOT NULL COMMENT 'Cuantas personas admite la mesa',
  `estado` VARCHAR(1) NOT NULL COMMENT 'Estado de la mesa: L libre, O ocupada, A atendida.\n',
  `idmesero` INT(11) NULL DEFAULT NULL COMMENT 'Es el mesero que está atendiendo la mesa. Corresponde al idServicio que sea tipo M\n',
  PRIMARY KEY (`idMesa`),
  INDEX `mesa_fk_1_idx` (`idmesero` ASC),
  CONSTRAINT `mesa_fk_1`
    FOREIGN KEY (`idmesero`)
    REFERENCES `restaurante`.`servicio` (`idservicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `restaurante`.`pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurante`.`pedido` ;

CREATE TABLE IF NOT EXISTS `restaurante`.`pedido` (
  `idPedido` INT(11) NOT NULL AUTO_INCREMENT,
  `idMesa` INT(11) NOT NULL,
  `idMesero` INT(11) NOT NULL,
  `fechaHora` DATETIME NOT NULL,
  `estado` VARCHAR(1) NOT NULL DEFAULT 'A' COMMENT 'Estado del pedido: A activo, P pagado, C canceado',
  PRIMARY KEY (`idPedido`),
  INDEX `idMesa` (`idMesa` ASC),
  INDEX `pedido_ibfk_1_idx` (`idMesero` ASC),
  CONSTRAINT `pedido_ibfk_1`
    FOREIGN KEY (`idMesero`)
    REFERENCES `restaurante`.`servicio` (`idservicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pedido_ibfk_2`
    FOREIGN KEY (`idMesa`)
    REFERENCES `restaurante`.`mesa` (`idMesa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `restaurante`.`producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurante`.`producto` ;

CREATE TABLE IF NOT EXISTS `restaurante`.`producto` (
  `idproducto` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(30) NOT NULL COMMENT 'Nombre del plato o producto',
  `descripcion` VARCHAR(120) NOT NULL COMMENT 'Descripcion mas detallada del producto',
  `stock` INT(11) NOT NULL,
  `precio` DOUBLE NOT NULL,
  `disponible` TINYINT(4) NULL DEFAULT NULL COMMENT 'Verdadero si ese producto está en la carta.',
  `idcategoria` INT(11) NULL DEFAULT NULL COMMENT 'Categoria a la que pertenece el producto: Sopas, carnes, pastas, pescados, bebidas, etc.',
  `despachadopor` INT(11) NULL DEFAULT NULL COMMENT 'idServicio del servicio que se encarga de despachar este producto (por ejemplo, un pollo con papas lo prepara y despacha la cocina, un licuado lo prepara y despacha el bar). Si es null, ningun servicio lo despacha, sino que el mozo lo agarra directamente (por ejemplo toma una gaseosa de la heladera)',
  PRIMARY KEY (`idproducto`),
  INDEX `producto_ibfk_idx` (`idcategoria` ASC),
  INDEX `producto_ibfk_2_idx` (`despachadopor` ASC),
  CONSTRAINT `producto_ibfk`
    FOREIGN KEY (`idcategoria`)
    REFERENCES `restaurante`.`categoria` (`idcategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `producto_ibfk_2`
    FOREIGN KEY (`despachadopor`)
    REFERENCES `restaurante`.`servicio` (`idservicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 46
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `restaurante`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurante`.`item` ;

CREATE TABLE IF NOT EXISTS `restaurante`.`item` (
  `idItem` INT(11) NOT NULL AUTO_INCREMENT,
  `idProducto` INT(11) NOT NULL COMMENT 'id del producto de este item',
  `cantidad` INT(11) NOT NULL COMMENT 'Cantidad a servir',
  `idPedido` INT(11) NOT NULL COMMENT 'Pedido al que pertenece el item',
  `estado` VARCHAR(1) NULL DEFAULT NULL COMMENT 'Estado del item: A anotado, S solicitado, D despachado, E entregado, C cancelado, V cancelado y visto.\nCuando el mozo toma nota del pedido del cliente, que como Anotado, luego el mozo solicita ese producto a la cocina o bar quedando como Solicitado, cuando la cocina o bar lo despacha queda Despachado, y cuando el mozo lo sirve queda como Entregado. Si en algún momento se cancela el pedido, el item queda como Cancelado.',
  PRIMARY KEY (`idItem`),
  INDEX `idPedido` (`idPedido` ASC),
  INDEX `idProducto` (`idProducto` ASC),
  CONSTRAINT `item_ibfk_1`
    FOREIGN KEY (`idPedido`)
    REFERENCES `restaurante`.`pedido` (`idPedido`),
  CONSTRAINT `item_ibfk_2`
    FOREIGN KEY (`idProducto`)
    REFERENCES `restaurante`.`producto` (`idproducto`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 94
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
