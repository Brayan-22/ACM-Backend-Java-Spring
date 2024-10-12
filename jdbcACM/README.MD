# Diferencia entre el uso de JDBC API, SPRING DATA JDBC Y SPRING DATA JPA

## Uso de un datasource
El usar un pool de conexiones JDBC aligera la carga de trabajo que puede llegar a tener
la base de datos si se establecieran conexiones y desconexiones en cada una de las operaciones.


### Ejecución del proyecto
#### Ejecutar el proyecto con la ayuda de un IDE y la dependencia de docker compose de springboot
    
Acceso a los logs de conexiones y desconexiones de la base de datos
```bash
docker logs -f jdbcacm-db-1
```