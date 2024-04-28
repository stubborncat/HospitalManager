package com.rabbiter.hospital.service;

import com.rabbiter.hospital.pojo.Drugadmin;

import java.util.HashMap;

public interface DrugadminService {
    /**
     * 登录数据校验
     * */
    Drugadmin login(int daId, String daPassword);
    /**
     * 分页模糊查询药局人员
     */
    HashMap<String, Object> findAllDrugadmin(int pageNumber, int size, String query);
    /**
     * 根据id查找药库人员
     */
    Drugadmin findDrugadmin(int dId);
    /**
     * 增加药局人员信息
     */
    Boolean addDrugadmin(Drugadmin drugadmin);
    /**
     * 删除药局人员信息
     */
    Boolean deleteDrugadmin(int daId);
    /**
     * 修改医生信息
     */
    Boolean modifyDrugadmin(Drugadmin drugadmin);
}
