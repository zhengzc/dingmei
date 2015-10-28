package com.dingmei.dao.mapper;

import com.alibaba.fastjson.JSONObject;
import com.dingmei.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by ying on 15/10/24.
 */
public class MybatisTest extends BaseTest {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private DataMapper dataMapper;

    @Test
    public void testFind(){
        List<Map<String,Object>> rets =  testMapper.find();
        System.out.println(JSONObject.toJSONString(rets));
    }

    @Test
    public void testQueryDataCommon(){
        List<Map<String,Object>> data =  dataMapper.queryDataCommon("1","param1,param2");

        System.out.println(JSONObject.toJSONString(data));
    }

}
