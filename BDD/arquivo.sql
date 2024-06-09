-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------

create database biblioteca;
use biblioteca;

select * from autor;
select * from libro;

-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------

DROP TABLE IF EXISTS `autor`;
CREATE TABLE `autor` (
  `id_autor` int NOT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

ALTER TABLE autor MODIFY COLUMN id_autor INT not null AUTO_INCREMENT PRIMARY KEY;

LOCK TABLES `autor` WRITE;

INSERT INTO `autor` VALUES (1,'Nacionalidade 1','Autor 1'),(2,'Nacionalidade 2','Autor 2'),(3,'Nacionalidade 3','Autor 3'),(4,'Nacionalidade 4','Autor 4'),(5,'Nacionalidade 5','Autor 5'),(6,'Nacionalidade 6','Autor 6'),(7,'Nacionalidade 7','Autor 7'),(8,'Nacionalidade 8','Autor 8'),(9,'Nacionalidade 9','Autor 9'),(10,'Nacionalidade 10','Autor 10'),(11,'Nacionalidade 11','Autor 11'),(12,'Nacionalidade 15','Autor 12');

UNLOCK TABLES;



DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `id_categoria` bigint NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;


LOCK TABLES `categoria` WRITE;

INSERT INTO `categoria` VALUES (1,'Categoria 1'),(2,'Categoria 2'),(3,'Categoria 3'),(4,'Categoria 4'),(5,'Categoria 5'),(6,'Categoria 6'),(7,'Categoria 7'),(8,'Categoria 8'),(9,'Categoria 9'),(10,'Categoria 10');

UNLOCK TABLES;

--
-- Table structure for table `editorial`
--

DROP TABLE IF EXISTS `editorial`;

CREATE TABLE `editorial` (
  `id_editorial` bigint NOT NULL,
  `contacto` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_editorial`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;


LOCK TABLES `editorial` WRITE;
INSERT INTO `editorial` VALUES (1,NULL,NULL,'Editorial 1',NULL),(2,NULL,NULL,'Editorial 2',NULL),(3,NULL,NULL,'Editorial 3',NULL),(4,NULL,NULL,'Editorial 4',NULL),(5,NULL,NULL,'Editorial 5',NULL),(6,NULL,NULL,'Editorial 6',NULL),(7,NULL,NULL,'Editorial 7',NULL),(8,NULL,NULL,'Editorial 8',NULL),(9,NULL,NULL,'Editorial 9',NULL),(10,NULL,NULL,'Editorial 10',NULL);
UNLOCK TABLES;


DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;


LOCK TABLES `hibernate_sequence` WRITE;
INSERT INTO `hibernate_sequence` VALUES (12),(12);
UNLOCK TABLES;


DROP TABLE IF EXISTS `libro`;
CREATE TABLE `libro` (
  `id_libro` INT not null AUTO_INCREMENT PRIMARY KEY,
  `titulo` varchar(255) DEFAULT NULL,
  `id_autor` int DEFAULT NULL,
  `id_categoria_id_categoria` bigint DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `disponivel` BOOLEAN not null DEFAULT true,
  KEY `FKi183oux1i0d5tjnoeyfvwywep` (`id_autor`),
  KEY `FK4rvnjebdr8a97wa9udjbqkq6n` (`id_categoria_id_categoria`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `libro` (`titulo`, `id_autor`, `id_categoria_id_categoria`, `genero`) 
VALUES 
('Livro 1', 1, 1, 'Ficção Científica'),
('Livro 2', 2, 2, 'Fantasia'),
('Livro 3', 3, 3, 'Romance'),
('Livro 4', 4, 4, 'Suspense'),
('Livro 5', 5, 5, 'Aventura'),
('Livro 6', 6, 6, 'Terror'),
('Livro 7', 7, 7, 'Mistério'),
('Livro 8', 8, 8, 'Drama'),
('Livro 9', 9, 9, 'Não Ficção'),
('Livro 10', 10, 10, 'História');

select * from libro;

DROP TABLE IF EXISTS `prestamo`;
CREATE TABLE `prestamo` (
  `id_prestamo` bigint NOT NULL,
  `carnet_estudiante` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_devolucion` datetime DEFAULT NULL,
  `fecha_entrega` datetime DEFAULT NULL,
  `monto_mora` decimal(19,2) DEFAULT NULL,
  `nombre_encargado` varchar(255) DEFAULT NULL,
  `id_autor_id_autor` int DEFAULT NULL,
  `id_editorial_id_editorial` bigint DEFAULT NULL,
  `id_libro_id_libro` int DEFAULT NULL,
  PRIMARY KEY (`id_prestamo`),
  KEY `FKlnj19mmhf05b83lp0qrphthv5` (`id_autor_id_autor`),
  KEY `FK5t9e1mlcs3kjrljqvihg5pvyu` (`id_editorial_id_editorial`),
  KEY `FKamx4uslqfg6yfuf3gs2coeii0` (`id_libro_id_libro`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;


LOCK TABLES `prestamo` WRITE;
UNLOCK TABLES;