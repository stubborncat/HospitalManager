package com.rabbiter.hospital.service;

import com.rabbiter.hospital.pojo.Equipment;

import java.util.HashMap;

public interface EquipmentService {



    /**
     * 登录数据校验
     * */
    Equipment login(int eId, String ePassword);
    /**
     * 分页模糊查询医疗库人员
     */
    HashMap<String, Object> findAllEquipment(int pageNumber, int size, String query);

    /**
     * 根据id查找药库人员
     */
    Equipment findEquipment(int eId);

    /**
     * 增加药库人员信息
     */
    Boolean addEquipment(Equipment equipment);
    /**
     * 删除医疗库人员信息
     */
    Boolean deleteEquipment(int eId);

    /**
     * 修改医生信息
     */
    Boolean modifyEquipment(Equipment equipment);


}
