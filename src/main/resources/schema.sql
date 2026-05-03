DROP TABLE IF EXISTS monitoriaRelatorio CASCADE;
DROP TABLE IF EXISTS monitoria CASCADE;
DROP TABLE IF EXISTS aluno CASCADE;
DROP TABLE IF EXISTS matrizDisciplina CASCADE;
DROP TABLE IF EXISTS matriz CASCADE;
DROP TABLE IF EXISTS disciplinaPreRequisito CASCADE;
DROP TABLE IF EXISTS disciplina CASCADE;
DROP TABLE IF EXISTS curso CASCADE;
DROP TABLE IF EXISTS formacaoProfessor CASCADE;
DROP TABLE IF EXISTS escola CASCADE;
DROP TABLE IF EXISTS professor CASCADE;
DROP TABLE IF EXISTS ies CASCADE;
DROP TABLE IF EXISTS userPerfil CASCADE;
DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS perfil CASCADE;


-- Tabelas de Segurança
CREATE TABLE perfil (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE usuario (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    login VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    status BOOLEAN DEFAULT TRUE
);

CREATE TABLE userPerfil (
    userId INTEGER REFERENCES usuario(id),
    perfilId INTEGER REFERENCES perfil(id),
    PRIMARY KEY (userId, perfilId)
);

-- Instituição
CREATE TABLE ies(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome CHARACTER VARYING(255) NOT NULL,
    endereco CHARACTER VARYING(255),
    telefone CHARACTER VARYING(15),
    status BOOLEAN DEFAULT TRUE
);

-- Professor 
CREATE TABLE professor (
    matricula BIGINT PRIMARY KEY, 
    nomeCompleto CHARACTER VARYING(255) NOT NULL,
    email CHARACTER VARYING(100) UNIQUE NOT NULL,
    telefone CHARACTER VARYING(20),
    usuarioId INTEGER UNIQUE REFERENCES usuario(id),
    dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_professor_usuario FOREIGN KEY (usuarioId) REFERENCES usuario(id) ON DELETE RESTRICT
);

-- Escola
CREATE TABLE escola(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome CHARACTER VARYING(255) NOT NULL,
    coordenadorResponsavelMatricula BIGINT NOT NULL REFERENCES professor(matricula),
    iesId INTEGER NOT NULL REFERENCES ies(id),
    dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_escola_ies FOREIGN KEY (iesId) REFERENCES ies(id) ON DELETE RESTRICT
);

-- Resolvendo referência circular
ALTER TABLE professor ADD COLUMN escolaId INTEGER REFERENCES escola(id) ON DELETE RESTRICT;

-- Formação do Professor
CREATE TABLE formacaoProfessor (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    professorMatricula BIGINT NOT NULL REFERENCES professor(matricula),
    categoriaTitulacao CHARACTER VARYING(50) CHECK (categoriaTitulacao IN ('Graduação', 'Especialização', 'MBA', 'Mestrado', 'Doutorado', 'Pós-Doutorado')),
    instituicaoConclusao CHARACTER VARYING(150),
    nomeCurso CHARACTER VARYING(150),
    anoConclusao INTEGER,
    CONSTRAINT fk_formacaoProfessor FOREIGN KEY (professorMatricula) REFERENCES professor(matricula) ON DELETE RESTRICT
);

-- Cursos e Disciplinas
CREATE TABLE curso (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    sigla CHARACTER VARYING(10) UNIQUE NOT NULL,
    descricao CHARACTER VARYING(150) NOT NULL,
    escolaId INTEGER NOT NULL REFERENCES escola(id),
    turno CHARACTER VARYING(20),
    coordenadorCurso CHARACTER VARYING(150),
    dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_curso_escola FOREIGN KEY (escolaId) REFERENCES escola(id) ON DELETE RESTRICT
);

CREATE TABLE disciplina (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    sigla CHARACTER VARYING(10) UNIQUE NOT NULL,
    descricao CHARACTER VARYING(150) NOT NULL,
    cargaHoraria INTEGER NOT NULL,
    escolaId INTEGER NOT NULL REFERENCES escola(id),
    dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_disciplina_escola FOREIGN KEY (escolaId) REFERENCES escola(id) ON DELETE RESTRICT
);

CREATE TABLE disciplinaPreRequisito (
    disciplinaId INTEGER REFERENCES disciplina(id),
    preRequisitoId INTEGER REFERENCES disciplina(id),
    PRIMARY KEY (disciplinaId, preRequisitoId)
);

-- Matriz Curricular
CREATE TABLE matriz (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nome CHARACTER VARYING(100) NOT NULL,
    descricao CHARACTER VARYING(300),
    cursoId INTEGER NOT NULL REFERENCES curso(id),
    dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_matriz_curso FOREIGN KEY (cursoId) REFERENCES curso(id) ON DELETE RESTRICT
);

CREATE UNIQUE INDEX idx_unica_matriz_ativa_por_curso ON matriz (cursoId) WHERE (status = true);

CREATE TABLE matrizDisciplina (
    matrizId INTEGER REFERENCES matriz(id),
    disciplinaId INTEGER REFERENCES disciplina(id),
    PRIMARY KEY (matrizId, disciplinaId)
);

-- Alunos e Monitoria
CREATE TABLE aluno (
    matricula BIGINT PRIMARY KEY, -- [AJUSTE] Mudamos para BIGINT para bater com o Java (Long)
    nomeCompleto CHARACTER VARYING(150) NOT NULL,
    dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status BOOLEAN DEFAULT TRUE
);

CREATE TABLE monitoria (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    alunoMatricula BIGINT NOT NULL REFERENCES aluno(matricula) ON DELETE RESTRICT,
    disciplinaId INTEGER NOT NULL REFERENCES disciplina(id),
    professorOrientadorMatricula BIGINT NOT NULL REFERENCES professor(matricula),
    semestre CHARACTER VARYING(10) NOT NULL,
    tipoMonitoria CHARACTER VARYING(20) CHECK (tipoMonitoria IN ('Presencial', 'Remoto')),
    localAtuacao CHARACTER VARYING(100),
    dataInicio DATE NOT NULL,
    dataFim DATE,
	dataCadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status BOOLEAN DEFAULT TRUE,

	CONSTRAINT fk_monitoria_aluno FOREIGN KEY (alunoMatricula) REFERENCES aluno(matricula),
	CONSTRAINT fk_monitoria_disciplina FOREIGN KEY (disciplinaId) REFERENCES disciplina(id),
	CONSTRAINT fk_monitoria_professor FOREIGN KEY (professorOrientadorMatricula) REFERENCES professor(matricula),

	-- Regra: Aluno só pode ser monitor de uma disciplina por semestre
    CONSTRAINT unique_monitor_semestre UNIQUE (alunoMatricula, semestre),
	
	-- Validação de datas
	CONSTRAINT check_datas CHECK (dataFim >= dataInicio OR dataFim IS NULL)
);

CREATE TABLE monitoriaRelatorio (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    monitoriaId INTEGER UNIQUE NOT NULL REFERENCES monitoria(id) ON DELETE RESTRICT,
    qtdAlunosAtendidos INTEGER DEFAULT 0,
    ocorrencias CHARACTER VARYING(300),
    parecerFinal CHARACTER VARYING(300)
);
