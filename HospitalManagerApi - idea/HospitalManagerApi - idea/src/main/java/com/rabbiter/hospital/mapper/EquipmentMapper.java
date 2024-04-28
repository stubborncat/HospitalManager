package com.rabbiter.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.hospital.pojo.Equipment;

import java.util.Map;

public interface EquipmentMapper extends BaseMapper<Equipment> {

    Equipment login(Map<String, String> loginInfo);

    // 根据药房 ID 删除药房信息
    void deleteEquipmentById(int equipmentId);

}
