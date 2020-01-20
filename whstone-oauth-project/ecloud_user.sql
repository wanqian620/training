/*
 Navicat Premium Data Transfer

 Source Server         : 127
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : ecloud_user

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 20/01/2020 15:37:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource`;
CREATE TABLE `auth_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NOT NULL COMMENT '-1 根目录',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源或菜单路径',
  `method` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'GET POST PUT DELETE PATCH',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态：enable-启用，forbidden-禁用',
  `type` int(4) NULL DEFAULT NULL COMMENT '1 菜单 2 资源',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of auth_resource
-- ----------------------------
INSERT INTO `auth_resource` VALUES (1, 'system', '系统管理', -1, NULL, NULL, 'enable', 1);
INSERT INTO `auth_resource` VALUES (2, 'system_user', '用户管理', 1, NULL, NULL, 'enable', 1);
INSERT INTO `auth_resource` VALUES (3, 'system_user_info', '用户信息', 2, NULL, NULL, 'enable', 1);
INSERT INTO `auth_resource` VALUES (4, 'system_user_info_add', '添加用户', 3, '/api/springcloud/user/createUser', 'POST', 'enable', 2);
INSERT INTO `auth_resource` VALUES (5, 'system_user_info_list', '用户查询', 3, '/api/springcloud/user/list', 'GET', 'enable', 2);
INSERT INTO `auth_resource` VALUES (6, 'system_user_info_del', '删除用户', 3, '/api/springcloud/user/delUser', 'DELETE', 'enable', 2);
INSERT INTO `auth_resource` VALUES (7, 'system_host', '设备管理', 1, NULL, NULL, 'enable', 1);
INSERT INTO `auth_resource` VALUES (8, 'system_host_list', '设备查询', 7, '/api/springcloud/system/host/list', 'GET', 'enable', 2);
INSERT INTO `auth_resource` VALUES (9, 'system_host_add', '添加设备', 7, '/api/springcloud/system/host/addHostInfo', 'POST', 'enable', 2);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码：role_admin/role_anon',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '状态	enable-启用，forbidden-禁用\r\n状态:enable-启用，forbidden-禁用',
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ROLE_ADMIN', '管理员', 'enable', '2020-01-06 11:50:02', '2020-01-06 11:50:06');
INSERT INTO `role` VALUES (2, 'ROLE_USER', '普通用户', 'enable', '2020-01-06 11:50:04', '2020-01-06 11:50:08');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `resource_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES (1, 1, 1);
INSERT INTO `role_resource` VALUES (2, 1, 2);
INSERT INTO `role_resource` VALUES (3, 1, 3);
INSERT INTO `role_resource` VALUES (4, 1, 4);
INSERT INTO `role_resource` VALUES (5, 1, 5);
INSERT INTO `role_resource` VALUES (6, 1, 6);
INSERT INTO `role_resource` VALUES (7, 1, 7);
INSERT INTO `role_resource` VALUES (8, 1, 8);
INSERT INTO `role_resource` VALUES (9, 1, 9);
INSERT INTO `role_resource` VALUES (10, 2, 7);
INSERT INTO `role_resource` VALUES (11, 2, 8);
INSERT INTO `role_resource` VALUES (12, 2, 9);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '用户主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱地址',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `status` int(4) NULL DEFAULT NULL COMMENT '状态:1.启用 2.禁用 3.拉黑',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (3, '2020-01-16 23:49:03', '17720547001@163.com', '$2a$10$qKPTs/zVJ3B2MFTE4Vtg6e2bBO4kkGq/.1p1CSKcvHmsVsdDj1Mci', 1, '2020-01-16 23:49:02', 'Ryan');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);
INSERT INTO `user_role` VALUES (3, 3, 2);

SET FOREIGN_KEY_CHECKS = 1;
