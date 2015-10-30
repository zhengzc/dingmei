package com.dingmei.service.macro;

import com.dingmei.dao.entity.DataGroup;
import com.dingmei.dao.entity.DataType;
import com.dingmei.dto.MyTimeDTO;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ying on 15/10/27.
 */
public interface MacroService {
    /**
     * 查询dataGroup信息
     * @param groupId
     * @return
     */
    public DataGroup loadDataGroup(String groupId);

    /**
     * 查询每个分类下的类型信息
     * @param groupId
     * @return
     */
    public List<DataType> queryDataTypes(String groupId);

    /**
     * 查询数据
     * @param dataType
     * @param columns
     * @return
     */
    public List<Map<String,Object>> queryDataCommon(String dataType, String[] columns);

    /**
     * 查询出一列数据，时间作为key值
     * @param
     * @return
     */
    public TreeMap<MyTimeDTO,String> queryDataOneColumn(String dataType,String timeStyle, String column);
}
