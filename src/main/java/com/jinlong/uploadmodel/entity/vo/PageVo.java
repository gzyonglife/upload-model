package com.jinlong.uploadmodel.entity.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: PageVo
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 9:13
 */
@Data
public class PageVo<T> {
    /**
     * 总条数
     */
    private Long total;
    /**
     * 当前页数
     */
    @NotNull(message = "current是必须的")
    @Min(value = 1, message = "当前页数不能小于1")
    private Long current;
    /**
     * 每页显示数量
     */
    private Long size;

    /**
     * 数据体
     */
    private List<T> data;

    public static <T, O> PageVo<T> createPageVoOfPage(Page<O> page, Class<T> t) {
        if (page.getRecords().isEmpty()) {
            return null;
        }
        PageVo<T> result = new PageVo<>();
        result.setTotal(page.getTotal());
        result.setSize(page.getSize());
        result.setCurrent(page.getCurrent());
        result.setData(BeanBeanHelpUtils.copyList(page.getRecords(), t));
        return result;
    }
}
