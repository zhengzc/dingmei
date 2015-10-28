package com.dingmei.dao.mapper;

import com.dingmei.dao.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ying on 15/10/28.
 */
public interface MenuMapper {
    /**
     * 查询所有菜单
     * @return
     */
    public List<Menu> queryAll();

    /**
     * 查询某个节点的子节点
     * @param parent
     * @return
     */
    public List<Menu> queryMenus(@Param("parent")Integer parent);
}
