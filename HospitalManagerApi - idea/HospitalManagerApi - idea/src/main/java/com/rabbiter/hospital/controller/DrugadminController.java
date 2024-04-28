package com.rabbiter.hospital.controller;


import com.rabbiter.hospital.pojo.Drugadmin;
import com.rabbiter.hospital.service.DrugadminService;
import com.rabbiter.hospital.utils.JwtUtil;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("drugadmin")

public class DrugadminController {


    @Autowired
    private DrugadminService drugadminService;


    /**
     * 登录数据验证
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(@RequestParam("daId") int daId, @RequestParam("daPassword") String daPassword) {
        Drugadmin drugadmin = this.drugadminService.login(daId, daPassword);
        if (drugadmin != null) {
            Map<String, String> map = new HashMap<>();
            map.put("daName", drugadmin.getDaName());
            map.put("daId", String.valueOf(drugadmin.getDaId()));
            String token = JwtUtil.getToken(map);
            map.put("token", token);
            return ResponseData.success("登录成功", map);
        } else {
            return ResponseData.fail("登录失败，密码或账号错误");
        }


    }
}
