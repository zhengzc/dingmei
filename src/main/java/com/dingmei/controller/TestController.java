package com.dingmei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by ying on 15/11/3.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/page")
    public ModelAndView page(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/page/test.ftl");
        return mv;
    }
}
