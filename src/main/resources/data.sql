-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 04 Mai 2017 à 09:02
-- Version du serveur :  10.1.10-MariaDB
-- Version de PHP :  7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `formateur`
--

--
-- Contenu de la table `eleve`
--

INSERT INTO `eleve` (`id`, `dtnais`, `nom`, `prenom`, `titre`, `note`, `formateur_id`) VALUES
(4, '2016-07-19', 'FERREIRA', 'Sabine', 'Mademoiselle', 15.7, 3),
(5, '1990-01-01', 'De la villardière', 'Bernard', 'Monsieur', 15.4, NULL),
(6, '1999-07-11', 'a', 'a', 'Monsieur', 12.5, NULL),
(8, '2016-07-13', 'Labrique', 'Dédé', NULL, 12.5, NULL);

--
-- Contenu de la table `formateur`
--

INSERT INTO `formateur` (`id`, `dtnais`, `nom`, `prenom`, `titre`, `dtdebut`, `dtfin`) VALUES
(3, '1978-01-04', 'SULTAN', 'Eric', 'Monsieur', '1978-02-04', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
