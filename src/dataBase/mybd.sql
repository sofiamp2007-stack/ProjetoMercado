DROP DATABASE IF EXISTS mydb;
Create database mydb; 
use mydb; 

CREATE TABLE produtos (
id int AUTO_INCREMENT PRIMARY KEY,
nome_produto VARCHAR(100) NOT NULL,
preco decimal(6,2) NOT NULL,
quantidade INT NOT NULL
);

CREATE TABLE usuarios (
id int AUTO_INCREMENT PRIMARY KEY,
nome varchar(100) not null,
CPF char(11) UNIQUE NOT NULL,
senha VARCHAR(20) NOT NULL,
tipo ENUM('admin','cliente') NOT NULL
);






