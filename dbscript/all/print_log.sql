-- ----------------------------
-- Table structure for `print_log`
-- ----------------------------
DROP TABLE IF EXISTS `print_log`;
CREATE TABLE `print_log` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `PRINT_BY` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印人',
  `REVIEW_BY` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核人',
  `PRINT_DATE` datetime DEFAULT NULL COMMENT '打印时间',
  `REVIEW_DATE` datetime DEFAULT NULL COMMENT '审核时间',
  `PRINT_QUANTITY` int(11) DEFAULT NULL COMMENT '打印数量',
  `PRINT_STATUS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印状态',
  `PRINT_TASK_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印任务id',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改时间',
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '删除标识',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION` int(22) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB COLLATE=utf8_unicode_ci;