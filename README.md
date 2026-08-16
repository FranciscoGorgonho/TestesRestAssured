# Gerenciador de Viagens do Montanha

Eu criei este projeto como uma API REST para gerenciar viagens, com foco em autenticação por JWT, operações de CRUD e testes automatizados com RestAssured. O objetivo principal foi desenvolver uma aplicação prática e didática, com estrutura simples, documentação Swagger e suporte para validação de endpoints em ambiente Java com Spring Boot.

## Visão geral

Este repositório reúne uma aplicação backend em Java, usando Spring Boot, com autenticação segura, persistência em banco H2 em memória e endpoints para cadastro, listagem, busca, atualização e exclusão de viagens. Além disso, a aplicação possui testes automatizados que validam o comportamento principal da API e servem como base para estudos de testes de integração.

## Funcionalidades

- Autenticação com JWT para acesso aos endpoints protegidos;
- Cadastro de viagens com dados como destino, datas, acompanhante e região;
- Listagem de viagens, com filtro por região opcional;
- Busca de uma viagem específica por identificador;
- Atualização de dados de uma viagem;
- Exclusão de viagens;
- Documentação interativa via Swagger;
- Testes automatizados com JUnit + RestAssured;
- Persistência em banco H2 para facilitar a execução local.

## Tecnologias utilizadas

- Java 8
- Spring Boot 1.5.10.RELEASE
- Spring Security
- JWT (jjwt)
- Spring Data JPA
- H2 Database
- Swagger 2
- Maven
- JUnit 4
- RestAssured

## Pré-requisitos

Antes de executar o projeto, eu preciso que você tenha instalado em sua máquina:

- JDK 8+
- Maven
- Git
- IDE de sua preferência (IntelliJ IDEA, Eclipse ou VS Code)

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/gerenciador-viagens-montanha.git
cd gerenciador-viagens-montanha
```

2. Verifique se as dependências do Maven serão baixadas corretamente:

```bash
./mvnw install
```

Se o sistema operacional for Windows, use:

```bash
mvnw.cmd install
```

## Como executar a aplicação

Eu posso iniciar a aplicação com o comando abaixo:

```bash
./mvnw spring-boot:run
```

Ou, em Windows:

```bash
mvnw.cmd spring-boot:run
```

A aplicação ficará disponível em:

- API: http://localhost:8089/api
- Swagger UI: http://localhost:8089/api/swagger-ui.html

## Autenticação

A aplicação já cria usuários padrão no startup para facilitar os testes locais.

Credenciais padrão:

- Usuário comum:
  - email: usuario@email.com
  - senha: 123456
- Administrador:
  - email: admin@email.com
  - senha: 654321

Para autenticar, eu utilizo o endpoint:

```http
POST /api/v1/auth
```

Exemplo de corpo da requisição:

```json
{
  "email": "admin@email.com",
  "senha": "654321"
}
```

A resposta retorna um token JWT que deve ser enviado no header `Authorization` nas requisições protegidas.

## Como usar a API

### 1. Fazer login

```bash
curl -X POST http://localhost:8089/api/v1/auth \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@email.com",
    "senha": "654321"
  }'
```

### 2. Cadastrar uma viagem

```bash
curl -X POST http://localhost:8089/api/v1/viagens \
  -H "Content-Type: application/json" \
  -H "Authorization: <token_jwt>" \
  -d '{
    "acompanhante": "Isabelle",
    "dataPartida": "2021-02-07",
    "dataRetorno": "2021-03-07",
    "localDeDestino": "Manaus",
    "regiao": "Norte"
  }'
```

### 3. Listar viagens

```bash
curl -X GET "http://localhost:8089/api/v1/viagens" \
  -H "Authorization: <token_jwt>"
```

### 4. Filtrar viagens por região

```bash
curl -X GET "http://localhost:8089/api/v1/viagens?regiao=Norte" \
  -H "Authorization: <token_jwt>"
```

### 5. Buscar uma viagem específica

```bash
curl -X GET http://localhost:8089/api/v1/viagens/1 \
  -H "Authorization: <token_jwt>"
```

### 6. Atualizar uma viagem

```bash
curl -X PUT http://localhost:8089/api/v1/viagens/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: <token_jwt>" \
  -d '{
    "acompanhante": "Carlos",
    "dataPartida": "2021-02-10",
    "dataRetorno": "2021-03-10",
    "localDeDestino": "São Paulo",
    "regiao": "Sudeste"
  }'
```

### 7. Excluir uma viagem

```bash
curl -X DELETE http://localhost:8089/api/v1/viagens/1 \
  -H "Authorization: <token_jwt>"
```

## Exemplos de uso com testes

Eu também incluí testes automatizados na estrutura do projeto para validar cenários importantes da API. Para executá-los, eu uso:

```bash
./mvnw test
```

Os testes estão localizados em:

- src/test/java/com/montanha/gerenciador/viagens/ViagensTest.java

Esses testes demonstram como enviar requisições HTTP com RestAssured, autenticar um usuário administrador e verificar o status de resposta.

## Estrutura do projeto

```text
src/
├── main/
│   ├── java/
│   │   └── com/montanha/
│   │       ├── gerenciador/
│   │       └── security/
│   └── resources/
│       └── application.properties
├── test/
│   └── java/
│       └── com/montanha/gerenciador/viagens/
├── pom.xml
├── README.md
└── swaggerfile.yml
```

## Contribuições

Eu aceito contribuições para melhorar o projeto, corrigir bugs, ampliar a API ou reforçar a cobertura de testes. Se você quiser colaborar, siga os passos abaixo:

1. Faça um fork do projeto;
2. Crie uma branch para sua alteração:

```bash
git checkout -b minha-contribuicao
```

3. Faça suas alterações e commit:

```bash
git add .
git commit -m "Adiciona nova funcionalidade"
```

4. Envie para o repositório remoto:

```bash
git push origin minha-contribuicao
```

5. Abra um Pull Request com uma descrição clara do que foi alterado e por quê.

## Observações finais

Este projeto foi pensado para servir como base de estudo e prática em desenvolvimento backend com Java, Spring Boot e testes de APIs REST. Eu o mantenho simples, objetivo e fácil de executar localmente, para facilitar tanto a aprendizagem quanto a evolução do projeto.

Se você quiser, também posso criar uma segunda versão do README com foco em:

- público técnico mais voltado para DevOps;
- apresentação mais comercial/profissional;
- documentação mais enxuta para GitHub;
- inclusão de badges e imagem de arquitetura.
