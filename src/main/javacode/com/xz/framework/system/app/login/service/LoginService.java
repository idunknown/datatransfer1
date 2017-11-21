package com.xz.framework.system.app.login.service;

import com.xz.framework.system.app.login.domain.User;

/**
 * @author  wuhy on 2017/11/15.
 */
public interface LoginService {
    boolean selectByLoginidPass(User user);
}
