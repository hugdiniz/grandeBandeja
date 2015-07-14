	CREATE TABLE IF NOT EXISTS CONSUMIDOR
(
	id bigint auto_increment,
	nome varchar(255),
	matricula varchar(255),
	anoIngresso varchar(255),
	sexo varchar(255),
	titulo varchar(255),
	cpf varchar(255),
	curso_fk bigint,
	habilitado boolean,
);

CREATE TABLE IF NOT EXISTS DEPARTAMENTO
(
	id bigint auto_increment,
	nome varchar(255),
	SIGLA varchar(255)
); 

CREATE TABLE IF NOT EXISTS CURSO
(
	id bigint auto_increment,
	nome varchar(255),
	SIGLA varchar(255),
	departamento_fk bigint,
	FOREIGN KEY (departamento_fk) REFERENCES DEPARTAMENTO(id)
) ;



CREATE TABLE IF NOT EXISTS ALUNO
(
	id bigint,	
	FOREIGN KEY (id) REFERENCES CONSUMIDOR(id),
	curso_fk bigint,
	FOREIGN KEY (curso_fk) REFERENCES CURSO(id)
) ;

CREATE TABLE IF NOT EXISTS FUNCIONARIO
(
	id bigint,
	departamento_fk bigint,
	FOREIGN KEY (id) REFERENCES CONSUMIDOR(id),
	FOREIGN KEY (departamento_fk) REFERENCES DEPARTAMENTO(id)
);

CREATE TABLE IF NOT EXISTS REFEICAO
(
	id bigint auto_increment,
	turno varchar(255),
	descricao varchar(255),
	opcaoVegan varchar(255)	
);


CREATE TABLE IF NOT EXISTS TICKET
(
	id bigint auto_increment,
	consumidor_fk bigint,
	FOREIGN KEY (consumidor_fk) REFERENCES CONSUMIDOR(id),	
	refeicao_fk bigint,
	FOREIGN KEY (refeicao_fk) REFERENCES REFEICAO(id)
);