package com.dingmei.controller;

import com.alibaba.fastjson.JSONObject;
import com.dingmei.dao.entity.DataGroup;
import com.dingmei.dao.entity.DataType;
import com.dingmei.dto.MyTimeDTO;
import com.dingmei.service.macro.MacroService;
import com.dingmei.vo.CommonTableVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by ying on 15/10/26.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    private final String DEFAULT_COLUMN_NAME = "数量,环比,同比";
    private final String DEFAULT_COLUMN_KEY = "total,huanBi,tongBi";
    @Resource
    private MacroService macroService;

    /**
     * 通用查询 构建图片和折线图
     * @param request
     * @return
     */
    @RequestMapping("/page")
    public ModelAndView page(WebRequest request){
        String groupId = request.getParameter("id");

        DataGroup dataGroup = this.macroService.loadDataGroup(groupId);
        String title = dataGroup.getGroupName();//标题

        List<CommonTableVO> commonTableVOs = new ArrayList<CommonTableVO>();//表格

        Map<String,String> columnKeyName = new HashMap<String, String>();
        //构建表格
        List<DataType> dataTypes = this.macroService.queryDataTypes(groupId);
        for(DataType dataType : dataTypes){
            String columnNameStr;
            String columnKeyStr;
            if ("common".equals(dataType.getColName())){
                columnNameStr = DEFAULT_COLUMN_NAME;
                columnKeyStr = DEFAULT_COLUMN_KEY;
            }else{
                columnNameStr = dataType.getColName();
                columnKeyStr = dataType.getColKey();
            }
            String[] columnNames = columnNameStr.split(",");
            String[] columnKeys = columnKeyStr.split(",");

            for(int i = 0 ; i < columnKeys.length ; i++){
                columnKeyName.put(columnKeys[i],columnNames[i]);
            }

            CommonTableVO commonTableVO = new CommonTableVO();
            commonTableVO.setTitle(dataType.getTypeName());
            List<String> colNames = Arrays.asList(columnNames);
            commonTableVO.setColumnName(colNames);

            List<Map<String,Object>> datas = this.macroService.queryDataCommon(dataType.getDataType(),columnKeys);
            List<List<String>> rows = new ArrayList<List<String>>();
            for(Map<String,Object> map : datas){
                List<String> row = new ArrayList<String>();

                MyTimeDTO myTimeDTO = new MyTimeDTO(dataType.getTimeStyle(), (Integer) map.get("year"), (Integer) map.get("month"), (Integer) map.get("quarter"), (Integer) map.get("week"), (Integer) map.get("day"));
                String timeStr = myTimeDTO.toString();
                row.add(timeStr);

                for(String key : columnKeys){
                    row.add((String)map.get(key));
                }

                rows.add(row);
            }

            commonTableVO.setDatas(rows);

            commonTableVOs.add(commonTableVO);
        }

        //构建折线图
        Map<String,Object> line = new HashMap<String, Object>();

        line.put("title", title);
        line.put("subTitle", title);
        line.put("yAxisTitle",title);

        Set<MyTimeDTO> categories = new HashSet<MyTimeDTO>();
        List<Object> series = new ArrayList<Object>();

        if(dataTypes.size() > 1){//多个表格的配置方式
            int i  = 0;
            for(DataType dataType : dataTypes){
                Map<String,Object> oneLine = new HashMap<String, Object>();

                String lineKeyStr = "total";
                oneLine.put("name",dataType.getTypeName());

                List<Double> oneLineData = new ArrayList<Double>();
                Map<MyTimeDTO,String> timeDatas = this.macroService.queryDataOneColumn(dataType.getDataType(),dataType.getTimeStyle(),lineKeyStr);

                if(timeDatas.size() > 0 && i == 0){
                    for(Map.Entry<MyTimeDTO,String> entry : timeDatas.entrySet()){
                        categories.add(entry.getKey());
                    }
                }

                for(MyTimeDTO x : categories){
                    if(timeDatas.containsKey(x)){
                        oneLineData.add(Double.valueOf(timeDatas.get(x)));
                    }else{
                        oneLineData.add(0.0);
                    }
                }

                oneLine.put("data",oneLineData);
                series.add(oneLine);

                i++;
            }
        }else{//单表格的配置方式
            DataType dataType = dataTypes.get(0);

            String lineKeyStr = dataType.getLineKey();

            String[] lineKeys = lineKeyStr.split(",");
            int i = 0;
            for(String lineKey : lineKeys){
                Map<String,Object> oneLine = new HashMap<String, Object>();
                oneLine.put("name",columnKeyName.get(lineKey));

                Map<MyTimeDTO,String> timeDatas = this.macroService.queryDataOneColumn(dataType.getDataType(),dataType.getTimeStyle(),lineKey);
                List<Double> oneLineData = new ArrayList<Double>();

                if(timeDatas.size() > 0 && i == 0){
                    for(Map.Entry<MyTimeDTO,String> entry : timeDatas.entrySet()){
                        categories.add(entry.getKey());
                    }
                }

                for(MyTimeDTO x : categories){
                    if(timeDatas.containsKey(x)){
                        oneLineData.add(Double.valueOf(timeDatas.get(x)));
                    }else{
                        oneLineData.add(0.0);
                    }
                }

                oneLine.put("data",oneLineData);
                series.add(oneLine);
                i++;
            }
        }

        List<String> categoriesStr = new ArrayList<String>();
        for(MyTimeDTO dto : categories){
            categoriesStr.add(dto.toString());
        }
        line.put("xAxisCategories",categoriesStr);
        line.put("series",series);


        ModelAndView mv = new ModelAndView();
        mv.setViewName("page/commonQuery.ftl");
        mv.getModel().put("title",title);
        mv.getModel().put("tables",commonTableVOs);
        mv.getModel().put("line", JSONObject.toJSONString(line));

        //处理一下选中节点
        String selectNode = request.getParameter("selectNode");
        mv.getModel().put("selectNode",selectNode != null ? selectNode : -1);
        return mv;
    }

}
