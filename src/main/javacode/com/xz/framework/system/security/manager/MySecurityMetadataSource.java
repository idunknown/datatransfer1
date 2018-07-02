package com.xz.framework.system.security.manager;

import com.xz.framework.system.security.dao.ResourcesMapper;
import com.xz.framework.system.security.domain.Resources;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author wuhy on 2017/11/23.
 */
public class MySecurityMetadataSource  implements FilterInvocationSecurityMetadataSource {
   @Resource
    private ResourcesMapper resourcesMapper;
    private static boolean reload=true;
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private SecurityAfterLogin securityAfterLogin;
    public SecurityAfterLogin getSecurityAfterLogin() {
        return securityAfterLogin;
    }

    public void setSecurityAfterLogin(SecurityAfterLogin securityAfterLogin) {
        this.securityAfterLogin = securityAfterLogin;
    }
    public  void init(){
        loadResourceDefine();
    }
    //加载所有资源与权限的关系
    private void loadResourceDefine() {
        if(resourceMap == null) {
            resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
            List<Resources> resources = this.resourcesMapper.findAll();
            for (Resources resource : resources) {
                Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
                //以权限名封装为Spring的security Object
                String url=resource.getUrl();
                int index=url.indexOf("?");
                if(index>0){
                    url =url.substring(0,index);
                }

                ConfigAttribute configAttribute = new SecurityConfig(resource.getName()+url);
                configAttributes.add(configAttribute);
                resourceMap.put(url, configAttributes);
            }
        }

        Set<Map.Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap.entrySet();
        Iterator<Map.Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet.iterator();

    }
    //返回所请求资源所需要的权限
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        requestUrl=SecuritySonMethod.mainUrl(requestUrl);
        if(resourceMap == null) {
            loadResourceDefine();
        }
        Collection<ConfigAttribute> o=resourceMap.get(requestUrl);
       if(ObjectUtils.isEmpty(o)&&reload){
               if(securityAfterLogin.isSecurity(requestUrl)){
                  return o;
               }else {
                   loadResourceDefine();
                   reload=false;
                   o=getAttributes( object);
                   if(ObjectUtils.isEmpty(o)){
                       reload=true;
                       throw new AccessDeniedException(" 没有权限访问！ ");
                   }
               }
       }

        reload=true;
        return o;
    }



    public ResourcesMapper getResourcesMapper() {
        return resourcesMapper;
    }

    public void setResourcesMapper(ResourcesMapper resourcesMapper) {
        this.resourcesMapper = resourcesMapper;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }

}
