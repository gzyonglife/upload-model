package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.vo.ProjectModelTypeVo;

import java.util.List;

/**
 * @description: ProjectModelTypeService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/11 11:15
 */
public interface ProjectModelTypeService {
    /**
     * 获取项目类型列表
     *
     * @return
     */
    List<ProjectModelTypeVo> getProjectModelTypeList();

    /**
     * 判断模型类型是否为文件夹
     * @param typeId
     * @return
     */
    boolean isFolder(Integer typeId);
}
