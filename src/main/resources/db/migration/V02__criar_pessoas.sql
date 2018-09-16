CREATE TABLE pessoa (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	bairro VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30),
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome , bairro,  cidade, estado, ativo) values ('João Silva',   'Brasil',  'Uberlândia', 'MG', true);
INSERT INTO pessoa (nome , bairro,  cidade, estado, ativo) values ('Maria Rita',   'Colina',  'Ribeirão Preto', 'SP', true);
INSERT INTO pessoa (nome , bairro,  cidade, estado, ativo) values ('Pedro Santos',   'Morumbi',  'Goiânia', 'GO', true);
INSERT INTO pessoa (nome , bairro,  cidade, estado, ativo) values ('Ricardo Pereira',  	'Aparecida',  'Salvador', 'BA', true);
INSERT INTO pessoa (nome , bairro,  cidade, estado, ativo) values ('Josué Mariano',   	'Jardins',  'Natal', 'RN', true);
INSERT INTO pessoa (nome , bairro,  cidade, estado, ativo) values ('Pedro Barbosa',   	'Tubalina',  'Porto Alegre', 'RS', true);
INSERT INTO pessoa (nome , bairro,  cidade, estado, ativo) values ('Henrique Medeiros', 'Centro',  'Rio de Janeiro', 'RJ', true);
INSERT INTO pessoa (nome , bairro,  cidade, estado, ativo) values ('Carlos Santana', 	'Pampulha',   'Belo Horizonte', 'MG', true);
INSERT INTO pessoa (nome , bairro,  cidade, estado, ativo) values ('Leonardo Oliveira', 'Carlos Prates' ,  'Uberlândia', 'MG', true);
INSERT INTO pessoa (nome , bairro,  cidade, estado, ativo) values ('Isabela Martins',   'Vigilato', 'Manaus', 'AM', true);