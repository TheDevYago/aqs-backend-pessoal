-- Inserindo os Perfils solicitadas
INSERT INTO perfil (nome) VALUES ('ADMIN'), ('PROFESSOR');

-- Usuário Administrador (Senha 'admin123' - Exemplo)
INSERT INTO usuario (login, password, status) 
VALUES ('admin_ucsal', '$2a$10$8.UnVuG9HHgffUDAlk8q6uy5akLPNndzqBzv6ayhBwV.6S9Z6FmQC', TRUE);

INSERT INTO userPerfil (userId, perfilId) VALUES (1, 1);

-- Inserindo a IES principal
INSERT INTO ies (nome, endereco, telefone, status) 
VALUES ('UCSAL - Universidade Católica de Salvador', 'Av. Prof. Pinto de Aguiar, 2589', '(71) 3324-7500', true);

-- [AJUSTE] Inserir Professor antes de inserir as Escolas para resolver a referência circular
INSERT INTO professor (matricula, nomeCompleto, email, telefone, usuarioId, status)
VALUES (10001, 'Coordenador Geral', 'coord@ucsal.br', '71999999999', 1, TRUE);

-- Inserindo as Escolas solicitadas
INSERT INTO escola (nome, coordenadorResponsavelMatricula, iesId, status) VALUES
('Escola de Educação, Cultura e Humanidades', 10001, 1, TRUE),
('Escola de Ciências Sociais e Aplicadas', 10001, 1, TRUE),
('Escola de Engenharias e Ciências Tecnológicas', 10001, 1, TRUE),
('Escola de Ciências Naturais e da Saúde', 10001, 1, TRUE)
