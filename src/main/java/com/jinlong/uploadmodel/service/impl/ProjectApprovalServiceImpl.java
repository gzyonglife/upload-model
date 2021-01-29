package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.ProjectApprovalDao;
import com.jinlong.uploadmodel.dao.ProjectTableDao;
import com.jinlong.uploadmodel.entity.data.ProjectApproval;
import com.jinlong.uploadmodel.entity.data.ProjectImplementation;
import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectApprovalVo;
import com.jinlong.uploadmodel.service.ProjectApprovalService;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author gzy
 * @Date 2021/1/28 11:21
 * @Version 1.0
 */

@Service
@Slf4j
public class ProjectApprovalServiceImpl implements ProjectApprovalService {
    @Autowired
    ProjectApprovalDao projectApprovalDao;

    @Autowired
    ProjectTableDao projectTableDao;

    /**
     * 添加项目批准情况
     * @param ProjectApproval
     * @return
     */
    @Override
    public Boolean addProjectApproval(ProjectApproval ProjectApproval) {
        return projectApprovalDao.insert(ProjectApproval)==1?true:false;
    }

    /**
     * 模糊查询分页
     * @param projectName
     * @param current
     * @param size
     * @return
     */
    @Override
    public PageVo<ProjectApprovalVo> getProjectApproval(String projectName, Integer current, Integer size) {
        Page<ProjectApproval> page = new Page<>(current,size);
        QueryWrapper<ProjectApproval> que = new QueryWrapper<>();
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
                que.lambda().in(projectIds!=null&&!projectIds.isEmpty(), ProjectApproval::getProjectId, projectIds);
            }
        }
        page = projectApprovalDao.selectPage(page,que);
        PageVo<ProjectApprovalVo> pagevo = BeanBeanHelpUtils.copyProperties(page,PageVo.class);
        List<ProjectApprovalVo> list = BeanBeanHelpUtils.copyList(page.getRecords(),ProjectApprovalVo.class);
        for(int i = 0;i<list.size();i++){
            ProjectApprovalVo vo = list.get(i);
            vo.setProjectName(projectTableDao.selectById(list.get(i).getProjectId()).getProjectName());
            list.set(i,vo);
        }
        pagevo.setData(list);
        return pagevo;
    }

    /**
     * 修改项目批准情况
     * @param ProjectApproval
     * @return
     */
    @Override
    public Boolean updateProjectApproval(ProjectApproval ProjectApproval) {
        return projectApprovalDao.updateById(ProjectApproval)==1?true:false;
    }
}
