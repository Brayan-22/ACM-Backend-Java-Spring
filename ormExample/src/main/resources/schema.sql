--Script generado con la herramienta del IDE, para evitar usar sentencias sql iniciales y que el ORM genere la DB
--Habilitar en application.properties : spring.jpa.hibernate.ddl-auto=create-drop, y spring.sql.init.mode=never
drop table if exists producto_categoria;
drop table if exists venta_producto;
drop table if exists producto;
drop table if exists categoria;
drop table if exists venta;
drop table if exists cliente;
drop table if exists cuenta;
drop table if exists ciudad;
drop table if exists departamento;
create table if not exists categoria
(
    impuesto     double precision,
    id_categoria bigint not null
        primary key,
    nombre       varchar(255)
);

alter table categoria
    owner to postgres;

create table if not exists cuenta
(
    id_cuenta  uuid not null
        primary key,
    password   varchar(255),
    privilegio varchar(255)
        constraint cuenta_privilegio_check
            check ((privilegio)::text = ANY
                   ((ARRAY ['VIP'::character varying, 'FRECUENTE'::character varying, 'NOFRECUENTE'::character varying])::text[]))
);

alter table cuenta
    owner to postgres;

create table if not exists departamento
(
    cod_departamento bigint,
    id_departamento  bigint not null
        primary key,
    nombre           varchar(255)
);

alter table departamento
    owner to postgres;

create table if not exists ciudad
(
    codigo_ciudad     bigint,
    id_ciudad         bigint not null
        primary key,
    id_departamentofk bigint
        constraint fkq4otfnjl9yb7h8vp3ajlifw62
            references departamento,
    nombre            varchar(255)
);

alter table ciudad
    owner to postgres;

create table if not exists cliente
(
    id_ciudadfk bigint
        constraint fk9okpkvbbpma4x9jpua4nkvfpw
            references ciudad,
    id_cliente  bigint not null
        primary key,
    id_cuentafk uuid   not null
        unique
        constraint fkgb35idb7py0gaw3ja2swct13g
            references cuenta,
    apellido    varchar(255),
    nombre      varchar(255)
);

alter table cliente
    owner to postgres;

create table if not exists producto
(
    precio      double precision,
    id_producto bigint not null
        primary key,
    nombre      varchar(255)
);

alter table producto
    owner to postgres;

create table if not exists producto_categoria
(
    id_categoria bigint not null
        constraint fkgwvyylk3oo917u8d8pq27olq7
            references categoria,
    id_producto  bigint not null
        constraint fkny56r8kiy3rt023t3ghlpre87
            references producto,
    primary key (id_categoria, id_producto)
);

alter table producto_categoria
    owner to postgres;

create table if not exists venta
(
    fecha        date,
    id_clientefk bigint
        constraint fkbs2qruxlymq69lsyujs65w5me
            references cliente,
    id_venta     bigint not null
        primary key
);

alter table venta
    owner to postgres;

create table if not exists venta_producto
(
    cantidad    integer,
    id_producto bigint not null
        constraint fkrnj9u4edr8xyj6wladg622hpr
            references producto,
    id_venta    bigint not null
        constraint fkngxnpluhk3b1br9xk4nxoj1np
            references venta,
    primary key (id_producto, id_venta)
);

alter table venta_producto
    owner to postgres;

