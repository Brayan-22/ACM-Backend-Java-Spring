drop table if exists prestamo_libro;
drop table if exists libro;
drop table if exists autor;
drop table if exists categoria;
drop table if exists prestamo;
drop table if exists cliente;


create table autor (
    id_autor SERIAL primary key,
    nombre_autor varchar(50) not null,
    pais_origen varchar(50) not null
);

create table categoria (
    id_categoria SERIAL primary key,
    nombre_categoria varchar(50) not null,
    descripcion_categoria varchar(255) not null
);

create table libro (
    id_libro SERIAL primary key,
    titulo varchar(50) not null,
    anio_publicacion date not null,
    disponibilidad boolean not null,
    descripcion varchar(255) not null,
    id_autorFK int not null,
    id_categoriaFK int not null,
    foreign key (id_autorFK) references autor(id_autor),
    foreign key (id_categoriaFK) references categoria(id_categoria)
);

create table cliente (
    id_cliente int primary key,
    nombre_cliente varchar(50) not null,
    correo_cliente varchar(50) not null,
    telefono_cliente varchar(10) not null,
    email_cliente varchar(50) not null,
    estado_cuenta boolean not null
);

create table prestamo (
    id_prestamo SERIAL primary key,
    fecha_prestamo date not null,
    fecha_devolucion date not null,
    id_clientefk int not null,
    foreign key (id_clientefk) references cliente(id_cliente)
);
create table prestamo_libro (
    id_prestamo int not null,
    id_libro int not null
);
alter table prestamo_libro add foreign key (id_prestamo) references prestamo(id_prestamo);
alter table prestamo_libro add foreign key (id_libro) references libro(id_libro);