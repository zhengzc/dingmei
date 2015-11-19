package com.dingmei.controller;

import com.alibaba.fastjson.JSONObject;
import com.dingmei.dao.entity.DataGroup;
import com.dingmei.dao.entity.DataType;
import com.dingmei.dto.MyTimeDTO;
import com.dingmei.service.macro.MacroService;
import com.dingmei.vo.CommonTableVO;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by ying on 15/10/26.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String DEFAULT_COLUMN_NAME = "环比,同比";
    private final String DEFAULT_COLUMN_KEY = "total,huanBi,tongBi";
    private final int DEFAULT_SHOW_COUNT = 12;//默认显示数据条数
    @Resource
    private MacroService macroService;

    /**
     * 通用查询 构建图片和折线图
     * @param request
     * @return
     */
    @RequestMapping("/page")
    public ModelAndView page(HttpServletRequest request){
        String groupId = request.getParameter("id");

        DataGroup dataGroup = this.macroService.loadDataGroup(groupId);
        String title = dataGroup.getGroupName();//标题

        List<CommonTableVO> commonTableVOs = new ArrayList<CommonTableVO>();//表格
        Map<String,String> columnKeyName = new HashMap<String, String>();//列key name映射

        //取数据的开始时间结束时间
        MyTimeDTO startTime = new MyTimeDTO();
        MyTimeDTO endTime = new MyTimeDTO();

        startTime.setYear(ServletRequestUtils.getIntParameter(request,"startYear",0));
        startTime.setQuarter(ServletRequestUtils.getIntParameter(request,"startQuarter",0));
        startTime.setMonth(ServletRequestUtils.getIntParameter(request,"startMonth",0));
        startTime.setWeek(ServletRequestUtils.getIntParameter(request,"startWeek",0));
        startTime.setDay(ServletRequestUtils.getIntParameter(request,"startDay",0));

        endTime.setYear(ServletRequestUtils.getIntParameter(request,"endYear",0));
        endTime.setQuarter(ServletRequestUtils.getIntParameter(request,"endQuarter",0));
        endTime.setMonth(ServletRequestUtils.getIntParameter(request,"endMonth",0));
        endTime.setWeek(ServletRequestUtils.getIntParameter(request,"endWeek",0));
        endTime.setDay(ServletRequestUtils.getIntParameter(request,"endDay",0));

        //是否是默认查询
        Boolean isDefaultQuery = startTime.isEmpty() && endTime.isEmpty();

        //时间key列表
        MyTimeDTO timeStyle = null;

        //是否为通用的查询方式
        Boolean isCommon = false;

        //构建表格
        List<DataType> dataTypes = this.macroService.queryDataTypes(groupId);
        for(DataType dataType : dataTypes){
            String columnNameStr;
            String columnKeyStr;
            //列名称
            List<String> columnNames = new ArrayList<String>();

            if ("common".equals(dataType.getColName())){
                columnNameStr = DEFAULT_COLUMN_NAME;
                columnKeyStr = DEFAULT_COLUMN_KEY;
                columnNames.add(dataType.getTotalUnit() != null ? dataType.getTotalUnit() : "总量");
                isCommon = true;
            }else{
                columnNameStr = dataType.getColName();
                columnKeyStr = dataType.getColKey();
            }
            columnNames.addAll(Arrays.asList(columnNameStr.split(",")));

            //列key
            String[] columnKeys = columnKeyStr.split(",");

            for(int i = 0 ; i < columnKeys.length ; i++){
                columnKeyName.put(columnKeys[i],columnNames.get(i));
            }

            CommonTableVO commonTableVO = new CommonTableVO();
            commonTableVO.setTitle(dataType.getTypeName());
            List<String> colNames = columnNames;
            commonTableVO.setColumnName(colNames);

            List<Map<String,Object>> datas = this.macroService.queryDataCommon(dataType.getDataType(),columnKeys);
            List<List<String>> rows = new ArrayList<List<String>>();
            for(Map<String,Object> map : datas){
                List<String> row = new ArrayList<String>();

                MyTimeDTO myTimeDTO = new MyTimeDTO(dataType.getTimeStyle(), (Integer) map.get("year"), (Integer) map.get("month"), (Integer) map.get("quarter"), (Integer) map.get("week"), (Integer) map.get("day"));
                if(timeStyle == null){//取第一条数据
                    timeStyle = myTimeDTO;
                }

                if(!isDefaultQuery && (myTimeDTO.compareTo(startTime) < 0 || myTimeDTO.compareTo(endTime) > 0)){
                    continue;
                }

                String timeStr = myTimeDTO.toString();
                row.add(timeStr);

                for(String key : columnKeys){
                    row.add((String)map.get(key));
                }

                rows.add(row);
            }

            //在没有分时间段查询的时候，默认取数据的数量
            if(isDefaultQuery && rows.size() > DEFAULT_SHOW_COUNT){
                rows = rows.subList(rows.size()-DEFAULT_SHOW_COUNT,rows.size());
            }

            commonTableVO.setDatas(rows);

            commonTableVOs.add(commonTableVO);
        }

        //构建折线图
        Map<String,Object> line = new HashMap<String, Object>();

        line.put("title", title);
        line.put("subTitle", title);
        line.put("yAxisTitle",title);

        TreeSet<MyTimeDTO> categories = new TreeSet<MyTimeDTO>();
        List<Object> series = new ArrayList<Object>();

        if(isCommon){//多个表格的配置方式
            int i  = 0;
            for(DataType dataType : dataTypes){
                Map<String,Object> oneLine = new HashMap<String, Object>();

                String lineKeyStr = "total";
                oneLine.put("name",dataType.getTypeName());

                List<Double> oneLineData = new ArrayList<Double>();
                Map<MyTimeDTO,String> timeDatas = this.macroService.queryDataOneColumn(dataType.getDataType(),dataType.getTimeStyle(),lineKeyStr);

                //获取categories列表
                if(timeDatas.size() > 0 && i == 0){
                    for(Map.Entry<MyTimeDTO,String> entry : timeDatas.entrySet()){
                        if (isDefaultQuery) {
                            categories.add(entry.getKey());
                        }else{
                            if (entry.getKey().compareTo(startTime) >= 0 && entry.getKey().compareTo(endTime) <= 0) {
                                categories.add(entry.getKey());
                            }
                        }
                    }
                }

                //当没有分时间段查询的时候，默认显示的数据数量
                if(isDefaultQuery && categories.size() > DEFAULT_SHOW_COUNT){
                    TreeSet<MyTimeDTO> tmpCategories = new TreeSet<MyTimeDTO>();
                    Iterator<MyTimeDTO> it = categories.descendingIterator();
                    int j = 0;
                    while(it.hasNext()){
                        if(j >= DEFAULT_SHOW_COUNT){
                            break;
                        }
                        tmpCategories.add(it.next());
                        j++;
                    }

                    categories = tmpCategories;
                }

                //构建line
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

                TreeMap<MyTimeDTO,String> timeDatas = this.macroService.queryDataOneColumn(dataType.getDataType(),dataType.getTimeStyle(),lineKey);
                List<Double> oneLineData = new ArrayList<Double>();

                //生成categories
                if(timeDatas.size() > 0 && i == 0){
                    for(Map.Entry<MyTimeDTO,String> entry : timeDatas.entrySet()) {
                        if (isDefaultQuery) {
                            categories.add(entry.getKey());
                        }else{
                            if (entry.getKey().compareTo(startTime) >= 0 && entry.getKey().compareTo(endTime) <= 0) {
                                categories.add(entry.getKey());
                            }
                        }
                    }
                }

                //当没有分时间段查询的时候，默认显示的数据数量
                if(isDefaultQuery && categories.size() > DEFAULT_SHOW_COUNT){
                    TreeSet<MyTimeDTO> tmpCategories = new TreeSet<MyTimeDTO>();
                    Iterator<MyTimeDTO> it = categories.descendingIterator();
                    int j = 0;
                    while(it.hasNext()){
                        if(j >= DEFAULT_SHOW_COUNT){
                            break;
                        }
                        tmpCategories.add(it.next());
                        j++;
                    }
                    categories = tmpCategories;
                }

                //生成line
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
        mv.getModel().put("timeKeys",timeStyle.getTimeKeys());
        mv.getModel().put("id",groupId);
        mv.getModel().put("description", StringEscapeUtils.escapeHtml4(dataGroup.getDescription().trim()));
        mv.getModel().put("analysis",StringEscapeUtils.escapeHtml4(dataGroup.getAnalysis().trim()));

        //处理一下选中节点
        String selectNode = request.getParameter("selectNode");
        mv.getModel().put("selectNode",selectNode != null ? selectNode : -1);
        return mv;
    }

    @RequestMapping("/updateGroupAnalysis")
    @ResponseBody
    public Object updateGroupAnalysis(HttpServletRequest request){
        Map<String,Object> ret = new HashMap<String, Object>();

        try {
            String groupId = ServletRequestUtils.getRequiredStringParameter(request,"groupId");
            String analysis = ServletRequestUtils.getRequiredStringParameter(request,"analysis");

            this.macroService.updateDataGroupAnalysis(groupId,analysis);

            ret.put("code",200);
        } catch (ServletRequestBindingException e) {
            logger.error(e.getMessage(),e);
            ret.put("code",500);
            ret.put("msg","参数错误");
            return ret;
        } catch (Exception e){
            logger.error(e.getMessage(), e);
            ret.put("code",500);
            return ret;
        }

        return ret;
    }

}
