-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2023 at 09:14 AM
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
-- Table structure for table `tbpenawaran`
--

CREATE TABLE `tbpenawaran` (
  `idPengguna` int(225) NOT NULL,
  `idMakanan` int(225) NOT NULL,
  `idPenawaran` int(225) NOT NULL,
  `tanggalPenawaran` varchar(225) NOT NULL,
  `namaMakanan` varchar(225) NOT NULL,
  `jumlahMakanan` int(225) NOT NULL,
  `jenisMakanan` varchar(225) NOT NULL,
  `lokasiPengambilan` varchar(225) NOT NULL,
  `tanggalKadaluwarsa` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbpenawaran`
--
ALTER TABLE `tbpenawaran`
  ADD PRIMARY KEY (`idPenawaran`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbpenawaran`
--
ALTER TABLE `tbpenawaran`
  MODIFY `idPenawaran` int(225) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
