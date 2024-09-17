# AluraFake

## Descrição

O AluraFake é uma aplicação web desenvolvida com Spring Boot que oferece funcionalidades para gerenciar cursos, usuários e matrículas. A aplicação permite criar e gerenciar cursos, registrar novos estudantes, listar todos os usuários e gerar relatórios sobre matrículas.

## Tecnologias

- **Spring Boot**: v3.3.3
- **Java**: 17 ou superior
- **Jakarta EE**: Para validação e mapeamento de dados

## Estrutura do Projeto

### Pacotes e Funcionalidades

#### `br.com.alura.AluraFake.course`

- **CourseController**: Gerencia as operações relacionadas aos cursos.
  - **POST** `/course/new`: Cria um novo curso.
  - **POST** `/course/{code}/inactive`: Inativa um curso pelo código.

#### `br.com.alura.AluraFake.user`

- **UserController**: Gerencia as operações relacionadas aos usuários.
  - **POST** `/user/newStudent`: Cria um novo estudante.
  - **GET** `/user/all`: Lista todos os usuários.

#### `br.com.alura.AluraFake.registration`

- **RegistrationController**: Gerencia as operações relacionadas às matrículas.
  - **POST** `/registration/new`: Cria uma nova matrícula.
  - **GET** `/registration/report`: Gera um relatório de matrículas.

## Endpoints

### Curso

- **POST** `/course/new`
  - **Descrição**: Cria um novo curso.
  - **Request Body**: `NewCourseDTO` (deve incluir código do curso, nome, descrição, e email do instrutor).
  - **Respostas**:
    - `201 Created` - Curso criado com sucesso.
    - `400 Bad Request` - Se o código do curso já existir ou o instrutor não for válido.

- **POST** `/course/{code}/inactive`
  - **Descrição**: Inativa um curso pelo código.
  - **Path Variable**: `code` (código do curso a ser inativado).
  - **Respostas**:
    - `200 OK` - Curso inativado com sucesso.
    - `400 Bad Request` - Se o curso já estiver inativo.
    - `404 Not Found` - Se o curso não for encontrado.

### Usuário

- **POST** `/user/newStudent`
  - **Descrição**: Cria um novo estudante.
  - **Request Body**: `NewStudentUserDTO` (deve incluir nome e email do estudante).
  - **Respostas**:
    - `201 Created` - Estudante criado com sucesso.
    - `400 Bad Request` - Se o email já estiver cadastrado.

- **GET** `/user/all`
  - **Descrição**: Lista todos os usuários.
  - **Respostas**:
    - `200 OK` - Lista de usuários.

### Matrícula

- **POST** `/registration/new`
  - **Descrição**: Cria uma nova matrícula.
  - **Request Body**: `NewRegistrationDTO` (deve incluir email do estudante e código do curso).
  - **Respostas**:
    - `201 Created` - Matrícula criada com sucesso.
    - `400 Bad Request` - Se o estudante ou curso não forem encontrados.
    - `409 Conflict` - Se o estudante já estiver matriculado no curso.

- **GET** `/registration/report`
  - **Descrição**: Gera um relatório de matrículas.
  - **Respostas**:
    - `200 OK` - Relatório de matrículas com detalhes de cursos e contagem de inscrições.

## Configuração

Configure as variáveis de ambiente para o banco de dados e outros serviços no arquivo `application.properties` ou `application.yml`.

## Dependências

- **Spring Boot Starter Web**: Para criação de APIs RESTful.
- **Spring Boot Starter Data JPA**: Para integração com o banco de dados.
- **Spring Boot Starter Validation**: Para validação de dados.

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/Cangemi/AluraFake.git
   ```
2. Navegue até o diretório do projeto:
  ```bash
   cd alura-fake
  ```
3. Compile e execute o projeto:
  ```bash
   ./mvnw spring-boot:run
  ```
4. A aplicação estará disponível em http://localhost:8080.

5. Documatação http://localhost:8080/swagger-ui/index.html#/


## Testes

Os testes podem ser executados com Maven:

  ```bash
   ./mvnw test
  ```

## Contribuição

Sinta-se à vontade para contribuir com melhorias ou correções. Faça um fork do repositório, crie uma branch para suas modificações e envie um pull request com uma descrição clara das alterações.
