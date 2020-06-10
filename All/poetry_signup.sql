/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : poetry_signup

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2020-06-10 12:36:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` char(32) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `headImage` varchar(50) DEFAULT NULL,
  `introduction` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '2', 'a', 'D:/poetryLinePic/1.jpg', '');
INSERT INTO `user` VALUES ('2', '2', null, null, 'D:/poetryLinePic/2.jpg', '');
INSERT INTO `user` VALUES ('3', '3', null, null, null, '');
INSERT INTO `user` VALUES ('4', '3', '3', null, null, '');
INSERT INTO `user` VALUES ('5', '3', '3', 'test', null, '');
INSERT INTO `user` VALUES ('6', '3', '3', 'test', null, '');
INSERT INTO `user` VALUES ('19', '13073190601', 'e10adc3949ba59abbe56e057f20f883e', '测试', null, '');
INSERT INTO `user` VALUES ('20', '18830931925', 'e10adc3949ba59abbe56e057f20f883e', '诗行测试', 'D:/poetryLinePic/18830931925.jpg', '诗行简介');
