--- Need executed manually by root
--- CREATE USER 'hbemployee'@'localhost' IDENTIFIED BY 'hbemployee';
--- GRANT ALL PRIVILEGES ON * . * TO 'hbemployee'@'localhost';

CREATE DATABASE IF NOT EXISTS `hb_employee_tracker`;
USE `hb_employee_tracker`;

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
