INSERT INTO `mesa`(`capacidad`, `estado`) VALUES (4, 'L'), (6, 'L'), (8 , 'L'), 
(2 , 'L'), (4 , 'L'), (5, 'L'), (4, 'L'), (2, 'L'), (10, 'L'), (6, 'L'), (4, 'L'), 
(8, 'L'), (5, 'L'), (3, 'L'), (6,'L'), (4,'L'), (8,'L'), (4,'L'), (2,'L'), (2,'L'), 
(4,'L'), (6,'L'), (12,'L');

INSERT INTO `producto` (`idProducto`, `nombre`, `descripcion`, `stock`, `precio`) 
VALUES (NULL, 'Pizza Jamón y Morrones', 'Mozzarella, Jamón y Morrones asados ', '40', '4500'), 
(NULL, 'Pizza Mozzarela', 'Mozzarella, salsa de tomate', '50', '4500'), 
(NULL, 'Pizza Margarita', 'Mozzarella, tomate y albahaca', '50', '4500'), 
(NULL, 'Pizza Napolitana', 'Mozzarella, tomate, ajo y aceitunas', '40', '4800'), 
(NULL, 'Pizza Calabresa', 'Mozzarella, salsa de tomate y longaniza', '45', '5000'), 
(NULL, 'Pizza 4 Quesos', 'Mozzarella, Provolone, Chedar y Queso Azul', '40', '6500'), 
(NULL, 'Pizza Rúcula', 'Mozzarella, rúcula, jamón crudo y parmesano', '35', '5700'), 
(NULL, 'Pizza Hawaiana', 'Mozzarella, tomate, jamón y anana', '35', '5200'), 
(NULL, 'Pizza Vegetariana', 'Mozzarella, tomate, champiñones y pimientos', '30', '4900'), 
(NULL, 'Sandwich de Jamón y Queso', 'Jamón, queso, lechuga y tomate', '60', '2800'), 
(NULL, 'Sandwich de Pollo', 'Pechuga de pollo a la parrilla, lechuga, tomate y mayonesa', '55', '3200'), 
(NULL, 'Sandwich de Lomito', 'Lomito de res, lechuga, tomate, cebolla y mayonesa', '50', '3000'), 
(NULL, 'Sandwich de Lomito Completo', 'Lomito de res, lechuga, tomate, huevo y queso chedar', '40', '4000'), 
(NULL, 'Sándwich BLT', 'Sándwich de bacon, lechuga y tomate', '37', '2700'), 
(NULL, 'Sandwich de Vegetales', 'Vegetales asados, mozzarella y aderezo especial', '40', '3300'), 
(NULL, 'Sandwich de Milanesa', 'Milanesa de ternera, lechuga, tomate y mayonesa', '45', '4200'), 
(NULL, 'Sandwich de Suprema', 'Milanesa de pollo, lechuga, tomate y mayonesa', '45', '4200'), 
(NULL, 'Taco de Carne', 'Carne de res, cebolla, cilantro y salsa de tomate', '40', '3500'), 
(NULL, 'Taco de Cerdo', 'Bondiola desmechada, cebolla caramelizada, y BBQ', '40', '3500'), 
(NULL, 'Tacos de Carne Asada', 'Tacos de carne asada con cebolla y cilantro', '25', '2500'), 
(NULL, 'Taco de Pollo Clásico', 'Pollo desmenuzado, cebolla, lechuga y crema agria', '35', '3160'), 
(NULL, 'Tacos de Pollo BBQ', 'Tacos de pollo a la barbacoa con cebolla morada', '38', '3300'), 
(NULL, 'Taco de Pescado', 'Filet de pescado empanizado, repollo y salsa de chipotle', '30', '2500'), 
(NULL, 'Taco de Camarones', 'Camarones salteados, aguacate, cilantro y salsa picante', '30', '4220'), 
(NULL, 'Taco Vegetariano', 'Vegetales a la parrilla, palta y salsa de cilantro', '25', '2890'), 
(NULL, 'Tacos de Tofu', 'Tacos de tofu con guacamole', '28', '3300'), 
(NULL, 'Hamburguesa Clásica', 'Carne de res, lechuga, tomate, cebolla y salsa especial', '50', '3260'), 
(NULL, 'Hamburguesa con queso', 'Hamburguesa con carne de res, lechuga, tomate, cebolla y queso cheddar', '45', '4200'), 
(NULL, 'Hamburguesa de Pollo', 'Hamburguesa de pollo, lechuga, tomate y mayonesa', '45', '3280'), 
(NULL, 'Hamburguesa BBQ', 'Carne de res, cebolla caramelizada, bacon y salsa barbacoa', '55', '3300'), 
(NULL, 'Hamburguesa Vegetariana', 'Hamburguesa de lentejas, lechuga, tomate y salsa de palta', '40', '3250'), 
(NULL, 'Tarta de Manzana', 'Tarta de manzana con canela y crema', '20', '2150'), 
(NULL, 'Helado de Chocolate', 'Helado de chocolate con nueces y salsa de caramelo', '15', '2120'), 
(NULL, 'Flan Casero', 'Flan casero con caramelo', '25', '2100'), 
(NULL, 'Mousse de Frutilla', 'Mousse de frutilla con frutas frescas', '20', '2180'), 
(NULL, 'Cheesecake de Oreo', 'Cheesecake de oreo con crema batida', '18', '2200'), 
(NULL, 'Gaseosa Coca-Cola', 'Refresco de cola, lata 355ml', '80', '600'), 
(NULL, 'Gaseosa Sprite', 'Refresco de lima-limón, lata 355ml', '60', '600'), 
(NULL, 'Cerveza Quilmes', 'Cerveza rubia, botella 355ml', '40', '1120'), 
(NULL, 'Cerveza Patagonia', 'Cerveza artesanal, botella 355ml', '35', '1150'), 
(NULL, 'Agua Mineral', 'Agua mineral sin gas, botella 500ml', '90', '660');

delete from producto;


update restaurante.producto set disponible = True;

ALTER TABLE `restaurante`.`items` 
RENAME TO  `restaurante`.`item` ;

INSERT INTO `mesero` (`idMesero`, `nombreCompleto`, `clave`) VALUES 
(NULL, 'Leticia Mores', '12345'), 
(NULL, 'Enrique Martinez', '23456'), 
(NULL, 'John Molina Velarde', '34567'), 
(NULL, 'Eduardo Beltran', '45678');


-- cambios desde el 4/10/23

ALTER TABLE `restaurante`.`item` 
ADD COLUMN `estado` VARCHAR(1) NULL AFTER `idPedido`;

update item set estado = 'A';

ALTER TABLE `restaurante`.`reserva` 
DROP COLUMN `hora`,
CHANGE COLUMN `fecha` `fechahora` DATETIME NOT NULL ;

ALTER TABLE `restaurante`.`pedido` 
ADD COLUMN `fechaHora` DATETIME NOT NULL AFTER `idMesero`;


-- cambios desde el 6/10/23
CREATE TABLE `restaurante`.`categoria` (
  `idcategoria` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`idcategoria`));
ALTER TABLE `restaurante`.`producto` 
ADD COLUMN `idcategoria` INT NULL AFTER `disponible`,
ADD INDEX `producto_ibfk_idx` (`idcategoria` ASC);
ALTER TABLE `restaurante`.`producto` 
ADD CONSTRAINT `producto_ibfk`
  FOREIGN KEY (`idcategoria`)
  REFERENCES `restaurante`.`categoria` (`idcategoria`)
  ON DELETE SET NULL
  ON UPDATE CASCADE;

CREATE TABLE `restaurante`.`servicio` (
  `idservicio` INT NOT NULL AUTO_INCREMENT,
  `nombreServicio` VARCHAR(45) NOT NULL,
  `host` VARCHAR(45) NOT NULL,
  `puerto` INT NOT NULL,
  PRIMARY KEY (`idservicio`));


INSERT INTO `categoria`(`nombre`) VALUES 
('Pizzas'),
('Sandwiches'),
('Tacos'),
('Hamburguesas'),
('Postres'),
('Bebidas');


ALTER TABLE `restaurante`.`producto` 
ADD COLUMN `idservicio` INT NULL AFTER `idcategoria`;

ALTER TABLE `restaurante`.`mesa` 
ADD COLUMN `idmesero` INT NULL AFTER `estado`;

