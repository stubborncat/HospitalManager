package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Drugadmin;
import com.rabbiter.hospital.pojo.Night;
import com.rabbiter.hospital.service.NightService;
import com.rabbiter.hospital.utils.JwtUtil;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("night")
public class NightController {

    @Autowired
    private NightService nightService;

    /**
     * 登录数据验证
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(@RequestParam("nId") int nId, @RequestParam("nPassword") String nPassword) {
        Night night = this.nightService.login(nId, nPassword);
        if (night != null) {
            Map<String, String> map = new HashMap<>();
            map.put("nName", night.getnName());
            map.put("nId", String.valueOf(night.getnId()));
            String token = JwtUtil.getToken(map);
            map.put("token", token);
            return ResponseData.success("登录成功", map);
        } else {
            return ResponseData.fail("登录失败，密码或账号错误");
        }
}
}
