/*
 Navicat Premium Data Transfer

 Source Server         : .
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : ebook

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 24/09/2020 21:53:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` bigint(6) NOT NULL AUTO_INCREMENT COMMENT '图书编号',
  `categoryId` bigint(6) NOT NULL COMMENT '图书分类编号',
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图书名称',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图书摘要',
  `uploaduser` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `creatdate` date NOT NULL COMMENT '创建时间',
  `del_flag` int(2) NOT NULL COMMENT '图书状态，0-正常 1-已删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_ID`(`categoryId`) USING BTREE,
  CONSTRAINT `FK_ID` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, 1, '超时空同居', '111222333', '小伟', '2020-09-22', 0);
INSERT INTO `book` VALUES (2, 2, '居里夫人', '居里夫人', '小伟', '2020-09-22', 0);
INSERT INTO `book` VALUES (3, 3, '神雕侠侣', '武侠小说666', '金庸', '2020-09-21', 0);
INSERT INTO `book` VALUES (4, 3, '射雕英雄传', '金庸神奇武侠666', '金庸', '2020-09-22', 0);
INSERT INTO `book` VALUES (5, 3, '射雕英雄传2', '金庸神奇武侠2', '金庸', '2020-09-22', 0);
INSERT INTO `book` VALUES (6, 3, '射雕英雄传3', '金庸神奇武侠3', '金庸', '2020-09-22', 0);
INSERT INTO `book` VALUES (7, 3, '小鱼儿与花无缺', '经典武侠故事演绎经典神话', '小伟', '2020-09-23', 0);
INSERT INTO `book` VALUES (8, 1, '一千零一夜', '经典童话故事', '小伟', '2020-09-23', 0);
INSERT INTO `book` VALUES (9, 4, '灰姑娘', '灰姑娘童话故事', '小伟', '2020-09-23', 0);
INSERT INTO `book` VALUES (10, 4, '白雪公主与七个小矮人', '白雪公主与七个小矮人', '小伟', '2020-09-23', 0);
INSERT INTO `book` VALUES (11, 2, '时空爱恋', '经典穿越爱情小说', '小伟', '2020-09-23', 0);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(6) NOT NULL AUTO_INCREMENT COMMENT '分类编号',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `del_flag` int(1) NOT NULL COMMENT '状态 0-正常 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '玄幻', 0);
INSERT INTO `category` VALUES (2, '言情', 0);
INSERT INTO `category` VALUES (3, '武侠', 0);
INSERT INTO `category` VALUES (4, '童话', 0);
INSERT INTO `category` VALUES (5, '悬疑', 0);

SET FOREIGN_KEY_CHECKS = 1;
