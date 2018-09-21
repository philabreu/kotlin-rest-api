CREATE TABLE categoria (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('Lazer');
INSERT INTO categoria (nome) values ('Alimentação');
INSERT INTO categoria (nome) values ('Entretenimento');
INSERT INTO categoria (nome) values ('Saúde');
INSERT INTO categoria (nome) values ('Emergências');
INSERT INTO categoria (nome) values ('Outros');