# Foro API - Proyecto Spring Boot

Este proyecto es una **API REST** de un foro, desarrollada en **Spring Boot 3** con **Java 17** y **MySQL**, que permite gestionar tópicos (temas de discusión).

---

## Tecnologías usadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL 8
- Hibernate ORM
- Flyway (para migraciones de base de datos)
- Insomnia/Postman para pruebas de API

---

## Funcionalidades de la API

### 1. Registrar un nuevo tópico
- **Endpoint:** `POST /topicos`
- **Descripción:** Permite crear un nuevo tópico con título, mensaje, autor y curso.
- **Validaciones:** No permite campos vacíos ni duplicados (título + mensaje).
- **Ejemplo de JSON:**
```json
{
  "titulo": "Mi segundo tópico",
  "mensaje": "Contenido del nuevo tópico",
  "autor": "Carlos Taborda",
  "curso": "Spring Boot 3"
}
2. Listar todos los tópicos
Endpoint: GET /topicos

Descripción: Devuelve todos los tópicos registrados en la base de datos.

Ejemplo de respuesta:

[
  {
    "id": 1,
    "titulo": "Mi primer tópico",
    "mensaje": "Este es el contenido del tópico",
    "fechaCreacion": "2026-02-09T09:34:53",
    "status": "NO_RESPONDIDO",
    "autor": "Carlos Taborda",
    "curso": "Spring Boot 3"
  }
]
3. Detalle de un tópico
Endpoint: GET /topicos/{id}

Descripción: Devuelve los detalles de un tópico específico usando su ID.

4. Actualizar un tópico
Endpoint: PUT /topicos/{id}

Descripción: Permite modificar un tópico existente (con validación de duplicados y campos obligatorios).

5. Eliminar un tópico
Endpoint: DELETE /topicos/{id}

Descripción: Elimina un tópico específico de la base de datos.

Cómo ejecutar el proyecto localmente
Clonar el repositorio:

git clone https://github.com/carlostabordataho-gif/forohub-api.git
Configurar la base de datos en application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=root
spring.datasource.password=root1234
spring.jpa.hibernate.ddl-auto=update
Ejecutar la aplicación:

mvn spring-boot:run
Probar los endpoints con Insomnia o Postman usando http://localhost:8080/topicos.

Autor
Carlos Taborda

