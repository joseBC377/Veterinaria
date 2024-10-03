## Base de datos  
- Copiar el codigo sql a su base de datos, de preferencia que la base de datos sea "Mysql". 
```
CREATE DATABASE VeterinariaPETVET;
USE VeterinariaPETVET;

CREATE TABLE Clientes (
    ClienteID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    Apellido VARCHAR(100),
    CorreoElectronico VARCHAR(150),
    Telefono VARCHAR(15),
    Direccion VARCHAR(255),
    Contrasena VARCHAR(255)
);

CREATE TABLE Categoria_Producto (
    CategoriaID INT AUTO_INCREMENT PRIMARY KEY,
    NombreCategoria VARCHAR(100)
);

CREATE TABLE Producto (
    ProductoID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50),
    Descripcion VARCHAR(150),
    Precio DECIMAL(10, 2),
    Stock INT,
    CategoriaID INT,
    FOREIGN KEY (CategoriaID) REFERENCES Categoria_Producto(CategoriaID)
);

CREATE TABLE Pedido (
    PedidoID INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATETIME,
    ClienteID INT,
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID)
);

CREATE TABLE Detalle_Pedido (
    DetalleID INT AUTO_INCREMENT PRIMARY KEY,
    PedidoID INT,
    ProductoID INT,
    Cantidad INT,
    PrecioUnitario DECIMAL(10, 2),
    FechaPedido DATETIME,
    FOREIGN KEY (PedidoID) REFERENCES Pedido(PedidoID),
    FOREIGN KEY (ProductoID) REFERENCES Producto(ProductoID)
);

CREATE TABLE Servicio (
    ServicioID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(60),
    Descripcion VARCHAR(150),
    Precio DECIMAL(10, 2)
);

CREATE TABLE Pagos (
    PagoID INT AUTO_INCREMENT PRIMARY KEY,
    PedidoID INT,
    Total DECIMAL(10, 2),
    FechaPago DATETIME,
    MetodoPago VARCHAR(30),
    FOREIGN KEY (PedidoID) REFERENCES Pedido(PedidoID)
);

CREATE TABLE Reserva (
    ReservaID INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATETIME,
    Hora TIME,
    ServicioID INT,
    Veterinario VARCHAR(150),
    ClienteID INT,
    FOREIGN KEY (ServicioID) REFERENCES Servicio(ServicioID),
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID)
);

CREATE TABLE Proveedores (
    ProveedorID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    Direccion VARCHAR(255),
    Telefono VARCHAR(15),
    Correo VARCHAR(150)
);

