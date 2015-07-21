DROP TABLE IF EXISTS refeicao ;
DROP TABLE IF EXISTS consumidor ;
DROP TABLE IF EXISTS ticket ;
DROP TABLE IF EXISTS departamento ;
DROP TABLE IF EXISTS curso ;
DROP TABLE IF EXISTS funcionario ;
DROP TABLE IF EXISTS aluno ;


-- Tabela Refeição
CREATE TABLE refeicao (
  idRefeicao INT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(45) NULL,
  opcaoVegetariana VARCHAR(45) NULL,
  turno VARCHAR(45) NOT NULL,
  situacao TINYINT(1) NOT NULL,
  PRIMARY KEY (idRefeicao));

-- Tabela consumidor
CREATE TABLE IF NOT EXISTS consumidor (
  matricula INT NOT NULL,
  nome VARCHAR(45) NOT NULL,
  ano_ingresso VARCHAR(45) NOT NULL,
  sexo VARCHAR(45) NOT NULL,
  titulo VARCHAR(45) NULL,
  cpf VARCHAR(45) NULL,
  situacao TINYINT(1) NOT NULL,
  PRIMARY KEY (matricula),
  UNIQUE INDEX cpf_UNIQUE (cpf ASC));

-- Tabela ticket
CREATE TABLE IF NOT EXISTS ticket (
  consumidor_matricula INT NOT NULL,
  refeicao_idRefeicao INT NOT NULL,
  preco DECIMAL(10,2) NOT NULL,
  pago TINYINT(1) NOT NULL,
  PRIMARY KEY (consumidor_matricula, refeicao_idRefeicao),
  CONSTRAINT fk_Consumidor_has_Refeicao_Consumidor1
    FOREIGN KEY (consumidor_matricula)
    REFERENCES consumidor (matricula)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Consumidor_has_Refeicao_Refeicao1
    FOREIGN KEY (refeicao_idRefeicao)
    REFERENCES refeicao (idRefeicao)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela departamento
CREATE TABLE IF NOT EXISTS departamento (
  iddepartamento INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  sigla VARCHAR(45) NOT NULL,
  PRIMARY KEY (iddepartamento));


-- Tabela curso
CREATE TABLE IF NOT EXISTS curso (
  idcurso INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  sigla VARCHAR(45) NOT NULL,
  departamento_iddepartamento INT NOT NULL,
  PRIMARY KEY (idcurso),
  CONSTRAINT fk_curso_departamento1
    FOREIGN KEY (departamento_iddepartamento)
    REFERENCES departamento (iddepartamento)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela funcionario
CREATE TABLE IF NOT EXISTS funcionario (
  departamento_iddepartamento INT NOT NULL,
  consumidor_matricula INT NOT NULL,
  PRIMARY KEY (departamento_iddepartamento, consumidor_matricula),
  CONSTRAINT fk_departamento_has_consumidor_departamento1
    FOREIGN KEY (departamento_iddepartamento)
    REFERENCES departamento (iddepartamento)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_departamento_has_consumidor_consumidor1
    FOREIGN KEY (consumidor_matricula)
    REFERENCES consumidor (matricula)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela aluno
CREATE TABLE IF NOT EXISTS aluno (
  consumidor_matricula INT NOT NULL,
  curso_idcurso INT NOT NULL,
  PRIMARY KEY (consumidor_matricula, curso_idcurso),
  CONSTRAINT fk_consumidor_has_curso_consumidor1
    FOREIGN KEY (consumidor_matricula)
    REFERENCES consumidor (matricula)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_consumidor_has_curso_curso1
    FOREIGN KEY (curso_idcurso)
    REFERENCES curso (idcurso)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Insert refeicao
INSERT INTO refeicao (descricao,opcaoVegetariana,turno, situacao) VALUES ('Arroz com bifé','Bolinho de soja','TARDE',1);
INSERT INTO refeicao (descricao,opcaoVegetariana,turno, situacao) VALUES ('Macarrão com salsicha','Quiche de legumes','NOITE',1);
INSERT INTO refeicao (descricao,opcaoVegetariana,turno, situacao) VALUES ('Arroz com carré','Soja','TARDE',1);

-- Insert departamento
INSERT INTO departamento (nome,sigla) VALUES ('Departamento de Tecnologias e Liguagens','DTL');
INSERT INTO departamento (nome,sigla) VALUES ('Departamento de Ciência da Computação','DCC');

-- Insert curso
INSERT INTO curso (nome,sigla,departamento_iddepartamento) VALUES ('Matematica','Mat', 1);
INSERT INTO curso (nome,sigla,departamento_iddepartamento) VALUES ('Ciência da Computação','CCOMP', 2);

-- Insert consumidor
INSERT INTO consumidor (matricula,nome,ano_ingresso,sexo,titulo,cpf,situacao) VALUES (123,'Miguel','2010','M','ESPECIALIZACAO','12345678901',1);
INSERT INTO consumidor (matricula,nome,ano_ingresso,sexo,titulo,cpf,situacao) VALUES (456,'Hugo','2010','M','ESPECIALIZACAO','12345678911',1);
INSERT INTO consumidor (matricula,nome,ano_ingresso,sexo,titulo,cpf,situacao) VALUES (789,'Duarte','2013','M','DOUTORADO','12345678922',1);

-- Insert Aluno
INSERT INTO aluno VALUES (123,2);
INSERT INTO aluno VALUES (456,1);

-- Insert funcionario
INSERT INTO funcionario VALUES (2,789);

-- Insert ticket
INSERT INTO ticket VALUES (123,1,0.5,1);
INSERT INTO ticket VALUES (789,1,3.0,1);