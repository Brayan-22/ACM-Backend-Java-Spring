insert into categoria (nombre_categoria,descripcion_categoria)
values ('Terror','Libros de terror'),
('Romance','Libros de romance'),
('Ciencia Ficcion','Libros de ciencia ficcion'),
('Aventura','Libros de aventura'),
('Fantasia','Libros de fantasia');



insert into autor (id_autor,nombre_autor,pais_origen)
values (1,'Stephen King','Estados Unidos'),
(2,'J.K. Rowling','Reino Unido'),
(3,'George R.R. Martin','Estados Unidos'),
(4,'J.R.R. Tolkien','Reino Unido'),
(5,'Isaac Asimov','Rusia'),
(6,'Franz Kafka','Republica Checa');

insert into libro (titulo, anio_publicacion, disponibilidad, descripcion, id_autorFK, id_categoriaFK)
values
    ('It', '1986-09-15', true, 'Una novela de terror que sigue a un grupo de niños enfrentándose a un monstruo.', 1, 1),
    ('Harry Potter', '1997-06-26', true, 'La historia de un joven mago y sus aventuras en Hogwarts.', 2, 5),
    ('Juego de Tronos', '1996-08-06', true, 'Primera entrega de la serie Canción de Hielo y Fuego.', 3, 5),
    ('El Señor de los Anillos', '1954-07-29', true, 'Una de las obras más importantes de la literatura fantástica.', 4, 5),
    ('Fundación', '1951-05-01', true, 'Una de las series más influyentes de la ciencia ficción.', 5, 3),
    ('La Metamorfosis', '1915-01-01', true, 'La historia de Gregor Samsa, quien se despierta convertido en un insecto gigante.', 6, 3);

insert into cliente (id_cliente, nombre_cliente, correo_cliente, telefono_cliente, email_cliente, estado_cuenta)
values
    (1, 'Juan Perez', 'juan.perez@example.com', 1234567890, 'juan.perez@example.com', true),
    (2, 'Maria Lopez', 'maria.lopez@example.com', 2345678901, 'maria.lopez@example.com', true),
    (3, 'Carlos Sanchez', 'carlos.sanchez@example.com', 3456789012, 'carlos.sanchez@example.com', true),
    (4, 'Ana Gomez', 'ana.gomez@example.com', 4567890123, 'ana.gomez@example.com', true),
    (5, 'Luis Fernandez', 'luis.fernandez@example.com', 5678901234, 'luis.fernandez@example.com', true);

insert into prestamo (fecha_prestamo, fecha_devolucion, id_clienteFK)
values ('2023-01-01', '2023-01-15', 1),
       ('2023-02-01', '2023-02-15', 2),
       ('2023-03-01', '2023-03-15', 3),
       ('2023-04-01', '2023-04-15', 4),
       ('2023-05-01', '2023-05-15', 5);

insert into prestamo_libro (id_prestamo, id_libro)
values (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6),
       (4, 1),
       (4, 3),
       (5, 2),
       (5, 4);
