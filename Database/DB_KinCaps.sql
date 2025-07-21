-- DROP DATABASE IF EXISTS DB_KinCaps
CREATE DATABASE IF NOT EXISTS DB_KinCaps;
USE DB_KinCaps;

CREATE TABLE proveedor (
    idProveedor INT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    contacto VARCHAR(100),
    telefono VARCHAR(15),
    CONSTRAINT PK_Proveedor PRIMARY KEY (idProveedor)
);

CREATE TABLE cliente (
    idCliente INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    telefono VARCHAR(15),
    direccion VARCHAR(255),
    contrasenaHash CHAR(64) NOT NULL,
    CONSTRAINT PK_Cliente PRIMARY KEY (idCliente)
);

CREATE TABLE empleados (
    idEmpleado INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    telefono VARCHAR(15),
    direccion VARCHAR(255),
    contrasenaHash CHAR(64) NOT NULL,
    puesto VARCHAR(50),
    fechaContratacion DATE,
    CONSTRAINT PK_Empleados PRIMARY KEY (idEmpleado)
);

CREATE TABLE gorras (
    idGorra INT AUTO_INCREMENT,
    modelo VARCHAR(100) NOT NULL,
    marca VARCHAR(50),
    color VARCHAR(30),
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    idProveedor INT,
    CONSTRAINT PK_Gorras PRIMARY KEY (idGorra),
    CONSTRAINT FK_GorrasProveedor FOREIGN KEY (idProveedor) REFERENCES proveedor(idProveedor)
);

CREATE TABLE carrito (
    idCarrito INT AUTO_INCREMENT,
    idCliente INT NOT NULL,
    fechaCreacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_Carrito PRIMARY KEY (idCarrito),
    CONSTRAINT FK_CarritoCliente FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
);

CREATE TABLE factura (
    idFactura INT AUTO_INCREMENT,
    idCliente INT NOT NULL,
    idEmpleado INT NOT NULL,
    fechaEmision DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10, 2) NOT NULL,
    metodoPago ENUM('Tarjeta de Crédito', 'Tarjeta de Débito', 'Efectivo', 'Transferencia Bancaria') NOT NULL,
    CONSTRAINT PK_Factura PRIMARY KEY (idFactura),
    CONSTRAINT FK_FacturaCliente FOREIGN KEY (idCliente) REFERENCES cliente(idCliente),
    CONSTRAINT FK_FacturaEmpleados FOREIGN KEY (idEmpleado) REFERENCES empleados(idEmpleado)
);

CREATE TABLE detalleCarrito (
    idDetalleCarrito INT AUTO_INCREMENT,
    idCarrito INT NOT NULL,
    idGorra INT NOT NULL,
    cantidad INT NOT NULL,
    precioUnitario DECIMAL(10, 2) NOT NULL,
    CONSTRAINT PK_DetalleCarrito PRIMARY KEY (idDetalleCarrito),
    CONSTRAINT FK_DetalleCarritoCarrito FOREIGN KEY (idCarrito) REFERENCES carrito(idCarrito),
    CONSTRAINT FK_DetalleCarritoGorras FOREIGN KEY (idGorra) REFERENCES gorras(idGorra)
);

CREATE TABLE detalleFactura (
    idDetalleFactura INT AUTO_INCREMENT,
    idFactura INT NOT NULL,
    idGorra INT NOT NULL,
    cantidad INT NOT NULL,
    precioVenta DECIMAL(10, 2) NOT NULL,
    CONSTRAINT PK_DetalleFactura PRIMARY KEY (idDetalleFactura),
    CONSTRAINT FK_DetalleFacturaFactura FOREIGN KEY (idFactura) REFERENCES factura(idFactura),
    CONSTRAINT FK_DetalleFacturaGorras FOREIGN KEY (idGorra) REFERENCES gorras(idGorra)
);
