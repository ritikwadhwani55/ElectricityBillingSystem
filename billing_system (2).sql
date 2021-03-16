-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 07, 2021 at 02:45 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `billing_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `gender` enum('Male','Female','Other') NOT NULL,
  `age` tinyint(4) NOT NULL,
  `salary` int(11) NOT NULL,
  `address` varchar(200) NOT NULL,
  `contact` bigint(20) NOT NULL,
  `doj` date NOT NULL,
  `dol` date DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `full_name`, `gender`, `age`, `salary`, `address`, `contact`, `doj`, `dol`, `email`, `password`) VALUES
(1, 'Ritik Wadhwani', 'Male', 25, 23000, 'Flat no.309,Rosevilla,Evergreen Complex,\nMatunga', 7596325912, '2021-01-01', NULL, 'ritik@gmail.com', 'r1'),
(2, 'Girish Vaswani', 'Male', 29, 38000, 'Flat no.702,Sunrise apartment, MayFlower garden, \nKurla', 9635899876, '2021-01-27', NULL, 'girish@gmail.com', 'girish'),
(3, 'Megha Shahri', 'Female', 32, 52000, 'Flat no.1102,B Wing,Gokuldhaam society, Goregaon', 7896555234, '2021-01-27', NULL, 'megha@gmail.com', 'megha'),
(4, 'Rohan Asrani', 'Male', 23, 42000, 'Flat no. 401,Apple Apartment, near Ram Garage, Khopoli', 8566983210, '2021-02-07', NULL, 'rohan@gmail.com', 'rohan');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL,
  `connection_id` int(11) NOT NULL,
  `month` varchar(30) NOT NULL,
  `year` varchar(4) NOT NULL,
  `total_bill` int(11) NOT NULL,
  `payment_status` varchar(10) NOT NULL,
  `payment_date` date DEFAULT NULL,
  `units_consumed` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_id`, `connection_id`, `month`, `year`, `total_bill`, `payment_status`, `payment_date`, `units_consumed`) VALUES
(1, 1, 'December', '2020', 923, 'Unpaid', NULL, '117.00'),
(4, 2, 'December', '2020', 664, 'Unpaid', NULL, '80.00'),
(8, 3, 'December', '2020', 1154, 'Paid', '2021-02-06', '150.00'),
(10, 2, 'January', '2021', 1140, 'Unpaid', NULL, '148.00'),
(11, 3, 'November', '2020', 1532, 'Paid', NULL, '204.00'),
(12, 7, 'January', '2021', 1504, 'Unpaid', NULL, '200.00'),
(13, 7, 'December', '2020', 979, 'Paid', '2021-02-06', '125.00'),
(14, 4, 'January', '2021', 790, 'Paid', '2021-02-07', '98.00');

-- --------------------------------------------------------

--
-- Table structure for table `connection`
--

CREATE TABLE `connection` (
  `connection_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `meter_number` int(11) NOT NULL,
  `conn_address` varchar(150) NOT NULL,
  `city` varchar(20) NOT NULL,
  `meter_location` varchar(30) NOT NULL,
  `installation_date` date NOT NULL,
  `disconnect_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `connection`
--

INSERT INTO `connection` (`connection_id`, `customer_id`, `meter_number`, `conn_address`, `city`, `meter_location`, `installation_date`, `disconnect_date`) VALUES
(1, 2, 3958, 'Flat no. 701, A wing, Collector Colony', 'Chembur', 'Inside', '2021-01-28', '2021-01-31'),
(2, 1, 706, 'Shop no.24, near McDonalds,Shivaji Road', 'Thane', 'Outside', '2021-01-28', NULL),
(3, 3, 4935, 'B.K no. 532,Shop no.202, Near post office', 'Santa Cruz', 'Inside', '2021-01-30', NULL),
(4, 4, 3301, 'Flat no. 402,C wing, Gokuldham Society', 'Goregaon', 'Outside', '2021-01-30', NULL),
(7, 2, 1080, 'Shop.39, near Shivaji Market', 'Navi Mumbai', 'Outside', '2021-02-06', NULL),
(10, 3, 360, 'B.K no. 549,Shop no.202, Near post office', 'Santa Cruz', 'Inside', '2021-02-07', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `conn_request`
--

CREATE TABLE `conn_request` (
  `request_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `connection_address` varchar(300) NOT NULL,
  `city` varchar(50) NOT NULL,
  `request_status` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `conn_request`
--

INSERT INTO `conn_request` (`request_id`, `customer_id`, `connection_address`, `city`, `request_status`) VALUES
(1, 3, 'Flat no.504,Diamond apartment, \nnear Hira Marriage Banquet,\nKurla', 'Kurla', 'Accepted'),
(2, 5, 'Flat no.703, Paradise apartment,\nKurla', 'Kurla', 'Accepted');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `gender` enum('Male','Female','Other') NOT NULL,
  `age` tinyint(4) NOT NULL,
  `address` varchar(100) NOT NULL,
  `contact` bigint(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `full_name`, `gender`, `age`, `address`, `contact`, `email`, `password`, `city`) VALUES
(1, 'Manish Hemwani', 'Male', 38, 'Flat no.203, D wing, Mohanpuram', 9658436662, 'manish@gmail.com', 'manish', 'Ambernath'),
(2, 'Anuja Shetye', 'Female', 44, 'Flat no. 701, A wing, Collector Colony', 7569953271, 'anuja@gmail.com', 'anuja', 'Chembur'),
(3, 'Kamlesh Gidwani', 'Male', 31, 'B.K no. 549,Shop no.202, Near post office', 9863325888, 'kamal@gmail.com', 'kamal', 'Santa Cruz'),
(4, 'Nisha Hemwani', 'Female', 36, 'Flat no. 404,C wing, Gokuldham Society', 7568954222, 'nisha@gmail.com', 'nisha', 'Goregaon'),
(5, 'Rohit Vadhya', 'Male', 43, 'Flat no.703, Paradise apartment,\nKurla', 9563244458, 'rohit@gmail.com', 'rohit', 'Kurla');

-- --------------------------------------------------------

--
-- Table structure for table `tax`
--

CREATE TABLE `tax` (
  `tax_id` int(11) NOT NULL,
  `cost_per_unit` int(11) NOT NULL,
  `meter_rent` int(11) NOT NULL,
  `service_charge` int(11) NOT NULL,
  `service_rate` int(11) NOT NULL,
  `fixed_rate` int(11) NOT NULL,
  `electricity_type` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tax`
--

INSERT INTO `tax` (`tax_id`, `cost_per_unit`, `meter_rent`, `service_charge`, `service_rate`, `fixed_rate`, `electricity_type`) VALUES
(1, 7, 32, 56, 12, 4, 'LT');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `bill_ibfk_1` (`connection_id`);

--
-- Indexes for table `connection`
--
ALTER TABLE `connection`
  ADD PRIMARY KEY (`connection_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `conn_request`
--
ALTER TABLE `conn_request`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `conn_request_ibfk_1` (`customer_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `tax`
--
ALTER TABLE `tax`
  ADD PRIMARY KEY (`tax_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `connection`
--
ALTER TABLE `connection`
  MODIFY `connection_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `conn_request`
--
ALTER TABLE `conn_request`
  MODIFY `request_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tax`
--
ALTER TABLE `tax`
  MODIFY `tax_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`connection_id`) REFERENCES `connection` (`connection_id`) ON UPDATE CASCADE;

--
-- Constraints for table `connection`
--
ALTER TABLE `connection`
  ADD CONSTRAINT `connection_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `conn_request`
--
ALTER TABLE `conn_request`
  ADD CONSTRAINT `conn_request_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
