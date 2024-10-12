-- Insertar categorías
delete from producto_categoria;
delete from venta_producto;
delete from producto;
delete from categoria;
delete from venta;
delete from cliente;
delete from cuenta;
delete from ciudad;
delete from departamento;
INSERT INTO categoria (id_categoria, nombre, impuesto)
VALUES
    (1, 'Electrónicos', 0.19),
    (2, 'Ropa', 0.16),
    (3, 'Alimentos', 0.05),
    (4, 'Libros', 0.10);

-- Insertar cuentas
INSERT INTO cuenta (id_cuenta, password, privilegio)
VALUES
    ('123e4567-e89b-12d3-a456-426614174000', 'password123', 'VIP'),
    ('223e4567-e89b-12d3-a456-426614174001', 'securepass', 'FRECUENTE'),
    ('323e4567-e89b-12d3-a456-426614174002', 'userpass', 'NOFRECUENTE');

-- Insertar departamentos
INSERT INTO departamento (id_departamento, cod_departamento, nombre)
VALUES
    (1, 101, 'Cundinamarca'),
    (2, 102, 'Antioquia'),
    (3, 103, 'Valle del Cauca');

-- Insertar ciudades
INSERT INTO ciudad (id_ciudad, codigo_ciudad, nombre, id_departamentofk)
VALUES
    (1, 1001, 'Bogotá', 1),
    (2, 1002, 'Medellín', 2),
    (3, 1003, 'Cali', 3);

-- Insertar clientes
INSERT INTO cliente (id_cliente, nombre, apellido, id_ciudadfk, id_cuentafk)
VALUES
    (1, 'Juan', 'Pérez', 1, '123e4567-e89b-12d3-a456-426614174000'),
    (2, 'María', 'González', 2, '223e4567-e89b-12d3-a456-426614174001'),
    (3, 'Carlos', 'Rodríguez', 3, '323e4567-e89b-12d3-a456-426614174002');

-- Insertar productos
INSERT INTO producto (id_producto, nombre, precio)
VALUES
    (1, 'Smartphone', 999.99),
    (2, 'Camiseta', 29.99),
    (3, 'Arroz', 5.99),
    (4, 'Novela bestseller', 19.99);

-- Insertar relaciones producto-categoria
INSERT INTO producto_categoria (id_producto, id_categoria)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4);

-- Insertar ventas
INSERT INTO venta (id_venta, fecha, id_clientefk)
VALUES
    (1, '2024-10-11', 1),
    (2, '2024-10-11', 2),
    (3, '2024-10-11', 3),
    (4,'2024-11-11',1),
    (5,'2024-11-11',1);

-- Insertar detalles de venta (venta_producto)
INSERT INTO venta_producto (id_venta, id_producto, cantidad)
VALUES
    (1, 1, 1),
    (1, 2, 2),
    (2, 3, 5),
    (3, 4, 1),
    (4,1,2),
    (5,2,3);
