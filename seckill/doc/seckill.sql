/*
 Source Server         : docker
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : 192.168.99.100:3306
 Source Schema         : seckill
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_no` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程号',
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '讲师名字',
  `course_desciption` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程描述',
  `course_period` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程周期',
  `start_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '选课开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '选课结束时间',
  `course_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `stock_quantity` int(11) NOT NULL,
  `course_type` int(11) NULL DEFAULT NULL,
  `course_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`course_no`, `start_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('202000', 'Python', 'xiao p', 'python study', '2020年6月到2020年6月', '2020-06-07 00:00:00', '2020-06-10 20:00:00', 9.00, 4, 0, 'c1.jpg', NULL);
INSERT INTO `course` VALUES ('202001', 'JAVA WEB', 'xiao j', 'web study', '2020年6月到2020年6月', '2020-06-07 00:00:00', '2020-06-10 20:00:00', 10.00, 6, 1, 'c2.jpg', NULL);
INSERT INTO `course` VALUES ('202002', 'Vue', 'xiao v', 'vue study', '2020年6月到2020年6月', '2020-06-07 00:00:00', '2020-06-10 20:00:00', 30.00, 7, 2, 'c4.jpg', NULL);
INSERT INTO `course` VALUES ('202003', 'test', 'xiao t', 'test study', '2020年6月到2020年6月', '2020-06-07 00:00:00', '2020-06-10 20:00:00', 10.00, 0, 3, 'c1.jpg', NULL);
INSERT INTO `course` VALUES ('202004', 'Spring', 'xiao s', 'spring study', '2020年6月到2020年6月', '2020-06-07 00:00:00', '2020-06-10 20:00:00', 10.00, 9, 4, 'c3.jpg', NULL);

-- ----------------------------
-- Table structure for course_type
-- ----------------------------
DROP TABLE IF EXISTS `course_type`;
CREATE TABLE `course_type`  (
  `course_type` int(11) NOT NULL,
  `course_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`course_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_price` decimal(10, 0) NULL DEFAULT NULL,
  `pay_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付价格',
  `payment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `pay_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pay_date` datetime(0) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `creat_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 201800015 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (201800012, '201800', 'null', 'python', 9, 9.00, NULL, '0', NULL, '2020-06-07 00:52:11', NULL, NULL, NULL, 'c1.jpg', 'null');
INSERT INTO `orders` VALUES (201800013, '202000', 'null', 'Python', 9, 9.00, NULL, '0', NULL, '2020-06-07 01:05:30', NULL, NULL, NULL, 'c1.jpg', 'null');
INSERT INTO `orders` VALUES (201800014, '202001', 'null', 'JAVA WEB', 10, 10.00, NULL, '0', NULL, '2020-06-07 01:08:26', NULL, NULL, NULL, 'c2.jpg', 'null');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` int(11) NOT NULL,
  `repassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dbflag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '9febf049d6f216a3fcf46b5b39970fb1', 2020, NULL, '608976');
INSERT INTO `user` VALUES ('test', '9ef3987b56840b5dbc398b2c0adbf006', 2020, NULL, '082816');

SET FOREIGN_KEY_CHECKS = 1;
