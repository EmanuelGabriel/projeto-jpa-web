-- INSERIR DADOS DO PROPRIETÁRIOS 
INSERT INTO proprietario (id, nome, email, telefone) VALUES (1, "João Silva", "joao@email.com", "98787-2383");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (2, "Maria Candida", "maria@email.com", "98870-6060");
INSERT INTO proprietario (id, nome, email, telefone) VALUES (3, "Ricardo Augusto", "ricardo@email.com.br", "99945-9091");

-- INSERIR DADOS DOS VEICULOS
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel, id_proprietario) VALUES (1, "FIAT", "Uno", 2016, 2016, "FLEX", 1);
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel, id_proprietario) VALUES (2, "Peugeot", "206", 2011, 2012, "GASOLINA", 1);
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel, id_proprietario) VALUES (3, "VW", "GOL G5", 2014, 2014, "ALCOOL", 2);
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel, id_proprietario) VALUES (4, "Ford", "Focus", 2014, 2014, "FLEX", 2);
INSERT INTO veiculo (id, fabricante, modelo, ano_fabricacao, ano_modelo, tipo_combustivel, id_proprietario) VALUES (5, "Chevrolet", "Cruze", 2015, 2015, "FLEX", 3);



