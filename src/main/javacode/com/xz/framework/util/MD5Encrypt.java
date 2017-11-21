package com.xz.framework.util;

import java.security.MessageDigest;

/**
 * @author  wuhy on 2017/11/16.
 */
public class MD5Encrypt  {
    public static String encrypt(String loginid,String loginid2) {
        String md5str = "";
        loginid +=loginid2; // 加盐
        loginid+="07532$qwm";
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 2 将消息变成byte数组
            byte[] input = loginid.getBytes();

            // 3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);

            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = HexUtil.bytes2Hex(buff);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }




}
