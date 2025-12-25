# 📚 Literalura

Aplicación de consola desarrollada en Java con Spring Boot que permite consultar libros desde la API Gutendex, almacenar libros y autores en una base de datos PostgreSQL y realizar consultas y estadísticas directamente desde la terminal.

Este proyecto forma parte del desafío **Literalura** de Alura Latam.

---

## 🚀 Funcionalidades

- Buscar libros por título consumiendo la API Gutendex
- Guardar libros y autores en una base de datos PostgreSQL
- Listar todos los libros almacenados
- Contar libros por idioma
- Listar todos los autores registrados
- Listar autores que estaban vivos en un año determinado

---

## 🛠️ Tecnologías utilizadas

- Java 21
- Spring Boot 3.5.9
- Spring Data JPA
- Hibernate
- PostgreSQL
- Jackson
- Maven

---

## 📦 Requisitos

- Java 21 o superior
- Maven
- PostgreSQL
- Conexión a internet para consumir la API Gutendex

---

## 🗄️ Base de Datos

La aplicación utiliza PostgreSQL como base de datos relacional.

Antes de ejecutar el proyecto, crea la base de datos:

```sql
CREATE DATABASE literalura;
```

---

## 🔐 Seguridad y credenciales

Por razones de seguridad, **la contraseña de la base de datos no se incluye en el repositorio**.

La aplicación utiliza una variable de entorno llamada:

```
DB_PASSWORD
```

### Definir variable de entorno

#### Windows (PowerShell)

```powershell
setx DB_PASSWORD "tu_contraseña"
```

#### Linux / macOS

```bash
export DB_PASSWORD=tu_contraseña
```

Después de configurarla, reinicia el IDE o la terminal.

---

## ⚙️ Configuración de la aplicación

El archivo `application.properties` está excluido del repositorio.

Ejemplo de configuración local:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
```

---

## ▶️ Ejecución del proyecto

Desde la raíz del proyecto, ejecutar:

```bash
mvn spring-boot:run
```

La aplicación se ejecutará en consola mostrando un menú interactivo.

---

## 📚 API utilizada

Gutendex – API pública del Project Gutenberg  
https://gutendex.com/

---

## 📂 Estructura del proyecto

```
literalura
│── src
│   └── main
│       ├── java
│       │   └── com.alura.literalura
│       │       ├── principal
│       │       ├── model
│       │       ├── repository
│       │       ├── service
│       │       └── LiteraluraApplication.java
│       └── resources
│           └── application.properties (ignorado)
│── pom.xml
│── .gitignore
│── README.md
```

---

## 👤 Autor

Proyecto desarrollado como parte del desafío **Literalura – Alura Latam**.
