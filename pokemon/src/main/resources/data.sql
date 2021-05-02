--Senha é 123456 em Bcrypt
INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$8aZ0iQn1UU8eecQ2tt2BMO7B9UFYfUykENRiU32Mm6VdsmdrWaWxG');


INSERT INTO TIPO(nome, autor_id, data_criacao) VALUES('Fogo', 1, '2019-05-05 18:00:00');
INSERT INTO TIPO(nome, autor_id, data_criacao) VALUES('Água', 1, '2019-05-04 18:00:00');
INSERT INTO TIPO(nome, autor_id, data_criacao) VALUES('Planta', 1, '2019-05-02 18:00:00');

INSERT INTO POKEMON(nome, descricao,tipo_id, autor_id, data_criacao) VALUES('Charmander', 'Melhor de todos', 1, 1, '2019-05-05 18:00:00');
INSERT INTO POKEMON(nome, descricao, tipo_id, autor_id, data_criacao) VALUES('Squirtle', 'Tartaruga Top', 2, 1, '2019-05-06 18:00:00');
INSERT INTO POKEMON(nome, descricao, tipo_id, autor_id, data_criacao) VALUES('Bulbasauro', 'Lindão', 3, 1, '2019-05-07 18:00:00');
