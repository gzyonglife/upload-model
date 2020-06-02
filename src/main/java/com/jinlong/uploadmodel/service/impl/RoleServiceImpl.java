package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.RoleTableDao;
import com.jinlong.uploadmodel.entity.data.RoleTable;
import com.jinlong.uploadmodel.entity.vo.RoleVo;
import com.jinlong.uploadmodel.service.RoleService;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: RoleServiceImpl
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/2 19:43
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleTableDao roleDao;


    @Override
    public List<RoleVo> getRoleList() {
        List<RoleTable> tableList = roleDao.selectList(new QueryWrapper<>());
        return BeanBeanHelpUtils.copyList(tableList, RoleVo.class);
    }
}
