-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.39


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema seca
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ seca;
USE seca;

--
-- Table structure for table `seca`.`aluno`
--

DROP TABLE IF EXISTS `aluno`;
CREATE TABLE `aluno` (
  `codigo_aluno` int(11) NOT NULL,
  `nome_usuario` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nome_completo` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_aluno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `seca`.`compromisso`
--

DROP TABLE IF EXISTS `compromisso`;
CREATE TABLE `compromisso` (
  `codigo_comp` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_aluno` int(11) NOT NULL,
  `data_ini` datetime NOT NULL,
  `data_fim` datetime NOT NULL,
  `dia_todo` TINYINT(1) NOT NULL DEFAULT 0,
  `titulo` varchar(45) DEFAULT NULL,
  `descricao` varchar(512) DEFAULT NULL,
  `codigo_disciplina` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_comp`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `seca`.`disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
CREATE TABLE `disciplina` (
  `codigo_disciplina` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `professor` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `seca`.`horario`
--

DROP TABLE IF EXISTS `horario`;
CREATE TABLE `horario` (
  `codigo_disciplina` int(11) NOT NULL,
  `periodo` int(2) NOT NULL,
  `dia_semana` int(2) NOT NULL,
  `sala` varchar(60) NOT NULL,
  PRIMARY KEY (`codigo_disciplina`, `periodo`, `dia_semana`, `sala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `seca`.`lembrete`
--

DROP TABLE IF EXISTS `lembrete`;
CREATE TABLE `lembrete` (
  `codigo_lembrete` int(11) NOT NULL,
  `codigo_compromisso` int(11) NOT NULL,
  `minutos_antes` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_lembrete`,`codigo_compromisso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `seca`.`lembrete`
--

/*!40000 ALTER TABLE `lembrete` DISABLE KEYS */;
INSERT INTO `lembrete` (`codigo_lembrete`,`codigo_compromisso`,`minutos_antes`) VALUES 
 (1,1,1);
/*!40000 ALTER TABLE `lembrete` ENABLE KEYS */;


--
-- Table structure for table `seca`.`matricula`
--

DROP TABLE IF EXISTS `matricula`;
CREATE TABLE `matricula` (
  `codigo_aluno` int(11) NOT NULL,
  `codigo_disciplina` int(11) NOT NULL,
  PRIMARY KEY (`codigo_aluno`,`codigo_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `seca`.`prova`
--

DROP TABLE IF EXISTS `prova`;
CREATE TABLE `prova` (
  `codigo_prova` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_disciplina` int(11) NOT NULL,
  `codigo_aluno` int(11) NOT NULL,
  `nota` float NULL,
  `peso` float NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo_prova`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
