CREATE TABLE `secu_company_group_member` (
	`ID` VARCHAR(50) NOT NULL COLLATE 'utf8_unicode_ci',
	`CREATED_BY_OPR_UNIT` VARCHAR(50) NOT NULL COLLATE 'utf8_unicode_ci',
	`VERSION` INT(22) NOT NULL,
	`CREATION_DATE` DATETIME NULL DEFAULT NULL,
	`CREATE_BY` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`UPDATE_BY` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`UPDATE_DATE` DATETIME NULL DEFAULT NULL,
	`DELETED_FLAG` VARCHAR(10) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	`SECU_COMPANY_CODE` VARCHAR(50) NOT NULL COMMENT '公司名称' COLLATE 'utf8_unicode_ci',
	`USER_ID` VARCHAR(50) NOT NULL COMMENT '用户id' COLLATE 'utf8_unicode_ci',
	`GROUP_NAME` VARCHAR(150) NOT NULL COMMENT '用户组名' COLLATE 'utf8_unicode_ci',
	PRIMARY KEY (`ID`)
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
;
