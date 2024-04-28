package com.rabbiter.hospital.controller;


import com.rabbiter.hospital.pojo.Pharmacy;
import com.rabbiter.hospital.service.DoctorService;
import com.rabbiter.hospital.service.OrderService;
import com.rabbiter.hospital.service.PatientService;
import com.rabbiter.hospital.service.PharmacyService;
import com.rabbiter.hospital.utils.JwtUtil;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("pharmacy")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;



    /**
     * 登录数据验证
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(@RequestParam("phId") int phId, @RequestParam("phPassword") String phPassword) {
        Pharmacy pharmacy = this.pharmacyService.login(phId, phPassword);
        if (pharmacy != null) {
            Map<String,String> map = new HashMap<>();
            map.put("phName", pharmacy.getPhName());
            map.put("phId", String.valueOf(pharmacy.getPhId()));
            String token = JwtUtil.getToken(map);
            map.put("token", token);
            return ResponseData.success("登录成功", map);
        } else {
            return ResponseData.fail("登录失败，密码或账号错误");
        }
    }






}
