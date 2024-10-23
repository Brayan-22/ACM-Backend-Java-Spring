#!/bin/bash

if [ -f .env ]; then
    export $(cat .env | awk '/=/ {print $1}')
fi

#docker compose up -d
mvn spring-boot:run