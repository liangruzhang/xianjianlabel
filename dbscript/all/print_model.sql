-- ----------------------------
-- Table structure for `print_model`
-- ----------------------------
DROP TABLE IF EXISTS `print_model`;
CREATE TABLE `print_model` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `PRINT_MODEL_NAME` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印模板名称',
  `TYPE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '模板类型（内标签，外标签，附标签）',
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '删除标识',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `VERSION` int(22) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB COLLATE=utf8_unicode_ci;