# API Cadastro de Usuário, Client e Pessoa Fisica

API RESTful para cadastro e gerenciamento de usuários, desenvolvida com Java e Spring Boot.

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.4-green)
![Maven](https://img.shields.io/badge/Maven-3.8.1-red)

## Descrição

Esta API permite realizar operações de CRUD (Create, Read, Update, Delete) em registros de usuários, incluindo validações e persistência em banco de dados.

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.5.5**
* **Maven 3.8.1**
* **Banco de Dados Relacional** (configuração a ser definida)

## Pré-requisitos

Antes de executar o projeto, verifique se você possui:

* Java 21 ou superior instalado
* Maven 3.8.1 ou superior instalado
* IDE de sua preferência (IntelliJ IDEA, Eclipse, etc.)

## Instalação

1. Clone o repositório:

   ```bash
   git clone https://github.com/diegof856/Api_cadastroUsuario.git
   cd Api_cadastroUsuario
   ```

2. Compile o projeto:

   ```bash
   mvn clean install
   ```

3. Execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

## Endpoints

### `POST /usuarios`

Cria um novo usuário.

**Exemplo de corpo da requisição:**

```json
{
   "login":"Seu login",
   "senha":"Sua senha",
   "roles":[
    "GERENTE"
   ]
}
```

**Resposta:**

* **201 Created**: Usuário criado com sucesso.
* **400 Bad Request**: Dados inválidos ou faltando.
### `POST /clientes`

Cria um novo cliente.
**Exemplo de corpo da requisição:**
```json
{
   "clienteId":"client id",
   "clienteSegredo":"client secret",
    "redirectURI":"http://localhost:8080/authorized",
    "scopo":"GERENTE"
}
```

**Resposta:**

* **201 Created**: Client criado com sucesso.
* **400 Bad Request**: Dados inválidos ou faltando.

### `POST /pessoas`

Cria um nova pessoa.
**Exemplo de corpo da requisição:**
```json
{
    "nome":"nome",
    "cpf":"cpf",
    "dataNascimento":"yyyy-mm-dd",
    "estadoCivil":"ESTADO CIVIL",
    "sexo":"GENERO",
    "endereco":{
        "cep":"CEP",
        "numero":"",
        "complemento":""
    }

}
```

**Resposta:**

* **201 Created**: Pessoa criado com sucesso.
* **400 Bad Request**: Dados inválidos ou faltando.


### `GET /usuarios/{id}`

Obtém os detalhes de um usuário pelo ID.

**Exemplo de resposta:**

```json
{
  "Id": "id",
  "Login": "login",
  "Senha": "senha",
  "Lista de autorizações": [
    "autorizações"
  ]
}
```
### `GET /clientes/{id}`

Obtém os detalhes de um cliente pelo ID.

**Exemplo de resposta:**

```json
{
  "Id": "id",
  "Client Id": "client id",
  "Senha do client": "client secret",
  "URI de redirecionamento": "url de redirecionamento",
  "Scopo": "autoridades"
}
```
### `GET /pessoas/{id}`

Obtém os detalhes de uma pessoa pelo ID.

**Exemplo de resposta:**

```json
{
  "Nome": "nome",
  "Cpf": "cpf",
  "Data de nascimento": "yyyy-mm-dd",
  "Genero": "genero",
  "Estado civil": "estado civil",
  "Endereco": {
    ...
  }
}
```
### `GET /pessoas?exemplo`

Realiza pesquisa de pesssoas por parametros pelo ID.

**Exemplo de resposta:**

```json
{
  "totalPages": 0,
  "totalElements": 0,
  "size": 0,
  "content": [
  {
    "nome":"nome",
    "cpf":"cpf",
    "dataNascimento":"yyyy-mm-dd",
    "estadoCivil":"ESTADO CIVIL",
    "sexo":"GENERO",
    "endereco":{
        "cep":"CEP",
        "numero":"",
        "complemento":""
    }

}
  ],
  "number": 0,
  "sort": {
    "empty": true,
    "sorted": true,
    "unsorted": true
  },
  "first": true,
  "last": true,
  "numberOfElements": 0,
  "pageable": {
    "offset": 0,
    "sort": {
      "empty": true,
      "sorted": true,
      "unsorted": true
    },
    "unpaged": true,
    "paged": true,
    "pageSize": 0,
    "pageNumber": 0
  },
  "empty": true
}
```

### `PUT /usuarios/{id}`

Atualiza os dados de um usuário existente.

**Exemplo de corpo da requisição:**

```json
{
   "login":"Login",
   "senha":"Nova senha",
   "roles":[
    "GERENTE",
    "OPERADOR"
   ]


}
```
### `PUT /clientes/{id}`

Atualiza os dados de um clientes existente.

**Exemplo de corpo da requisição:**

```json
{
  "Id": "id",
  "Client Id": "client id",
  "Senha do client": "client secret",
  "URI de redirecionamento": "url de redirecionamento",
  "Scopo": "autoridades"
}
```
### `PUT /pessoas/{id}`

Atualiza os dados de uma pessoa existente.

**Exemplo de corpo da requisição:**

```json
{
  "Nome": "nome",
  "Cpf": "cpf",
  "Data de nascimento": "yyyy-mm-dd",
  "Genero": "genero",
  "Estado civil": "estado civil",
  "Endereco": {
    ...
  }
}
```

### `DELETE /usuarios/{id}`

Remove um usuário pelo ID.

**Resposta:**

* **200 OK**: Usuário removido com sucesso.
* **404 Not Found**: Usuário não encontrado.

 ### `DELETE /pessoas/{id}`

Remove uma pessoa pelo ID.

**Resposta:**

* **200 OK**: Pessoa removida com sucesso.
* **404 Not Found**: Usuário não encontrado.

### `DELETE /clientes/{id}`

Remove um cliente pelo ID.

**Resposta:**

* **200 OK**: cliente removido com sucesso.
* **404 Not Found**: Usuário não encontrado.

## Contribuição

Contribuições são bem-vindas! Siga os passos abaixo para contribuir:

1. Fork este repositório.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Commit suas alterações (`git commit -am 'Adiciona nova feature'`).
4. Push para a branch (`git push origin feature/nova-feature`).
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
