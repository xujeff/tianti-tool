/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : tianti_tool_0

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-10 14:51:15
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- Table structure for `pre_tag`
-- ----------------------------
DROP TABLE IF EXISTS `pre_tag`;
CREATE TABLE `pre_tag` (
  `id` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `create_by` varchar(32) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `update_by` varchar(32) DEFAULT NULL,
  `delete_flag` int(1) DEFAULT '0',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `tag_type` int(2) DEFAULT NULL COMMENT '标签属性',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签';

-- ----------------------------
-- Records of pre_tag
-- ----------------------------
INSERT INTO `pre_tag` VALUES ('00dc84c6e55111e7b9def48e38c3c5b0', '2018-07-20 14:42:56', null, '2018-07-20 14:42:56', null, '0', 'tianti', '4');
