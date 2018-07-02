package com.xz.framework.system.security.impl;

import com.xz.framework.system.security.dao.UserMapper;
import com.xz.framework.system.security.domain.MyUser;
import com.xz.framework.system.security.domain.Resources;
import com.xz.framework.system.security.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wuhy on 2017/11/22.
 */
public class MyUserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper usersDao;
    public UserMapper getUsersDao() {
        return usersDao;
    }

    public void setUsersDao(UserMapper usersDao) {
        this.usersDao = usersDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
        System.out.println("username is " + username);
        MyUser user = this.usersDao.selectByLoginid(username);
        if(user == null) {
            throw new UsernameNotFoundException(username);
        }
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);

        boolean enables = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        User userdetail =  new User(user.getLoginid(), user.getLoginidgo(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);

        return userdetail;
    }

    //取得用户的权限
    private Set<GrantedAuthority> obtionGrantedAuthorities(MyUser user) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        Set<Role>  roles= user.getRole();

        for(Role role : roles ) {
            Set<Resources> tempRes = role.getResources();
            for(Resources res : tempRes) {
              //  authSet.add(new GrantedAuthorityImpl(res.getName()));
                String url=res.getUrl();
                int index=url.indexOf("?");
                if(index>0){
                    url=url.substring(0,index);
                }
                authSet.add(new SimpleGrantedAuthority(res.getName()+url));
            }
        }
        return authSet;
    }
}