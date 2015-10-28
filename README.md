说明
=====
#通用查询配置方式
* dm_dataGroup表，配置一个页面。groupName目前用于折线图标题，副标题，纵轴说明。
        dm_dataType表，配置每个页面下的数据类型，一对多的映射。<br/>
        dataType每个表格唯一标识，typeName表格名字，timeStyle时间格式，colName和colKey分别表示显示列名称和显示列key值，注意必须一一对应，lineKey表示折线图取的字段，注意必须是col字段中定义返回的列。<br/>
        其中colName设置为common的时候表示显示总数,环比,同比的通用模式，不必设置后面两个字段。
* dm_data表，请注意表示数量的字段total，param*必须为数字格式，否则会出现转换异常。请注意保证数据的一致性，不要缺失。

