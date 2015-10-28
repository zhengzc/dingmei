package com.dingmei.service.macro;

import com.dingmei.dao.entity.Menu;

import java.util.List;

/**
 * Created by ying on 15/10/28.
 */
public interface MenuService {
    /**
     * 获取所有菜单列表
     * @return
     */
    public List<Menu> queryAllMenu();

    /**
     * 根据某节点下的所有子节点
     * @param parent
     * @return
     */
    public List<Menu> queryMenu(Integer parent);
}
