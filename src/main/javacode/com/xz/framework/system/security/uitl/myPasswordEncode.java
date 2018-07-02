package com.xz.framework.system.security.uitl;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 * @author wuhy on 2017/11/22.
 */
public class myPasswordEncode extends MessageDigestPasswordEncoder {

    public myPasswordEncode(String algorithm) {
        super(algorithm);
    }

    public myPasswordEncode(String algorithm, boolean encodeHashAsBase64) throws IllegalArgumentException {
        super(algorithm, encodeHashAsBase64);
    }
    @Override
    public String encodePassword(String rawPass, Object salt) {
        salt=null==salt?salt.toString()+"helloworld":"helloworld";
        return super.encodePassword(rawPass,salt);
    }
}
