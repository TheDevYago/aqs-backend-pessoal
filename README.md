# Arks Requiem - API de Gestão Acadêmica e Monitorias

## Uma API RESTful robusta e segura, desenvolvida em Java com Spring Boot, para o gerenciamento completo da hierarquia de Instituições de Ensino Superior (IES), estrutura curricular, corpo docente e programa de monitorias.

> Sobre o Projeto

O Arks Requiem é um sistema back-end projetado para centralizar e modernizar a gestão educacional. A API fornece endpoints protegidos para gerenciar todo o fluxo acadêmico: desde a infraestrutura física (IES e Escolas), estruturação pedagógica (Cursos, Matrizes e Disciplinas), até a alocação de Professores e o controle detalhado de Alunos, Monitorias e emissão de Relatórios (PDF/Excel).

O sistema foi arquitetado para ser consumido por um Front-end em Angular, possuindo configurações nativas de CORS e autenticação via tokens.

> Tecnologias e Padrões Arquiteturais

O sistema foi construído seguindo rigorosas práticas de engenharia de software e segurança:

* **Java 21+ & Spring Boot 3:** Base de alta performance para a construção da API RESTful.

* **Spring Data JPA & PostgreSQL:** Persistência relacional e mapeamento avançado de entidades (`@ManyToOne`, `@OneToMany`, `@IdClass` para chaves compostas).

* **Spring Security & Autenticação JWT (Auth0):** Proteção rigorosa de rotas. Controle de acesso baseado em Roles (`ADMIN`, `USER`), com geração, validação e extração de claims via `TokenService`.

* **Padrão DTO (Data Transfer Object):** Isolamento total da camada de banco de dados, garantindo que apenas os dados estritamente necessários (e seguros) transitem nas requisições HTTP.

* **Estratégia de Soft Delete:** Ao invés de exclusões físicas (Hard Delete), o sistema prioriza a inativação de registros (alteração do campo `status` para `false`). Isso preserva o histórico acadêmico e previne erros de integridade referencial (`DataIntegrityViolationException`).

* **Global Exception Handler:** Uso de `@RestControllerAdvice` para capturar exceções globalmente, padronizando as respostas de erro (400, 404, 409, 500) em um formato JSON amigável e seguro para o Front-end.

> Módulos e Funcionalidades

O sistema está dividido em módulos lógicos, garantindo o princípio de responsabilidade única:

* **Segurança e Usuários:** Login, Registro (Criptografia BCrypt) e gerenciamento de perfis.
* **Infraestrutura:** Gestão de IES (Instituições) e Escolas (com vinculação de Coordenadores).

> Estrutura Curricular: Gestão de Cursos.

  * Regra de negócio estrita: É permitida apenas uma Matriz Curricular ativa por curso.
  * Cadastro de Disciplinas, vinculação à Matriz e associação de Pré-requisitos.
* **Corpo Docente:** Cadastro de Professores e registro de seu histórico de Formação Acadêmica.
* **Monitorias:** * Cadastro unificado de Alunos/Monitores utilizando a Matrícula como identificador.
  * Controle de alocação (Vigência, Local, Disciplina e Professor Orientador).
  * Lançamento de Pareceres e geração de Relatórios Exportáveis.

> Estrutura de Endpoints Principais

A API responde, por padrão, na porta `8080`. O CORS está liberado para `http://localhost:4200` (Angular).

> Autenticação (`/auth`)

- `POST /auth/login` - Autentica usuário e retorna o Token JWT

- `POST /auth/register` - Cadastra um novo usuário criptografado

> Acadêmico e Estrutura

- `/ies` e `/escola` - Endpoints de infraestrutura.

- `/curso` e `/matriz` - Endpoints de gestão de cursos e matrizes ativas.

- `/disciplinas` e `/disciplina-pre-requisitos` - Endpoints de grade curricular.

> Corpo Docente e Discente

- `/professor` e `/professor/formacao` - Gestão de professores e titulações.

- `/alunos-monitor` - Gestão unificada de alunos (Soft Delete implementado).

> Programa de Monitoria

- `/monitorias` - Vinculação de aluno, professor, disciplina e período.

- `/relatorios` - CRUD de pareceres e endpoint de download de relatórios (`/relatorios/{tipoId}`) com suporte a formatos PDF e Excel.

*(Todos os módulos possuem operações completas de CRUD: GET, POST, PUT e DELETE)*

> Como Executar o Projeto Localmente

Pré-requisitos:

- JDK 21 ou superior
- Apache Maven
- Servidor PostgreSQL em execução
- *Opcional:* Node.js e Angular CLI (para rodar o Front-end)

Passo a passo:

- Clone o repositório:
   ```bash
   git clone [https://github.com/SeuUsuario/Arks_Requiem.git](https://github.com/SeuUsuario/Arks_Requiem.git)


