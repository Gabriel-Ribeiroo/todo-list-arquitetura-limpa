# Todo List API

API Restful para gerenciamento de tarefas, construída com **Spring Boot** e seguindo os princípios da **Arquitetura Limpa (Clean Architecture)** para garantir fácil manutenção e escalabilidade. Implementa autenticação via JWT com **Spring Security**, persistência com **JPA** e documentação interativa com **Swagger**. 

## 🛠️ Tecnologias 

- Java 24 
- Spring Boot 
- Spring Security 
- JPA 
- PostgreSQL
- JWT (JSON Web Token)
- Swagger (OpenApi)
- Docker

## 🗂️ Arquitetura 

Esse projeto segue os princípios da Arquitetura Limpa, organizado em camadas: 

- **Domain:** Entidades e regras de negócio 
- **Application:** Casos de uso que implementam as regras de negócio 
- **Adapters:** DTOs e controllers 
- **Infra:** Implementações técnicas 

## 🚀 Como rodar 

### Pré-requisitos 

- Java 24
- Maven 
- Docker

### Variáveis de ambiente

No arquivo `.env` defina as variáveis de ambiente do banco de dados, autenticação e demais variáveis necessárias. 

- POSTGRES_USER 
- POSTGRES_PASSWORD
- POSTGRES_DB
- JWT_PRIVATE_KEY 
- JWT_PUBLIC_KEY

### Suba o Ambiente com Docker Compose 

Para subir o banco de dados e demais depêndencias, execute: 

`docker compose up`

### Inicie a aplicação 

Para inicializar a aplicação, execute: 

`mvn spring-boot:run`.

A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

## 🧪 Testes: 

Para rodas os testes, use: 

`mvn test`.

## 📚 Acesse a documentação 

Para visualizar a documentação e realizar requisições diretamente pelo navegador, acesse: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)