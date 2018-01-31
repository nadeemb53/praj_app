-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2016 at 10:59 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `prajwalan`
--
CREATE DATABASE `prajwalan` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `prajwalan`;

-- --------------------------------------------------------

--
-- Table structure for table `contacts`
--

CREATE TABLE IF NOT EXISTS `contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `committeeid` varchar(20) NOT NULL,
  `committeename` varchar(50) NOT NULL,
  `post` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `mobno` varchar(20) NOT NULL,
  `emailid` varchar(50) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `contacts`
--

INSERT INTO `contacts` (`id`, `committeeid`, `committeename`, `post`, `name`, `mobno`, `emailid`, `date`) VALUES
(1, 'dr', 'death race', 'convenor', 'Vaibhav Bende', '814 982 7076', 'fdfsddQ@ccc', '2016-01-05'),
(2, 'dr', 'death race', 'co-convenor', 'Sanjay Padwal', '997 596 8602', 'dsdsd!@dfdf', '2016-01-05');

-- --------------------------------------------------------

--
-- Table structure for table `downloads`
--

CREATE TABLE IF NOT EXISTS `downloads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eventid` varchar(20) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `name` varchar(300) NOT NULL,
  `url` varchar(1000) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE IF NOT EXISTS `events` (
  `eventid` varchar(30) NOT NULL,
  `eventname` varchar(50) NOT NULL,
  `eventinfo` varchar(1000) NOT NULL,
  `eventfees` varchar(20) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`eventid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`eventid`, `eventname`, `eventinfo`, `eventfees`, `date`) VALUES
('dr', 'Death Race', 'The simple manual robot has to complete the race in minimum time overcoming the obstacles.', '20000', '2016-01-05'),
('lf', 'Grid Solver', 'Team must build an autonomous robot which will reach the END point from the START point solving the GRID by the principle of line follower.', '1000', '2016-01-05'),
('robosoccer', 'Robo Soccer', 'The simple manual robot has to make maximum goals while competing with opponent robot.  TASK: Two teams will play the soccer at a time. 3 min. will be given for this task. If in case tie up happens then penalty shootout of three goals will be given. You can’t pick the ball or hold the ball or can’t fight with each other if a case occurs then the team will be disqualified. The diameter of ball is between 7cm to 10cm.', '2000', '2016-01-05');

-- --------------------------------------------------------

--
-- Table structure for table `rules`
--

CREATE TABLE IF NOT EXISTS `rules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eventid` varchar(20) NOT NULL,
  `rule` varchar(1000) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `rules`
--

INSERT INTO `rules` (`id`, `eventid`, `rule`, `date`) VALUES
(1, 'robosoccer', 'Each team may consist of maximum 4 members with entry fees of Rs 400/-.', '2016-01-05'),
(2, 'robosoccer', 'A team may consist of students from different colleges and each member should bring their college ID proof.', '2016-01-05'),
(3, 'robosoccer', '32V variable power supply will be provided by organizers.', '2016-01-05'),
(4, 'robosoccer', 'Organizers decision will be the final decision.', '2016-01-05'),
(5, 'lf', 'Maximum 3 trials will be given to each team and each trial will be given maximum 3 minutes.', '2016-01-05'),
(6, 'lf', 'In case if no team reaches the END, the winner will be decided according to the performance of robot.', '2016-01-05'),
(7, 'dr', 'Robot should have minimum 20 ft. wire for smooth working.', '2016-01-05'),
(8, 'dr', 'he size of robot should be less than 30*30*30 cm and weight should be less than 5 kg.', '2016-01-05');

-- --------------------------------------------------------

--
-- Table structure for table `updates`
--

CREATE TABLE IF NOT EXISTS `updates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `update` varchar(500) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
