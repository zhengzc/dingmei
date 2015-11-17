package com.dingmei.dao.mapper;

import com.dingmei.dao.entity.User;

import java.util.List;
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

    /**
     * 根据userId读取用户角色信息
     * @param userId
     * @return
     */
    public List<String> queryUserRoleStrs(@Param("userId") int userId);
}
