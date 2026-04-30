# ms-invex-test

Microservicio REST desarrollado con Spring Boot 2.7.18 bajo arquitectura hexagonal 	microservicio, orientado a la gestión de empleados. El proyecto aplica buenas prácticas de desarrollo backend, separación de responsabilidades y manejo estructurado de errores.

## Tecnologías

* Java 17
* Spring Boot 2.7.18
* Spring Data JPA (Hibernate)
* H2 Database
* MySQL
* Maven
* Lombok
* Springdoc OpenAPI
* JUnit 5 y Mockito
* Docker

## Arquitectura

El proyecto sigue el patrón de arquitectura hexagonal (Ports and Adapters), organizado en las siguientes capas:

entrypoints: controladores REST, DTOs
application: casos de uso
domain: modelo de dominio y reglas de negocio
infrastructure: persistencia, configuración y adaptadores

## Funcionalidades

* Obtener todos los empleados
* Obtener empleado por identificador
* Crear uno o múltiples empleados
* Actualizar información de empleado
* Eliminar empleado
* Buscar empleados por nombre

## Modelo de datos

Empleado:

* firstName
* middleName
* lastName
* motherLastName
* age
* gender
* birthDate
* position
* createdAt
* active

## Endpoints

GET /api/v1/employees
GET /api/v1/employees/{id}
POST /api/v1/employees
PUT /api/v1/employees/{id}
DELETE /api/v1/employees/{id}
GET /api/v1/employees/search?name=

## Documentación API

Swagger UI disponible en:

http://localhost:8080/api/v1/swagger-ui.html

OpenAPI JSON:

http://localhost:8080/api/v1/docs

## Health Check

http://localhost:8080/api/v1/actuator/health

## Base de datos local

H2 Console:

http://localhost:8080/api/v1/h2-console

Parámetros de conexión:

JDBC URL: jdbc:h2:mem:invexdb
Usuario: sa
Contraseña: (vacía)

## Ejecución local

Compilar el proyecto:

mvn clean install

Ejecutar:

mvn spring-boot:run

## Docker

Construir imagen:

docker build -t ms-invex-test .

Ejecutar contenedor:

docker run -p 8080:8080 ms-invex-test

## Docker Compose

Levantar aplicación con base de datos MySQL:

docker-compose up --build

## Configuración

El proyecto permite configuración mediante variables de entorno:

SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD

## Manejo de errores

Se implementa un manejador global de excepciones que devuelve respuestas estructuradas con el siguiente formato:

{
"code": "ERROR_CODE",
"message": "Descripción",
"details": [],
"timestamp": "YYYY-MM-DDTHH:MM:SS"
}

## Logs

Se implementa un interceptor para registrar:

* Peticiones entrantes
* Encabezados HTTP
* Código de respuesta
* Tiempo de ejecución

## Pruebas

Ejecutar pruebas unitarias:

mvn test

## Buenas prácticas

* Principios SOLID
* Separación de capas
* Uso de DTOs
* Validación de datos
* Manejo centralizado de errores
* Logging estructurado
* Configuración desacoplada

## Notas

* H2 se utiliza para desarrollo local
* MySQL se utiliza en entorno Docker
* Swagger habilitado para pruebas
* Actuator expone endpoints de monitoreo

## Estado

Proyecto funcional y listo para evaluación técnica.
