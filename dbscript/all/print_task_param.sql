-- ----------------------------
-- Table structure for `print_task_param`
-- ----------------------------
DROP TABLE IF EXISTS `print_task_param`;
CREATE TABLE `print_task_param` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NAME` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格名称',
  `VALUE` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格值',
  `PRINT_TASK_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印任务id',
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '删除标识',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改时间',
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '修改人',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION` int(22) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB COLLATE=utf8_unicode_ci;
