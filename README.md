<h1 align="center">Sistema de Gerenciamento de Eventos</h1>

<p align="center">
  Aplicação Web desenvolvida com Java, Spring Boot, Thymeleaf, MongoDB e Docker.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-25-orange">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen">
  <img src="https://img.shields.io/badge/MongoDB-Atlas-green">
  <img src="https://img.shields.io/badge/Docker-Container-blue">
  <img src="https://img.shields.io/badge/Render-Deploy-purple">
  <img src="https://img.shields.io/badge/Maven-3.9.x-red">
</p>

---

# 📋 Sobre o projeto

O **eventoapp** é uma aplicação Web desenvolvida em Java para gerenciamento de eventos e seus respectivos convidados.

O sistema permite realizar o cadastro, consulta e exclusão de eventos, além do gerenciamento dos convidados associados a cada evento.

A aplicação foi desenvolvida originalmente a partir de uma estrutura tradicional de aplicações Java Web e posteriormente adaptada para uma arquitetura baseada em **Spring Boot + Spring Data MongoDB**, utilizando o **MongoDB Atlas** como banco de dados em nuvem.

O projeto também foi preparado para execução em **Docker** e implantação em ambiente de produção utilizando o **Render**.

---

# 🎯 Objetivos

O projeto tem como principais objetivos:

* Desenvolver uma aplicação Web utilizando Java e Spring Boot.
* Implementar operações de persistência utilizando MongoDB.
* Utilizar MongoDB Atlas como banco de dados em nuvem.
* Implementar operações de cadastro, consulta e exclusão.
* Relacionar convidados aos respectivos eventos.
* Utilizar Thymeleaf para renderização das páginas HTML.
* Implementar validação de dados utilizando Bean Validation.
* Utilizar Maven para gerenciamento do projeto e dependências.
* Containerizar a aplicação utilizando Docker.
* Utilizar variáveis de ambiente para configurações de produção.
* Realizar deploy da aplicação utilizando Render.
* Integrar GitHub, Docker, Render e MongoDB Atlas em um fluxo de desenvolvimento e publicação.

---

# 🚀 Funcionalidades

## Eventos

* [x] Cadastro de eventos
* [x] Listagem de eventos
* [x] Visualização dos detalhes de um evento
* [x] Exclusão de eventos
* [x] Geração automática do identificador MongoDB
* [x] Validação dos campos obrigatórios

## Convidados

* [x] Cadastro de convidados
* [x] Listagem de convidados vinculados ao evento
* [x] Exclusão de convidados
* [x] Utilização do RG como identificador do convidado
* [x] Associação do convidado ao evento
* [x] Validação dos campos obrigatórios

---

# 🖥️ Tecnologias utilizadas

## Backend

* **Java 25**
* **Spring Boot 4.1.1**
* **Spring MVC**
* **Spring Data MongoDB**
* **Spring Validation**
* **Maven**

## Frontend

* **HTML5**
* **CSS3**
* **Thymeleaf**
* **Materialize CSS**

## Banco de dados

* **MongoDB**
* **MongoDB Atlas**

## Infraestrutura

* **Docker**
* **Docker Multi-Stage Build**
* **Render**

## Controle de versão

* **Git**
* **GitHub**

---

# 🏗️ Arquitetura da aplicação

A aplicação utiliza uma arquitetura baseada na separação entre controle das requisições, modelos de domínio e acesso aos dados.

Fluxo simplificado:

```text
Navegador
    │
    ▼
Spring MVC / Controller
    │
    ▼
Modelos de domínio
    │
    ▼
Spring Data MongoDB
    │
    ▼
MongoDB Atlas
```

Durante o processo de publicação:

```text
Desenvolvimento
      │
      ▼
     Git
      │
      ▼
   GitHub
      │
      ▼
    Render
      │
      ▼
   Docker
      │
      ▼
 Spring Boot
      │
      ▼
MongoDB Atlas
```

---

# 📁 Estrutura do projeto

A estrutura principal do projeto está organizada da seguinte forma:

```text
eventoapp/
│
├── Dockerfile
├── pom.xml
├── .gitignore
│
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── eventoapp/
        │           └── eventoapp/
        │               │
        │               ├── EventoappApplication.java
        │               ├── ValidationConfig.java
        │               │
        │               ├── controllers/
        │               │   └── EventoController.java
        │               │
        │               ├── models/
        │               │   ├── Evento.java
        │               │   └── Convidado.java
        │               │
        │               └── repository/
        │                   ├── EventoRepository.java
        │                   └── ConvidadoRepository.java
        │
        └── resources/
            ├── application.properties
            │
            ├── static/
            │   └── css/
            │
            └── templates/
                ├── index.html
                └── evento/
                    ├── formEvento.html
                    └── detalhesEvento.html
```

---

# 🧩 Principais componentes

## EventoappApplication

Classe principal responsável pela inicialização da aplicação Spring Boot.

```java
@SpringBootApplication
public class EventoappApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EventoappApplication.class, args);
    }
}
```

---

## Evento

Representa o domínio de eventos da aplicação.

Os principais atributos são:

```text
id
nome
local
data
horario
convidados
```

O identificador é gerenciado pelo MongoDB:

```java
@Id
private String id;
```

O campo `id` não recebe `@NotBlank`, pois o identificador é gerado pelo MongoDB durante a persistência.

Os campos obrigatórios utilizam Bean Validation:

```java
@NotBlank
private String nome;

@NotBlank
private String local;

@NotBlank
private String data;

@NotBlank
private String horario;
```

---

# 👥 Convidado

Representa um convidado associado a um evento.

Os principais atributos são:

```text
rg
nomeConvidado
eventoCodigo
```

O RG é utilizado como identificador do documento:

```java
@Id
@NotBlank
private String rg;
```

A associação com o evento é realizada através do identificador do evento:

```java
private String eventoCodigo;
```

Dessa forma, os convidados podem ser recuperados utilizando o identificador do evento.

---

# 🗄️ Banco de dados

O projeto utiliza **MongoDB Atlas** como banco de dados.

Banco utilizado:

```text
eventosapp
```

Coleções principais:

```text
eventos
convidados
```

## Coleção eventos

Exemplo de documento:

```json
{
  "_id": {
    "$oid": "6ab5a9726ab09e370a3940e8"
  },
  "nome": "ZE RAMALHO",
  "local": "BRASILIA",
  "data": "2026-09-24",
  "horario": "20:00"
}
```

O `_id` é gerenciado pelo MongoDB.

---

## Coleção convidados

Exemplo conceitual:

```json
{
  "_id": "123456789",
  "nomeConvidado": "Nome do Convidado",
  "eventoCodigo": "6ab5a9726ab09e370a3940e8"
}
```

O campo `eventoCodigo` estabelece a associação entre o convidado e o evento.

---

# 📦 Repositórios

O acesso ao MongoDB é realizado utilizando Spring Data MongoDB.

## EventoRepository

```java
public interface EventoRepository extends CrudRepository<Evento, String> {
}
```

O `CrudRepository` disponibiliza operações como:

* `save()`
* `findById()`
* `findAll()`
* `deleteById()`

---

## ConvidadoRepository

O repositório dos convidados possui uma consulta derivada para recuperar os convidados associados a um evento:

```java
Iterable<Convidado> findByEventoCodigo(String eventoCodigo);
```

Também possui consulta por RG:

```java
Convidado findByRg(String rg);
```

---

# 🎮 Controller

O principal controller da aplicação é:

```text
EventoController
```

Ele é responsável por receber as requisições HTTP relacionadas aos eventos e convidados.

Entre as operações implementadas estão:

```text
GET  /cadastrarEvento
POST /cadastrarEvento

GET  /
GET  /eventos

GET  /eventos/{id}
POST /eventos/{id}

GET  /deletarEvento
GET  /deletarConvidado
```

---

# 🌐 Principais rotas

## Listagem de eventos

```text
GET /
```

Também disponível em:

```text
GET /eventos
```

---

## Formulário de cadastro

```text
GET /cadastrarEvento
```

---

## Cadastro do evento

```text
POST /cadastrarEvento
```

---

## Detalhes do evento

```text
GET /eventos/{id}
```

Exemplo:

```text
/eventos/6ab5a9726ab09e370a3940e8
```

---

## Cadastro de convidado

```text
POST /eventos/{id}
```

O identificador do evento é utilizado para associar o convidado ao evento.

---

## Exclusão de evento

```text
GET /deletarEvento?id={id}
```

---

## Exclusão de convidado

```text
GET /deletarConvidado?rg={rg}
```

---

# ✅ Validação

A aplicação utiliza Jakarta Bean Validation.

Os campos obrigatórios possuem:

```java
@NotBlank
```

A validação é realizada através do:

```java
@Valid
```

utilizado nos métodos do controller.

Exemplo:

```java
public String form(
    @Valid @ModelAttribute("evento") Evento evento,
    BindingResult result,
    RedirectAttributes attributes
)
```

A configuração de validação é disponibilizada através da classe:

```text
ValidationConfig.java
```

---

# ⚙️ Configuração da aplicação

O projeto utiliza:

```text
src/main/resources/application.properties
```

A configuração de produção utiliza variáveis de ambiente:

```properties
spring.application.name=eventoapp
spring.mongodb.uri=${MONGODB_URI}
server.port=${PORT:8080}
```

## MONGODB_URI

A URI de conexão com o MongoDB **não fica armazenada no código-fonte**.

Ela é fornecida através da variável de ambiente:

```text
MONGODB_URI
```

Exemplo conceitual:

```text
mongodb+srv://usuario:senha@cluster.mongodb.net/eventosapp
```

A credencial real não deve ser publicada no GitHub.

---

# 🔐 Segurança das configurações

O projeto utiliza variáveis de ambiente para informações sensíveis.

A URI do MongoDB Atlas não deve ser inserida diretamente no:

```text
application.properties
```

nem no:

```text
Dockerfile
```

nem no:

```text
GitHub
```

Em ambiente local, a variável pode ser fornecida ao executar o container:

```bash
docker run \
  --name eventoapp-container \
  -p 8080:8080 \
  -e MONGODB_URI="SUA_URI_DO_MONGODB_ATLAS" \
  eventoapp
```

No Render, a variável:

```text
MONGODB_URI
```

é configurada na área de Environment Variables do serviço.

---

# 🐳 Docker

A aplicação possui um `Dockerfile` utilizando **multi-stage build**.

O objetivo é realizar a compilação Maven dentro da própria construção da imagem.

## Etapa 1 — Build

A primeira etapa utiliza Maven + Java 25:

```dockerfile
FROM maven:3.9.11-eclipse-temurin-25 AS build
```

O projeto é copiado para o container:

```dockerfile
COPY pom.xml .
COPY src ./src
```

E compilado:

```dockerfile
RUN mvn clean package -DskipTests
```

---

## Etapa 2 — Runtime

A segunda etapa utiliza uma imagem Java 25:

```dockerfile
FROM eclipse-temurin:25-jdk
```

O JAR gerado na primeira etapa é copiado:

```dockerfile
COPY --from=build /app/target/eventoapp-0.0.1-SNAPSHOT.jar app.jar
```

A aplicação é iniciada através de:

```dockerfile
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

# 🐳 Executando localmente com Docker

Primeiro, construir a imagem:

```bash
docker build -t eventoapp .
```

Depois executar:

```bash
docker run \
  --name eventoapp-container \
  -p 8080:8080 \
  -e MONGODB_URI="SUA_URI_DO_MONGODB_ATLAS" \
  eventoapp
```

Após a inicialização:

```text
http://localhost:8080
```

ou:

```text
http://localhost:8080/eventos
```

---

# ☕ Executando localmente com Maven

Também é possível executar a aplicação diretamente através do Maven.

Primeiro, configurar a variável:

```text
MONGODB_URI
```

Depois executar:

```bash
mvn clean package -DskipTests
```

E iniciar o JAR:

```bash
java -jar target/eventoapp-0.0.1-SNAPSHOT.jar
```

A aplicação será disponibilizada na porta configurada.

Por padrão:

```text
8080
```

---

# 📦 Maven

O projeto utiliza Maven para gerenciamento de dependências e construção da aplicação.

Principais dependências:

* Spring Boot Starter Thymeleaf
* Spring Boot Starter Web MVC
* Spring Boot Starter Data MongoDB
* Spring Boot Starter Validation
* Spring Boot DevTools

O projeto utiliza:

```text
Spring Boot 4.1.1
```

e:

```text
Java 25
```

---

# 🌍 Deploy

A aplicação está preparada para implantação utilizando:

```text
GitHub
   ↓
Render
   ↓
Docker
   ↓
Spring Boot
   ↓
MongoDB Atlas
```

O repositório utilizado é:

```text
https://github.com/FelipeMaximus/eventoapp
```

## Render

O serviço Web do Render utiliza o `Dockerfile` presente na raiz do projeto.

Durante o deploy, o Render:

1. Clona o repositório GitHub.
2. Obtém o `Dockerfile`.
3. Constrói a imagem Docker.
4. Executa o Maven dentro da etapa de build.
5. Gera o arquivo JAR.
6. Cria a imagem final.
7. Executa o Spring Boot.
8. Disponibiliza a aplicação publicamente.

---

# 🔄 Processo de deploy

O fluxo utilizado no projeto é:

```text
Alteração no código
       ↓
Teste local
       ↓
Docker build
       ↓
Docker run
       ↓
Teste local
       ↓
Git add
       ↓
Git commit
       ↓
Git push
       ↓
GitHub
       ↓
Render
       ↓
Docker Build
       ↓
Deploy
       ↓
Aplicação em produção
```

---

# 🌐 Deploy em produção

A aplicação está publicada no Render.

**URL da aplicação:**

```text
COLOCAR_AQUI_A_URL_PUBLICA_DO_RENDER
```

> A URL pública deve ser adicionada aqui após a confirmação do endereço definitivo do serviço.

---

# 🔄 Integração com MongoDB Atlas

A aplicação em produção utiliza o MongoDB Atlas para persistência dos dados.

O fluxo é:

```text
Usuário
   ↓
Aplicação Web
   ↓
Spring Boot
   ↓
Spring Data MongoDB
   ↓
MongoDB Atlas
```

A conexão é realizada através da variável:

```text
MONGODB_URI
```

---

# 🧪 Testes realizados

Durante a preparação do ambiente foram realizados testes de:

* [x] Compilação com Java 25
* [x] Build Maven
* [x] Construção da imagem Docker
* [x] Execução do container local
* [x] Conexão Docker → MongoDB Atlas
* [x] Execução do Spring Boot dentro do Docker
* [x] Acesso via `localhost:8080`
* [x] Push para GitHub
* [x] Build Docker realizado pelo Render
* [x] Deploy no Render
* [x] Inicialização da aplicação em produção
* [x] Acesso à aplicação através da URL pública
* [x] Comunicação da aplicação publicada com MongoDB Atlas

---

# 🗂️ Banco de dados

Banco:

```text
eventosapp
```

Coleções:

```text
eventos
convidados
```

## Eventos

Estrutura principal:

```text
_id
nome
local
data
horario
```

## Convidados

Estrutura principal:

```text
_id / rg
nomeConvidado
eventoCodigo
```

---

# 🎨 Interface

A aplicação utiliza páginas HTML renderizadas no servidor através do Thymeleaf.

A interface possui:

* Barra de navegação;
* Listagem de eventos;
* Formulário de cadastro;
* Página de detalhes do evento;
* Cadastro de convidados;
* Exclusão de convidados;
* Botões estilizados;
* Gradientes;
* Personalização da barra de rolagem;
* Layout baseado em Materialize CSS.

---

# 🔧 Configurações importantes

## Porta

A aplicação utiliza:

```properties
server.port=${PORT:8080}
```

Isso permite que:

* localmente seja utilizada a porta `8080`;
* em ambientes que fornecem a variável `PORT`, como plataformas de hospedagem, a aplicação utilize a porta disponibilizada pelo ambiente.

---

# 🚫 Arquivos ignorados pelo Git

O projeto utiliza `.gitignore` para evitar o versionamento de arquivos gerados e configurações específicas do ambiente de desenvolvimento.

Entre eles:

```text
target/
.classpath
.project
.settings/
.factorypath
.springBeans
.sts4-cache/
.idea/
*.iml
.env
```

A pasta:

```text
target/
```

não é enviada ao GitHub.

O JAR é gerado durante o processo de build do Docker.

---

# 📚 Conceitos aplicados

Este projeto permite demonstrar conceitos relacionados a:

* Desenvolvimento Web com Java;
* Spring Boot;
* Spring MVC;
* Injeção de dependências;
* Controllers;
* Modelos de domínio;
* Repositories;
* Spring Data;
* MongoDB;
* MongoDB Atlas;
* CRUD;
* HTTP;
* GET e POST;
* Path Variables;
* Model Attributes;
* Bean Validation;
* Thymeleaf;
* Renderização server-side;
* Maven;
* Docker;
* Docker Multi-Stage Build;
* Variáveis de ambiente;
* Git;
* GitHub;
* Deploy em nuvem;
* Integração entre aplicação e banco de dados;
* Configuração de ambientes de desenvolvimento e produção.

---

# 📌 Melhorias futuras

Possíveis evoluções para o projeto:

* [ ] Implementação de autenticação de usuários
* [ ] Controle de acesso
* [ ] Edição de eventos
* [ ] Edição de convidados
* [ ] Paginação de eventos
* [ ] Busca por eventos
* [ ] Filtros por data
* [ ] API REST
* [ ] Documentação da API com OpenAPI/Swagger
* [ ] Tratamento global de exceções
* [ ] Testes automatizados
* [ ] Testes de integração
* [ ] Logs estruturados
* [ ] Monitoramento da aplicação
* [ ] Domínio personalizado
* [ ] HTTPS com domínio próprio
* [ ] Melhorias de acessibilidade
* [ ] Melhorias de responsividade
* [ ] Separação de DTOs
* [ ] Camada de serviços (`Service`)
* [ ] Paginação e ordenação utilizando recursos do MongoDB

---

# 👨‍💻 Autor

**Filipy Maycon**

Projeto desenvolvido para estudo e aplicação prática de tecnologias relacionadas ao desenvolvimento de sistemas Web utilizando Java, Spring Boot, MongoDB, Docker e serviços de nuvem.

---

# 📄 Licença

Este projeto pode ser utilizado para fins educacionais e de estudo.

---

# ⭐ Projeto

Se este projeto foi útil para seus estudos, considere deixar uma ⭐ no repositório do GitHub.

**Repositório:**

https://github.com/FelipeMaximus/eventoapp
