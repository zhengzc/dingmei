package com.dingmei.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingmei.dao.entity.Menu;
import com.dingmei.service.macro.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by ying on 15/10/28.
 */
@Controller
@RequestMapping("/index")
public class IndexPageController {

    @Resource
    private MenuService menuService;

    /**
     * 登陆
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(WebRequest request){
        ModelAndView mv = new ModelAndView();

        String error = request.getParameter("error");
        if(error != null){
            mv.getModel().put("error",true);
        }

        mv.setViewName("/page/login.ftl");
        return mv;
    }


    /**
     * 左侧树
     * @return
     */
    @RequestMapping("/leftTree")
    @ResponseBody
    public Object leftTree(){

        //构建tree
        Queue<JSONObject> queue = new LinkedList<JSONObject>();

        JSONObject root = new JSONObject();//虚拟出个根节点
        root.put("id",0);
        queue.add(root);

        while(queue.size() > 0){
            JSONObject node = queue.remove();
            JSONArray children = new JSONArray();

            List<Menu> menuList = this.menuService.queryMenu(node.getInteger("id"));
            for(Menu menu : menuList){
                JSONObject tmp = new JSONObject();
                tmp.put("id",menu.getId());
                tmp.put("text",menu.getName());
                if("d".equals(menu.getMenuType())){//是菜单
                    queue.add(tmp);
                }else{
                    if("c".equals(menu.getMenuType())) {
                        tmp.put("urlType","c");
                        tmp.put("url", "/common/page?id=" + menu.getPath());
                    }else if("l".equals(menu.getMenuType())){
                        tmp.put("urlType","l");
                        tmp.put("url",menu.getPath());
                    }else{
                        tmp.put("urlType","d");
                        tmp.put("url","#");
                    }
                }

                children.add(tmp);
            }
            node.put("nodes",children);
        }

        return root.getJSONArray("nodes");
    }

}
