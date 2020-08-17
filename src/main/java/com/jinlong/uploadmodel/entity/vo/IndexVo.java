package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class IndexVo {
    List<Object> data = new ArrayList<Object>();

    public boolean addData(Object object){
        if(object==null){
            return false;
        }
        data.add(object);
        return true;
    }

}
