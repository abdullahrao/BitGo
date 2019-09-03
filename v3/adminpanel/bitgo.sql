-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 03, 2019 at 06:43 PM
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
(1, 15, 5050, 'A', '08/27/2019', '08/30/2019'),
(2, 3, 3, 'X', '08/27/2019', '08/31/2019'),
(3, 0, 4, 'X', '08/27/2019', '08/30/2019'),
(4, 0, 2, 'X', '08/28/2019', '08/31/2019');

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
(1, 'Abdullah', 'abdullahayoub7842@gmail.com', '202cb962ac59075b964b07152d234b70', 12),
(2, 'suha', 'suha@g.c', '202cb962ac59075b964b07152d234b70', 22),
(3, 'shakir', 'shakir@g.c', '202cb962ac59075b964b07152d234b70', 23);

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
(1, 2, 2, 'Complete'),
(2, 2, 1, 'Complete'),
(3, 2, 3, 'Complete'),
(4, 2, 3, 'Complete'),
(5, 2, 3, 'Complete'),
(6, 2, 4, 'Complete'),
(7, 1, 1, 'Complete'),
(8, 5, 1, 'UnComplete'),
(9, 5, 1, 'UnComplete'),
(10, 5, 1, 'UnComplete'),
(11, 5, 1, 'UnComplete'),
(12, 5, 1, 'UnComplete'),
(13, 5, 1, 'UnComplete');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_store`
--

CREATE TABLE `tbl_store` (
  `s_id` bigint(20) NOT NULL,
  `image` varchar(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `price` bigint(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_store`
--

INSERT INTO `tbl_store` (`s_id`, `image`, `name`, `price`) VALUES
(1, 'https://i5.walmartimages.com/asr/3e11141d-5fad-4a66-a37b-94cfa6243d46_1.a3bfaba9533f2cf74678b9b1e35520df.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF', 'Smart Watch', 10),
(2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTNohoihlt7y2q23iObU_k4_y_b48JQ2NWNgsqcdXFwHfFTMHnG', 'Mug', 5);

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
-- Indexes for table `tbl_store`
--
ALTER TABLE `tbl_store`
  ADD PRIMARY KEY (`s_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `daily_challenge`
--
ALTER TABLE `daily_challenge`
  MODIFY `Challenge_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `login_tb`
--
ALTER TABLE `login_tb`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `take_challenge`
--
ALTER TABLE `take_challenge`
  MODIFY `tc_Id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tbl_store`
--
ALTER TABLE `tbl_store`
  MODIFY `s_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
