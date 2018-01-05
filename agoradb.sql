--
-- Database: `agoradb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL,
  `admin_fullname` varchar(50) NOT NULL,
  `admin_name` varchar(50) NOT NULL,
  `admin_password` varchar(50) NOT NULL,
  `admin_dateJoined` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `admin_email` varchar(50) NOT NULL,
  `admin_nickname` varchar(50) NOT NULL,
  `admin_imgPath` varchar(100) NOT NULL DEFAULT 'resources/images/profile/profiledefault.jpg',
  `city_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`admin_id`, `admin_fullname`, `admin_name`, `admin_password`, `admin_dateJoined`, `admin_email`, `admin_nickname`, `admin_imgPath`, `city_fk`) VALUES
(1, 'Edwin Olivarez', 'edwin', 'qwerty', '2017-12-29 04:11:09', 'edwin@email.com', 'Edwin', 'resources/images/profile/profiledefault.jpg', 10),
(2, 'Lani Cayetano', 'lani', 'qwerty', '2017-12-29 04:11:09', 'lani@email.com', 'Mama Lani', 'resources/images/profile/profiledefault.jpg', 16),
(3, 'Mar-Len Abigail S. Binay-Campos', 'abigail', 'qwerty', '2017-12-29 04:12:54', 'abigail@email.com', 'Abigail', 'resources/images/profile/profiledefault.jpg', 3),
(4, 'Antonino Calixto', 'antonino', 'qwerty', '2017-12-29 04:15:01', 'antonino@email.com', 'Antonino', 'resources/images/profile/profiledefault.jpg', 11),
(5, 'John Estrada', 'john', 'qwerty', '2017-12-29 04:16:25', 'john@email.com', 'Erap', 'resources/images/profile/profiledefault.jpg', 6),
(6, 'Herbert Bautista', 'herbert', 'qwerty', '2017-12-29 04:37:57', 'herbert@email.com', 'Herbert', 'resources/images/profile/profiledefault.jpg', 14);

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `city_id` int(11) NOT NULL,
  `city_name` varchar(50) NOT NULL,
  `city_imgpath` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`city_id`, `city_name`, `city_imgpath`) VALUES
(1, 'Caloocan City', 'resources/images/cities/caloocan.jpg'),
(2, 'Las Piñas City', 'resources/images/cities/laspinas.jpg'),
(3, 'Makati City', 'resources/images/cities/makati.jpg'),
(4, 'Malabon City', 'resources/images/cities/malabon.jpg'),
(5, 'Mandaluyong City', 'resources/images/cities/mandaluyong.jpg'),
(6, 'Manila City', 'resources/images/cities/manila.jpg'),
(7, 'Marikina City', 'resources/images/cities/marikina.jpg'),
(8, 'Muntinlupa City', 'resources/images/cities/muntinlupa.jpg'),
(9, 'Navotas City', 'resources/images/cities/navotas.jpg'),
(10, 'Parañaque City', 'resources/images/cities/paranaque.jpg'),
(11, 'Pasay City', 'resources/images/cities/pasay.jpg'),
(12, 'Pasig City', 'resources/images/cities/pasig.jpg'),
(13, 'Pateros Municipality', 'resources/images/cities/pateros.jpg'),
(14, 'Quezon City', 'resources/images/cities/quezoncity.jpg'),
(15, 'San Juan City', 'resources/images/cities/sanjuan.jpg'),
(16, 'Taguig City', 'resources/images/cities/taguig.jpg'),
(17, 'Valenzuela City', 'resources/images/cities/valenzuela.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `cities_nearby`
--

CREATE TABLE `cities_nearby` (
  `id` int(11) NOT NULL,
  `city_fk` varchar(50) NOT NULL,
  `city_nearby` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cities_nearby`
--

INSERT INTO `cities_nearby` (`id`, `city_fk`, `city_nearby`) VALUES
(1, 'Muntinlupa City', 'Las Piñas City'),
(2, 'Muntinlupa City', 'Parañaque City'),
(3, 'Las Piñas City', 'Muntinlupa City'),
(4, 'Las Piñas City', 'Parañaque City'),
(5, 'Parañaque City', 'Muntinlupa City'),
(6, 'Parañaque City', 'Las Piñas City'),
(7, 'Parañaque City', 'Pasay City'),
(8, 'Parañaque City', 'Taguig City'),
(10, 'Pasay City', 'Parañaque City'),
(11, 'Pasay City', 'Taguig City'),
(12, 'Pasay City', 'Makati City'),
(13, 'Taguig City', 'Parañaque City'),
(14, 'Taguig City', 'Pasay City'),
(15, 'Taguig City', 'Muntinlupa City'),
(16, 'Taguig City', 'Makati City'),
(17, 'Taguig City', 'Pateros Municipality'),
(18, 'Pasay City', 'Manila City'),
(19, 'Makati City', 'Pasay City'),
(20, 'Makati City', 'Taguig City'),
(21, 'Makati City', 'Pateros Municipality'),
(22, 'Makati City', 'Pasig City'),
(23, 'Makati City', 'Manila City'),
(24, 'Makati City', 'Mandaluyong City'),
(25, 'Pateros Municipality', 'Taguig City'),
(26, 'Pateros Municipality', 'Makati City'),
(27, 'Pateros Municipality', 'Pasig City'),
(28, 'Pasig City', 'Taguig City'),
(29, 'Pasig City', 'Pateros Municipality'),
(30, 'Pasig City', 'Makati City'),
(31, 'Pasig City', 'Mandaluyong City'),
(32, 'Pasig City', 'Quezon City'),
(33, 'Pasig City', 'Marikina City'),
(34, 'Mandaluyong City', 'Makati City'),
(35, 'Mandaluyong City', 'Manila City'),
(36, 'Mandaluyong City', 'San Juan City'),
(37, 'Mandaluyong City', 'Quezon City'),
(38, 'Mandaluyong City', 'Pasig City'),
(39, 'Manila City', 'Pasay City'),
(40, 'Manila City', 'Makati City'),
(41, 'Manila City', 'Mandaluyong City'),
(42, 'Manila City', 'San Juan City'),
(43, 'Manila City', 'Quezon City'),
(44, 'Manila City', 'Caloocan City'),
(45, 'Manila City', 'Navotas City'),
(46, 'San Juan City', 'Mandaluyong City'),
(47, 'San Juan City', 'Manila City'),
(48, 'San Juan City', 'Quezon City'),
(49, 'Quezon City', 'Marikina City'),
(50, 'Quezon City', 'Pasig City'),
(51, 'Quezon City', 'Mandaluyong City'),
(52, 'Quezon City', 'San Juan City'),
(53, 'Quezon City', 'Manila City'),
(54, 'Quezon City', 'Caloocan City'),
(55, 'Quezon City', 'Valenzuela City'),
(56, 'Marikina City', 'Pasig City'),
(57, 'Marikina City', 'Quezon City'),
(58, 'Caloocan City', 'Manila City'),
(59, 'Caloocan City', 'Quezon City'),
(60, 'Caloocan City', 'Navotas City'),
(61, 'Caloocan City', 'Malabon City'),
(62, 'Caloocan City', 'Valenzuela City'),
(63, 'Navotas City', 'Manila City'),
(64, 'Navotas City', 'Caloocan City'),
(67, 'Navotas City', 'Malabon City'),
(68, 'Malabon City', 'Navotas City'),
(69, 'Malabon City', 'Caloocan City'),
(70, 'Navotas City', 'Valenzuela City'),
(71, 'Valenzuela City', 'Malabon City'),
(72, 'Valenzuela City', 'Caloocan City');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `comments_id` int(11) NOT NULL,
  `comments_text` text NOT NULL,
  `comments_datePosted` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comments_mood` varchar(10) NOT NULL,
  `comments_upvotes` int(11) NOT NULL DEFAULT '0',
  `project_fk` int(11) NOT NULL,
  `user_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `project_id` int(11) NOT NULL,
  `project_name` varchar(100) NOT NULL,
  `project_description` text NOT NULL,
  `project_address` varchar(100) NOT NULL,
  `project_datePosted` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `project_imgpath` varchar(250) NOT NULL DEFAULT 'resource/images/projects/project.jpg',
  `mood_happy` int(11) NOT NULL DEFAULT '1',
  `mood_sad` int(11) NOT NULL DEFAULT '1',
  `mood_angry` int(11) NOT NULL DEFAULT '1',
  `mood_disgusted` int(11) NOT NULL DEFAULT '1',
  `mood_fearful` int(11) NOT NULL DEFAULT '1',
  `budget_materials` double(10,2) NOT NULL DEFAULT '0.00',
  `budget_operations` double(10,2) NOT NULL DEFAULT '0.00',
  `budget_management` double(10,2) NOT NULL DEFAULT '0.00',
  `budget_labor` double(10,2) NOT NULL DEFAULT '0.00',
  `budget_misc` double(10,2) NOT NULL DEFAULT '0.00',
  `admin_fk` int(11) NOT NULL,
  `city_fk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`project_id`, `project_name`, `project_description`, `project_address`, `project_datePosted`, `project_imgpath`, `mood_happy`, `mood_sad`, `mood_angry`, `mood_disgusted`, `mood_fearful`, `budget_materials`, `budget_operations`, `budget_management`, `budget_labor`, `budget_misc`, `admin_fk`, `city_fk`) VALUES
(1, 'The MRT 7 Extension', 'The MRT 7 Extension project was endorsed by the Regional Development Council for National Capital Region (RDC-NCR) Infrastructure Committee to the RDC-NCR Proper. The Project is assigned to the Department of Transportation and Communication (DOTC) in coordination with the MMDA, concerned local government units and the RDC-NCR. It will benefit thousands of Metro Manila commuters who traverse across the boundaries of Quezon City and North Luzon and is estimated to take 3 years to complete. The project consists of a 22-kilometer, six-lane asphalt road that will connect the North Luzon Expressway to Tala, San Jose del Monte, Bulacan. It will run in a northeast direction traversing Quezon City and part of Caloocan City and ending at San Jose del Monte, Bulacan. It will have 14 stations. MRT 7 will be operated by the Universal LRT Corporation (ULC) under a Built-Gradual Transfer-Operate-and- Maintain Scheme.', 'EDSA, Manila', '2017-12-30 02:16:54', 'resources/images/projects/mrt.jpg', 1, 2, 3, 1, 2, 1000000.00, 200000.00, 100000.00, 500000.00, 50000.00, 4, 11),
(2, 'NLEx-SLEx Connector Road', 'The project involves the construction and operation and maintenance (O&M) of a 8 km. 4-lane elevated expressway over the Philippine National Railway (PNR) right of way. It starts from C3 Road in Caloocan through Manila crossing Espana towards PUP, Sta. Mesa connecting Metro Manila Skyway Stage 3 (MMSS3). Once completed, the NLEX-SLEX Connector road is expected to decongest traffic in Metro Manila by providing an alternative to C-5 Road, Efipanio de Los Santos Avenue (EDSA), and other major thoroughfares, and cut the travel time between NLEX and SLEX to 15-20 minutes which today takes more than an hour.', 'Metro Manila', '2017-12-28 08:05:38', 'resources/images/projects/connector.jpg', 1, 1, 1, 1, 1, 1200000.00, 300000.00, 500000.00, 1000000.00, 200000.00, 5, 6),
(3, 'Pahayagan ng Bayan', 'The Pahayagan ng Bayan is public service announcement tool of the Agency where events, projects and activities of various national and local government units in Metro Manila as well as non-government or civic organizations. These are steel pylons, measuring 20 ft. high and 6 ft. wide, strategically-placed on different areas in Metro Manila. The advertisement of the events, printed on tarpaulins, are posted on the pylons for a maximum of three months. As of Dec. 1, 2016, there are 114 Pahayagan ng Bayan pylons on the stretch of EDSA, C-5, Roxas Blvd., NAIA Road, Ortigas, Marcos Highway, Magsaysay Blvd. and Quezon Avenue. The maintenance and supervision of the use of the Pahayagan ng Bayan is under the Public Information Office of the MMDA.', 'EDSA, C-5, Roxas Blvd., NAIA Road, Ortigas, Marcos Highway, Magsaysay Blvd. and Quezon Avenue', '2017-12-29 04:43:46', 'resources/images/projects/pahayagan.jpg', 1, 2, 1, 1, 1, 100000.00, 200000.00, 150000.00, 50000.00, 50000.00, 6, 14),
(4, 'PPP for School Infrastructure', 'The PSIP Phase II involves the designing, financing, and constructing of 4,370 one-storey, two-storey, three-storey and four-storey classrooms, including furniture, fixtures, and toilets in 1,895 public schools in 6 regions (Regions I, II, III, X, CAR, and CARAGA). The project will involve the design, financing and construction of about 9,303 one-storey and two-storey classrooms, including furniture and fixtures, in various sites in Region I, III, and IV-A. The project aims to supplement the current program of the Department of Education in reducing classroom backlog.', 'Pasay City', '2017-12-27 11:09:30', 'resources/images/projects/school.jpg', 1, 1, 1, 1, 1, 1300000.00, 1000000.00, 900000.00, 1000000.00, 300000.00, 2, 16),
(5, 'Intensified Enforcement of Smoke Free Environment Policy', 'MMDA is a recipient of a grant from The International Union against Tuberculosis and Lung Diseases, a not-for-profit scientific organization working in the field of lung health worldwide and which manages grants programs for tobacco control. The goal of the project is to ensure the enforcement of local issuances related to the reduction of tobacco use in Metro Manila based on the WHO Framework Convention on Tobacco Control, RA 9211 (Tobacco Regulations Act of 2003), and the Civil Service Commission Memorandum Circular No. 17, Series of 2009 on smoking prohibitions. Various strategies on information, education and communication campaigns, advocacy, policy development, implementation and enforcement of tobacco control are employed to ensure that Smoke Free Environment policy is put into effect and sustained in the cities. The on – going project started in July 2010. Activities are conducted in partnership with the 17 Local Government Units (LGUs) of Metropolitan Manila. These are coordinated with the other national agencies and non - government organizations working on the enforcement of tobacco control. This project is managed by the Health, Public Safety and Environmental Protection Office, with The Union Project Management Team.', 'Parañaque City', '2017-12-28 07:36:15', 'resources/images/projects/smokefree.jpg', 1, 2, 2, 1, 1, 200000.00, 100000.00, 500000.00, 1000000.00, 100000.00, 1, 10),
(6, 'Subway Tunnel Project', 'Transportation has been always an issue here in the Philippines. former Vice President Jejomar Binay started the very first subway here in the Philippines. Analysts Suggests to halt the project for thorough studies. However Jejomar Binay gave no statement regarding this and as of now no progress can be seen by the media. The project was held questionable by Leni Robredo because of the liquidation she received last Tuesday, December 14,2017.', 'Makati City', '2017-12-27 04:36:15', 'resources/images/projects/subway.jpg', 1, 1, 1, 1, 1, 1400000.00, 700000.00, 1000000.00, 1200000.00, 300000.00, 3, 3),
(7, 'Project Title', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Project, NCR, Philippines', '2017-12-28 00:00:00', 'resources/images/projects/housing.jpg', 1, 1, 1, 1, 1, 1000000.00, 1000000.00, 1000000.00, 1000000.00, 1000000.00, 5, 6);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_fname` varchar(50) NOT NULL,
  `user_lname` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_dateJoined` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_email` varchar(50) NOT NULL,
  `user_nickname` varchar(50) NOT NULL,
  `user_imgpath` varchar(50) NOT NULL DEFAULT 'resource/images/profile/profiledefault.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `user_fname`, `user_lname`, `user_password`, `user_dateJoined`, `user_email`, `user_nickname`, `user_imgpath`) VALUES
(4, 'linksparrow', 'Jade Ericson', 'Adriano', 'password', '2017-12-27 17:22:21', 'adrianojason12@gmail.com', 'Jade Ericson', 'resources/images/profile/profiledefault.jpg'),
(5, 'test', 'ImTest', 'Test', 'me', '2017-12-27 19:13:38', 'test@email.com', 'ImTest', 'resources/images/profile/profiledefault.jpg'),
(6, 'josie', 'Josephine', 'Adriano', 'mum', '2017-12-28 00:24:31', 'josie@email.com', 'Josephine', 'resources/images/profile/profiledefault.jpg'),
(7, 'yohan', 'Johanna April', 'Adriano', 'daybaduduy', '2017-12-28 14:29:54', 'yohan@email.com', 'Johanna April', 'resources/images/profile/profiledefault.jpg'),
(8, 'jason', 'Jason', 'Adri', 'password', '2017-12-29 19:15:16', 'jason@email.com', 'Jason', 'resources/images/profile/profiledefault.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users_has_cities`
--

CREATE TABLE `users_has_cities` (
  `id` int(11) NOT NULL,
  `user_fk` varchar(50) NOT NULL,
  `city_fk` varchar(50) NOT NULL,
  `is_home` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users_has_cities`
--

INSERT INTO `users_has_cities` (`id`, `user_fk`, `city_fk`, `is_home`) VALUES
(1, 'josie', 'Parañaque City', 1),
(2, 'josie', 'Pasay City', 0),
(4, 'linksparrow', 'Manila City', 1),
(5, 'linksparrow', 'Parañaque City', 0),
(6, 'test', 'Manila City', 1),
(7, 'yohan', 'Manila City', 1),
(8, 'yohan', 'Parañaque City', 0),
(9, 'yohan', 'Pasay City', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_has_mood`
--

CREATE TABLE `user_has_mood` (
  `id` int(11) NOT NULL,
  `user_fk` int(11) NOT NULL,
  `project_fk` int(11) NOT NULL,
  `user_mood` int(6) NOT NULL DEFAULT '5'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_has_mood`
--

INSERT INTO `user_has_mood` (`id`, `user_fk`, `project_fk`, `user_mood`) VALUES
(1, 4, 1, 1),
(2, 4, 2, 5),
(3, 4, 5, 1),
(4, 6, 5, 0),
(5, 6, 1, 0),
(6, 6, 2, 5),
(7, 6, 7, 5),
(8, 5, 1, 1),
(9, 7, 1, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`admin_id`),
  ADD UNIQUE KEY `admin_name` (`admin_name`),
  ADD KEY `admin_fullname` (`admin_fullname`),
  ADD KEY `city_fk` (`city_fk`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`city_id`),
  ADD UNIQUE KEY `city_name` (`city_name`);

--
-- Indexes for table `cities_nearby`
--
ALTER TABLE `cities_nearby`
  ADD PRIMARY KEY (`id`),
  ADD KEY `city_fk` (`city_fk`),
  ADD KEY `city_nearby` (`city_nearby`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comments_id`),
  ADD KEY `project_fk` (`project_fk`),
  ADD KEY `user_fk` (`user_fk`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`project_id`),
  ADD KEY `admin_fk` (`admin_fk`),
  ADD KEY `city_fk` (`city_fk`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_email` (`user_email`),
  ADD UNIQUE KEY `user_name_2` (`user_name`),
  ADD KEY `user_lname` (`user_lname`),
  ADD KEY `user_name` (`user_name`);

--
-- Indexes for table `users_has_cities`
--
ALTER TABLE `users_has_cities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_fk` (`user_fk`),
  ADD KEY `city_fk` (`city_fk`);

--
-- Indexes for table `user_has_mood`
--
ALTER TABLE `user_has_mood`
  ADD PRIMARY KEY (`id`),
  ADD KEY `project_fk` (`project_fk`),
  ADD KEY `user_pk` (`user_fk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
  MODIFY `city_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `cities_nearby`
--
ALTER TABLE `cities_nearby`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;
--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `comments_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `users_has_cities`
--
ALTER TABLE `users_has_cities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `user_has_mood`
--
ALTER TABLE `user_has_mood`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `admins`
--
ALTER TABLE `admins`
  ADD CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`city_fk`) REFERENCES `cities` (`city_id`) ON UPDATE CASCADE;

--
-- Constraints for table `cities_nearby`
--
ALTER TABLE `cities_nearby`
  ADD CONSTRAINT `cities_nearby_ibfk_1` FOREIGN KEY (`city_fk`) REFERENCES `cities` (`city_name`),
  ADD CONSTRAINT `cities_nearby_ibfk_2` FOREIGN KEY (`city_nearby`) REFERENCES `cities` (`city_name`);

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`project_fk`) REFERENCES `projects` (`project_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_fk`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`admin_fk`) REFERENCES `admins` (`admin_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `projects_ibfk_2` FOREIGN KEY (`city_fk`) REFERENCES `admins` (`city_fk`);

--
-- Constraints for table `users_has_cities`
--
ALTER TABLE `users_has_cities`
  ADD CONSTRAINT `users_has_cities_ibfk_1` FOREIGN KEY (`user_fk`) REFERENCES `users` (`user_name`),
  ADD CONSTRAINT `users_has_cities_ibfk_2` FOREIGN KEY (`city_fk`) REFERENCES `cities` (`city_name`);

--
-- Constraints for table `user_has_mood`
--
ALTER TABLE `user_has_mood`
  ADD CONSTRAINT `user_has_mood_ibfk_2` FOREIGN KEY (`project_fk`) REFERENCES `projects` (`project_id`),
  ADD CONSTRAINT `user_has_mood_ibfk_3` FOREIGN KEY (`user_fk`) REFERENCES `users` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
