-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 11, 2019 at 05:06 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bitgo`
--

-- --------------------------------------------------------

--
-- Table structure for table `daily_challenge`
--

CREATE TABLE `daily_challenge` (
  `Challenge_id` int(11) NOT NULL,
  `Reward` int(20) NOT NULL,
  `No_Steps` bigint(20) NOT NULL,
  `Challenge_Status` varchar(50) NOT NULL,
  `Challenge_CreateDate` varchar(50) NOT NULL,
  `Challenge_ExpireDate` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daily_challenge`
--

INSERT INTO `daily_challenge` (`Challenge_id`, `Reward`, `No_Steps`, `Challenge_Status`, `Challenge_CreateDate`, `Challenge_ExpireDate`) VALUES
(1, 500, 5000, 'X', '03/26/2019', '03/28/2019'),
(2, 300, 230, 'A', '03/26/2019', '03/31/2019');

-- --------------------------------------------------------

--
-- Table structure for table `login_tb`
--

CREATE TABLE `login_tb` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_age` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login_tb`
--

INSERT INTO `login_tb` (`user_id`, `user_name`, `user_email`, `user_password`, `user_age`) VALUES
(1, 'Abdullah', 'abdullahrao7842@gmail.com', '202cb962ac59075b964b07152d234b70', 23),
(2, 'Abdullah Rao', 'abdullahrao', '202cb962ac59075b964b07152d234b70', 23),
(3, 'ali', 'ali@gmail.com', '202cb962ac59075b964b07152d234b70', 12),
(4, 'Zeeshan', 'zee@gmail.com', '202cb962ac59075b964b07152d234b70', 23),
(5, 'Suha', 'suha@gmail.com', '202cb962ac59075b964b07152d234b70', 23),
(6, 'Zahid', 'z@g.c', '202cb962ac59075b964b07152d234b70', 34),
(7, 'irfan', 'irfan@gmail.com', '202cb962ac59075b964b07152d234b70', 23);

-- --------------------------------------------------------

--
-- Table structure for table `take_challenge`
--

CREATE TABLE `take_challenge` (
  `tc_Id` int(20) NOT NULL,
  `user_Id` int(10) NOT NULL,
  `challenge_Id` int(10) NOT NULL,
  `userchallenge_Status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `take_challenge`
--

INSERT INTO `take_challenge` (`tc_Id`, `user_Id`, `challenge_Id`, `userchallenge_Status`) VALUES
(1, 5, 1, 'UnComplete'),
(2, 5, 2, 'UnComplete');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `daily_challenge`
--
ALTER TABLE `daily_challenge`
  ADD PRIMARY KEY (`Challenge_id`);

--
-- Indexes for table `login_tb`
--
ALTER TABLE `login_tb`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `take_challenge`
--
ALTER TABLE `take_challenge`
  ADD PRIMARY KEY (`tc_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `daily_challenge`
--
ALTER TABLE `daily_challenge`
  MODIFY `Challenge_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `login_tb`
--
ALTER TABLE `login_tb`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `take_challenge`
--
ALTER TABLE `take_challenge`
  MODIFY `tc_Id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
