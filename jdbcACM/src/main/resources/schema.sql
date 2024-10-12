drop table if exists persona;
create table if not exists persona(
    id_persona integer not null primary key,
    nombre_persona varchar(32) not null,
    correo_persona varchar(32) not null
);
delete from persona;
insert into persona(id_persona, nombre_persona, correo_persona) values (1,'fulano','fulano@Correo.com');