
-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_no` varchar(11) NOT NULL COMMENT '课程号',
  `course_name` varchar(255) NOT NULL COMMENT '课程名称',
  `teacher_name` varchar(255) NOT NULL COMMENT '讲师名字',
  `course_desciption` varchar(255) DEFAULT NULL COMMENT '课程描述',
  `course_period` varchar(255) DEFAULT NULL COMMENT '课程周期',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '选课开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '选课结束时间',
  `course_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `stock_quantity` int(11) NOT NULL,
  `course_type` int(11) DEFAULT NULL,
  `course_pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_no`,`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('201800', 'python', 'jacke', 'python study', '2018年5月到2018年5月', '2018-05-31 19:50:04', '2018-05-31 20:55:03', '9.00', '1', '0', 'c1.jpg');
INSERT INTO `course` VALUES ('201801', 'JAVA WEB开发', '鲁智深', '使用倒拔杨柳的功力帮你打通JAVA WEB开发', '2018年5月到2018年5月', '2018-05-22 20:54:57', '2018-05-31 20:55:03', '10.00', '7', '1', 'c2.jpg');
INSERT INTO `course` VALUES ('201802', 'Vue 开发', '武松', '三拳叫你走通vue核心流程', '2018年5月到2018年5月', '2018-05-24 12:50:04', '2018-05-31 12:50:09', '30.00', '7', '2', 'c4.jpg');
INSERT INTO `course` VALUES ('201803', 'test', 'test', 'ttttttttest', '2018年5月到2018年5月', '2018-05-23 12:50:04', '2018-05-31 20:55:03', '10.00', '0', '3', 'c1.jpg');
INSERT INTO `course` VALUES ('201804', 'JAVA EE', '林冲', '误闯JAVA世界', null, '2018-04-27 20:39:00', '2018-05-31 20:55:03', '10.00', '9', '4', 'c3.jpg');

-- ----------------------------
-- Table structure for `course_type`
-- ----------------------------
DROP TABLE IF EXISTS `course_type`;
CREATE TABLE `course_type` (
  `course_type` int(11) NOT NULL,
  `course_description` varchar(255) NOT NULL,
  PRIMARY KEY (`course_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_type
-- ----------------------------

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('1');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_no` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `course_name` varchar(255) NOT NULL,
  `course_price` decimal(10,0) DEFAULT NULL,
  `pay_price` decimal(10,2) DEFAULT NULL COMMENT '支付价格',
  `payment` varchar(255) DEFAULT NULL COMMENT '支付方式',
  `pay_status` varchar(255) NOT NULL,
  `pay_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creat_by` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `course_pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=201800011 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('201800001', '201803', 'alex', 'JAVA WEB开发', '99', '99.00', 'iphone', '0', '2018-04-24 15:51:37', '2018-04-25 12:17:18', 'alex', '2018-04-24 15:51:46', '123', null);
INSERT INTO `orders` VALUES ('201800002', '201804', 'alex', 'JAVA 开发', null, null, null, '0', null, '2018-04-27 13:11:26', null, null, null, null);
INSERT INTO `orders` VALUES ('201800003', '201805', 'alex', 'JAVA 开发', null, null, null, '1', null, '2018-04-28 13:11:29', null, null, null, null);
INSERT INTO `orders` VALUES ('201800004', '201806', 'alex2', 'JAVA 开发', null, null, null, '1', null, '2018-04-29 13:11:31', null, null, null, null);
INSERT INTO `orders` VALUES ('201800006', '201802', 'emok', 'Vue 开发', null, '30.00', null, '0', null, '2018-04-27 09:41:27', 'emok', null, null, null);
INSERT INTO `orders` VALUES ('201800007', '201800', '11', 'python', null, '9.00', null, '0', null, '2018-05-07 14:39:31', '11', null, null, 'c1.jpg');
INSERT INTO `orders` VALUES ('201800008', '201802', '11', 'Vue 开发', null, '30.00', null, '0', null, '2018-05-07 14:40:04', '11', null, null, 'c4.jpg');
INSERT INTO `orders` VALUES ('201800009', '201801', 'alex', 'JAVA WEB开发', null, '10.00', null, '0', null, '2018-05-15 23:35:33', 'alex', null, null, 'c2.jpg');
INSERT INTO `orders` VALUES ('201800010', '201801', '666', 'JAVA WEB开发', null, '10.00', null, '0', null, '2018-06-13 14:24:51', '666', null, null, 'c2.jpg');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id` int(11) NOT NULL,
  `repassword` varchar(255) DEFAULT NULL,
  `dbflag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('000', '0000', '2018', '0000', null);
INSERT INTO `user` VALUES ('001', 'c393fd86f30233cd7fa67364125a39c5', '2018', '0000', 'alex');
INSERT INTO `user` VALUES ('11', '11', '0', null, null);
INSERT INTO `user` VALUES ('12', '1234', '2018', '1234', null);
INSERT INTO `user` VALUES ('123', '123', '0', null, null);
INSERT INTO `user` VALUES ('1233', '1234', '2018', '1234', null);
INSERT INTO `user` VALUES ('13', '1234', '2018', '1234', null);
INSERT INTO `user` VALUES ('321', '21f6eb45261975cb0993fcaf256db784', '2018', null, null);
INSERT INTO `user` VALUES ('345', '59cfc7ebb315a70e12ab8546557b51a4', '2018', null, null);
INSERT INTO `user` VALUES ('43', '9999', '2018', '9999', null);
INSERT INTO `user` VALUES ('444', '5678', '2018', '5678', null);
INSERT INTO `user` VALUES ('45', 'db96197ba75deea60a0630c94997187c', '2018', 'db96197ba75deea60a0630c94997187c', null);
INSERT INTO `user` VALUES ('55', '5555', '2018', '5555', null);
INSERT INTO `user` VALUES ('654', '4444', '2018', '4444', null);
INSERT INTO `user` VALUES ('666', '6ebee29b6aa964acff1e494a9aea679c', '2018', '0000', 'alex');
INSERT INTO `user` VALUES ('678', '8888', '2018', '8888', null);
INSERT INTO `user` VALUES ('99', '38daffdacab5ab753843807452bfd15f', '2018', null, null);
INSERT INTO `user` VALUES ('@', '@', '0', null, null);
INSERT INTO `user` VALUES ('a', 'a', '0', null, null);
INSERT INTO `user` VALUES ('aa', '1234', '2018', '1234', null);
INSERT INTO `user` VALUES ('alex', 'a', '1', null, null);
INSERT INTO `user` VALUES ('alex201805', '1234', '201805', null, null);
INSERT INTO `user` VALUES ('alex201805018', '1234', '201805', null, null);
INSERT INTO `user` VALUES ('alex20180520', '1234', '2018', '1234', null);
INSERT INTO `user` VALUES ('alex20180522', '210d1154bd5e99d60eeb4feaee64cca3', '2018', null, 'alex');
INSERT INTO `user` VALUES ('andy', '123456', '0', null, null);
INSERT INTO `user` VALUES ('andyzheng', 'andyzheng', '0', null, null);
INSERT INTO `user` VALUES ('asd', '1234', '2018', '1234', null);
INSERT INTO `user` VALUES ('emok', '123', '0', null, null);
INSERT INTO `user` VALUES ('emoker', '123', '0', null, null);
INSERT INTO `user` VALUES ('q', 'q', '0', null, null);
INSERT INTO `user` VALUES ('seven', '123456', '0', null, null);
INSERT INTO `user` VALUES ('ss', 'ss', '0', null, null);
INSERT INTO `user` VALUES ('sxb', '123', '0', null, null);
INSERT INTO `user` VALUES ('test4', '123', '0', null, null);
INSERT INTO `user` VALUES ('test@qq.com', '123', '0', null, null);
INSERT INTO `user` VALUES ('test@test.com', 'test', '0', null, null);
INSERT INTO `user` VALUES ('w', 'w', '0', null, null);
INSERT INTO `user` VALUES ('xiaomantou3', '1234567890', '0', null, null);
INSERT INTO `user` VALUES ('z', 'z', '0', null, null);
