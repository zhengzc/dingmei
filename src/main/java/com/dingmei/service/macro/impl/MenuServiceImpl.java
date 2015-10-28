package com.dingmei.service.macro.impl;

import com.dingmei.dao.entity.Menu;
import com.dingmei.dao.mapper.MenuMapper;
import com.dingmei.service.macro.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ying on 15/10/28.
 */
@Service
public class MenuServiceImpl implements MenuService{
    @Resource
    private MenuMapper menuMapper;

    public List<Menu> queryAllMenu(){
        return this.menuMapper.queryAll();
    }

    public List<Menu> queryMenu(Integer parent){
        return this.menuMapper.queryMenus(parent);
    }
}
