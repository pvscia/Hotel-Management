-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2023 at 03:57 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `amenities_req`
--

CREATE TABLE `amenities_req` (
  `name` varchar(255) NOT NULL,
  `roomNumber` int(11) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `guestID` varchar(255) NOT NULL,
  `roomNumber` int(11) NOT NULL,
  `checkIn` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`guestID`, `roomNumber`, `checkIn`) VALUES
('U4553', 101, '2023-06-09');

-- --------------------------------------------------------

--
-- Table structure for table `facility`
--

CREATE TABLE `facility` (
  `name` varchar(255) NOT NULL,
  `capacity` int(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `custCount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `facility`
--

INSERT INTO `facility` (`name`, `capacity`, `status`, `location`, `custCount`) VALUES
('Gym', 75, 'Available', '3rd Floor', 0),
('Playground', 30, 'Available', '11th floor', 0),
('Rooftop', 200, 'Available', 'Rooftop', 0),
('Spa', 15, 'Available', '7th Floor', 0),
('Swimming Pool', 75, 'Available', 'Ground Floor', 0);

-- --------------------------------------------------------

--
-- Table structure for table `facility_book`
--

CREATE TABLE `facility_book` (
  `roomNo` int(11) NOT NULL,
  `fac_name` varchar(255) NOT NULL,
  `no_people` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `name` varchar(255) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`name`, `stock`) VALUES
('Extra Bed', 29),
('Shampoo', 60),
('Slippers', 150),
('Soap', 300),
('Tissue', 100),
('Toothbrush', 250),
('Towel', 140);

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE `rating` (
  `date` date NOT NULL,
  `complaint` varchar(255) NOT NULL,
  `roomNumber` int(11) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `roomNumber` int(11) NOT NULL,
  `bedType` varchar(255) NOT NULL,
  `roomType` varchar(255) NOT NULL,
  `roomView` varchar(255) NOT NULL,
  `availability` varchar(255) NOT NULL,
  `roomSize` int(11) NOT NULL,
  `roomCapacity` int(11) NOT NULL,
  `roomPrice` int(11) NOT NULL,
  `do_not_disturb` tinyint(1) NOT NULL,
  `wake_up_call` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomNumber`, `bedType`, `roomType`, `roomView`, `availability`, `roomSize`, `roomCapacity`, `roomPrice`, `do_not_disturb`, `wake_up_call`) VALUES
(101, 'Single Bed', 'Single', 'Ocean', 'Booked', 24, 1, 400000, 0, 0),
(102, 'Double Bed', 'Double', 'City', 'Available', 21, 2, 300000, 0, 0),
(103, 'Twin Bed', 'Single', 'Mountain', 'Available', 22, 1, 350000, 0, 0),
(104, 'Single Bed', 'Single', 'Ocean', 'Available', 30, 1, 350000, 0, 0),
(105, 'Double Bed', 'Double', 'City', 'Available', 20, 2, 350000, 0, 0),
(106, 'Twin Bed', 'Single', 'Mountain', 'Available', 23, 1, 350000, 0, 0),
(107, 'Single Bed', 'Single', 'Ocean', 'Available', 24, 1, 350000, 0, 0),
(108, 'Double Bed', 'Double', 'City', 'Available', 22, 2, 300000, 0, 0),
(109, 'Twin Bed', 'Single', 'Mountain', 'Available', 29, 1, 300000, 0, 0),
(110, 'Single Bed', 'Single', 'Ocean', 'Available', 26, 1, 300000, 0, 0),
(111, 'Double Bed', 'Double', 'City', 'Available', 24, 2, 350000, 0, 0),
(112, 'Twin Bed', 'Single', 'Mountain', 'Available', 23, 1, 400000, 0, 0),
(113, 'Single Bed', 'Single', 'Ocean', 'Available', 23, 1, 400000, 0, 0),
(114, 'Double Bed', 'Double', 'City', 'Available', 28, 2, 350000, 0, 0),
(115, 'Twin Bed', 'Single', 'Mountain', 'Available', 29, 1, 300000, 0, 0),
(116, 'Single Bed', 'Single', 'Ocean', 'Available', 30, 1, 400000, 0, 0),
(117, 'Double Bed', 'Double', 'City', 'Available', 22, 2, 350000, 0, 0),
(118, 'Twin Bed', 'Single', 'Mountain', 'Available', 20, 1, 300000, 0, 0),
(119, 'Single Bed', 'Single', 'Ocean', 'Available', 28, 1, 300000, 0, 0),
(120, 'Double Bed', 'Double', 'City', 'Available', 25, 2, 350000, 0, 0),
(201, 'Twin Bed', 'Single', 'Mountain', 'Available', 20, 1, 350000, 0, 0),
(202, 'Single Bed', 'Single', 'Ocean', 'Available', 29, 1, 300000, 0, 0),
(203, 'Double Bed', 'Double', 'City', 'Available', 23, 2, 350000, 0, 0),
(204, 'Twin Bed', 'Single', 'Mountain', 'Available', 21, 1, 400000, 0, 0),
(205, 'Single Bed', 'Single', 'Ocean', 'Available', 28, 1, 300000, 0, 0),
(206, 'Double Bed', 'Double', 'City', 'Available', 24, 2, 350000, 0, 0),
(207, 'Twin Bed', 'Single', 'Mountain', 'Available', 25, 1, 400000, 0, 0),
(208, 'Single Bed', 'Single', 'Ocean', 'Available', 24, 1, 400000, 0, 0),
(209, 'Double Bed', 'Double', 'City', 'Available', 27, 2, 350000, 0, 0),
(210, 'Twin Bed', 'Single', 'Mountain', 'Available', 21, 1, 350000, 0, 0),
(211, 'Single Bed', 'Single', 'Ocean', 'Available', 28, 1, 400000, 0, 0),
(212, 'Double Bed', 'Double', 'City', 'Available', 22, 2, 350000, 0, 0),
(213, 'Twin Bed', 'Single', 'Mountain', 'Available', 29, 1, 400000, 0, 0),
(214, 'Single Bed', 'Single', 'Ocean', 'Available', 25, 1, 400000, 0, 0),
(215, 'Double Bed', 'Double', 'City', 'Available', 20, 2, 300000, 0, 0),
(216, 'Twin Bed', 'Single', 'Mountain', 'Available', 25, 1, 400000, 0, 0),
(217, 'Single Bed', 'Single', 'Ocean', 'Available', 26, 1, 400000, 0, 0),
(218, 'Double Bed', 'Double', 'City', 'Available', 26, 2, 400000, 0, 0),
(219, 'Twin Bed', 'Single', 'Mountain', 'Available', 20, 1, 300000, 0, 0),
(220, 'Single Bed', 'Single', 'Ocean', 'Available', 24, 1, 300000, 0, 0),
(301, 'Double Bed', 'Double', 'City', 'Available', 28, 2, 300000, 0, 0),
(302, 'Twin Bed', 'Single', 'Mountain', 'Available', 28, 1, 400000, 0, 0),
(303, 'Single Bed', 'Single', 'Ocean', 'Available', 23, 1, 350000, 0, 0),
(304, 'Double Bed', 'Double', 'City', 'Available', 26, 2, 400000, 0, 0),
(305, 'Twin Bed', 'Single', 'Mountain', 'Available', 30, 1, 350000, 0, 0),
(306, 'Single Bed', 'Single', 'Ocean', 'Available', 29, 1, 400000, 0, 0),
(307, 'Double Bed', 'Double', 'City', 'Available', 24, 2, 300000, 0, 0),
(308, 'Twin Bed', 'Single', 'Mountain', 'Available', 25, 1, 400000, 0, 0),
(309, 'Single Bed', 'Single', 'Ocean', 'Available', 22, 1, 400000, 0, 0),
(310, 'Double Bed', 'Double', 'City', 'Available', 25, 2, 400000, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `gender`, `username`, `email`, `password`, `role`) VALUES
('U0000', 'admin', 'admin', 'admin', 'admin@gmail.com', 'Admin1234', 'admin'),
('U4553', 'patricia', 'Female', 'pvscia', 'patrice@gmail.com', 'user1234', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `facility`
--
ALTER TABLE `facility`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomNumber`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
