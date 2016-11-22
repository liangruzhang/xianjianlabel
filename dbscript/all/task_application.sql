-- ----------------------------
-- Table structure for `task_application`
-- ----------------------------
DROP TABLE IF EXISTS `task_application`;
CREATE TABLE `task_application` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `NAME` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印任务申请名称',
  `STATUS` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '申请状态',
  `REVIEW_BY` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核人',
  `REVIEW_DATE` datetime DEFAULT NULL COMMENT '审核时间',
  `APP_REMARK` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '申请备注',
  `REVIEW_REMARK` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核备注',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `CREATED_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改时间',
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '删除标识',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION` int(22) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;