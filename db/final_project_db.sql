DROP DATABASE `final_project`;
CREATE DATABASE `final_project`;
USE final_project;

DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL DEFAULT '',
  `last_name` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `item_id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` int unsigned NOT NULL AUTO_INCREMENT,
  `person_id` int unsigned NOT NULL,
  `order_date` datetime NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `person_id` (`person_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`person_id`) REFERENCES `people` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `order_details_id` int unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int unsigned NOT NULL,
  `item_id` int unsigned NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`order_details_id`),
  KEY `order_id` (`order_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

INSERT INTO `people` VALUES (1,'George','Williams','will@gmail.com'),(2,'John','Doe','johndoe@example.com'),(3,'Johnny','Sholl','johnnysh@example.com');
INSERT INTO `items` VALUES (1,'hp_laptop'),(2,'dell_laptop'),(3,'lenovo_laptop'),(4,'hp_screen');
INSERT INTO `orders` VALUES (1,1,'2023-06-19 00:00:00'),(2,1,'2023-05-17 00:00:00'),(3,3,'2023-05-18 00:00:00'),(4,3,'2023-05-17 00:00:00');
INSERT INTO `order_details` VALUES (1,1,2,2),(2,2,1,1),(3,2,2,1);

