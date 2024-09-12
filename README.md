# Case Tecnico Alura
Seja bem-vindo ao teste para desenvolvedor Java Júnior da Alura. Neste
desafio, simulamos uma parte do nosso domínio para que você possa demonstrar seus conhecimentos. 
Não há respostas certas ou erradas, nosso objetivo é avaliar como você aplica lógica e 
conceitos de orientação a objetos para solucionar problemas.

## Requisitos

- Utilizar java 18+
- Utilizar Spring boot
- Utilizar Spring data JPA
- Utilizar mysql
- utilizar criação de tabelas manuais ([flyway](https://www.baeldung.com/database-migrations-with-flyway))

## Orientações

1. Suba o templete incial do projeto no seu github e deixe o repositório público(Seus commits serão avaliados).
2. Abra o projeto na IDE de sua preferência.
3. requisitos estão em português, mas lembre-se de no código escrever tudo em inglês.
4. bônus não é obrigatório e não possui ordem, então você pode realizar apenas um dos que
   são citados lá, de acordo com sua preferência.

## Desafio

Já disponibilizamos um projeto base como ponto de partida, no qual as tecnologias exigidas já estão configuradas. 
Algumas lógicas relacionadas à entidade de usuário já estão implementadas, 
e podem ser utilizadas como orientação para a resolução das questões.

**Importante:** Não se preocupe com a parte visual, toda a interação devem ser feitas
por API.

### Questão 1 - Cadastro de usuários

Na Alura, praticamente tudo está relacionado aos cursos, portanto, faz sentido começar por esse tema. 
Sua tarefa é implementar o cadastro de cursos, que deve conter os seguintes atributos e obedecer às regras estabelecidas.

#### Atributos
- Nome
- Código(Caracteres: Minimo 4, máximo 10)
- Instrutor
- Descrição
- Status (ACTIVE, INACTIVE)
- Data de inativação

#### Restrições
- Pode apenas ter um curso com o mesmo código.
- O código de um curso deve ser textual, sem espaços, nem caracteres numéricos e nem
  caracteres especiais, mas pode ser separado por - , exemplo: spring-boot-avancado .
- Apenas usuários instrutores podem ser autores de um curso.
- A data de inativação deve ser apenas definida quando o curso estiver desativado.
- Ao cadastrar um curso novo, automaticamente ele deve ser ACTIVE

**Dica:** Já deixamos um começo de código pronto no CourseController, com a rota `/course/new`

### Questão 2 - Inativação de um curso
Um curso pode ser inativado por diversos motivos, como o lançamento de uma nova versão ou a descontinuação da tecnologia. 
Como desenvolvedor, sua tarefa será criar uma funcionalidade para inativá-lo, seguindo as regras definidas.

#### Regras
Devemos possibilitar o acesso à rota `/course/{code}/inactive` utilizando o código do curso para desativá-lo. 
Ao realizar a requisição, 
o status do curso deve ser alterado para "INACTIVE", e o atributo "Data de inativação" deve ser preenchido com a data e hora atuais.

### Questão 3 - Criação de matriculas
Agora que temos a implementação de usuários e Cursos, podemos avançar para um requisito essencial: a matrícula de um aluno em um curso.

#### Atributos
- Usuário
- Curso
- Data de matricula

#### Restrições:
- Um usuário não pode matricular-se mais de uma vez em um curso.
- Podemos apenas nos matricular em cursos ativos.

**Dica:** Já deixamos um começo de código pronto no RegistrationController

### Questão 4 - Relatório de cursos mais acessado

Agora que a base está operante, desejamos criar um relatório para identificar os cursos com maior engajamento. 
Implemente a lógica na rota /registration/report para listar os cursos com mais matrículas, ordenados pela quantidade de inscrições.

**Atenção:** A Alura possui um grande volume de dados, portanto, priorize o uso de SQL nativo na construção do relatório e 
evite o [anti-pattern N+1](https://semantix.ai/o-que-e-o-problema-n1/).

## Considerações finais

- A avaliação do case será realizada exclusivamente com base nos requisitos e na forma como você utiliza lógica e 
orientação a objetos. Qualquer tecnologia fora do escopo, como Swagger, Docker, ou aspectos visuais, 
não será considerada como um diferencial.
- Caso você tenha alguma dúvida sobre a descrição das questões, faça anotações no código e siga o que considerar mais adequado.
- Os testes são extremamente importantes. Candidatos que implementarem testes receberão pontos extras. :)
- Outros candidatos estão concorrendo à mesma vaga, e códigos muito semelhantes resultarão na anulação do case.
- Utilize ferramentas de IA, mas tenha cautela com o código gerado automaticamente. Caso avance para a próxima etapa, 
a entrevista síncrona será baseada no código que você produziu.