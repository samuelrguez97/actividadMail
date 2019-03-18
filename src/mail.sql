-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 18, 2019 at 09:40 AM
-- Server version: 8.0.13
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE mail;
USE mail;

--
-- Database: `mail`
--

-- --------------------------------------------------------

--
-- Table structure for table `noticias`
--

CREATE TABLE `noticias` (
  `id` int(11) NOT NULL,
  `titulo` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `localizacion` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `fecha` date NOT NULL,
  `descripcion` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `fuente` varchar(256) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `noticias`
--

INSERT INTO `noticias` (`id`, `titulo`, `localizacion`, `fecha`, `descripcion`, `fuente`) VALUES
(1, 'Las dudas que debe aclarar Trapero en el juicio por el \'procés\'', 'Madrid', '2019-03-14', 'El mayor de los Mossos d\'Esquadra, Josep Lluís Trapero, reaparece este jueves en el Tribunal Supremo (TS) tras haberse mantenido apartado de la escena pública desde el 28 de octubre de 2017.', 'https://www.elmundo.es/espana/2019/03/13/5c897fb2fdddffbc558b468d.html'),
(2, 'El Barça sí cumple en Europa', 'Barcelona', '2019-03-14', 'Los azulgrana sobreviven a 20 minutos de pánico para acabar arrasando al Lyon de la mano de Messi y Luis Suárez.', 'https://www.elmundo.es/deportes/futbol/2019/03/13/5c897e1321efa0745d8b4571.html'),
(3, 'La UE pierde la paciencia y medita qué prórroga conceder a Theresa May', 'Bruselas', '2019-03-14', 'Los 27 dejan claro su \"pesar\" y preocupación por el hecho de que Westminster rechace, por segunda vez, el Acuerdo de Salida que ellos respaldan y recuerdan que no habrá tercera oportunidad.', 'https://www.elmundo.es/internacional/2019/03/13/5c89555421efa054018b46d4.html'),
(4, 'prueba', 'prueba', '0023-07-12', 'prueba', 'prueba');

-- --------------------------------------------------------

--
-- Table structure for table `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `usuario` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `clave` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nombre` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `apellidos` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `telefono` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `hashEmail` varchar(256) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `usuarios`
--

INSERT INTO `usuarios` (`id`, `usuario`, `clave`, `nombre`, `apellidos`, `telefono`, `email`, `hashEmail`) VALUES
(9, 'samuel', '44C283B98A7DB1B6E53F26119F02DF661E2F8DD15307E1E5905CD6E110A942274C596FF4ED849B1828AD0E973F89361D2BF5905F1E03F36FA4776810ADDD85F0', 'samuel', 'rodriguez', '656789678', 'samuelrguez97@gmail.com', ''),
(10, 'samu', 'A0B318762209850C7FECF445A9249239A16178DDB16998F52576FBA71D97F412649B44B02241621C8688F62AE096F887C7CA00A5769107A01EF31396EADED9FA', 'samu', 'rodr', 'agul', 'azmun091@gmail.com', ''),
(11, 'pepevi', '85F779F4230FA802D029FD480611300DCBF9A547DAC96D26776B7D9C6A6C65D1F3E7B828A56A67ED90C9924C7A7B6588333B1B656C60489F1C3A76BF7BF7D6E7', 'pepeviyuela', 'pepe', '1234', 'samuelrguez97@gmail.com', ''),
(12, 'samuel12', 'A342A4325E6B37F1AA0B324C79956F4A788B4904469B192070971E7EB923A6C0B670FC4A4E0C5AA353A342DEA99395E64CED109BB7F502B712B61E4867F734A2', 'pepe', 'pepe123', '667767', 'samuelrguez97@gmail.com', 'C983B8AD67B8DBAE72F324099609220DC1D784901C7F6FB5522B6C9CC0C4D5E7FFF12BC0D1BB3A4CB5417F8B5CE370F10447A74F53631CC4BC8B2607805092DC'),
(14, 'prueba1', '079F8794C57E875AE5F71B17C60AE6BE8E90A112627303B4C038F715A7C0625962F62710BCB74E2024038D5BA356ECB113FBDB32CEAD00CAC1CAB9FB83CADED6', 'prueba1', 'prueba1', '123456', 'samuelrguez97@gmail.com', ''),
(15, 'pepe2', 'BE864C17721FB2B700B372244B269712D28A08D7155B7D3C3C55642C800293B383EC0C65F733CBF24C1E5886DE0E57A88DA5260322753E96E9E6C3DAB9ABCDB9', 'pepe2', 'pepe2', '1234', 'pepe@gmail.com', '4F06481C186B8B2DE31F05378E7A8532049B31BA7A5B1AC8F8F133D073CA705F77D19E08961683022AD4298E246B2CB46E7842C5324A7219917F521BBFAECF3C');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `noticias`
--
ALTER TABLE `noticias`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `noticias`
--
ALTER TABLE `noticias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
