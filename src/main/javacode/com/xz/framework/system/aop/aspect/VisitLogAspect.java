package com.xz.framework.system.aop.aspect;

import com.xz.framework.system.aop.annotation.VisitLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author  wuhy on 2017/11/19.
 */
@Aspect
@Component
public class VisitLogAspect {


   /* @Resource
    private BaseDaoI<CmsOperationLog> logDao;*/

    /**
     * 日志记录
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VisitLogAspect.class);

    /**
     * Service层切点
     */

    @Pointcut("@annotation(com.xz.framework.system.aop.annotation.VisitLog)")
    public void serviceAspect() {

    }

    /**
     * doServiceLog:获取注解参数，记录日志.
     * @author wuhy
     * @param joinPoint 切入点参数
     * @since JDK 1.7
     */
    @Before("serviceAspect()")
    public  void doServiceLog(JoinPoint joinPoint) {
        LOGGER.info("日志记录");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            Enumeration<String> paramters= request.getParameterNames();
            Set keSet=request.getParameterMap().entrySet();
            for(Iterator itr = keSet.iterator(); itr.hasNext();){
                Map.Entry me=(Map.Entry)itr.next();
                System.out.println(me.getKey()+":"+me.getValue());
                HttpSession session=request.getSession(false);
                String loginid= session.getAttribute("loginid").toString();
                System.out.println(loginid);
                //此处增加插入数据库的方法
                //通过session获取用户登录账号
            }

            System.out.println();
        }  catch (Exception e) {
            LOGGER.error("异常信息:", e);
        }
    }


    /**
     * getServiceMthodDescription:获取注解中对方法的描述信息 用于service层注解  .
     * @author wuhy
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     * @since JDK 1.7
     */
    private String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for(Method method : methods) {
            if(method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if(clazzs.length == arguments.length) {
                    description = method.getAnnotation(VisitLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * getServiceMthodTableType:获取注解中对方法的数据表类型 用于service层注解 . <br/>
     * @author wuhy
     * @param joinPoint
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
/*    private int getServiceMthodTableType(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        int tableType = 0;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    tableType = method.getAnnotation(VisitLog.class).method();
                    break;
                }
            }
        }
        return tableType;
    }*/

    /**
     * getServiceMthodParams:获取json格式的参数. <br/>
     * @author wuhy
     * @param joinPoint
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
  /*  private String getServiceMthodParams(JoinPoint joinPoint)
            throws Exception {
        Object[] arguments = joinPoint.getArgs();
        String params = JacksonUtil.toJSon(arguments);
        return params;
    }*/

}