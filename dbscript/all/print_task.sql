-- ----------------------------
-- Table structure for `print_task`
-- ----------------------------
DROP TABLE IF EXISTS `print_task`;
CREATE TABLE `print_task` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '删除标识',
  `PRINT_STAUTS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印状态（1：已打印  2：未打印 3:正在打印  4：打印失败）',
  `REVIEW_STAUTS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核状态',
  `PRODUCT_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品id',
  `PRIORITY` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '优先级（1：高  2：中  3：低）',
  `QUANTITY` int(11) DEFAULT NULL COMMENT '打印数量',
  `DISINFECT_DATE` datetime DEFAULT NULL COMMENT '灭菌日期',
  `INVALID_DATE` int(11) DEFAULT NULL COMMENT '有效期',
  `TASK_NAME` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '打印任务名称',
  `REMARK` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '任务说明',
  `TYPE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '种类规格',
  `BEGIN_SEQUENCE` int(11) DEFAULT NULL COMMENT '起始序列号',
  `CREATED_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改时间',
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '修改人',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION` int(22) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB COLLATE=utf8_unicode_ci;