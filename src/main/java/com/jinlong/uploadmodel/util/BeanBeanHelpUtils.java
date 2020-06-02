package com.jinlong.uploadmodel.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: BeanBeanHelpUtils
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 19:29
 */
@Slf4j
public class BeanBeanHelpUtils {
    private BeanBeanHelpUtils() {
    }

    /**
     * 将一个javaBean转化为另一个javaBean
     * 遇到参数名相同，类型不同的参数不会进行赋值
     *
     * @param o   源对象类型
     * @param t   目标对象class对象
     * @param <T> 目标对象class对象
     * @param <O> 源对象类型
     * @return T
     */
    public static <T, O> T copyProperties(O o, Class<T> t) {
        if (o == null) {
            log.warn("数据转化异常，异常信息为：源对象为null");
            return null;
        }
        try {
            T newInstance = t.newInstance();
            BeanUtils.copyProperties(o, newInstance);
            return newInstance;
        } catch (Exception e) {
            log.error("数据转化异常，异常信息为：{}", e.getMessage());
            return null;
        }
    }

    public static <T, O> List<T> copyList(List<O> o, Class<T> t) {
        if (o == null || o.isEmpty()) {
            log.warn("数据转化异常，异常信息为：源对象为null");
            return Collections.emptyList();
        }
        ArrayList<T> result = new ArrayList<>(o.size());
        try {
            for (O oClass : o) {
                result.add(copyProperties(o, t));
            }
            return result;
        } catch (Exception e) {
            log.error("数据转化异常，异常信息为：{}", e.getMessage());
            return null;
        }
    }

}
