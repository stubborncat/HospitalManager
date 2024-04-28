package com.rabbiter.hospital.service;

import com.rabbiter.hospital.pojo.Buy;
import com.rabbiter.hospital.pojo.Orders;
import com.rabbiter.hospital.utils.ResponseData;

import java.util.HashMap;

public interface BuyService {


    /**
     * 根据id设置缴费状态
     */
    Boolean updateBuy(int dbId);
    /**
     * 根据id设置夜间缴费状态
     */
    Boolean updatenightBuy(int dbId);
    /**
     * 根据id更新购买信息
     */
    Boolean updatePurch(Buy buy );
    /**
     * 增加购买信息
     */
    ResponseData addBuy(Buy buy, String arId);
    /**
     * 分页模糊查询所有购买信息
     */
    HashMap<String, Object> findAllBuy(int pageNumber, int size, String query);
}
