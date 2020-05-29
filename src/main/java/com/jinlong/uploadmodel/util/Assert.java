package com.jinlong.uploadmodel.util;

import com.jinlong.uploadmodel.config.exception.CustomExceptionEnum;

/**
 * @description: Assert
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/20 11:21
 */
public class Assert {

    /**
     * <p>断言对象<code>obj</code>非空。如果对象<code>obj</code>为空，则抛出异常
     *
     * @param obj     待判断对象
     * @param message 异常信息
     * @throws RuntimeException
     */
    public static void assertNotNull(Object obj, String message) {
        if (obj == null) {
            throw new RuntimeException(message);
        }
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
