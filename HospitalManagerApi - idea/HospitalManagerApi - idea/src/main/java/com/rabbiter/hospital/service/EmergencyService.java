package com.rabbiter.hospital.service;

import com.rabbiter.hospital.pojo.Buy;
import com.rabbiter.hospital.pojo.Emergency;
import com.rabbiter.hospital.utils.ResponseData;

import java.util.HashMap;

public interface EmergencyService {

    /**
     * 增加急诊信息
     */
    ResponseData addEmergency(Emergency emergency, String arId);

    /**
     * 根据id更新购买信息
     */
    Boolean updateEmergency(Emergency emergency );


    /**
     * 根据id设置缴费状态
     */
    Boolean updateBuy(int edId);
    /**
     * 根据id设置缴费状态
     */
    Boolean updateNightBuy(int edId);
    /**
     * 分页模糊查询所有急诊信息
     */
    HashMap<String, Object> findAllEmergency(int pageNumber, int size, String query);
}
