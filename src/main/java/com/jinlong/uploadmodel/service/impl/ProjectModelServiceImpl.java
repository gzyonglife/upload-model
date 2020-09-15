package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.config.selector.ProjectZoneSelector;
import com.jinlong.uploadmodel.dao.ProjectModelTableDao;
import com.jinlong.uploadmodel.dao.ProjectModelTypeTableDao;
import com.jinlong.uploadmodel.dao.ProjectTableDao;
import com.jinlong.uploadmodel.entity.data.ProjectModelTable;
import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.vo.ModelShowVo;
import com.jinlong.uploadmodel.entity.vo.ProjectModelVo;
import com.jinlong.uploadmodel.service.ProjectModelService;
import com.jinlong.uploadmodel.service.ProjectModelTypeService;
import com.jinlong.uploadmodel.util.Assert;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @description: ProjectModelServiceImpl
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/8 16:39
 */
@Slf4j
@Service
public class ProjectModelServiceImpl implements ProjectModelService {

    @Autowired
    ProjectTableDao projectTableDao;

    @Autowired
    ProjectModelTableDao projectModelTableDao;


    @Autowired
    ProjectModelTypeService projectModelTypeService;

    @Autowired
    ProjectModelTypeTableDao projectModelTypeTableDao;

    @Autowired
    ProjectZoneSelector projectZoneSelector;

    /**
     * 上传模型
     *
     * @param projectModelVo
     * @param folder
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<ProjectModelVo> saveProjectModel(ProjectModelVo projectModelVo, MultipartFile[] folder) {
        String path = "";
        Set<String> set = new HashSet<>();

        // 获取模型名称
        ProjectModelTable modelTable = BeanBeanHelpUtils.copyProperties(projectModelVo, ProjectModelTable.class);
        // 断言对象转化后不为空
        Assert.assertNotNull(modelTable, CustomExceptionEnum.SERVER_ERROR);
        // 保存模型文件
        // 获取项目空间地址id
        ProjectTable projectTable = projectTableDao.selectById(modelTable.getProjectId());
        // 断言数据库取出的对应项目数据不为空
        Assert.assertNotNull(projectTable, CustomExceptionEnum.SERVER_ERROR);
        // 获取项目空间地址
        path = projectZoneSelector.getProjectZonePathById(projectTable.getProjectZoneId()) + "/" + projectTable.getProjectName();
        boolean isFolder = projectModelTypeService.isFolder(modelTable.getProjectModelTypeId());

        path += "/" + modelTable.getProjectModelPath();
        for (MultipartFile field : folder) {
            String originalFilename = path;
            if (isFolder){
                originalFilename+="/"+field.getOriginalFilename().substring(field.getOriginalFilename().indexOf("/")+1);
            }
            String folderPath = originalFilename.substring(0, originalFilename.lastIndexOf("/"));
            File folderFile = null;
            if (set.add(folderPath)) {
                folderFile = new File(folderPath);
                if (!folderFile.exists()) {
                    folderFile.mkdirs();
                }
            }
            File file = new File(originalFilename);
            try {
                field.transferTo(new File(originalFilename));
            } catch (IOException e) {
                log.error("保存模型出错，异常信息为：{}", e.getMessage());
                throw CustomExceptionEnum.SERVER_ERROR.getException();
            }

        }
        // 判断是否为文件
//        if (!projectModelTypeService.isFolder(modelTable.getProjectModelTypeId())) {
//            modelTable.setProjectModelPath(modelTable.getProjectModelPath());
//        }
        // 保存模型信息到数据库
        int insert = projectModelTableDao.insert(modelTable);
        // 断言数据库更改条数为1
        Assert.assertEqual(insert, 1, CustomExceptionEnum.SERVER_ERROR);
        return Optional.ofNullable(BeanBeanHelpUtils.copyProperties(modelTable, ProjectModelVo.class));
    }

    /**
     * 根据项目id获取项目模型列表
     *
     * @param projectId
     * @return
     */
    @Override
    public List<ModelShowVo> getProjectModelListByProjectId(Integer projectId) {
        // 获取模型基本信息
        List<ProjectModelTable> projectModelList = projectModelTableDao.selectList(new QueryWrapper<ProjectModelTable>().eq("project_id", projectId));
        List<ModelShowVo> modelShowVos = BeanBeanHelpUtils.copyList(projectModelList, ModelShowVo.class);

        for (ModelShowVo showVo : modelShowVos) {
            // 添加模型类型
            ProjectModelTable modelTable = projectModelTableDao.selectById(showVo.getProjectModelId());
            Integer typeId = modelTable.getProjectModelTypeId();
            showVo.setProjectModelType(projectModelTypeTableDao.selectById(typeId).getProjectModelType());
            // 获取项目信息
            ProjectTable projectTable = projectTableDao.selectById(projectId);
            // 添加模型端口与访问路径
            showVo.setPort(projectZoneSelector.getProjectZonePortById(projectTable.getProjectZoneId()));
            showVo.setProjectModelPath(projectTable.getProjectName() + "/" + showVo.getProjectModelPath());
        }

        return modelShowVos;
    }

    /**
     * 获取所有项目模型
     *
     * @return
     */
    @Override
    public List<ModelShowVo> getProjectModelList() {
        ArrayList<ModelShowVo> result = new ArrayList<>();
        // 查询所有项目id
        List<ProjectTable> projectTableList = projectTableDao.selectList(new QueryWrapper<ProjectTable>().select("project_id"));
        // 断言有项目数据信息
        Assert.assertCollectionNotEmpty(projectTableList, CustomExceptionEnum.GET_NONE);
        // 遍历查询模型
        for (ProjectTable projectTable : projectTableList) {
            result.addAll(getProjectModelListByProjectId(projectTable.getProjectId()));
        }
        if (!result.isEmpty()){
            return result;
        }
        return Collections.emptyList();
    }

    /**
     * 修改模型视角
     *
     * @param projectModelId
     * @param projectModelView
     * @return
     */
    @Override
    public Optional<ProjectModelVo> modifyProjectModelForView(Integer projectModelId, String projectModelView) {
        ProjectModelTable projectModelTable = new ProjectModelTable();
        projectModelTable.setProjectModelId(projectModelId);
        projectModelTable.setProjectModelView(projectModelView);
        projectModelTableDao.updateById(projectModelTable);
        return Optional.ofNullable(BeanBeanHelpUtils.copyProperties(projectModelTable, ProjectModelVo.class));
    }
    /**
     * 修改模型信息
     * @param projectModelId
     * @param projectId
     * @param projectModelName
     * @param projectModelTypeId
     * @return
     */
    @Override
    public Boolean updateModel(Integer projectModelId,Integer projectId, String projectModelName, Integer projectModelTypeId) {
        ProjectModelTable model = projectModelTableDao.selectById(projectModelId);
        if(projectId!=null&&projectId!=0){
            model.setProjectId(projectId);
        }
        if(projectModelName!=null&&!projectModelName.equals("")){
            model.setProjectModelName(projectModelName);
        }
        if(projectModelTypeId!=null&&projectModelTypeId!=0){
            model.setProjectModelTypeId(projectModelTypeId);
        }
        return projectModelTableDao.updateById(model)==1?true:false;
    }
    /**
     * 根据id获取模型信息
     * @param projectModelId
     * @return
     */
    @Override
    public ProjectModelTable getModelById(Integer projectModelId) {
        return projectModelTableDao.selectById(projectModelId);
    }

    private String getProjectModelName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName.substring(0, fileName.indexOf("/"));
    }
}