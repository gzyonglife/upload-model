package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.ProjectPublicityDao;
import com.jinlong.uploadmodel.dao.ProjectTableDao;
import com.jinlong.uploadmodel.entity.data.ProjectPublicity;
import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectPublicityVo;
import com.jinlong.uploadmodel.service.ProjectPublicityService;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author gzy
 * @Date 2021/1/28 11:24
 * @Version 1.0
 */

@Service
@Slf4j
public class ProjectPublicityServiceImpl implements ProjectPublicityService {
    @Autowired
    ProjectPublicityDao ProjectPublicityDao;

    @Autowired
    ProjectTableDao projectTableDao;

    /**
     * 添加项目公示信息
     * @param projectPublicity
     * @return
     */
    @Override
    public Boolean addProjectPublicity(ProjectPublicity projectPublicity) {
        return ProjectPublicityDao.insert(projectPublicity)==1?true:false;
    }

    /**
     * 修改项目公示信息
     * @param projectPublicity
     * @return
     */
    @Override
    public Boolean updateProjectPublicity(ProjectPublicity projectPublicity) {
        return ProjectPublicityDao.updateById(projectPublicity)==1?true:false;
    }

    /**
     * 模糊查询项目公示信息
     * @param projectName
     * @param current
     * @param size
     * @return
     */
    @Override
    public PageVo<ProjectPublicityVo> getProjectPublicity(String projectName, Integer current, Integer size) {
        Page<ProjectPublicity> page = new Page<>(current,size);
        QueryWrapper<ProjectPublicity> que = new QueryWrapper<>();
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
                que.lambda().in(projectIds!=null&&!projectIds.isEmpty(), ProjectPublicity::getProjectId, projectIds);
            }
        }
        page = ProjectPublicityDao.selectPage(page,que);
        PageVo<ProjectPublicityVo> pagevo = BeanBeanHelpUtils.copyProperties(page,PageVo.class);
        List<ProjectPublicityVo> list = BeanBeanHelpUtils.copyList(page.getRecords(),ProjectPublicityVo.class);
        for(int i = 0;i<list.size();i++){
            ProjectPublicityVo vo = list.get(i);
            vo.setProjectName(projectTableDao.selectById(list.get(i).getProjectId()).getProjectName());
            list.set(i,vo);
        }
        pagevo.setData(list);
        return pagevo;
    }
}
