说明
=====
#通用查询配置方式
* dm_dataGroup表，配置一个页面。groupName目前用于折线图标题，副标题，纵轴说明。
        dm_dataType表，配置每个页面下的数据类型，一对多的映射。<br/>
        dataType每个表格唯一标识，typeName表格名字，timeStyle时间格式，colName和colKey分别表示显示列名称和显示列key值，注意必须一一对应，lineKey表示折线图取的字段，注意必须是col字段中定义返回的列。<br/>
        其中colName设置为common的时候表示显示总数,环比,同比的通用模式，不必设置后面两个字段。
* dm_data表，请注意表示数量的字段total，param*必须为数字格式，否则会出现转换异常。请注意保证数据的一致性，不要缺失。

#菜单配置方式
* dm_menu表，使用parent字段构建树形结构
* menuType 三种类型，d表示目录，c表示通用的查询，l表示一半叶子节点
* path配合menuType使用，d：表示目录，此节点无需跳转，path可以为空。c表示通用查询，path配置对应的groupId。l表示普通节点，path配置链接地址 eg:/common/page

#mvn eclipse最佳实践
* 配置mvn环境
* 项目根目录执行 mvn eclipse:clean eclipse:eclipse -Dwtpversion=2.0 会自动生成eclipse项目的配置文件
* 将项目导入eclipse
* 如果eclispe没有配置maven_home需要手动配置maven_home

#角色权限用户配置方式
* 采用springSecurity实现登录和权限控制
* applicationContext-security3.1.xml文件中配置有每个url需要的角色，具体配置方式请参考文件和下面el表达式的说明，这里配置资源与角色的映射
* 表t_user中存储用户信息，其中role字段配置用户角色，采用最简单的方式，所以这里只允许每个用户有一个角色

#使用springSecurity控制访问 SpringEl表达式配置角色访问
* hasRole('role')	返回 true 如果当前主体拥有特定角色。
* hasAnyRole([role1,role2])	返回 true 如果当前主体拥有任何一个提供的角色 （使用逗号分隔的字符串队列）
* principal	允许直接访问主体对象，表示当前用户
* authentication	允许直接访问当前 Authentication对象 从SecurityContext中获得
* permitAll	一直返回true
* denyAll	一直返回false
* isAnonymous()	如果用户是一个匿名登录的用户 就会返回 true
* isRememberMe()	如果用户是通过remember-me 登录的用户 就会返回 true
* isAuthenticated()	如果用户不是匿名用户就会返回true
* isFullyAuthenticated()	如果用户不是通过匿名也不是通过remember-me登录的用户时， 就会返回true。