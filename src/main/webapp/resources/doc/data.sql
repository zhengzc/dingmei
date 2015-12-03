INSERT INTO `dm_data` (`id`, `dataType`, `year`, `month`, `quarter`, `week`, `day`, `total`, `huanBi`, `tongBi`, `param1`, `param2`, `param3`, `param4`, `param5`, `param6`, `param7`, `param8`, `param9`, `param10`, `param11`, `param12`, `param13`, `param14`, `param15`, `updateTime`, `operator`)
VALUES
	(1, '1', 2015, 8, NULL, NULL, 6, NULL, NULL, NULL, '0.35', '1.35', '1.55', '1.75', '2.35', '3.00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-25 21:29:32', 0),
	(2, '1', 2015, 9, NULL, NULL, 10, NULL, NULL, NULL, '0.45', '2.45', '1.65', '1.85', '5.45', '3.10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-25 21:30:29', 0),
	(3, 'M2', NULL, 8, NULL, NULL, NULL, '135.69', '0.27%', '13.3%', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-25 21:36:12', 0),
	(4, 'M1', NULL, 8, NULL, NULL, NULL, '36.279', '2.74%', '9.3%', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-25 21:37:04', 0),
	(5, 'M0', NULL, 8, NULL, NULL, NULL, '5.9', '0.09%', '1.8%', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-25 21:37:38', 0),
	(6, 'M2', NULL, 9, NULL, NULL, NULL, '145.69', '0.37%', '14.3%', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-25 21:38:05', 0),
	(7, 'M1', NULL, 9, NULL, NULL, NULL, '37.279', '2.84%', '10.3%', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-25 21:38:34', 0),
	(8, 'M0', NULL, 9, NULL, NULL, NULL, '6.9', '0.19%', '2.8%', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-25 21:38:56', 0),
	(9, '1', 2015, 10, NULL, NULL, 4, NULL, NULL, NULL, '0.56', '1.33', '2.33', '4.5', '0.35', '2.95', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-28 09:43:53', 0),
	(10, '1', 2015, 11, NULL, NULL, 3, NULL, NULL, NULL, '1.55', '2.55', '1.23', '0.54', '3.5', '1.11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-28 09:44:27', 0),
	(11, '1', 2016, 11, NULL, NULL, 4, NULL, NULL, NULL, '3.55', '3.33', '0.53', '1.35', '3.22', '4.5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-10-28 09:44:54', 0);


INSERT INTO `dm_dataType` (`id`, `groupId`, `dataType`, `typeName`, `timeStyle`, `colName`, `totalUnit`, `colKey`, `lineKey`)
VALUES
	(1, '1', '1', '利率', 'yyyy.MM.dd', '活期存款,三个月,半年,一年,两年,三年', '1', 'param1,param2,param3,param4,param5,param6', 'param1,param2,param3,param4,param5,param6'),
	(2, '2', 'M2', 'M2供应量', 'yyyy.MM', 'common', '1', '', ''),
	(3, '2', 'M1', 'M1', 'yyyy.MM', 'common', '1', '', ''),
	(4, '2', 'M0', 'M0', 'yyyy.MM', 'common', '1', '', '');


INSERT INTO `dm_dataGroup` (`id`, `groupId`, `groupName`, `description`, `analysis`)
VALUES
	(1, '1', '活期存款利率', '利率就表现形式来说，是指一定时期内利息额同借贷资本总额的比率。利率是单位货币在单位时间内的利息水平，表明利息的多少。经济学家一直在致力于寻找一套能够完全解释利率结构和变化的理论。利率通常由国家的中央银行控制，在美国由联邦储备委员会管理。至今，所有国家都把利率作为宏观经济调控的重要工具之一。', '利率就表现形式来说，是指一定时期内利息额同借贷资本总额的比率。利率是单位货币在单位时间内的利息水平，表明利息的多少。经济学家一直在致力于寻找一套能够完全解释利率结构和变化的理论。利率通常由国家的中央银行控制，在美国由联邦储备委员会管理。至今，所有国家都把利率作为宏观经济调控的重要工具之一。'),
	(2, '2', '货币供应量（万亿）', '世界各国中央银行货币估计口径不完全一致，但划分的基本依据是一致的，即流动性大小。所谓流动性，是指一种资产随时可以变为现金或商品，而对持款人又不带来任何损失，货币的流动性程度不同，在流通中的周转次数就不同，形成的货币购买力及其对整个社会经济活动的影响也不一样。', '');


INSERT INTO `dm_menu` (`id`, `name`, `menuType`, `path`, `parent`)
VALUES
	(1, '货币政策', 'd', NULL, 0),
	(2, '利率', 'c', '1', 1),
	(3, '货币供应量', 'c', '2', 1),
	(4, '菜单1', 'd', NULL, 0),
	(5, '叶子', 'l', '/test/page', 4);


INSERT INTO `t_resource` (`id`, `name`, `content`, `addTime`)
VALUES
	(1, '通用折线图查询', '/common/page', '2015-11-04 13:56:57'),
	(3, '左侧菜单', '/index/leftTree', '2015-11-04 14:00:36'),
	(4, '测试页面', '/test/page', '2015-11-04 19:06:49'),
	(5, 'ajax更改分析结果', '/common/updateGroupAnalysis', '2015-11-11 17:34:39');


INSERT INTO `t_role` (`id`, `roleStr`, `roleName`, `description`)
VALUES
	(1, 'u1', '普通用户', '浏览'),
	(2, 'login', '登录用户', '通用的角色，只要用户登录就拥有的权限，直接在代码中写死'),
	(3, 'test', '测试角色', NULL),
	(4, 'u2', '特权用户', '能够修改一些数据');


INSERT INTO `t_role_resource` (`id`, `roleStr`, `resourceId`)
VALUES
	(1, 'u1', 1),
	(2, 'login', 3),
	(3, 'test', 4);

INSERT INTO `t_user` (`id`, `userName`, `password`, `realName`, `addTime`)
VALUES
	(1, 'test', '123456', '郑志超', '2015-10-29 16:10:12'),
	(2, 'test2', '123456', 'johnson.zheng', '2015-11-11 17:38:37');


INSERT INTO `t_user_role` (`id`, `userId`, `roleId`)
VALUES
	(1, 1, 1),
	(2, 2, 1),
	(3, 2, 4);

------
INSERT INTO `dm_dataType` (`id`, `groupId`, `dataType`, `typeName`, `timeStyle`, `colName`, `totalUnit`, `colKey`, `lineKey`)
VALUES
	(22, '5', '8', '税收收入', '', '项目,2014年执行数,2015年预算数,预算数为上年执行数的%', '', 'param1,param2,param3,param4', '');

INSERT INTO `dm_dataGroup` (`id`, `groupId`, `groupName`, `description`, `analysis`)
VALUES
	(5, '5', '税收', '税收相关', '分析结果');

INSERT INTO `dm_data` (`id`, `dataType`, `year`, `month`, `quarter`, `week`, `day`, `total`, `huanBi`, `tongBi`, `param1`, `param2`, `param3`, `param4`, `param5`, `param6`, `param7`, `param8`, `param9`, `param10`, `param11`, `param12`, `param13`, `param14`, `param15`, `updateTime`, `operator`)
VALUES
	(67, '8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '国内增值税', '21102', '21500', '101', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-12-03 12:00:00', 0),
	(68, '8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '国内消费税', '8906', '11200', '125', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2015-12-03 12:00:37', 0);

INSERT INTO `dm_menu` (`id`, `name`, `menuType`, `path`, `parent`)
VALUES
	(6, '税收', 'c2', '5', 4);
