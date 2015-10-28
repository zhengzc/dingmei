package com.dingmei.vo;

import java.util.List;

/**
 * Created by ying on 15/10/27.
 * 表格
 */
public class CommonTableVO {
    private String title;
    private List<String> columnName;
    private List<List<String>> datas;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getColumnName() {
        return columnName;
    }

    public void setColumnName(List<String> columnName) {
        this.columnName = columnName;
    }

    public List<List<String>> getDatas() {
        return datas;
    }

    public void setDatas(List<List<String>> datas) {
        this.datas = datas;
    }
}
