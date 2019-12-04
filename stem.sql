-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2019 at 09:26 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stuloan`
--

-- --------------------------------------------------------

--
-- Table structure for table `loan_account`
--

CREATE TABLE `loan_account` (
  `acc_no` int(11) NOT NULL,
  `student_id` int(11) DEFAULT NULL,
  `loan_balance` double DEFAULT NULL,
  `loan_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan_account`
--

INSERT INTO `loan_account` (`acc_no`, `student_id`, `loan_balance`, `loan_date`) VALUES
(5, 3, 0, NULL),
(6, 4, 0, NULL),
(7, 9, 0, NULL),
(8, 10, 0, NULL),
(9, 11, 0, NULL),
(10, 12, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `loan_application`
--

CREATE TABLE `loan_application` (
  `application_id` int(11) NOT NULL,
  `applicant_id` int(11) DEFAULT NULL,
  `application_date` date DEFAULT NULL,
  `loan_amount` double DEFAULT NULL,
  `approval_date` date DEFAULT NULL,
  `approved` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loan_application`
--

INSERT INTO `loan_application` (`application_id`, `applicant_id`, `application_date`, `loan_amount`, `approval_date`, `approved`) VALUES
(1, 3, '2019-11-20', 150, NULL, 0),
(2, 3, '2019-11-20', 200, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `sname` varchar(50) DEFAULT NULL,
  `phy_address` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `flagged` tinyint(1) NOT NULL DEFAULT '0',
  `user_type` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `fname`, `sname`, `phy_address`, `username`, `password`, `flagged`, `user_type`) VALUES
(3, 'Monil', 'Parekh', 'Gaborone plot 5343', 'mp123', 'pass', 0, 'student'),
(4, 'James', 'Bond', 'Street 5443 NY', 'j.bond', 'pass', 0, 'student'),
(5, 'John', 'Smith', '6536 Lakeside', 'admin', 'admin', 0, 'admin'),
(8, 'Jane', 'Doe', 'Box 53625 Gaborone', 'admin2', 'admin', 0, 'admin'),
(9, 'Chris', 'Rutherford', 'P Bag 424\nGaborone', 'chris', 'password', 0, 'student'),
(10, 'abc', 'abc', '123', 'abc', 'password', 0, 'student'),
(11, 'name', 'surname', '3211', 'abcd', 'pass', 0, 'student'),
(12, 'Name', 'Surname', 'ahkldajh', 'Username', '1234', 0, 'student');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `loan_account`
--
ALTER TABLE `loan_account`
  ADD PRIMARY KEY (`acc_no`);

--
-- Indexes for table `loan_application`
--
ALTER TABLE `loan_application`
  ADD PRIMARY KEY (`application_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `loan_account`
--
ALTER TABLE `loan_account`
  MODIFY `acc_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `loan_application`
--
ALTER TABLE `loan_application`
  MODIFY `application_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
