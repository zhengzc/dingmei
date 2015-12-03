package com.dingmei.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by ying on 15/10/27.
 */
public interface DataMapper {
    /**
     * 查询出某种类型的数据，返回指定的列，并根据时间排序
     * @param dataType
     * @param column
     * @return
     */
    public List<Map<String,Object>> queryDataCommonWithDate(
            @Param("dataType") String dataType,
            @Param("column") String column
    );

    /**
     * 查询出某种类型的数据，返回指定的列
     * @param dataType
     * @param column
     * @return
     */
    public List<Map<String,Object>> queryDataCommon(
            @Param("dataType") String dataType,
            @Param("column") String column
    );
}
