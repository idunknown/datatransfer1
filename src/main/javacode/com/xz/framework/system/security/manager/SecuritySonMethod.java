package com.xz.framework.system.security.manager;

/**
 * @author wuhy on 2017/11/24.
 */
public class SecuritySonMethod {

    public static String mainUrl(String url){
        int subMethod=url.indexOf("!");
        if(subMethod>0){
            url=url.substring(0,subMethod);
            int paramIndex=url.indexOf("?");
            if(paramIndex>0){
                url=url.substring(0,paramIndex);
            }
        }
        if(url.lastIndexOf("/")!=url.length()-1){
           url=url+"/";
        }
       return url;
    }
}
