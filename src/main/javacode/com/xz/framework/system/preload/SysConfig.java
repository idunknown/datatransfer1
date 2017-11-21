package com.xz.framework.system.preload;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author  wuhy on 2017/11/9.
 */
public class SysConfig {

    private static Logger logger = LoggerFactory.getLogger(SysConfig.class);
    private static Map configMap = null;
    private static String fileEncoding ;

    public SysConfig() {
    }

    public static void init() {
        try {
            logger.info("============开始加载系统参数！==============");
            initSysconfig();
            logger.info("============系统参数加载完成==============");
        } catch (Exception var1) {
            if(logger.isErrorEnabled()) {
                logger.error("系统参数加载失败...", var1);
            }
        }

    }

    private static void initSysconfig() {
        configMap = new HashMap();
        Properties props = new Properties();
        BufferedReader bf = null;
        BufferedReader bf_bs = null;

        try {
            ClassPathResource ex = new ClassPathResource("com/xz/framework/config/config.properties");
            ClassPathResource classPathResource_bs = new ClassPathResource("config/config.properties");
            bf = new BufferedReader(new InputStreamReader(ex.getInputStream(), fileEncoding));
            bf_bs = new BufferedReader(new InputStreamReader(classPathResource_bs.getInputStream(), fileEncoding));
            props.load(bf);
            Iterator it = props.entrySet().iterator();

            while(it.hasNext()) {
                Map.Entry it_bs = (Map.Entry)it.next();
                configMap.put(it_bs.getKey(), it_bs.getValue());
            }

            props.load(bf_bs);
            Iterator it_bs1 = props.entrySet().iterator();

            while(it_bs1.hasNext()) {
                Map.Entry m = (Map.Entry)it_bs1.next();
                configMap.put(m.getKey(), m.getValue());
            }
        } catch (Exception var20) {
            logger.error(ExceptionUtils.getStackTrace(var20));
        } finally {
            if(bf != null) {
                try {
                    bf.close();
                } catch (IOException var19) {
                    logger.error(ExceptionUtils.getStackTrace(var19));
                }
            }

            if(bf_bs != null) {
                try {
                    bf_bs.close();
                } catch (IOException var18) {
                    logger.error(ExceptionUtils.getStackTrace(var18));
                }
            }

        }

    }

    public static Map getConfigs() {
        return configMap == null?null:configMap;
    }

    public static String getSysConfig(String key) {
        return configMap == null?null:(String)configMap.get(key);
    }

    public static Integer getSysConfigToInteger(String key) {
        String value = getSysConfig(key);
        Integer configValue = null;

        try {
            configValue = Integer.valueOf(value);
        } catch (NumberFormatException var4) {
            configValue = null;
        }

        return configValue;
    }

    public static Integer getSysConfigToInteger(String key, int defaultValue) {
        Integer value = getSysConfigToInteger(key);
        return value == null?Integer.valueOf(defaultValue):value;
    }

    public static boolean getSysconfigToBoolean(String key) {
        String bool = getSysConfig(key);
        return bool != null && (Boolean.parseBoolean(bool) || "true".equals(bool));
    }

    public static boolean getSysconfigToBoolean(String key, boolean defaultValue) {
        String bool = getSysConfig(key);
        return bool != null && !"".equals(bool)?Boolean.parseBoolean(bool) || "true".equals(bool):defaultValue;
    }

    public static String getSysConfig(String key, boolean isSuperposition, String... args) {
        String tmp = getSysConfig(key);
        if(!isSuperposition) {
            return MessageFormat.format(tmp, args);
        } else {
            tmp = tmp == null?"":tmp;
            String[] var7 = args;
            int var6 = args.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                String arg = var7[var5];
                tmp = tmp + arg;
            }

            return tmp;
        }
    }

    public static String getSysConfig(String key, String defaultValue) {
        String configvalue = getSysConfig(key);

        return ObjectUtils.isEmpty(configvalue)?defaultValue:configvalue;
    }

    public static String getSysConfig(String key, String defaultValue, boolean isSuperposition, String... args) {
        String tmp = getSysConfig(key, defaultValue);
        if(!isSuperposition) {
            return MessageFormat.format(tmp, args);
        } else {
            tmp = tmp == null?"":tmp;
            String[] var8 = args;
            int var7 = args.length;

            for(int var6 = 0; var6 < var7; ++var6) {
                String arg = var8[var6];
                tmp = tmp + arg;
            }

            return tmp;
        }
    }

    public void destroy() {
        configMap.clear();
        logger.info("系统参数缓存清除");
    }

    public void setFileEncoding(String fileEncoding) {
        SysConfig.fileEncoding = fileEncoding;
    }
}
