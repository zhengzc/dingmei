package com.dingmei.dao.entity;

/**
 * Created by ying on 15/10/27.
 */
public class DataGroup {
    private Integer id;
    private String groupId;
    private String groupName;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
