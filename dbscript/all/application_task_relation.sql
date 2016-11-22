-- ----------------------------
-- Table structure for `application_task_relation`
-- ----------------------------
DROP TABLE IF EXISTS `application_task_relation`;
CREATE TABLE `application_task_relation` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TASK_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印任务id',
  `APP_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印任务申请id',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION` int(22) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB COLLATE=utf8_unicode_ci;