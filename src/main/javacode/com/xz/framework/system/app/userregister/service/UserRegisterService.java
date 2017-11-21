package com.xz.framework.system.app.userregister.service;

import com.xz.framework.system.app.login.domain.User;
import com.xz.framework.system.sysbean.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @author  wuhy on 2017/11/16.
 */
public interface UserRegisterService {
    Map register(User user) throws  Exception;
    List getUser(User user) throws Exception;
    PageBean getUserPageBean(User user)  throws Exception;
}
