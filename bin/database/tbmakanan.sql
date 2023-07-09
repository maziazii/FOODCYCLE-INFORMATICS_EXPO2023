-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 29, 2023 at 11:14 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `foodcycle`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbmakanan`
--

CREATE TABLE `tbmakanan` (
  `idPengguna` int(255) NOT NULL,
  `idMakanan` int(255) NOT NULL,
  `tanggalPenawaran` varchar(50) NOT NULL,
  `namaMakanan` varchar(50) NOT NULL,
  `jumlahMakanan` int(255) NOT NULL,
  `lokasiPengambilan` varchar(50) NOT NULL,
  `jenisMakanan` varchar(50) NOT NULL,
  `tanggalKadaluwarsa` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbmakanan`
--
ALTER TABLE `tbmakanan`
  ADD PRIMARY KEY (`idMakanan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbmakanan`
--
ALTER TABLE `tbmakanan`
  MODIFY `idMakanan` int(255) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
