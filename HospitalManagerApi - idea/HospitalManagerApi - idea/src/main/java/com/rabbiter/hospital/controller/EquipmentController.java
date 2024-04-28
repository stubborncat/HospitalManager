package com.rabbiter.hospital.controller;


import com.rabbiter.hospital.pojo.Equipment;
import com.rabbiter.hospital.pojo.Pharmacy;
import com.rabbiter.hospital.service.EquipmentService;
import com.rabbiter.hospital.service.PharmacyService;
import com.rabbiter.hospital.utils.JwtUtil;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("equipment")

public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;


    /**
     * 登录数据验证
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(@RequestParam("eId") int eId, @RequestParam("ePassword") String ePassword) {
        Equipment equipment = this.equipmentService.login(eId, ePassword);
        if (equipment != null) {
            Map<String, String> map = new HashMap<>();
            map.put("eName", equipment.geteName());
            map.put("eId", String.valueOf(equipment.geteId()));
            String token = JwtUtil.getToken(map);
            map.put("token", token);
            return ResponseData.success("登录成功", map);
        } else {
            return ResponseData.fail("登录失败，密码或账号错误");
        }


    }
}
