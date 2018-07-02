package com.xz.framework.system.app.userregister.service.impl;

import com.xz.framework.system.security.dao.UserMapper;
import com.xz.framework.system.app.login.domain.User;
import com.xz.framework.system.app.userregister.service.UserRegisterService;
import com.xz.framework.system.security.domain.MyUser;
import com.xz.framework.system.sysbean.PageBean;
import com.xz.framework.system.sysbean.impl.ResultInfo;
import com.xz.framework.system.sysservice.BaseService;
import com.xz.framework.util.MD5Encrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author  wuhy on 2017/11/16.
 */
@Transactional
@Service("userRegisterServiceImpl")
public class UserRegisterServiceImpl extends BaseService implements UserRegisterService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Map register(User user) throws  Exception {
        ResultInfo resultInfo=new ResultInfo();
        MyUser u=userMapper.selectByLoginid(user.getLoginid());
        if(!ObjectUtils.isEmpty(u)){
            resultInfo.setMsg("账号已经存在！");
            resultInfo.setSuccess(false);
            return resultInfo;
        }
        user.setLoginidgo(MD5Encrypt.encrypt(user.getLoginid(),user.getLoginidgo()));
        user.setEffective("1");
        int count=userMapper.insert(user);
       if (count==1){
           resultInfo.setMsg("注册成功！");
           resultInfo.setSuccess(true);
       }else{
           resultInfo.setMsg("注册失败！");
           resultInfo.setSuccess(false);
       }
        return resultInfo;
    }
    @Override
    public List getUser(User user) throws Exception{
        ResultInfo resultInfo =new ResultInfo();
        List list=selectListForPageByObject("com.xz.framework.system.app.login.dao.UserMapper.selectByLoginid",user,3,1);
        return list;
    }

    @Override
    public PageBean getUserPageBean(User user) throws Exception{
       return selectPageBeabForPageByObject("com.xz.framework.system.security.dao.UserMapper.selectByLoginid",user,3,1);
    }
}
