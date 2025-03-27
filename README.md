# Aplicação Java 17 com Spring Boot 3 e PostgreSQL

Este projeto é uma aplicação Java 17 utilizando Spring Boot 3.0 e PostgreSQL.

## Requisitos

Antes de iniciar, certifique-se de ter os seguintes requisitos instalados:

- [Java 17](https://jdk.java.net/17/)
- [Maven](https://maven.apache.org/)
- [PostgreSQL](https://www.postgresql.org/)

## Configuração e Execução

### 1. Clonar o repositório
sh
git clone https://github.com/ofabiogontijo/Bank-Management-Api.git
cd Bank-Management-Api


### 2. Configurar o banco de dados
Crie um banco de dados PostgreSQL com as seguintes credenciais:

- *Banco de dados*: bank-management-api
- *Usuário*: postgres
- *Senha*: mysecretpassword

### 3. Configurar a aplicação
Atualize o application.properties com as configurações do banco de dados:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bank-management-api
spring.datasource.username=postgres
spring.datasource.password=mysecretpassword
spring.jpa.hibernate.ddl-auto=none


### 4. Construir a aplicação
mvn clean spring-javaformat:apply install


### 5. Executar a aplicação
sh
java -jar target/*.jar

Isso iniciará a aplicação Spring Boot na porta configurada (padrão: 8080).

### 6. Acessar a aplicação
Após iniciar a aplicação, acesse:

http://localhost:8080


## Estrutura do Projeto


├── src/               # Código-fonte da aplicação
├── pom.xml            # Configuração do Maven
└── README.md          # Documentação do projeto


## Banco de Dados
Se precisar acessar o banco de dados manualmente:
sh
psql -U postgres -d bank-management-api
