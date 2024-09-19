## Base de datos  
- Copiar el codigo sql a su base de datos, de preferencia que la base de datos sea "Mysql". 
```
CREATE DATABASE veterinaria_petvet;
USE veterinaria_petvet;

CREATE TABLE Clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    correo_electronico VARCHAR(150),
    telefono VARCHAR(15),
    direccion VARCHAR(255),
    contrasena VARCHAR(255)
);

CREATE TABLE Categoria_Producto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre_categoria VARCHAR(100)
);

CREATE TABLE Items (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    descripcion VARCHAR(150),
    precio DECIMAL(10,2),
    tipo_item VARCHAR(50),
    direccion VARCHAR(255),
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria_Producto(id)
);

CREATE TABLE Citas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha_cita DATETIME,
    hora_cita TIME,
    estado_cita VARCHAR(50),
    notas TEXT,
    veterinario_asignado VARCHAR(150),
    id_cliente INT,
    id_item INT,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id),
    FOREIGN KEY (id_item) REFERENCES Items(id)
);

CREATE TABLE Contactanos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    apellido VARCHAR(20),
    correo VARCHAR(150),
    tipo_servicio VARCHAR(50),
    nombre VARCHAR(20),
    mensaje TEXT,
    fecha_contacto DATETIME,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id)
);

CREATE TABLE Pagos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    total DECIMAL(10,2),
    metodo_pago VARCHAR(30),
    fecha_pago DATETIME,
    id_pedido INT,
    id_cita INT,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id),
    FOREIGN KEY (id_cita) REFERENCES Citas(id)
);

CREATE TABLE DetallePedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cantidad INT,
    precio_unitario DECIMAL(10,2),
    fecha_pedido DATETIME,
    id_cliente INT,
    id_item INT,
    id_pagos INT,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id),
    FOREIGN KEY (id_item) REFERENCES Items(id),
    FOREIGN KEY (id_pagos) REFERENCES Pagos(id)
);

