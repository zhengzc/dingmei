package com.dingmei.dao.entity;

/**
 * Created by ying on 15/10/27.
 */
public class DataType {
    private Integer id;
    private String groupId;
    private String dataType;
    private String typeName;
    private String timeStyle;
    private String colName;
    private String totalUnit;
    private String colKey;
    private String lineKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName.trim();
    }

    public String getTimeStyle() {
        return timeStyle;
    }

    public void setTimeStyle(String timeStyle) {
        this.timeStyle = timeStyle.trim();
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName.trim();
    }

    public String getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(String totalUnit) {
        this.totalUnit = totalUnit.trim();
    }

    public String getColKey() {
        return colKey;
    }

    public void setColKey(String colKey) {
        this.colKey = colKey.trim();
    }

    public String getLineKey() {
        return lineKey;
    }

    public void setLineKey(String lineKey) {
        this.lineKey = lineKey.trim();
    }
}
