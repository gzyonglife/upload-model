package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.ProjectModelTypeTableDao;
import com.jinlong.uploadmodel.dao.ProjectZoneTableDao;
import com.jinlong.uploadmodel.entity.data.ProjectModelTypeTable;
import com.jinlong.uploadmodel.entity.vo.ProjectModelTypeVo;
import com.jinlong.uploadmodel.service.ProjectModelTypeService;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * @description: ProjectModelTypeServiceImpl
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/11 11:16
 */
@Slf4j
@Service
public class ProjectModelTypeServiceImpl implements ProjectModelTypeService {

    @Autowired
    ProjectModelTypeTableDao projectModelTypeDao;



    /**
     * 获取项目类型列表
     *
     * @return
     */
    @Override
    public List<ProjectModelTypeVo> getProjectModelTypeList() {
        try {
            List<ProjectModelTypeTable> projectModelTables = projectModelTypeDao.selectList(new QueryWrapper<>());
            if (projectModelTables.isEmpty())
                return Collections.emptyList();
            return BeanBeanHelpUtils.copyList(projectModelTables, ProjectModelTypeVo.class);
        } catch (Exception e) {
            log.error("查询模型类型异常，异常信息为：{}", e.getMessage());
            throw CustomExceptionEnum.SERVER_ERROR.getException();
        }
    }

    /**
     * 判断模型类型是否为文件夹
     *
     * @param typeId
     * @return
     */
    @Override
    public boolean isFolder(Integer typeId) {
        return projectModelTypeDao.selectById(typeId).getIsFolder();
    }


}
