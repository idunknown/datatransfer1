package com.xz.framework.system.security.dao;

import com.xz.framework.system.app.login.domain.User;
import com.xz.framework.system.security.domain.MyUser;


public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User selectByLoginidPass(User user);
    MyUser selectByLoginid(String longid);
}