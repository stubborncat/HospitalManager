package com.rabbiter.hospital.service;



import com.rabbiter.hospital.pojo.Doctor;
import com.rabbiter.hospital.pojo.Pharmacy;

import java.util.HashMap;

public interface PharmacyService {
    /**
     * 登录数据校验
     * */
    Pharmacy login(int phId, String phPassword);
    /**
     * 分页模糊查询药库人员
     */
    HashMap<String, Object> findAllPharmacy(int pageNumber, int size, String query);
    /**
     * 根据id查找药库人员
     */
    Pharmacy findPharmacy(int phId);
    /**
     * 增加药库人员信息
     */
    Boolean addPharmacy(Pharmacy pharmacy);
    /**
     * 删除药库人员信息
     */
    Boolean deletePharmacy(int phId);
    /**
     * 修改医生信息
     */
    Boolean modifyPharmacy(Pharmacy pharmacy);
}
