package com.jinlong.uploadmodel.config.selector;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.ProjectZoneTableDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @description: DefaultProjectZoneSelector
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/8 15:22
 */
@Data
@Configuration
//@ConditionalOnMissingBean(value = ProjectZoneSelector.class)
public class DefaultProjectZoneSelector implements ProjectZoneSelector {

    @Autowired
    private ProjectZoneTableDao projectZoneTableDao;

    /**
     * 选择项目根路径空间地址id
     * 选择第一个（只有一个空间地址）
     *
     * @return
     */
    @Override
    public Integer getProjectZoneId() {
        return new Random().nextInt(projectZoneTableDao.selectCount(new QueryWrapper<>())) + 1;
//        return 2;
    }

    /**
     * 根据空间地址id获取具体地址位置
     *
     * @return
     */
    @Override
    public String getProjectZonePathById(Integer id) {
        return projectZoneTableDao.selectById(id).getProjectZonePath();
    }

    /**
     * 根据空间地址id获取具体访问端口
     *
     * @param id
     * @return
     */
    @Override
    public String getProjectZonePortById(Integer id) {
        return projectZoneTableDao.selectById(id).getProjectZonePort();
    }

}
