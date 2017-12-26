-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 26, 2017 at 11:30 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `agoradb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL,
  `admin_name` varchar(50) NOT NULL,
  `admin_password` varchar(50) NOT NULL,
  `admin_dateJoined` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `admin_email` varchar(50) NOT NULL,
  `admin_nickname` varchar(50) NOT NULL,
  `city_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `budgets`
--

CREATE TABLE `budgets` (
  `budget_id` int(11) NOT NULL,
  `budget_materials` decimal(10,0) NOT NULL,
  `budget_operations` decimal(10,0) NOT NULL,
  `budget_management` decimal(10,0) NOT NULL,
  `budget_labor` decimal(10,0) NOT NULL,
  `budget_misc` decimal(10,0) NOT NULL,
  `project_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `city_id` int(11) NOT NULL,
  `city_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `moods`
--

CREATE TABLE `moods` (
  `prmood_id` int(11) NOT NULL,
  `prmood_happy` int(11) NOT NULL,
  `prmood_sad` int(11) NOT NULL,
  `prmood_angry` int(11) NOT NULL,
  `prmood_disgusted` int(11) NOT NULL,
  `prmood_fearful` int(11) NOT NULL,
  `project_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL,
  `post_name` varchar(50) NOT NULL,
  `post_description` text NOT NULL,
  `post_datePosted` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `post_likes` int(11) NOT NULL,
  `post_dislikes` int(11) NOT NULL,
  `user_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `post_comments`
--

CREATE TABLE `post_comments` (
  `pocomments_id` int(11) NOT NULL,
  `pocomments_text` text NOT NULL,
  `pocomments_datePosted` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `post_fk` int(11) NOT NULL,
  `user_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `project_id` int(11) NOT NULL,
  `project_name` varchar(50) NOT NULL,
  `project_description` text NOT NULL,
  `project_address` varchar(100) NOT NULL,
  `project_datePosted` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `admin_fk` int(11) NOT NULL,
  `city_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `project_comments`
--

CREATE TABLE `project_comments` (
  `prcomments_id` int(11) NOT NULL,
  `prcomments_text` text NOT NULL,
  `prcomments_datePosted` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `project_fk` int(11) NOT NULL,
  `user_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_dateJoined` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_email` varchar(50) NOT NULL,
  `user_nickname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users_has_cities`
--

CREATE TABLE `users_has_cities` (
  `uhc_id` int(11) NOT NULL,
  `user_fk` int(11) NOT NULL,
  `city_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`admin_id`),
  ADD UNIQUE KEY `admin_name` (`admin_name`),
  ADD KEY `city_fk` (`city_fk`);

--
-- Indexes for table `budgets`
--
ALTER TABLE `budgets`
  ADD PRIMARY KEY (`budget_id`),
  ADD KEY `project_fk` (`project_fk`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`city_id`),
  ADD UNIQUE KEY `city_name` (`city_name`);

--
-- Indexes for table `moods`
--
ALTER TABLE `moods`
  ADD PRIMARY KEY (`prmood_id`),
  ADD KEY `project_fk` (`project_fk`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`post_id`),
  ADD KEY `user_fk` (`user_fk`);

--
-- Indexes for table `post_comments`
--
ALTER TABLE `post_comments`
  ADD PRIMARY KEY (`pocomments_id`),
  ADD KEY `post_fk` (`post_fk`),
  ADD KEY `user_fk` (`user_fk`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`project_id`),
  ADD KEY `admin_fk` (`admin_fk`),
  ADD KEY `city_fk` (`city_fk`);

--
-- Indexes for table `project_comments`
--
ALTER TABLE `project_comments`
  ADD PRIMARY KEY (`prcomments_id`),
  ADD KEY `project_fk` (`project_fk`),
  ADD KEY `user_fk` (`user_fk`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_name` (`user_name`);

--
-- Indexes for table `users_has_cities`
--
ALTER TABLE `users_has_cities`
  ADD PRIMARY KEY (`uhc_id`),
  ADD KEY `user_fk` (`user_fk`),
  ADD KEY `city_fk` (`city_fk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `budgets`
--
ALTER TABLE `budgets`
  MODIFY `budget_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
  MODIFY `city_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `moods`
--
ALTER TABLE `moods`
  MODIFY `prmood_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `post_comments`
--
ALTER TABLE `post_comments`
  MODIFY `pocomments_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `project_comments`
--
ALTER TABLE `project_comments`
  MODIFY `prcomments_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users_has_cities`
--
ALTER TABLE `users_has_cities`
  MODIFY `uhc_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `admins`
--
ALTER TABLE `admins`
  ADD CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`city_fk`) REFERENCES `cities` (`city_id`) ON UPDATE CASCADE;

--
-- Constraints for table `budgets`
--
ALTER TABLE `budgets`
  ADD CONSTRAINT `budgets_ibfk_1` FOREIGN KEY (`project_fk`) REFERENCES `projects` (`project_id`) ON UPDATE CASCADE;

--
-- Constraints for table `moods`
--
ALTER TABLE `moods`
  ADD CONSTRAINT `moods_ibfk_1` FOREIGN KEY (`project_fk`) REFERENCES `projects` (`project_id`) ON UPDATE CASCADE;

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_fk`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE;

--
-- Constraints for table `post_comments`
--
ALTER TABLE `post_comments`
  ADD CONSTRAINT `post_comments_ibfk_1` FOREIGN KEY (`post_fk`) REFERENCES `posts` (`post_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `post_comments_ibfk_2` FOREIGN KEY (`user_fk`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE;

--
-- Constraints for table `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`admin_fk`) REFERENCES `admins` (`admin_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `projects_ibfk_2` FOREIGN KEY (`city_fk`) REFERENCES `cities` (`city_id`) ON UPDATE CASCADE;

--
-- Constraints for table `project_comments`
--
ALTER TABLE `project_comments`
  ADD CONSTRAINT `project_comments_ibfk_1` FOREIGN KEY (`project_fk`) REFERENCES `projects` (`project_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `project_comments_ibfk_2` FOREIGN KEY (`user_fk`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE;

--
-- Constraints for table `users_has_cities`
--
ALTER TABLE `users_has_cities`
  ADD CONSTRAINT `users_has_cities_ibfk_1` FOREIGN KEY (`user_fk`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `users_has_cities_ibfk_2` FOREIGN KEY (`city_fk`) REFERENCES `cities` (`city_id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
