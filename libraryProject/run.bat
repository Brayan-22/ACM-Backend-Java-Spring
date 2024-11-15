@echo off

:: Load environment variables from .env file
for /f "tokens=1,2 delims==" %%a in ('type .env ^| findstr /r /v "^#.*$"') do (
    set %%a=%%b
)

:: Run the Spring Boot application with Maven
mvn spring-boot:run