-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 09, 2023 at 04:01 AM
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
-- Table structure for table `tbpemesanan`
--

CREATE TABLE `tbpemesanan` (
  `idPengguna` int(255) NOT NULL,
  `idMakanan` int(255) NOT NULL,
  `idPemesanan` int(225) NOT NULL,
  `tanggalPemesanan` varchar(225) NOT NULL,
  `namaMakanan` varchar(225) NOT NULL,
  `jumlahPemesanan` int(225) NOT NULL,
  `metodePengambilan` varchar(225) NOT NULL,
  `lokasiMetode` varchar(225) NOT NULL,
  `status` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbpemesanan`
--

INSERT INTO `tbpemesanan` (`idPengguna`, `idMakanan`, `idPemesanan`, `tanggalPemesanan`, `namaMakanan`, `jumlahPemesanan`, `metodePengambilan`, `lokasiMetode`, `status`) VALUES
(17, 50, 1, '2023-07-08', 'Gudeg Mbok Kasitem', 1, 'Makanan Diantar', 'az', '')
--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbpemesanan`
--
ALTER TABLE `tbpemesanan`
  ADD PRIMARY KEY (`idPemesanan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbpemesanan`
--
ALTER TABLE `tbpemesanan`
  MODIFY `idPemesanan` int(225) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
