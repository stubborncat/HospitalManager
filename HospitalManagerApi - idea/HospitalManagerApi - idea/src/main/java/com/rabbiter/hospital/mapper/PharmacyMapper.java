package com.rabbiter.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.hospital.pojo.Pharmacy;

import java.util.List;
import java.util.Map;

public interface PharmacyMapper extends BaseMapper<Pharmacy> {
    Pharmacy login(Map<String, String> loginInfo);
    // 根据药房 ID 删除药房信息
    void deletePharmacyById(int pharmacyId);




}
