# Esquema de la Base de Datos PetVet

A continuación, se muestra el esquema de la base de datos **PetVet**:

```sql
CREATE DATABASE petvet;
USE petvet;

CREATE TABLE `categoria_producto` (
  `CategoriaID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Tipo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CategoriaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `clientes` (
  `ClienteID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Apellido` varchar(100) NOT NULL,
  `Email` varchar(150) NOT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `Contrasena` varchar(255) NOT NULL,
  `rol` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `clientes` (`Nombre`, `Apellido`, `Email`, `Telefono`, `Direccion`, `Contrasena`, `rol`) VALUES
('Luis', 'Torres', 'dani@example.com', '931234567', 'Pasaje del Sol 89', '$2a$12$7BfJO5qTgKuEFjgCQjBMZezujGXtEGvCY3BcjudDc5TVrDR9Jcihe', 'ADMIN');

CREATE TABLE `servicio` (
  `ServicioID` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `detalles` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ServicioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `pedido` (
  `PedidoID` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` datetime NOT NULL,
  `ClienteID` int(11) NOT NULL,
  PRIMARY KEY (`PedidoID`),
  FOREIGN KEY (`ClienteID`) REFERENCES `clientes`(`ClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `producto` (
  `ProductoID` int(11) NOT NULL AUTO_INCREMENT,
  `Imagen` varchar(255) NOT NULL,
  `Titulo` varchar(50) NOT NULL,
  `Precio` decimal(10,2) NOT NULL,
  `CategoriaID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ProductoID`),
  FOREIGN KEY (`CategoriaID`) REFERENCES `categoria_producto`(`CategoriaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `contactanos` (
  `ContactoID` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Apellido` varchar(100) NOT NULL,
  `Correo` varchar(150) NOT NULL,
  `Mensaje` text NOT NULL,
  `ServicioID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ContactoID`),
  FOREIGN KEY (`ServicioID`) REFERENCES `servicio`(`ServicioID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `detalle_pedido` (
  `DetalleID` int(11) NOT NULL AUTO_INCREMENT,
  `PedidoID` int(11) NOT NULL,
  `ProductoID` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `PrecioUnitario` decimal(10,2) NOT NULL,
  `FechaPedido` datetime NOT NULL,
  PRIMARY KEY (`DetalleID`),
  FOREIGN KEY (`PedidoID`) REFERENCES `pedido`(`PedidoID`),
  FOREIGN KEY (`ProductoID`) REFERENCES `producto`(`ProductoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `pagos` (
  `PagoID` int(11) NOT NULL AUTO_INCREMENT,
  `PedidoID` int(11) NOT NULL,
  `Total` decimal(10,2) NOT NULL,
  `FechaPago` datetime NOT NULL,
  `MetodoPago` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`PagoID`),
  FOREIGN KEY (`PedidoID`) REFERENCES `pedido`(`PedidoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `reserva` (
  `ReservaID` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `ServicioID` int(11) DEFAULT NULL,
  `Veterinario` varchar(150) DEFAULT NULL,
  `ClienteID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ReservaID`),
  FOREIGN KEY (`ServicioID`) REFERENCES `servicio`(`ServicioID`),
  FOREIGN KEY (`ClienteID`) REFERENCES `clientes`(`ClienteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
                   
);


           
