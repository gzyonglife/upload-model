package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.ProjectImplementationDao;
import com.jinlong.uploadmodel.dao.ProjectTableDao;
import com.jinlong.uploadmodel.entity.data.ProjectImplementation;
import com.jinlong.uploadmodel.entity.data.ProjectPlanTable;
import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectPlanTableVo;
import com.jinlong.uploadmodel.service.ProjectImplementationService;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author gzy
 * @Date 2021/1/26 15:23
 * @Version 1.0
 */

@Service
@Slf4j
public class ProjectImplementationServiceImpl implements ProjectImplementationService {
    @Autowired
    ProjectImplementationDao projectImplementationDao;

    @Autowired
    ProjectTableDao projectDao;

    @Override
    public Boolean addProjectImplementation(ProjectImplementation projectImplementation) {
        return projectImplementationDao.insert(projectImplementation)>0?true:false;
    }

    @Override
    public Boolean updateProjectImplementation(ProjectImplementation projectImplementation) {
        return projectImplementationDao.updateById(projectImplementation)>0?true:false;
    }

    @Override
    public Boolean delProjectImplementationAll(List list) {
        return projectImplementationDao.deleteBatchIds(list)>=1?true:false;
    }

    @Override
    public PageVo<ProjectImplementation> getProjectImplementationByLimt(Integer current, Integer size, String projectName, String implementationDate) throws ParseException {
        Page<ProjectImplementation> page = new Page<>(current, size);
        List<ProjectTable> listProject = null;
        List<Integer> projectIds= Collections.emptyList();
        if(projectName!=null && !projectName.equals("")){
            listProject = projectDao.selectList(new QueryWrapper<ProjectTable>().like("project_name",projectName));
            if(listProject==null||listProject.isEmpty()){
                return null;
            }else{
                projectIds=new ArrayList<>(listProject.size());
                for(ProjectTable plt : listProject){
                    projectIds.add(plt.getProjectId());
                }
            }
        }
        List<ProjectImplementation> lis = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Page<ProjectImplementation> projectImplementationPage = null;
        if(implementationDate!=null&&!implementationDate.equals("")&&projectIds!=null){
            projectImplementationPage = projectImplementationDao.selectPage(page, new QueryWrapper<ProjectImplementation>().lambda()
                    .eq(implementationDate!=null&&!implementationDate.equals(""),ProjectImplementation::getImplementationDate, sdf.parse(implementationDate + "-01"))
                    .in(projectIds!=null&&!projectIds.isEmpty(),ProjectImplementation::getProjectId, projectIds));
        }else{
            if(implementationDate!=null&&!implementationDate.equals("")){
                projectImplementationPage = projectImplementationDao.selectPage(page, new QueryWrapper<ProjectImplementation>().lambda()
                        .eq(implementationDate!=null&&!implementationDate.equals(""),ProjectImplementation::getImplementationDate, sdf.parse(implementationDate + "-01")));
            }else if (projectIds!=null){
                projectImplementationPage = projectImplementationDao.selectPage(page, new QueryWrapper<ProjectImplementation>().lambda()
                        .in(projectIds!=null&&!projectIds.isEmpty(),ProjectImplementation::getProjectId, projectIds));
            }else{
                projectImplementationPage = projectImplementationDao.selectPage(page,new QueryWrapper<>());
            }
        }
        PageVo<ProjectImplementation> pagevo = BeanBeanHelpUtils.copyProperties(projectImplementationPage, PageVo.class);
        if(!projectImplementationPage.getRecords().isEmpty()){
            pagevo.setData(projectImplementationPage.getRecords());
        }
        return pagevo;
    }
}
