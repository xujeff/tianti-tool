/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : tianti_tool

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-10 14:50:51
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
INSERT INTO `pre_tag` VALUES ('00dc84c6e55111e7b9def48e38c3c5b0', '2017-12-20 14:42:56', null, '2017-12-20 14:42:56', null, '0', 'follower', '4');
INSERT INTO `pre_tag` VALUES ('04984a46e47011e7b9def48e38c3c5b0', '2017-12-19 11:52:25', null, '2017-12-19 11:52:25', null, '0', 'follower2', '4');
INSERT INTO `pre_tag` VALUES ('09776c14e48a11e7b9def48e38c3c5b0', '2017-12-19 14:58:40', null, '2017-12-19 14:58:40', null, '0', 'follower3', '4');
INSERT INTO `pre_tag` VALUES ('1023000', null, null, null, null, '0', 'zhangsan', '1');
INSERT INTO `pre_tag` VALUES ('1023100', null, null, null, null, '0', 'zhangsan', '1');
INSERT INTO `pre_tag` VALUES ('200001', null, null, null, null, '0', 'apple', '1');
INSERT INTO `pre_tag` VALUES ('200111', null, null, null, null, '0', 'pear', '1');
INSERT INTO `pre_tag` VALUES ('2011111', null, null, null, null, '0', 'orange', '1');
