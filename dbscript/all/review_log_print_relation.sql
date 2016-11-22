-- ----------------------------
-- Table structure for `review_log_print_relation`
-- ----------------------------
DROP TABLE IF EXISTS `review_log_print_relation`;
CREATE TABLE `review_log_print_relation` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `REVIEW_LOG_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核记录id',
  `PRINT_TASK_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印任务id',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION` int(22) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  COLLATE=utf8_unicode_ci;