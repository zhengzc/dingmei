package com.dingmei.service.macro.impl;

import com.dingmei.dao.entity.DataGroup;
import com.dingmei.dao.entity.DataType;
import com.dingmei.dao.mapper.DataGroupMapper;
import com.dingmei.dao.mapper.DataMapper;
import com.dingmei.dao.mapper.DataTypeMapper;
import com.dingmei.dto.MyTimeDTO;
import com.dingmei.service.macro.MacroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ying on 15/10/27.
 */
@Service
public class MacroServiceImpl implements MacroService{
    @Autowired
    private DataGroupMapper dataGroupMapper;
    @Autowired
    private DataMapper dataMapper;
    @Autowired
    private DataTypeMapper dataTypeMapper;

    @Override
    public DataGroup loadDataGroup(String groupId) {
        return this.dataGroupMapper.loadDataGroup(groupId);
    }

    @Override
    public List<DataType> queryDataTypes(String groupId) {
        return this.dataTypeMapper.queryDataTypeByGroupId(groupId);
    }

    @Override
    public List<Map<String, Object>> queryDataCommon(String dataType, String[] columns) {
        String columnStr = StringUtils.join(columns,",");
        return this.dataMapper.queryDataCommon(dataType,columnStr);
    }

    @Override
    public Map<String, String> queryDataOneColumn(String dataType,String timeStyle, String column) {
        Map<String, String> retMap = new HashMap<String, String>();

        List<Map<String,Object>> rets = this.dataMapper.queryDataCommon(dataType,column);
        for(Map<String,Object> map : rets){

            MyTimeDTO myTimeDTO = new MyTimeDTO();
            myTimeDTO.setTimeStyle(timeStyle);
            myTimeDTO.setMonth((Integer)map.get("month"));
            myTimeDTO.setQuarter((Integer)map.get("quarter"));
            myTimeDTO.setDay((Integer)map.get("day"));
            myTimeDTO.setWeek((Integer)map.get("week"));
            myTimeDTO.setYear((Integer)map.get("year"));

            retMap.put(myTimeDTO.toString(), (String) map.get(column));
        }


        return retMap;
    }
}
