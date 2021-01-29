package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.FirmTableDao;
import com.jinlong.uploadmodel.dao.ProjectBeCompletedDao;
import com.jinlong.uploadmodel.dao.ProjectTableDao;
import com.jinlong.uploadmodel.entity.data.FirmTable;
import com.jinlong.uploadmodel.entity.data.ProjectBeCompleted;
import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectBeCompletedVo;
import com.jinlong.uploadmodel.service.ProjectBeCompletedService;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author gzy
 * @Date 2021/1/28 11:15
 * @Version 1.0
 */
@Service
@Slf4j
public class ProjectBeCompletedServiceImpl implements ProjectBeCompletedService {
    @Autowired
    ProjectBeCompletedDao projectBeCompletedDao;

    @Autowired
    ProjectTableDao projectTableDao;

    @Autowired
    FirmTableDao firmTableDao;

    /**
     * 项目竣工信息添加
     * @param ProjectBeCompleted
     * @return
     */
    @Override
    public Boolean addProjectBeCompleted(ProjectBeCompleted ProjectBeCompleted) {
        return projectBeCompletedDao.insert(ProjectBeCompleted)==1?true:false;
    }

    /**
     * 修改项目竣工信息
     * @param ProjectBeCompleted
     * @return
     */
    @Override
    public Boolean updateProjectBeCompleted(ProjectBeCompleted ProjectBeCompleted) {
        return projectBeCompletedDao.updateById(ProjectBeCompleted)==1?true:false;
    }

    /**
     * 模糊查询项目竣工信息
     * @param projectName
     * @param current
     * @param size
     * @return
     */
    @Override
    public PageVo<ProjectBeCompletedVo> getProjectBeCompleted(String projectName, Integer current, Integer size) {
        Page<ProjectBeCompleted> page = new Page<>(current,size);
        QueryWrapper<ProjectBeCompleted> que = new QueryWrapper<>();
        List<ProjectTable> listProject = null;
        List<Integer> projectIds= Collections.emptyList();
        if(projectName!=null&&!projectName.equals("")){
            listProject = projectTableDao.selectList(new QueryWrapper<ProjectTable>().like("project_name",projectName));
            if(listProject==null||listProject.isEmpty()){
                return null;
            }else{
                projectIds=new ArrayList<>(listProject.size());
                for(ProjectTable plt : listProject){
                    projectIds.add(plt.getProjectId());
                }
                que.lambda().in(projectIds!=null&&!projectIds.isEmpty(), ProjectBeCompleted::getProjectId, projectIds);
            }
        }
        page = projectBeCompletedDao.selectPage(page,que);
        PageVo<ProjectBeCompletedVo> pagevo = BeanBeanHelpUtils.copyProperties(page,PageVo.class);
        List<ProjectBeCompletedVo> list = BeanBeanHelpUtils.copyList(page.getRecords(),ProjectBeCompletedVo.class);
        for(int i = 0;i<list.size();i++){
            ProjectBeCompletedVo vo = list.get(i);
            vo.setProjectName(projectTableDao.selectById(list.get(i).getProjectId()).getProjectName());
            FirmTable firmTable = firmTableDao.selectById(list.get(i).getConstructionUnit());
            if(firmTable==null){
                vo.setFirmName("单位已被删除");
            }else{
                vo.setFirmName(firmTable.getFirmName());
            }

            list.set(i,vo);
        }
        pagevo.setData(list);
        return pagevo;
    }
}
