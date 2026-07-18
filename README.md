# Bootcamp - Tecnologia Microservice

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-6DB33F?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![WebFlux](https://img.shields.io/badge/WebFlux-Reactive-6DB33F?style=flat-square&logo=spring&logoColor=white)](https://docs.spring.io/spring-framework/reference/web/webflux.html)
[![Gradle](https://img.shields.io/badge/Gradle-8.14-02303A?style=flat-square&logo=gradle&logoColor=white)](https://gradle.org/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Descripcion

Microservicio de gestion de tecnologias para la plataforma **Bootcamp**. Permite crear, eliminar y consultar tecnologias, asi como gestionar la asociacion entre capacidades y tecnologias.

**Puerto:** `8081`
**Base Path:** `/tecnologia-service`

## Stack Tecnologico

| Componente | Tecnologia |
|---|---|
| Lenguaje | Java 17 |
| Framework | Spring Boot 3.5.5 |
| Web | Spring WebFlux (Reactiva) |
| Base de datos | MySQL via R2DBC |
| Pool de conexiones | R2DBC Pool |
| Build Tool | Gradle |
| Mapeo | MapStruct 1.5.5 + Lombok |
| Documentacion API | SpringDoc OpenAPI 2.6.0 (WebFlux) |
| Resiliencia | Resilience4j (CircuitBreaker, Retry, Bulkhead) |
| Monitoreo | Spring Boot Actuator |
| Testing | JUnit 5 + Mockito + Reactor Test |

## Arquitectura

Arquitectura Hexagonal (Puertos y Adaptadores) con stack reactivo:

```
com.onclass.tecnologia
├── application/
│   ├── config/                    # Configuracion (UseCases)
│   └── configSwagger/             # Configuracion OpenAPI/Swagger
├── domain/
│   ├── api/                       # Puertos entrantes (TecnologiaServicePort, CapacidadTecnologiaServicePort)
│   ├── spi/                       # Puertos salientes (Persistence ports)
│   ├── model/                     # Modelos de dominio (Tecnologia, CapacidadTecnologia)
│   ├── usecase/                   # Casos de uso (TecnologiaUseCase, CapacidadTecnologiaUseCase)
│   ├── constants/                 # Constantes de dominio
│   ├── enums/                     # Mensajes tecnicos
│   └── exceptions/                # Excepciones de dominio
└── infrastructure/
    ├── entrypoints/
    │   ├── RouterRest             # Rutas funcionales WebFlux
    │   ├── handler/               # TecnologiaHandlerImpl, CapacidadTecnologiaHandlerImpl
    │   ├── dto/                   # TecnologiaDTO, CapacidadTecnologiaDTO
    │   ├── mapper/                # TecnologiaMapper, CapacidadTecnologiaMapper
    │   └── util/                  # Constants, APIResponse, ErrorDTO
    └── adapters/
        └── persistence/
            ├── entity/            # TecnologiaEntity, CapacidadTecnologiaEntity
            ├── repository/        # TecnologiaRepository, CapacidadTecnologiaRepository
            ├── mapper/            # TecnologiaEntityMapper, CapacidadTecnologiaEntityMapper
            └── util/              # EntityConstants, MapperConstants, RepositoryConstants
```

## Endpoints

Todos los endpoints requieren el header `x-message-id` para trazabilidad.

### Tecnologias

| Metodo | Ruta | Descripcion |
|---|---|---|
| `POST` | `/tecnologia-service/tecnologias` | Crear una nueva tecnologia |
| `DELETE` | `/tecnologia-service/tecnologias/{id}` | Eliminar tecnologia por ID |

### Capacidad - Tecnologias

| Metodo | Ruta | Descripcion |
|---|---|---|
| `POST` | `/tecnologia-service/capacidad-tecnologias` | Asociar tecnologias a una capacidad |
| `GET` | `/tecnologia-service/capacidad-tecnologias/{capacidadId}/tecnologias` | Listar tecnologias de una capacidad |
| `DELETE` | `/tecnologia-service/capacidad-tecnologias/by-capacidades` | Eliminar tecnologias por capacidades |
| `POST` | `/tecnologia-service/capacidad-tecnologias/tecnologias/by-capacidades` | Obtener IDs de tecnologias por capacidades |
| `GET` | `/tecnologia-service/capacidad-tecnologias/count/by-tecnologia/{tecnologiaId}` | Contar capacidades asociadas a una tecnologia |

### Request - Crear Tecnologia

```json
{
  "nombre": "Java",
  "descripcion": "Lenguaje de programacion orientado a objetos"
}
```

**Response (201 Created):**
```json
{
  "code": "201-0",
  "message": "Tecnologia creada exitosamente",
  "identifier": "msg-uuid-123",
  "date": "2026-07-17T10:30:00",
  "data": {
    "id": 1,
    "nombre": "Java",
    "descripcion": "Lenguaje de programacion orientado a objetos"
  }
}
```

### Request - Asociar Tecnologias a Capacidad

```json
{
  "capacidadId": 1,
  "tecnologiaIds": [1, 2, 3]
}
```

**Response (201 Created):**
```json
{
  "code": "201-0",
  "message": "Tecnologias asociadas exitosamente",
  "identifier": "msg-uuid-456",
  "date": "2026-07-17T10:30:00"
}
```

### Request - Obtener IDs de Tecnologias por Capacidades

```json
[1, 2, 3]
```

**Response (200 OK):**
```json
{
  "code": "200-0",
  "message": "Tecnologias obtenidas exitosamente",
  "identifier": "msg-uuid-789",
  "date": "2026-07-17T10:30:00",
  "data": [1, 2, 3, 4, 5]
}
```

### Respuesta de Error

```json
{
  "code": "400-2",
  "message": "La tecnologia ya esta registrada",
  "identifier": "msg-uuid-123",
  "date": "2026-07-17T10:30:00",
  "errors": [
    {
      "code": "400-2",
      "message": "La tecnologia ya esta registrada",
      "param": "nombre"
    }
  ]
}
```

## Modelo de Datos

```
┌──────────────────┐       ┌──────────────────────────┐
│   tecnologias    │       │  capacidad_tecnologias   │
├──────────────────┤       ├──────────────────────────┤
│ id (PK)          │◄──FK──│ id_tecnologia (FK)       │
│ nombre           │       │ id (PK)                  │
│ descripcion      │       │ id_capacidad (FK)        │
└──────────────────┘       └──────────────────────────┘
```

## Variables de Entorno

| Variable | Descripcion | Ejemplo |
|---|---|---|
| `DB_HOST` | Host de MySQL | `localhost` |
| `DB_PORT` | Puerto de MySQL | `3306` |
| `DB_NAME` | Nombre de la base de datos | `tecnologia` |
| `DB_USER` | Usuario de MySQL | `root` |
| `DB_PASSWORD` | Contrasena de MySQL | `password` |

**Base de datos por defecto:** `tecnologia`

## Resiliencia

| Patron | Nombre | Configuracion |
|---|---|---|
| CircuitBreaker | `tecnologiaDB` | Proteccion contra fallos de DB |
| Retry | `tecnologiaRetry` | Max 5 intentos, backoff exponencial |
| Bulkhead | `tecnologiaBulkhead` | Max 5 llamadas concurrentes |

## Actuator

```
http://localhost:8081/tecnologia-service/actuator/health
http://localhost:8081/tecnologia-service/actuator/metrics
http://localhost:8081/tecnologia-service/actuator/loggers
```

## Ejecutar el Proyecto

```bash
cd tecnologia-service
./gradlew bootRun
```

> **Requisito:** MySQL debe estar ejecutandose en `localhost:3306`

### 1. Crear la base de datos

Ejecuta en MySQL el script `init.sql`:

```bash
mysql -u root -p < src/main/resources/init.sql
```

o ejecuta manualmente:

```sql
CREATE DATABASE IF NOT EXISTS tecnologia;
```

### 2. Iniciar la aplicacion

Las tablas se crean automaticamente al iniciar la app (via `schema.sql`).

La aplicacion estara disponible en `http://localhost:8081`

## Documentacion API (Swagger)

```
http://localhost:8081/webjars/swagger-ui/index.html
http://localhost:8081/v3/api-docs
```

## Ejecutar Tests

```bash
./gradlew test
```

## Reglas de Negocio

- Una tecnologia debe tener nombre unico
- Las tecnologias se asocian a capacidades a traves de la tabla de relacion capacidad_tecnologias
- No se permiten asociaciones duplicadas (misma capacidad con misma tecnologia)
- Al eliminar una capacidad, se eliminan todas sus asociaciones con tecnologias
