package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.FirmTableDao;
import com.jinlong.uploadmodel.entity.data.FirmTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.service.FirmTableService;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FirmTableServiceImpl implements FirmTableService {
    @Autowired
    FirmTableDao firmTableDao;

    @Override
    public FirmTable getFirmTableById(Integer firmId) {
        FirmTable firmTable = firmTableDao.selectById(firmId);
        return firmTable;
    }

    @Override
    public PageVo<FirmTable> getFirmTableByLimt(String firmName, Integer current, Integer size) {
        Page<FirmTable> page = new Page<>(current, size);
        QueryWrapper<FirmTable> qw = new QueryWrapper<>();
        if(firmName!=null&&!firmName.equals("")){
            qw.like("firm_name",firmName);
        }
        page = firmTableDao.selectPage(page,qw);
        PageVo<FirmTable> pageVo = BeanBeanHelpUtils.copyProperties(page, PageVo.class);
        pageVo.setData(page.getRecords());
        return pageVo;
    }
}
