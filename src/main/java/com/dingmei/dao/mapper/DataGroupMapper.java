package com.dingmei.dao.mapper;

import com.dingmei.dao.entity.DataGroup;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ying on 15/10/27.
 */
public interface DataGroupMapper {
    public DataGroup loadDataGroup(@Param("groupId")String groupId);

    /**
     * 更新某页面的分析说明结果
     * @param groupId
     * @param analysis
     */
    public int updateDataGroupAnalysis(@Param("groupId")String groupId,@Param("analysis")String analysis);
}
