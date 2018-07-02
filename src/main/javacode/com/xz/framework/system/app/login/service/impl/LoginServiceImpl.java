package com.xz.framework.system.app.login.service.impl;

import com.xz.framework.system.security.dao.UserMapper;
import com.xz.framework.system.app.login.domain.User;
import com.xz.framework.system.app.login.service.LoginService;
import com.xz.framework.util.MD5Encrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @author  wuhy on 2017/11/15.
 */
@Transactional
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;
    @Override
    public boolean selectByLoginidPass(User user){

        String secret=user.getLoginidgo();
        user.setLoginidgo(MD5Encrypt.encrypt(user.getLoginid(),user.getLoginidgo()));
        User u=userMapper.selectByLoginidPass(user);
        if (!ObjectUtils.isEmpty(u)){
            return true;
        }else{
            return false;
        }
    }
}
