# 🧩 FórumHub API

API REST desarrollada con Spring Boot para la gestión de tópicos de un foro, incluyendo autenticación segura mediante JWT y control de acceso a los endpoints.

---

## 📌 Descripción

FórumHub es una API backend que permite:

- Crear tópicos
- Consultar tópicos
- Actualizar tópicos
- Eliminar tópicos
- Autenticación de usuarios con JWT
- Protección de endpoints con Spring Security

Este proyecto fue desarrollado como parte de un challenge técnico enfocado en buenas prácticas backend, seguridad y arquitectura REST.

---

## 🚀 Tecnologías Utilizadas

- Java 17
- Spring Boot 3
- Spring Security
- JWT (Auth0 Java JWT)
- MySQL
- JPA / Hibernate
- Flyway (Migraciones de BD)
- Maven
- Insomnia (Testing API)

---

## 🔐 Autenticación

La API usa autenticación basada en JWT.

### 📍 Login

**POST**
http://localhost:8080/login


### 📥 Body JSON

```json
{
  "username": "carlos",
  "password": "123456"
}
📤 Respuesta
Retorna un token JWT que debe enviarse en las siguientes solicitudes.

🪪 Uso del Token
Agregar en headers:

Authorization: Bearer TU_TOKEN_AQUI
📚 Endpoints Principales
📌 Tópicos
Método	Endpoint	Descripción
GET	/topicos	Listar tópicos
POST	/topicos	Crear tópico
PUT	/topicos/{id}	Actualizar tópico
DELETE	/topicos/{id}	Eliminar tópico
⚠ Requieren Token JWT.

🗄 Base de Datos
MySQL

Configuración en:

application.properties
Ejemplo:

spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=root
spring.datasource.password=******
⚙ Configuración JWT
jwt.secret=mi_secreto_super_seguro
jwt.expiration=2
🧠 Arquitectura
controller
service
repository
domain
config (Security + JWT)
🧪 Pruebas
Se realizaron pruebas usando:

Insomnia

Requests con y sin token

Validación de acceso protegido (403 sin token)

📦 Cómo Ejecutar el Proyecto
1️⃣ Clonar repositorio
git clone https://github.com/carlostabordataho-gif/forohub-api.git
2️⃣ Abrir en IntelliJ o VSCode
3️⃣ Configurar base de datos
4️⃣ Ejecutar proyecto
mvn spring-boot:run
📈 Estado del Proyecto
✅ CRUD Tópicos
✅ Autenticación JWT
✅ Seguridad Spring Security
✅ Validación Token en Requests
✅ API Stateless

👨‍💻 Autor
Carlos Taborda
Desarrollador Backend en formación
Enfocado en Java, Spring Boot y desarrollo de APIs seguras.