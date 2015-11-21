/*
Navicat MySQL Data Transfer

Source Server         : shijie99_localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : tpf

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-08-26 14:30:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for home
-- ----------------------------
DROP TABLE IF EXISTS `home`;
CREATE TABLE `home` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `homelink` varchar(255) DEFAULT NULL,
  `homedesc` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of home
-- ----------------------------
INSERT INTO `home` VALUES ('1', 'http://127.0.0.1:8080/home', 'home描述', '无备注');
