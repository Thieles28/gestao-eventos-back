# Gestão de eventos - Backend

Este é o backend da aplicação **Gestão de Eventos**, desenvolvido com **Spring Boot** e containerizado com **Docker**.

---

## Pré-requisitos

- Docker
- Docker Compose

---

## Subindo a aplicação

Execute o comando abaixo para subir a aplicação com o MySQL:

```bash
docker-compose up --build
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

## Banco de Dados

Ao iniciar, o banco de dados `evento` será criado com as seguintes credenciais:

- **Usuário do banco:** `admin`
- **Senha do banco:** `admin`

Essas informações são utilizadas automaticamente pela aplicação via variáveis de ambiente definidas no
`docker-compose.yml`.

---

## Configurações do Docker

### `docker-compose.yml`

- Cria dois containers: um para o **backend** e outro para o **MySQL**.
- Aguarda o MySQL estar saudável antes de iniciar o backend.
- Expõe as portas:
    - **3306** para acesso ao MySQL
    - **8080** para acesso à API

### `Dockerfile`

- Utiliza uma imagem do **Maven** para construir o JAR da aplicação.
- Em seguida, copia o JAR para uma imagem com **JDK** para execução final.

---

## Documentação da API (Swagger)

A documentação interativa da API está disponível em:

```
http://localhost:8080/api/v1/swagger-ui/index.html#/
```

Você pode utilizá-la para testar os endpoints, visualizar os modelos de requisição e resposta, e entender melhor o
funcionamento da API.

