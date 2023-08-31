-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 22-Jun-2023 às 15:49
-- Versão do servidor: 10.4.24-MariaDB
-- versão do PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `clinica`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `consulta`
--

CREATE TABLE `consulta` (
  `cod_consulta` int(11) NOT NULL,
  `data` date NOT NULL,
  `cod_paciente` int(11) NOT NULL,
  `cod_dentista` int(11) NOT NULL,
  `cod_secretaria` int(11) NOT NULL,
  `nome_paciente` varchar(100) NOT NULL,
  `hora` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `consulta`
--

INSERT INTO `consulta` (`cod_consulta`, `data`, `cod_paciente`, `cod_dentista`, `cod_secretaria`, `nome_paciente`, `hora`) VALUES
(1, '2020-05-02', 4, 1, 1, 'Joaquim Silveira', '10:35:00'),
(2, '2023-06-22', 1, 1, 1, 'Lucas Ricardo', '10:00:00'),
(3, '2018-11-06', 2, 1, 1, 'Joana Gomes', '08:00:00'),
(4, '2021-01-30', 3, 1, 1, 'Josue', '19:30:00');

-- --------------------------------------------------------

--
-- Estrutura da tabela `dentista`
--

CREATE TABLE `dentista` (
  `cod_dentista` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cro` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `dentista`
--

INSERT INTO `dentista` (`cod_dentista`, `nome`, `cro`) VALUES
(1, 'Marcia Rocha', '005'),
(2, 'Catarina Silva', '045'),
(3, 'Ana Marques', '578'),
(4, 'Marcelo Falcao', '5623');

-- --------------------------------------------------------

--
-- Estrutura da tabela `paciente`
--

CREATE TABLE `paciente` (
  `cod_paciente` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `rg` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `paciente`
--

INSERT INTO `paciente` (`cod_paciente`, `nome`, `rg`, `senha`) VALUES
(1, 'Lucas Ricardo', '693', '124'),
(2, 'Joana Gomes', '777', '012'),
(3, 'Josué', '105', '357'),
(4, 'Joaquim Silveira', '754', '156'),
(5, 'Vitoria', '333', '024'),
(6, 'Roberta Silva', '423', '555'),
(7, 'Romario Bento', '998', '007'),
(8, 'Neymar Jr', '052', '103'),
(9, 'Francisco Dunas', '0246', '1145'),
(10, 'ab', '1', '1');

-- --------------------------------------------------------

--
-- Estrutura da tabela `secretaria`
--

CREATE TABLE `secretaria` (
  `cod_secretaria` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `rg` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `secretaria`
--

INSERT INTO `secretaria` (`cod_secretaria`, `nome`, `rg`) VALUES
(1, 'Maria do Carmo', '789'),
(2, 'Iara Santos', '357'),
(3, 'Anamelia Jorge', '12374'),
(4, 'Gina Vasconselos', '5487');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `consulta`
--
ALTER TABLE `consulta`
  ADD PRIMARY KEY (`cod_consulta`),
  ADD KEY `fk_dentista` (`cod_dentista`),
  ADD KEY `fk_paciente` (`cod_paciente`),
  ADD KEY `fk_secretaria` (`cod_secretaria`);

--
-- Índices para tabela `dentista`
--
ALTER TABLE `dentista`
  ADD PRIMARY KEY (`cod_dentista`);

--
-- Índices para tabela `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`cod_paciente`);

--
-- Índices para tabela `secretaria`
--
ALTER TABLE `secretaria`
  ADD PRIMARY KEY (`cod_secretaria`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `consulta`
--
ALTER TABLE `consulta`
  MODIFY `cod_consulta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de tabela `dentista`
--
ALTER TABLE `dentista`
  MODIFY `cod_dentista` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `paciente`
--
ALTER TABLE `paciente`
  MODIFY `cod_paciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de tabela `secretaria`
--
ALTER TABLE `secretaria`
  MODIFY `cod_secretaria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `consulta`
--
ALTER TABLE `consulta`
  ADD CONSTRAINT `fk_dentista` FOREIGN KEY (`cod_dentista`) REFERENCES `dentista` (`cod_dentista`),
  ADD CONSTRAINT `fk_paciente` FOREIGN KEY (`cod_paciente`) REFERENCES `paciente` (`cod_paciente`),
  ADD CONSTRAINT `fk_secretaria` FOREIGN KEY (`cod_secretaria`) REFERENCES `secretaria` (`cod_secretaria`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
