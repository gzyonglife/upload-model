package com.jinlong.uploadmodel.config.selector;

/**
 * @description: ProjectZoneSelector
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/8 15:16
 */
public interface ProjectZoneSelector {


    /**
     * 选择项目根路径空间地址id
     *
     * @return
     */
    Integer getProjectZoneId();

    /**
     * 根据空间地址id获取具体地址位置
     *
     * @param id
     * @return
     */
    String getProjectZonePathById(Integer id);

    /**
     * 根据空间地址id获取具体访问端口
     *
     * @param id
     * @return
     */
    String getProjectZonePortById(Integer id);
}
