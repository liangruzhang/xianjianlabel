-- ----------------------------
-- Table structure for `review_log`
-- ----------------------------
DROP TABLE IF EXISTS `review_log`;
CREATE TABLE `review_log` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `REVIEW_DATE` datetime DEFAULT NULL COMMENT '审核时间',
  `REVIEW_BY` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核人',
  `REVIEW_STATUS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核状态',
  `REMARK` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核意见',
  `OPERATE_BY` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '当前操作人',
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '删除标识',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CREATINO_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `UPDATE_DATE` datetime NOT NULL,
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION` int(22) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
