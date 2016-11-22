-- ----------------------------
-- Table structure for `email_log`
-- ----------------------------
DROP TABLE IF EXISTS `email_log`;
CREATE TABLE `email_log` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `SEND_DATE` datetime DEFAULT NULL COMMENT '邮件发送时间',
  `SEND_TO` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮件接收人',
  `SEND_BY` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮件发送人',
  `STATUS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮件发送状态',
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '删除标识',
  `EMAIL_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮件id',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB COLLATE=utf8_unicode_ci;