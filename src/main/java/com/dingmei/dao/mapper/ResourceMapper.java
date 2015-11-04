package com.dingmei.dao.mapper;

import java.util.List;
import com.dingmei.dao.entity.Resource;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ying on 15/11/4.
 */
public interface ResourceMapper {
    /**
     * 获取所有资源列表
     * @return
     */
    public List<Resource> queryAll();

    /**
     * 根据资源id获取对应的角色ids
     * @param resourceId
     * @return
     */
    public List<String> queryRoleStrsByResourceId(@Param("resourceId") Integer resourceId);

}
