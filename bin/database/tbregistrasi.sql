-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 07, 2023 at 03:42 PM
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
-- Table structure for table `tbregistrasi`
--

CREATE TABLE `tbregistrasi` (
  `idPengguna` int(255) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` text NOT NULL,
  `noTelepon` varchar(15) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbregistrasi`
--

INSERT INTO `tbregistrasi` (`idPengguna`, `nama`, `alamat`, `noTelepon`, `username`, `password`) VALUES
(16, 'Muhamad Azis', 'Umbulmartani, Kec.Ngalik, Kab.Sleman', '081215623332', 'maziazi', '12345678');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbregistrasi`
--
ALTER TABLE `tbregistrasi`
  ADD PRIMARY KEY (`idPengguna`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbregistrasi`
--
ALTER TABLE `tbregistrasi`
  MODIFY `idPengguna` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
