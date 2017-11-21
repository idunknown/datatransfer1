package com.xz.framework.system.app.login.dao;

import com.xz.framework.system.app.login.domain.*;

import java.util.List;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User selectByLoginidPass(User user);
    List selectByLoginid(String longid);
}