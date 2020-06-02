package com.jinlong.uploadmodel.util;

import org.springframework.util.StringUtils;

/**
 * @description: Assert
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/20 11:21
 */
public class Assert {

    public static void assertNotEmpty(String obj, CustomExceptionEnum exceptionEnum) {
        if (!StringUtils.hasText(obj))
            throw exceptionEnum.getException();
    }

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     *
     * @param obj           待判断对象
     * @param exceptionEnum 为空时抛出的异常枚举对象
     * @throws RuntimeException
     */
    public static void assertNotNull(Object obj, CustomExceptionEnum exceptionEnum) {
        if (obj == null) {
            throw exceptionEnum.getException();
        }
    }
}
