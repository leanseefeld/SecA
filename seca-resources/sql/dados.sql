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


-- Popular aluno

INSERT INTO aluno (codigo_aluno, nome_usuario, senha, nome_completo) VALUES (1, "teste", "123", "Nome do Teste");
INSERT INTO aluno (codigo_aluno, nome_usuario, senha, nome_completo) VALUES (2, "teste2", "123", "Nome do Segundo Teste");
INSERT INTO aluno (codigo_aluno, nome_usuario, senha, nome_completo) VALUES (3, "gsabel", "123456", "Gustavo Sabel");
INSERT INTO aluno (codigo_aluno, nome_usuario, senha, nome_completo) VALUES (4, "nomedeusuariogigante", "000010", "Mendonça Aécio Ubiporã Lêtche Nome-Grande");

-- Popular disciplina

INSERT INTO disciplina (codigo_disciplina, nome, professor) VALUES (1, "Sistemas Distribuídos", "Paulo Fernando da Silva"); 
INSERT INTO disciplina (codigo_disciplina, nome, professor) VALUES (2, "Processo de Software I", "Everaldo Artur Grahl");
INSERT INTO disciplina (codigo_disciplina, nome, professor) VALUES (3, "Desafios Sociais e Contemporâneos", "Cíntia Aparecida da Luz Silva Pereira Goes");
INSERT INTO disciplina (codigo_disciplina, nome, professor) VALUES (4, "Álgebra Linear para Computação", "Evandro Felin Londero");
INSERT INTO disciplina (codigo_disciplina, nome, professor) VALUES (5, "Linguagens de Programação", "José Roque Voltolini da Silva");
INSERT INTO disciplina (codigo_disciplina, nome, professor) VALUES (6, "Teoria dos Grafos", "Aurélio Hoppe");
INSERT INTO disciplina (codigo_disciplina, nome, professor) VALUES (7, "Arquitetura de Computadores", "Miguel Alexandre Wisintainer");
INSERT INTO disciplina (codigo_disciplina, nome, professor) VALUES (8, "Linguagens Formais", "Joyce Martins");

-- Popular matricula (aluno 2 não possui matrículas)

/* -- regex --
 * sample-input: 3, (2, 4, 5, 6, 7, 8)
 * pattern:
 *  input:  (\d), \((\d)(, |\))(.*)$
 *  output: INSERT INTO matricula \(codigo_aluno, codigo_disciplina\) VALUES \(\1, \2\);\r\n\1, \(\4)
 */
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (1, 1);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (1, 2);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (1, 3);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (1, 4);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (1, 5);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (1, 6);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (1, 7);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (1, 8);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (3, 2);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (3, 4);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (3, 6);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (3, 7);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (3, 8);
INSERT INTO matricula (codigo_aluno, codigo_disciplina) VALUES (4, 5);

-- Popular prova (aluno 2 não possui provas)

-- SNB = Sem Nota na Base
/*
 * sample-input: 1 1 5 4 "Avaliação 1"
 * pattern: cdAluno cdDisc1 n1 peso "desc1"
 *  input:  (\d) (\d) (\d+\.?\d*|null) (\d+\.?\d*) (.+)$
 *  output: INSERT INTO prova \(codigo_aluno, codigo_disciplina, nota, peso, nome\) VALUES \(\1, \2, \3, \4, \5\);
 */
-- teste, "Sistemas Distribuídos"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 1, 10.0, 3, null);
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 1, 5, 4, "Avaliação 1");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 1, 8.333, 3, "Exercícios");
-- teste, "Processo de Software I"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 2, 10.0, 4, "Prova 1");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 2, null, 2, "Trabalho de alguma coisa");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 2, null, 4, null);
-- teste, "Desafios Sociais e Contemporâneos"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 3, null, 10, "Prova Única, pq é só uma");
-- teste, "Álgebra Linear para Computação"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 4, null, 5, "Primeira prova (SNB)");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 4, null, 5, "Segunda prova (SNB)");
-- teste, "Linguagens de Programação"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 5, 10, 10, "Provão");
-- teste, "Teoria dos Grafos"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 6, 6, 6, "Prova Macabra I");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 6, 6, 3, "Prova Macabra II");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 6, null, 1, "Trabalhos Macabros");
-- teste, "Arquitetura de Computadores"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (1, 7, null, 10, null);
-- sem avaliações para {1, 8 ("Linguagens Formais")}
-- gsabel, "Processo de Software I"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 2, null, 5, "Prova I");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 2, null, 5, "Prova II");
-- gsabel, "Álgebra Linear para Computação"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 4, 6.88, 5, "Prova I");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 4, null, 5, "Provitcha II");
-- gsabel, "Linguagens de Programação"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 10, 0.5, "Trabalho 01 (20 provas)");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 10, 0.5, "Trabalho 02");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 10, 0.5, "Trabalho 03");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 10, 0.5, "Trabalho 04");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 10, 0.5, "Trabalho 05");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 10, 0.5, "Trabalho 06");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 10, 0.5, "Trabalho 07");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 10, 0.5, "Trabalho 08");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 10, 0.5, "Trabalho 09");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 10, 0.5, "Trabalho 10");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 5, 0.5, "Trabalho 11");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 5, 0.5, "Trabalho 12");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 5, 0.5, "Trabalho 13");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 5, 0.5, "Trabalho 14");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 5, 0.5, "Trabalho 15");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 5, 0.5, "Trabalho 16");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 5, 0.5, "Trabalho 17");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 5, 0.5, "Trabalho 18");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 5, 0.5, "Trabalho 19");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 5, 5, 0.5, "Trabalho 20 (Último da lista)");
-- gsabel, "Teoria dos Grafos"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 6, 10, 1.5, "Exercicio I");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 6, 8, 1.5, "Exercicio II");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 6, null, 1.5, "Exercicio III");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 6, 6.88, 2.5, "Prova Um");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 6, null, 3, "Prova Dois");
-- gsabel, "Arquitetura de Computadores"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 7, 10, 5, "Primeira Prova");
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (3, 7, 10, 5, "Segunda Prova");
-- nomedeusuariogigante, "Linguagens de Programação"
INSERT INTO prova (codigo_aluno, codigo_disciplina, nota, peso, nome) VALUES (4, 3, 8.5, 10, "Prova Única, pq é só uma");

-- Popular horario

/* -- regex --
 * sample-input: 5 13 2 T-102/1
 * pattern: codDisc periodo diaSemana sala
 *  input:  (\d+) (\d+) (\d+) (.+)$
 *  output: INSERT INTO horario \(codigo_disciplina, periodo, dia_semana, sala\) VALUES \(\1, \2, \3, "\4"\);
 */
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (1, 12, 4, "S-432/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (1, 13, 4, "S-432/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (1, 14, 5, "S-403/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (1, 15, 5, "S-403/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (2, 12, 3, "S-301/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (2, 13, 3, "S-301/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (2, 14, 4, "S-403/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (2, 15, 4, "S-403/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (3, 14, 2, "S-301/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (3, 14, 3, "S-301/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (3, 12, 6, "S-301/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (3, 13, 6, "S-301/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (4, 12, 5, "S-305/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (4, 13, 5, "S-305/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (4, 14, 6, "S-401/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (4, 15, 6, "S-401/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (5, 12, 2, "T-102/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (5, 13, 2, "T-102/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (5, 14, 3, "S-321/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (5, 15, 3, "S-321/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (6, 12, 4, "R-103/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (6, 13, 4, "R-103/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (6, 12, 6, "B-203/4");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (6, 13, 6, "B-203/4");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (7, 12, 5, "A-307/2");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (7, 13, 5, "A-307/2");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (7, 14, 5, "A-307/2");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (7, 15, 5, "A-307/2");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (8, 12, 2, "S-309/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (8, 13, 2, "S-309/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (8, 12, 3, "S-305/1");
INSERT INTO horario (codigo_disciplina, periodo, dia_semana, sala) VALUES (8, 13, 3, "S-305/1");

-- Popular compromisso (aluno 4 sem compromissos)

/* -- regex --
 * sample-input: 3 2014-12-04 22:30 2014-12-04 22:40 0 'Exemplo SecA' 'Segundo compromisso na mesma disciplina' 2
 * pattern: codAluno dataIni(YYYY-MM-DD hh:mm) dataFim(YYYY-MM-DD hh:mm) diaTodo(0=false) 'titulo' 'descricao' codDisc
 *  input:  (\d+) (\d{4}-\d\d-\d\d \d\d:\d\d) (\d{4}-\d\d-\d\d \d\d:\d\d) (\d) ('.+') ('.+'|null) (\d+|null)$
 *  output: INSERT INTO compromisso \(codigo_aluno, data_ini, data_fim, dia_todo, titulo, descricao, codigo_disciplina\) VALUES \(\1, '\2:00', '\3:00', \4, \5, \6, \7\);
 */
INSERT INTO compromisso (codigo_aluno, data_ini, data_fim, dia_todo, titulo, descricao, codigo_disciplina) VALUES (1, '2014-12-25 00:00:00', '2014-12-25 00:00:00', 1, 'Natal', 'Entregar os presentes', null);
INSERT INTO compromisso (codigo_aluno, data_ini, data_fim, dia_todo, titulo, descricao, codigo_disciplina) VALUES (2, '2015-02-01 17:00:00', '2015-02-01 18:00:00', 0, 'Descobrir quando voltam as aulas', null, null);
INSERT INTO compromisso (codigo_aluno, data_ini, data_fim, dia_todo, titulo, descricao, codigo_disciplina) VALUES (3, '2014-12-04 18:30:00', '2014-12-04 22:00:00', 0, 'Apresentar SecA', 'Apresentar com todos os 4 usuários, passando por todas as telas', 2);
INSERT INTO compromisso (codigo_aluno, data_ini, data_fim, dia_todo, titulo, descricao, codigo_disciplina) VALUES (3, '2014-12-04 22:30:00', '2014-12-04 22:40:00', 0, 'Exemplo SecA', 'Segundo compromisso na mesma disciplina', 2);
INSERT INTO compromisso (codigo_aluno, data_ini, data_fim, dia_todo, titulo, descricao, codigo_disciplina) VALUES (3, '2014-12-20 22:30:00', '2014-12-04 22:40:00', 0, 'Fazer algo', 'Faltou criatividade pra um título melhor...', 4);
INSERT INTO compromisso (codigo_aluno, data_ini, data_fim, dia_todo, titulo, descricao, codigo_disciplina) VALUES (3, '2014-12-15 00:00:00', '2014-12-24 00:00:00', 1, 'Comprar presente de Natal', 'Comprar um presente bem da hora pros amigos! :D', null);
INSERT INTO compromisso (codigo_aluno, data_ini, data_fim, dia_todo, titulo, descricao, codigo_disciplina) VALUES (3, '2014-07-10 12:10:00', '2014-07-10 13:10:00', 0, 'Compromisso ocorrido no passado', null, 6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
