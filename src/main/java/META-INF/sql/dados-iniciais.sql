-- INSERIR DADOS DO PROPRIETARIOS 
INSERT INTO proprietario (id, nome, email, telefone) VALUES (1, "João Silva", "joao@email.com", "98787-2383");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (2, "Maria Candida", "maria@email.com", "98870-6060");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (3, "Ricardo Augusto", "ricardo@email.com.br", "99945-9091");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (4, "Fernando de Souza Amorin", "fernando@email.com", "94345-8391");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (5, "Cláudio Moreno Bezerra", "claud@metaemail.com.br", "98163-9041");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (6, "Brena de Oliveira Martins", "brenaolive@email.com.br", "932152-3231");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (7, "Helena Duarte Bonfim", "helenalove@email.com.br", "932152-3231");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (8, "Carlos da Silva Costa", "carlitocosta@email.com", "93455-9090");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (9, "Rossandro Albino da Conceição", "rossandro2020@email.com.br", "93212-6678");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (10, "Maria Isabel de Souza", "mariasouza@email.com.br", "93353-4431");

-- INSERIR DADOS DOS VEICULOS
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel) VALUES (1, "FIAT", "Uno", 2016, 2016, "FLEX");
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel) VALUES (2, "Peugeot", "206", 2011, 2012, "GASOLINA");
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel) VALUES (3, "VW", "GOL G5", 2014, 2014, "ALCOOL");
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel) VALUES (4, "Ford", "Focus", 2014, 2014, "FLEX");
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel) VALUES (5, "Chevrolet", "Cruze", 2015, 2015, "FLEX");
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel) VALUES (6, "Audi", "A5", 2018, 2019, "GASOLINA");
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel) VALUES (7, "Troller", "Troller T4", 2017, 2018, "GASOLINA");
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel) VALUES (8, "Honda", "New Civic", 2018, 2019, "FLEX");
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel) VALUES (9, "Hyunday", "Creta", 2018, 2018, "GASOLINA");
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel) VALUES (10, "Suzuki", "Jimny 4All", 2014, 2015, "GASOLINA");


-- INSERIR DADOS DE VEICULO E PROPRIETARIO
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (1,1); 
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (2,2);
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (3,3);
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (4,4);
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (5,5);
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (6,6);
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (7,7);
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (7,8);
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (8,9);
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (9,10);
INSERT INTO veiculo_proprietario (veiculo_id, proprietario_id) VALUES (10,10);



-- INSERIR DADOS DA TABELA AUTOR
INSERT INTO autor (id, nome, sobrenome) VALUES (1, 'Pedro Júnior Alcântara', 'Júnior');
INSERT INTO autor (id, nome, sobrenome) VALUES (2, 'Mário Quintana', 'Quintana');
INSERT INTO autor (id, nome, sobrenome) VALUES (3, 'Marquinhos Pontes', 'Pontes');
INSERT INTO autor (id, nome, sobrenome) VALUES (4, 'Eduardo Cabral', 'Ferreira');
INSERT INTO autor (id, nome, sobrenome) VALUES (5, 'Clodoalto Nunes', 'Vanzin');


-- INSERIR DADOS DA TABELA LIVRO
INSERT INTO livro (id, titulo, data_publicacao, isbn) VALUES (1, 'Jakarta EE', '2018-11-12', '3183912');
INSERT INTO livro (id, titulo, data_publicacao, isbn) VALUES (2, 'Os segredos de uma vida saudável', '2019-01-11', '1029876');
INSERT INTO livro (id, titulo, data_publicacao, isbn) VALUES (3, 'SpaceX e suas tecnologias', '2020-07-08', '8769983');
INSERT INTO livro (id, titulo, data_publicacao, isbn) VALUES (4, 'Como amar sem amar?', '2002-01-01', '140896');
INSERT INTO livro (id, titulo, data_publicacao, isbn) VALUES (5, 'A vida animal a dois', '2001-02-10', '1775623');


-- INSERIR DADOS NA TABELA DE RELACIONAMENTO - livro_autor
INSERT INTO livro_autor (livro_id, autor_id) VALUES (1,1);
INSERT INTO livro_autor (livro_id, autor_id) VALUES (2,2);
INSERT INTO livro_autor (livro_id, autor_id) VALUES (3,3);
INSERT INTO livro_autor (livro_id, autor_id) VALUES (4,4);
INSERT INTO livro_autor (livro_id, autor_id) VALUES (5,5);
INSERT INTO livro_autor (livro_id, autor_id) VALUES (1,3);


-- INSERIR DADOS NA TABELA POST
INSERT INTO post (id, descricao, titulo) VALUES (1, 'Como tudo começou no Mundo?', 'A vida existe além de um próximo dia');
INSERT INTO post (id, descricao, titulo) VALUES (2, 'A natureza como nunca vista', 'Os rebanhos de toda a natureza');
INSERT INTO post (id, descricao, titulo) VALUES (3, 'O segredo dos macacos no Brasil', 'Cada macaco no seu galho');

-- INSERIR DADOS DA TABELA POST_COMENTARIO
INSERT INTO post_comentario (id, data_comentario, texto, post_id) VALUES (1, '2021-01-01','Esse é meu post favorito jovem',1);
INSERT INTO post_comentario (id, data_comentario, texto, post_id) VALUES (2, '2021-01-01','Como assim a natureza se modifica?',2);
INSERT INTO post_comentario (id, data_comentario, texto, post_id) VALUES (3, '2021-01-01','Essa música eu já ouvi',3);




