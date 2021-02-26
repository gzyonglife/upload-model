package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.config.selector.ProjectZoneSelector;
import com.jinlong.uploadmodel.dao.*;
import com.jinlong.uploadmodel.entity.data.*;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectVo;
import com.jinlong.uploadmodel.service.ProjectApprovalService;
import com.jinlong.uploadmodel.service.ProjectClassTableService;
import com.jinlong.uploadmodel.service.ProjectService;
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
 * @description: ProjectServiceImpl
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 13:21
 */
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectTableDao projectDao;

    @Autowired
    ProjectZoneSelector projectZoneSelector;

    @Autowired
    ProjectPlanTableDao projectPlanTableDao;

    @Autowired
    ProjectCategoryTableDao projectCategoryTableDao;

    @Autowired
    AdministrativeTableDao administrativeTableDao;

    /**
     * 获取项目列表
     *
     * @param pageVo
     * @return
     */
    @Override
    public PageVo<ProjectVo> getProjectListOfPage(PageVo pageVo,Integer type) {
        if(type!=null&&type!=0){
            if(type==1){
                Page<ProjectTable> tablePage = new Page<>();
                tablePage.setRecords(projectDao.selectList(new QueryWrapper<>()));
                return PageVo.createPageVoOfPage(tablePage, ProjectVo.class);
            }
        }else{
            Page<ProjectTable> tablePage = projectDao.selectPage(
                    new Page<>(pageVo.getCurrent(), pageVo.getSize())
                    , new QueryWrapper<ProjectTable>().orderByDesc("create_time"));
            return PageVo.createPageVoOfPage(tablePage, ProjectVo.class);
        }
        return null;
    }

    /**
     * 获取项目列表
     *
     * @param pageVo
     * @param userId
     * @return
     */
    @Override
    public PageVo<ProjectVo> getProjectListOfPage(PageVo pageVo, Integer userId,Integer type) {
        Page<ProjectTable> tablePage = projectDao.selectPage(
                new Page<>(pageVo.getCurrent(), pageVo.getSize())
                , new QueryWrapper<ProjectTable>().eq("user_id", userId).orderByDesc("create_time"));

        return PageVo.createPageVoOfPage(tablePage, ProjectVo.class);
    }

    /**
     * 根据项目分类id查询项目
     *
     * @param categoryId
     * @param userId
     * @return
     */
    @Override
    public List<ProjectVo> getProjectByCategoryId(Integer categoryId, Integer userId) {
        List<ProjectTable> projectTables = projectDao.selectList(
                new QueryWrapper<ProjectTable>().
                        eq("user_id", userId).
                        eq("project_category_id", categoryId));
        Assert.assertCollectionNotEmpty(projectTables, CustomExceptionEnum.SERVER_ERROR);
        List<ProjectVo> projectVos = BeanBeanHelpUtils.copyList(projectTables, ProjectVo.class);
        Assert.assertCollectionNotEmpty(projectVos, CustomExceptionEnum.SERVER_ERROR);
        return projectVos;
    }

    /**
     * 根据项目分类id查询项目
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<ProjectVo> getProjectByCategoryId(Integer categoryId) {
        List<ProjectTable> projectTables = projectDao.selectList(
                new QueryWrapper<ProjectTable>().eq("project_category_id", categoryId));
        Assert.assertCollectionNotEmpty(projectTables, CustomExceptionEnum.SERVER_ERROR);
        List<ProjectVo> projectVos = BeanBeanHelpUtils.copyList(projectTables, ProjectVo.class);
        Assert.assertCollectionNotEmpty(projectVos, CustomExceptionEnum.SERVER_ERROR);
        return projectVos;
    }

    /**
     * 添加新项目
     *
     * @param projectVo
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectVo addProject(ProjectVo projectVo, Integer userId) {
        ProjectTable entity = BeanBeanHelpUtils.copyProperties(projectVo, ProjectTable.class);
        entity.setUserId(userId);
        entity.setProjectZoneId(projectZoneSelector.getProjectZoneId());
        File file = new File(projectZoneSelector.getProjectZonePathById(entity.getProjectZoneId()) + "/" + entity.getProjectName());
        if (file.exists()) {
            log.warn("文件路径已经存在，请检查存储空间");
            // TODO 抛出文件路径已经存在异常
            throw CustomExceptionEnum.SERVER_ERROR.getException();
        } else {
            file.mkdir();
        }
        if (projectDao.insert(entity) == 1) {
            return projectVo;
        }
        return null;
    }

    /**
     * 添加新项目
     *
     * @param projectVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectVo addProject(ProjectVo projectVo) {
        ProjectTable entity = BeanBeanHelpUtils.copyProperties(projectVo, ProjectTable.class);
        entity.setProjectZoneId(projectZoneSelector.getProjectZoneId());
        // 新建项目文件夹
        File file = new File(projectZoneSelector.getProjectZonePathById(entity.getProjectZoneId()) + "/" + entity.getProjectName());
        if (file.exists()) {
            log.warn("文件路径已经存在，请检查存储空间");
            // TODO 抛出文件路径已经存在异常
            throw CustomExceptionEnum.SERVER_ERROR.getException();
        } else {
            file.mkdir();
        }

        // 保存到数据库
        if (projectDao.insert(entity) == 1)
            return projectVo;
        return null;
    }


    /**
     * 根据项目id查询项目信息
     *
     * @param id
     * @return
     */
    @Override
    public Optional<ProjectVo> getProjectById(Integer id) {
        ProjectTable projectTable = projectDao.selectById(id);
        if(projectTable==null){
            return null;
        }
        ProjectVo projectVo = BeanBeanHelpUtils.copyProperties(projectTable, ProjectVo.class);
        return Optional.ofNullable(projectVo);
    }

    /**
     * 根据项目id以及用户id查询项目信息
     *
     * @param id
     * @param userDetailsId
     * @return
     */
    @Override
    public Optional<ProjectVo> getProjectById(Integer id, Integer userDetailsId) {
        ProjectTable projectTable = projectDao.selectById(id);
        if (userDetailsId.equals(projectTable.getUserId())) {
            ProjectVo projectVo = BeanBeanHelpUtils.copyProperties(projectTable, ProjectVo.class);
            return Optional.ofNullable(projectVo);
        }
        return Optional.empty();
    }

    /**
     * @param projectCategoryId
     * @param name
     * @param year
     * @param current
     * @param size
     * @return
     */
    @Override
    public PageVo<ProjectVo> searchProjectForPage(Integer projectCategoryId, String name, String year, Integer current, Integer size) {
        Page<ProjectTable> page = new Page<>(current, size);
        QueryWrapper<ProjectTable> projectTableQueryWrapper = new QueryWrapper<>();

//        PageVo<ProjectVo> pageVo = new PageVo<>();

        ProjectTable projectTable = new ProjectTable();

        if (projectCategoryId != null) {
            projectTable.setProjectCategoryId(projectCategoryId);
        }

        if (name != null) {
            projectTableQueryWrapper.like("project_name", name);
        }

        List<Integer> idList = Collections.emptyList();
        QueryWrapper<ProjectPlanTable> projectPlanQueryWrapper = new QueryWrapper<ProjectPlanTable>().select("distinct project_id");
        if(year!=null){
            projectPlanQueryWrapper.eq("project_plan_year",year+"-01-01");
            List<ProjectPlanTable> list = projectPlanTableDao.selectList(projectPlanQueryWrapper);
            if(!list.isEmpty()){
                idList = new ArrayList<>(list.size());
                for(ProjectPlanTable projectPlanTable : list){
                    idList.add(projectPlanTable.getProjectId());
                }
            }

            if(!idList.isEmpty()){
                projectTableQueryWrapper.lambda().in(ProjectTable::getProjectId, idList);
            }
        }



        projectTableQueryWrapper.setEntity(projectTable);
        Page<ProjectTable> tablePage = projectDao.selectPage(page, projectTableQueryWrapper.orderByDesc("create_time"));
        PageVo<ProjectVo> pageVo = BeanBeanHelpUtils.copyProperties(tablePage, PageVo.class);
        pageVo.setData(BeanBeanHelpUtils.copyList(tablePage.getRecords(),ProjectVo.class));
        return pageVo;
    }

    @Override
    public Boolean delPeojectAll(List list) {
        return projectDao.deleteBatchIds(list)>0;
    }

    @Override
    public ProjectVo updateProject(ProjectVo projectVo) {
        ProjectTable entity = projectDao.selectById(projectVo.getProjectId());
        entity.setProjectZoneId(projectZoneSelector.getProjectZoneId());
        File file = new File(projectZoneSelector.getProjectZonePathById(entity.getProjectZoneId()) + "/" + entity.getProjectName());
        file.renameTo(new File(projectZoneSelector.getProjectZonePathById(entity.getProjectZoneId()) + "/" + projectVo.getProjectName()));
        if(projectDao.updateById(BeanBeanHelpUtils.copyProperties(projectVo, ProjectTable.class))==1?true:false){
            return projectVo;
        }
        return null;
    }

    @Override
    public Boolean addImgVideo(MultipartFile[] imgFolder, MultipartFile[] videoFolder, Integer projectId) {
        ProjectTable projectTable = projectDao.selectById(projectId);
        // 断言数据库取出的对应项目数据不为空
        Assert.assertNotNull(projectTable, CustomExceptionEnum.SERVER_ERROR);
        String path = projectZoneSelector.getProjectZonePathById(projectTable.getProjectZoneId()) + "/" + projectTable.getProjectName();
        Set<String> set = new HashSet<>();
        //path += "/" + imgFolder.;
        if(imgFolder!=null){
            for (MultipartFile field : imgFolder) {
                String originalFilename = path;
                originalFilename+="/"+field.getOriginalFilename().substring(field.getOriginalFilename().indexOf("/")+1);
                projectTable.setImgUrl(originalFilename);
                projectDao.updateById(projectTable);
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
                    log.error("保存文件出错，异常信息为：{}", e.getMessage());
                    throw CustomExceptionEnum.SERVER_ERROR.getException();
                }

            }
        }
        if(videoFolder!=null){
            for (MultipartFile field : videoFolder) {
                String originalFilename = path;
                originalFilename+="/"+field.getOriginalFilename().substring(field.getOriginalFilename().indexOf("/")+1);
                projectTable.setVideoUrl(originalFilename);
                projectDao.updateById(projectTable);
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
                    log.error("保存文件出错，异常信息为：{}", e.getMessage());
                    throw CustomExceptionEnum.SERVER_ERROR.getException();
                }

            }
        }

        return true;
    }

    @Override
    public List<ProjectVo> setProjectById(List<ProjectTable> list) {
        List<ProjectVo> projectVoList = new ArrayList<>();
        if(!list.isEmpty()){
            for(ProjectTable projectTable : list){
                ProjectVo projectVo = BeanBeanHelpUtils.copyProperties(projectTable,ProjectVo.class);
                if(projectTable.getProjectCategoryId()!=null&&projectTable.getProjectCategoryId()!=0){
                    projectVo.setProjectClassTableName(
                            projectCategoryTableDao.selectById(projectTable.getProjectCategoryId()).
                                    getProjectCategoryName());
                }
                if(projectTable.getProjectParent()!=null&&projectTable.getProjectParent()!=0){
                    projectVo.setProjectParentName(
                            projectDao.selectById(projectTable.getProjectParent())
                            .getProjectName()
                    );
                }
                //青浦区行政区域名称没写
//                if(projectTable.getAdministrativeTableId()!=null&&projectTable.getAdministrativeTableId()!=0){
//                    projectVo.setAdministrativeTableName(
//                            administrativeTableDao.selectById(projectTable.getAdministrativeTableId())
//                            .getAdministrativeName()
//                    );
//                }
                projectVoList.add(projectVo);
            }
        }else{
            return null;
        }
        return projectVoList;
    }

    /**
     * 根据行政区域名称查询
     * @param administrativeName
     * @return
     */
    @Override
    public List<ProjectVo> getProjectByAdministrative(String administrativeName) {
        List<ProjectTable> list = projectDao.selectList(new QueryWrapper<ProjectTable>());
        List<ProjectVo> listProVo = new ArrayList<>();
        for(ProjectTable projectTable : list){
            if(projectTable.getAdministrativeTableId()!=null&&!projectTable.getAdministrativeTableId().equals("")){
                String str[] = projectTable.getAdministrativeTableId().split(",");
                int[] array = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
                for(int i : array){
                    AdministrativeTable administrativeTable = administrativeTableDao.selectById(i);
                    if(administrativeTable.getAdministrativeName().equals(administrativeName)){
                        listProVo.add(BeanBeanHelpUtils.copyProperties(projectTable,ProjectVo.class));
                    }
                }
            }
        }
        return listProVo;
    }

    /**
     * 查询今年所有的重点关注项目
     * @return
     */
    @Override
    public List<ProjectTable> getProjectByFoucus() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        List<ProjectPlanTable> list = projectPlanTableDao.selectList(new QueryWrapper<ProjectPlanTable>()
                .eq("is_focus", 1)
                .eq("project_plan_year",year+"-01-01"));
        List<Integer> idList = Collections.emptyList();
        if(!list.isEmpty()){
            idList = new ArrayList<>(idList.size());
            for(ProjectPlanTable projectPlanTable : list){
                idList.add(projectPlanTable.getProjectId());
            }
        }else{
            return null;
        }
        List<ProjectTable> projectList = projectDao.selectList(new QueryWrapper<ProjectTable>().lambda()
                .in(idList!=null&&!idList.isEmpty(),ProjectTable::getProjectId, idList));
        return projectList;
    }
}
