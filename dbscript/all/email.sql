-- ----------------------------
-- Table structure for `email`
-- ----------------------------
DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `TITLE` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮件标题',
  `CONTENT` varchar(6000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮件模板内容',
  `CREATION_DATE` datetime NOT NULL,
  `CREATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `EMAIL_NAME` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮件模板名称',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION` int(22) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB COLLATE=utf8_unicode_ci;