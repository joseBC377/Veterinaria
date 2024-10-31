## Base de datos  
- Copiar el codigo sql a su base de datos, de preferencia que la base de datos sea "Mysql". 
```
CREATE DATABASE petvet;
USE petvet;


-- Crear la tabla Clientes
CREATE TABLE IF NOT EXISTS Clientes (
    ClienteID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Email VARCHAR(150) NOT NULL UNIQUE,
    Telefono VARCHAR(15),
    Direccion VARCHAR(255),
    Contrasena VARCHAR(255) NOT NULL
);


-- Crear la tabla Categoria_Producto
CREATE TABLE IF NOT EXISTS Categoria_Producto (
    CategoriaID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Tipo VARCHAR(50)


);


-- Crear la tabla Producto
CREATE TABLE IF NOT EXISTS Producto (
    ProductoID INT AUTO_INCREMENT PRIMARY KEY,
    Imagen VARCHAR(255) NOT NULL,
    Titulo VARCHAR(50) NOT NULL,
    Precio DECIMAL(10, 2) NOT NULL,
    CategoriaID INT,  -- Relación con la tabla de categoría
    FOREIGN KEY (CategoriaID) REFERENCES Categoria_Producto(CategoriaID)


);


-- Crear la tabla Pedido
CREATE TABLE IF NOT EXISTS Pedido (
    PedidoID INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATETIME NOT NULL,
    ClienteID INT NOT NULL,
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID)
);


-- Crear la tabla Detalle_Pedido
CREATE TABLE IF NOT EXISTS Detalle_Pedido (
    DetalleID INT AUTO_INCREMENT PRIMARY KEY,
    PedidoID INT NOT NULL,
    ProductoID INT NOT NULL,
    Cantidad INT NOT NULL,
    PrecioUnitario DECIMAL(10, 2) NOT NULL,
    FechaPedido DATETIME NOT NULL,
    FOREIGN KEY (PedidoID) REFERENCES Pedido(PedidoID),
    FOREIGN KEY (ProductoID) REFERENCES Producto(ProductoID)
);


-- Crear la tabla Servicio
CREATE TABLE IF NOT EXISTS servicio (
   ServicioID INT AUTO_INCREMENT PRIMARY KEY,
   nombre varchar(255) NOT NULL,
   descripcion varchar(255) DEFAULT NULL,
   precio varchar(100)DEFAULT NULL,
   imagen varchar(255) DEFAULT NULL,
   detalles varchar(255) DEFAULT NULL
);


-- Crear la tabla Pagos
CREATE TABLE IF NOT EXISTS Pagos (
    PagoID INT AUTO_INCREMENT PRIMARY KEY,
    PedidoID INT NOT NULL,
    Total DECIMAL(10, 2) NOT NULL,
    FechaPago DATETIME NOT NULL,
    MetodoPago VARCHAR(30),
    FOREIGN KEY (PedidoID) REFERENCES Pedido(PedidoID)
);


-- Crear la tabla Reserva
CREATE TABLE IF NOT EXISTS reserva (
    ReservaID INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATE NOT NULL,
    Hora TIME NOT NULL,
    ServicioID INT NOT NULL,
    Veterinario VARCHAR(150),
    ClienteID INT NOT NULL,
    FOREIGN KEY (ServicioID) REFERENCES Servicio(ServicioID),
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID)
);


-- Crear la tabla Contactanos
CREATE TABLE IF NOT EXISTS Contactanos (
    ContactoID INT AUTO_INCREMENT PRIMARY KEY,  
    Nombre VARCHAR(100) NOT NULL,               
    Apellido VARCHAR(100) NOT NULL,             
    Correo VARCHAR(150) NOT NULL,                
    Mensaje TEXT NOT NULL,              
    ServicioID INT,
    FOREIGN KEY (ServicioID) REFERENCES Servicio(ServicioID)
);
                  



