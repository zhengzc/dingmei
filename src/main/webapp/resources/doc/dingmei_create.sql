CREATE TABLE `test` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `dm_menu` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT '',
  `level` int(11) DEFAULT NULL COMMENT '1 2 3 表示三级菜单',
  `menuType` varchar(10) DEFAULT NULL COMMENT 'l 叶子节点 d目录节点',
  `path` varchar(50) DEFAULT NULL COMMENT '如果是普通叶子节点l 对应的路径',
  `parent` int(11) DEFAULT NULL COMMENT '父节点id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dm_dataGroup` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `groupId` varchar(20) NOT NULL DEFAULT '' COMMENT '一组数据',
  `groupName` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idx_groupId` (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `dm_dataType` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `groupId` varchar(20) NOT NULL,
  `dataType` varchar(20) NOT NULL DEFAULT '',
  `typeName` varchar(50) NOT NULL DEFAULT '',
  `timeStyle` varchar(50) NOT NULL DEFAULT '' COMMENT '时间类型',
  `colName` varchar(200) NOT NULL DEFAULT '' COMMENT '列名 eg:列1,列2',
  `totalUnit` varchar(50) NOT NULL DEFAULT '' COMMENT 'colName为common时候，total的单位',
  `colKey` varchar(200) NOT NULL DEFAULT '' COMMENT '列key eg:param1,param2',
  `lineKey` varchar(200) NOT NULL DEFAULT '' COMMENT '折线图字段,colName为common的时候可以不设置，否则设置绘制的字段key',
  PRIMARY KEY (`id`),
  KEY `idx_groupId` (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `dm_data` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dataType` varchar(20) NOT NULL DEFAULT '' COMMENT '数据分类',
  `year` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `quarter` int(11) DEFAULT NULL,
  `week` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `total` varchar(50) DEFAULT NULL,
  `huanBi` varchar(50) DEFAULT NULL,
  `tongBi` varchar(50) DEFAULT NULL,
  `param1` varchar(50) DEFAULT NULL,
  `param2` varchar(50) DEFAULT NULL,
  `param3` varchar(50) DEFAULT NULL,
  `param4` varchar(50) DEFAULT NULL,
  `param5` varchar(50) DEFAULT NULL,
  `param6` varchar(50) DEFAULT NULL,
  `param7` varchar(50) DEFAULT NULL,
  `param8` varchar(50) DEFAULT NULL,
  `param9` varchar(50) DEFAULT NULL,
  `param10` varchar(50) DEFAULT NULL,
  `param11` varchar(50) DEFAULT NULL,
  `param12` varchar(50) DEFAULT NULL,
  `param13` varchar(50) DEFAULT NULL,
  `param14` varchar(50) DEFAULT NULL,
  `param15` varchar(50) DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `operator` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_dataType` (`dataType`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;





CREATE TABLE `t_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '资源名称',
  `content` varchar(100) NOT NULL DEFAULT '' COMMENT '资源内容',
  `addTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `t_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roleStr` varchar(20) NOT NULL DEFAULT '' COMMENT 'role的字符串表示形式',
  `roleName` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名字',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_roleStr` (`roleStr`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `t_role_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `roleStr` varchar(11) NOT NULL DEFAULT '',
  `resourceId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_roleId` (`roleStr`),
  KEY `idx_resourceId` (`resourceId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  `realName` varchar(20) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `addTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;