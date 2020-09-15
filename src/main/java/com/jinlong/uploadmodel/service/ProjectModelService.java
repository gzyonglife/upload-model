package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectModelTable;
import com.jinlong.uploadmodel.entity.vo.ModelShowVo;
import com.jinlong.uploadmodel.entity.vo.ProjectModelVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * @description: ProjectModelService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/8 16:38
 */
public interface ProjectModelService {
    /**
     * 上传模型
     *
     * @param projectModelVo
     * @param folder
     * @return
     */
    Optional<ProjectModelVo> saveProjectModel(ProjectModelVo projectModelVo, MultipartFile[] folder);

    /**
     * 根据项目id获取项目模型列表
     *
     * @param projectId
     * @return
     */
    List<ModelShowVo> getProjectModelListByProjectId(Integer projectId);

    /**
     * 获取所有项目模型
     *
     * @return
     */
    List<ModelShowVo> getProjectModelList();

    /**
     * 修改模型视角
     * @param projectModelId
     * @param projectModelView
     * @return
     */
    Optional<ProjectModelVo> modifyProjectModelForView(Integer projectModelId, String projectModelView);

    /**
     * 修改模型信息
     * @param projectModelId
     * @param projectId
     * @param projectModelName
     * @param projectModelTypeId
     * @return
     */
    Boolean updateModel(Integer projectModelId,Integer projectId,String projectModelName,Integer projectModelTypeId);

    /**
     * 根据id获取模型信息
     * @param projectModelId
     * @return
     */
    ProjectModelTable getModelById(Integer projectModelId);
}
