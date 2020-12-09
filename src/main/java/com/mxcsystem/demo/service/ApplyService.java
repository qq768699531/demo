package com.mxcsystem.demo.service;

import com.mxcsystem.demo.entity.Apply;
import com.mxcsystem.demo.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {
    @Autowired
    private ApplyMapper applyMapper;

    /**
     *
     * @param apply 表单
     * @return 返回审批id
     */
    public int createNewApply(Apply apply){
        applyMapper.createNewApply(apply)
        return apply.getID();
    }
}
