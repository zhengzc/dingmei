package com.dingmei.dao.mapper;

import com.dingmei.dao.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ying on 15/10/29.
 */
public interface UserMapper {
    /**
     * 根据userName读取用户信息
     * @param userName
     * @return
     */
    public User loadUserByUserName(@Param("userName") String userName);
}
