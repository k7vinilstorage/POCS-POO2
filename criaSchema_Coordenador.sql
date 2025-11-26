CREATE SCHEMA IF NOT EXISTS pocs

USE pocs;

CREATE TABLE IF NOT EXISTS Coordenador(Codigo VARCHAR(20) PRIMARY KEY NOT NULL, Nome text, Cpf text, Senha text, Celular text, Idade int, Email text);

INSERT INTO Coordenador (Codigo, Nome, Cpf, Senha, Celular, Idade, Email) VALUES("c0001", "Chuck", "051.724.738-07", "bradock123", "996804866", 56, "norrischuck@gmail.com");