-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `ID` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `PRODUCT_NAME` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品名称',
  `PRODUCT_CODE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品编码',
  `DELETED_FLAG` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT '删除标识（N：未删除，Y：删除）',
  `RRODUCT_MODEL` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品型号',
  `PRODUCT_TYPE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品类型',
  `FROMAT_CODE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格代码',
  `FORMAT_NAME` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '规格名称',
  `SIGN_CODE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '产品标识代码',
  `SPECIAL_FLAG` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否特制品（Y：是  N：否）',
  `INNER_LABEL_MODEL_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '内标签模板id',
  `OUTER_LABEL_MODEL_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '外标签模板id',
  `AUXILIARY_LABEL_MODEL_ID` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '附标签模板id',
  `INVALID_DATE` int(11) DEFAULT NULL COMMENT '失效期（单位年）',
  `REMARK` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `CREATION_DATE` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_DATE` datetime NOT NULL COMMENT '修改时间',
  `CREATED_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `UPDATE_BY` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT '修改人',
  `CREATED_BY_OPR_UNIT` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION` int(22) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB COLLATE=utf8_unicode_ci COMMENT='产品基础表';