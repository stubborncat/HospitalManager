package com.rabbiter.hospital.service;

import com.rabbiter.hospital.pojo.Drugadmin;
import com.rabbiter.hospital.pojo.Equipment;
import com.rabbiter.hospital.pojo.Night;

import java.util.HashMap;

public interface NightService {
    /**
     * 登录数据校验
     * */
    Night login(int nId, String nPassword);
    /**
     * 分页模糊查询药局人员
     */
    HashMap<String, Object> findAllNight(int pageNumber, int size, String query);
    /**
     * 根据id查找药库人员
     */
    Night findNight(int nId);
    /**
     * 增加药柜人员信息
     */
    Boolean addNight(Night night);
    /**
     * 删除药柜人员信息
     */
    Boolean deleteNight(int nId);
    /**
     * 修改药柜信息
     */
    Boolean modifyNight(Night night);
}
