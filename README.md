# API de Usuarios

API RESTful para la gestión de usuarios implementando arquitectura hexagonal.

## Descripción

Este proyecto implementa un CRUD completo de usuarios con los siguientes campos:
- ID
- Nombre
- Apellido
- Número de teléfono
- Fecha de nacimiento

La API está construida utilizando Spring Boot y sigue los principios de la arquitectura hexagonal (ports and adapters).

## Arquitectura Hexagonal

El proyecto está estructurado siguiendo la arquitectura hexagonal:

- **Dominio**: Contiene las entidades de negocio y los puertos (interfaces).
- **Aplicación**: Servicios que implementan los casos de uso.
- **Infraestructura**: Adaptadores para interactuar con componentes externos (base de datos, API REST).

Esta arquitectura permite desacoplar el núcleo de la aplicación de los detalles técnicos, facilitando el mantenimiento y las pruebas.

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.1.5
- Spring Data JPA
- MySQL
- Swagger/OpenAPI para documentación
- Docker y Docker Compose
- Arquitectura Hexagonal

## Requisitos previos

- JDK 17
- Maven
- Docker y Docker Compose (para ejecución con contenedores)
- MySQL (para ejecución local)

## Ejecución local

### Configuración de la base de datos

Asegúrese de tener MySQL ejecutándose en el puerto 3307 con las siguientes credenciales:
- Base de datos: `usersdb`
- Usuario: `root`
- Contraseña: `root`

O modifique las propiedades en `application.properties` según su configuración.

### Construcción y ejecución

```bash
mvn clean install
mvn spring-boot:run
```

La API estará disponible en: http://localhost:8080
Documentación Swagger: http://localhost:8080/swagger-ui.html

## Ejecución con Docker

### Construir y ejecutar con Docker Compose

```bash
docker-compose up --build
```

Este comando construirá la imagen de la aplicación y la ejecutará junto con MySQL en contenedores separados.

### Detener los contenedores

```bash
docker-compose down
```

### Limpiar volúmenes

```bash
docker-compose down -v
```

## Estructura del proyecto

```
ApiUser/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ucb/
│   │   │       └── apiuser/
│   │   │           ├── application/
│   │   │           │   ├── dto/
│   │   │           │   └── service/
│   │   │           ├── domain/
│   │   │           │   ├── model/
│   │   │           │   └── port/
│   │   │           │       ├── in/
│   │   │           │       └── out/
│   │   │           └── infrastructure/
│   │   │               ├── adapter/
│   │   │               │   ├── in/
│   │   │               │   └── out/
│   │   │               └── config/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Endpoints API

| Método HTTP | Endpoint       | Descripción                       |
|-------------|----------------|-----------------------------------|
| POST        | /api/users     | Crear un nuevo usuario            |
| GET         | /api/users     | Obtener todos los usuarios        |
| GET         | /api/users/{id}| Obtener usuario por ID            |
| PUT         | /api/users/{id}| Actualizar usuario existente      |
| DELETE      | /api/users/{id}| Eliminar usuario                  |

## Atributos de calidad identificados

1. Mantenibilidad
¿Por qué? La arquitectura hexagonal separa claramente las responsabilidades en capas (dominio, aplicación e infraestructura), lo que facilita enormemente el mantenimiento. Cuando necesitas modificar la lógica de negocio, solo tocas el dominio sin afectar las capas externas.

2. Testeabilidad
¿Por qué? La inversión de dependencias mediante interfaces (puertos) permite realizar pruebas unitarias fácilmente utilizando mocks o stubs.

3. Adaptabilidad
¿Por qué? Se pueden cambiar componentes externos (por ejemplo, la base de datos) sin modificar la lógica de negocio.

4. Documentación
¿Por qué? Una API bien documentada facilita su adopción y reduce los errores de integración.

5. Portabilidad
¿Por qué? La aplicación puede ejecutarse en diferentes entornos sin modificaciones gracias a la containerización.

6. Escalabilidad
¿Por qué? La separación de componentes facilita la escalabilidad horizontal y vertical.

7. Interoperabilidad
¿Por qué? La API REST permite la integración con otros sistemas utilizando protocolos estándar.