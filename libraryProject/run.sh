#!/bin/bash

# Cargar variables de entorno desde el archivo .env
if [ -f .env ]; then
  export $(cat .env | grep -v '#' | awk '/=/ {print $1}')
fi

# Ejecutar la aplicación Spring Boot con Maven
mvn spring-boot:run