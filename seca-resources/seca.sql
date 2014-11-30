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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seca`.`aluno`
--

/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` (`codigo_aluno`,`nome_usuario`,`senha`,`nome_completo`) VALUES 
 (1,'teste','123','teste'),
 (2,'teste2','123','teste');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;


--
-- Table structure for table `seca`.`compromisso`
--

DROP TABLE IF EXISTS `compromisso`;
CREATE TABLE `compromisso` (
  `codigo_comp` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_aluno` int(11) DEFAULT NULL,
  `data_ini` datetime DEFAULT NULL,
  `data_fim` datetime DEFAULT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `codigo_disciplina` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_comp`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seca`.`compromisso`
--

/*!40000 ALTER TABLE `compromisso` DISABLE KEYS */;
INSERT INTO `compromisso` (`codigo_comp`,`codigo_aluno`,`data_ini`,`data_fim`,`titulo`,`descricao`,`codigo_disciplina`) VALUES 
 (1,1,'2014-10-01 00:00:00','2014-10-03 00:00:00','teste','teste',1);
/*!40000 ALTER TABLE `compromisso` ENABLE KEYS */;


--
-- Table structure for table `seca`.`disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
CREATE TABLE `disciplina` (
  `codigo_disciplina` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `professor` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo_disciplina`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seca`.`disciplina`
--

/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` (`codigo_disciplina`,`nome`,`professor`) VALUES 
 (1,'teste','teste');
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;


--
-- Table structure for table `seca`.`horario`
--

DROP TABLE IF EXISTS `horario`;
CREATE TABLE `horario` (
  `codigo_disciplina` int(11) DEFAULT NULL,
  `periodo` int(11) DEFAULT NULL,
  `dia_semana` int(11) DEFAULT NULL,
  `sala` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seca`.`horario`
--

/*!40000 ALTER TABLE `horario` DISABLE KEYS */;
INSERT INTO `horario` (`codigo_disciplina`,`periodo`,`dia_semana`,`sala`) VALUES 
 (1,1,2,'teste');
/*!40000 ALTER TABLE `horario` ENABLE KEYS */;


--
-- Table structure for table `seca`.`lembrete`
--

DROP TABLE IF EXISTS `lembrete`;
CREATE TABLE `lembrete` (
  `codigo_lembrete` int(11) NOT NULL,
  `codigo_compromisso` int(11) NOT NULL,
  `minutos_antes` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_lembrete`,`codigo_compromisso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seca`.`matricula`
--

/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
INSERT INTO `matricula` (`codigo_aluno`,`codigo_disciplina`) VALUES 
 (1,1);
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;


--
-- Table structure for table `seca`.`prova`
--

DROP TABLE IF EXISTS `prova`;
CREATE TABLE `prova` (
  `codigo_disciplina` int(11) NOT NULL,
  `codigo_aluno` int(11) NOT NULL,
  `nota` float NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo_disciplina`,`codigo_aluno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seca`.`prova`
--

/*!40000 ALTER TABLE `prova` DISABLE KEYS */;
INSERT INTO `prova` (`codigo_disciplina`,`codigo_aluno`,`nota`,`nome`) VALUES 
 (1,1,10,'teste');
/*!40000 ALTER TABLE `prova` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
