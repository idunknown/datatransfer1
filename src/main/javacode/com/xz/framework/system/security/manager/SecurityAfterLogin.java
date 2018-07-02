package com.xz.framework.system.security.manager;

import java.util.Set;

/**
 * @author wuhy on 2017/11/23.
 */
public class SecurityAfterLogin {



    private  Set<String> url;
    public  Set getUrl() {
        return url;
    }

    public  void setUrl(Set url) {
        this.url = url;
    }

    public Boolean isSecurity(String url){
        url=SecuritySonMethod.mainUrl(url);
       if( this.url.contains(url)){
           return true;
       }else{
           String[] urls=url.split("\\.");
           for(String u:this.url){
               if(u.endsWith("**")&&!u.equals("/")){
                  String ul=u.substring(0,u.indexOf("/**"));
                   if(url.startsWith(ul)){
                       return true;
                   }

               }
               if(u.contains("**.")&&!u.equals("/")) {
                  String ul=u.replace("*","");
                   String[]  uls= ul.split("\\.");
                  if(urls[0].startsWith(uls[0])&&uls[1].equals(urls[1])) {
                      return  true;
                  }
               }
           }
       }
        return false;


    }


}
