package com.dingmei.dao.mapper;

import com.dingmei.dao.entity.DataType;

import java.util.List;

/**
 * Created by ying on 15/10/27.
 */
public interface DataTypeMapper {
    public List<DataType> queryDataTypeByGroupId(String groupId);
}
