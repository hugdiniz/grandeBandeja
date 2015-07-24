DROP TABLE DEPARTAMENTO;
DROP TABLE CURSO;
DROP TABLE CONSUMIDOR;
DROP TABLE REFEICAO;
DROP TABLE TICKET;
DROP TABLE ALUNO;
DROP TABLE FUNCIONARIO;

	CREATE TABLE IF NOT EXISTS CONSUMIDOR
(
	id bigint auto_increment,
	nome varchar(255),
	matricula varchar(255),
	anoIngresso varchar(255),
	sexo varchar(255),
	titulo varchar(255),
	cpf varchar(255),
	habilitado boolean
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
	departamento_fk bigint
) ;

CREATE TABLE IF NOT EXISTS ALUNO
(
	id bigint,	
	curso_fk bigint
) ;

CREATE TABLE IF NOT EXISTS FUNCIONARIO
(
	id bigint,
	departamento_fk bigint
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
	refeicao_fk bigint,
	pago boolean
);