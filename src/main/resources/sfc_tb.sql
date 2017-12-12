/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : sfc_tb

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2017-11-28 17:36:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cinema_tb
-- ----------------------------
DROP TABLE IF EXISTS `cinema_tb`;
CREATE TABLE `cinema_tb` (
  `cinema_id` int(11) NOT NULL AUTO_INCREMENT,
  `cinema_name` varchar(100) DEFAULT NULL,
  `cinema_describe` text,
  `cinema_address` varchar(200) DEFAULT NULL,
  `cinema_tel` varchar(100) DEFAULT NULL,
  `cinema_business_hours` varchar(100) DEFAULT NULL,
  `cinema_longitude` varchar(20) DEFAULT NULL,
  `cinema_latitude` varchar(20) DEFAULT NULL,
  `cinema_img` varchar(200) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cinema_id`),
  KEY `city_id` (`city_id`),
  CONSTRAINT `cinema_tb_ibfk_1` FOREIGN KEY (`city_id`) REFERENCES `city_tb` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cinema_tb
-- ----------------------------

-- ----------------------------
-- Table structure for city_tb
-- ----------------------------
DROP TABLE IF EXISTS `city_tb`;
CREATE TABLE `city_tb` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(100) DEFAULT NULL,
  `city_zip_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city_tb
-- ----------------------------

-- ----------------------------
-- Table structure for coupon_tb
-- ----------------------------
DROP TABLE IF EXISTS `coupon_tb`;
CREATE TABLE `coupon_tb` (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_no` varchar(16) DEFAULT NULL,
  `coupon_money` int(11) DEFAULT NULL,
  `coupon_min_money` int(11) DEFAULT NULL,
  `coupon_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `cinema_id` int(11) DEFAULT NULL,
  `coupon_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`coupon_id`),
  UNIQUE KEY `coupon_no` (`coupon_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coupon_tb
-- ----------------------------

-- ----------------------------
-- Table structure for hall_tb
-- ----------------------------
DROP TABLE IF EXISTS `hall_tb`;
CREATE TABLE `hall_tb` (
  `hall_id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_name` varchar(100) DEFAULT NULL,
  `hall_screen_type` varchar(50) DEFAULT NULL,
  `hall_describe` varchar(100) DEFAULT NULL,
  `hall_max_row` int(11) DEFAULT NULL,
  `hall_max_col` int(11) DEFAULT NULL,
  `cinema_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`hall_id`),
  KEY `cinema_id` (`cinema_id`),
  CONSTRAINT `hall_tb_ibfk_1` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_tb` (`cinema_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hall_tb
-- ----------------------------

-- ----------------------------
-- Table structure for movie_tb
-- ----------------------------
DROP TABLE IF EXISTS `movie_tb`;
CREATE TABLE `movie_tb` (
  `movie_id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(100) DEFAULT NULL,
  `movie_play_time` int(11) DEFAULT NULL,
  `movie_publish_time` datetime DEFAULT NULL,
  `movie_director` varchar(100) DEFAULT NULL,
  `movie_country` varchar(100) DEFAULT NULL,
  `movie_language` varchar(100) DEFAULT NULL,
  `movie_actor` text,
  `movie_type` varchar(100) DEFAULT NULL,
  `movie_poster` varchar(200) DEFAULT NULL,
  `min_price` int(11) DEFAULT NULL,
  `min_vip_price` int(11) DEFAULT NULL,
  PRIMARY KEY (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie_tb
-- ----------------------------

-- ----------------------------
-- Table structure for order_seat_tb
-- ----------------------------
DROP TABLE IF EXISTS `order_seat_tb`;
CREATE TABLE `order_seat_tb` (
  `os_id` int(11) NOT NULL AUTO_INCREMENT,
  `os_price` int(11) DEFAULT NULL,
  `seat_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`os_id`),
  KEY `order_id` (`order_id`),
  KEY `seat_id` (`seat_id`),
  CONSTRAINT `order_seat_tb_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order_tb` (`order_id`),
  CONSTRAINT `order_seat_tb_ibfk_2` FOREIGN KEY (`seat_id`) REFERENCES `seat_tb` (`seat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_seat_tb
-- ----------------------------

-- ----------------------------
-- Table structure for order_tb
-- ----------------------------
DROP TABLE IF EXISTS `order_tb`;
CREATE TABLE `order_tb` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `order_pay_time` datetime DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `plan_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  KEY `plan_id` (`plan_id`),
  CONSTRAINT `order_tb_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tb` (`user_id`),
  CONSTRAINT `order_tb_ibfk_2` FOREIGN KEY (`plan_id`) REFERENCES `plan_tb` (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_tb
-- ----------------------------

-- ----------------------------
-- Table structure for permission_tb
-- ----------------------------
DROP TABLE IF EXISTS `permission_tb`;
CREATE TABLE `permission_tb` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(100) NOT NULL,
  `permission_describe` varchar(200) DEFAULT NULL,
  `permission_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission_tb
-- ----------------------------

-- ----------------------------
-- Table structure for plan_tb
-- ----------------------------
DROP TABLE IF EXISTS `plan_tb`;
CREATE TABLE `plan_tb` (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_price` int(11) DEFAULT NULL,
  `plan_start_time` datetime DEFAULT NULL,
  `plan_data` date DEFAULT NULL,
  `plan_time` time DEFAULT NULL,
  `plan_language` varchar(50) DEFAULT NULL,
  `plan_screen_type` varchar(50) DEFAULT NULL,
  `plan_discount` int(11) DEFAULT NULL,
  `plan_status` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL,
  `hall_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  KEY `movie_id` (`movie_id`),
  KEY `hall_id` (`hall_id`),
  CONSTRAINT `plan_tb_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movie_tb` (`movie_id`),
  CONSTRAINT `plan_tb_ibfk_2` FOREIGN KEY (`hall_id`) REFERENCES `hall_tb` (`hall_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plan_tb
-- ----------------------------

-- ----------------------------
-- Table structure for role_permission_tb
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_tb`;
CREATE TABLE `role_permission_tb` (
  `rp_id` int(11) NOT NULL AUTO_INCREMENT,
  `rp_role_id` int(11) DEFAULT NULL,
  `rp_pms_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rp_id`),
  KEY `rp_role_id` (`rp_role_id`),
  KEY `rp_pms_id` (`rp_pms_id`),
  CONSTRAINT `role_permission_tb_ibfk_1` FOREIGN KEY (`rp_role_id`) REFERENCES `role_tb` (`role_id`),
  CONSTRAINT `role_permission_tb_ibfk_2` FOREIGN KEY (`rp_pms_id`) REFERENCES `permission_tb` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission_tb
-- ----------------------------

-- ----------------------------
-- Table structure for role_tb
-- ----------------------------
DROP TABLE IF EXISTS `role_tb`;
CREATE TABLE `role_tb` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `role_describe` varchar(200) DEFAULT NULL,
  `role_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_tb
-- ----------------------------

-- ----------------------------
-- Table structure for seat_tb
-- ----------------------------
DROP TABLE IF EXISTS `seat_tb`;
CREATE TABLE `seat_tb` (
  `seat_id` int(11) NOT NULL AUTO_INCREMENT,
  `seat_row` int(11) DEFAULT NULL,
  `seat_col` int(11) DEFAULT NULL,
  `hall_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
  UNIQUE KEY `seat_id` (`seat_id`,`hall_id`),
  KEY `hall_id` (`hall_id`),
  CONSTRAINT `seat_tb_ibfk_1` FOREIGN KEY (`hall_id`) REFERENCES `hall_tb` (`hall_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seat_tb
-- ----------------------------

-- ----------------------------
-- Table structure for select_tb
-- ----------------------------
DROP TABLE IF EXISTS `select_tb`;
CREATE TABLE `select_tb` (
  `choice_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `seat_id` int(11) DEFAULT NULL,
  `plan_id` int(11) DEFAULT NULL,
  `seat_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`choice_id`),
  UNIQUE KEY `seat_id` (`seat_id`,`plan_id`),
  KEY `plan_id` (`plan_id`),
  CONSTRAINT `select_tb_ibfk_1` FOREIGN KEY (`seat_id`) REFERENCES `seat_tb` (`seat_id`),
  CONSTRAINT `select_tb_ibfk_2` FOREIGN KEY (`plan_id`) REFERENCES `plan_tb` (`plan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of select_tb
-- ----------------------------

-- ----------------------------
-- Table structure for user_role_tb
-- ----------------------------
DROP TABLE IF EXISTS `user_role_tb`;
CREATE TABLE `user_role_tb` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT,
  `ur_user_id` int(11) DEFAULT NULL,
  `ur_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ur_id`),
  KEY `ur_role_id` (`ur_role_id`),
  KEY `ur_user_id` (`ur_user_id`),
  CONSTRAINT `user_role_tb_ibfk_1` FOREIGN KEY (`ur_role_id`) REFERENCES `role_tb` (`role_id`),
  CONSTRAINT `user_role_tb_ibfk_2` FOREIGN KEY (`ur_user_id`) REFERENCES `user_tb` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role_tb
-- ----------------------------

-- ----------------------------
-- Table structure for user_tb
-- ----------------------------
DROP TABLE IF EXISTS `user_tb`;
CREATE TABLE `user_tb` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(100) NOT NULL,
  `user_password` varchar(32) NOT NULL,
  `user_salt` varchar(32) DEFAULT NULL,
  `user_email` varchar(20) DEFAULT NULL,
  `user_tel` varchar(11) DEFAULT NULL,
  `user_img` varchar(200) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  `user_register_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_tb
-- ----------------------------

-- ----------------------------
-- Table structure for vip_tb
-- ----------------------------
DROP TABLE IF EXISTS `vip_tb`;
CREATE TABLE `vip_tb` (
  `vip_id` int(11) NOT NULL AUTO_INCREMENT,
  `vip_no` varchar(20) DEFAULT NULL,
  `vip_money` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `cinema_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`vip_id`),
  UNIQUE KEY `vip_no` (`vip_no`),
  KEY `user_id` (`user_id`),
  KEY `cinema_id` (`cinema_id`),
  CONSTRAINT `vip_tb_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tb` (`user_id`),
  CONSTRAINT `vip_tb_ibfk_2` FOREIGN KEY (`cinema_id`) REFERENCES `cinema_tb` (`cinema_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip_tb
-- ----------------------------
