package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.Buy;
import com.rabbiter.hospital.pojo.Emergency;
import com.rabbiter.hospital.service.EmergencyService;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("emergency")
public class EmergencyController {
    @Autowired
    private EmergencyService emergencyService;

    /**
     * 增加购买信息
     */
    @RequestMapping("addEmergency")
    @ResponseBody
    public ResponseData addEmergency(Emergency emergency, String arId){
        System.out.println(arId);
        return this.emergencyService.addEmergency(emergency, arId);
    }

    /**
     * 根据id更新急诊信息
     */
    @PostMapping("updateEmergency")
    @ResponseBody
    public ResponseData updateEmergency(@RequestBody Emergency emergency) {
        if (this.emergencyService.updateEmergency(emergency)) return ResponseData.success("更新挂号信息成功");
        return ResponseData.fail("更新挂号信息失败！");
    }
    /**
     * 根据id设置缴费状态
     */
    @RequestMapping("updateBuy")
    public ResponseData updateBuy(int edId){
        if (this.emergencyService.updateBuy(edId))
            return ResponseData.success("根据id设置缴费状态成功");
        return ResponseData.success("根据id设置缴费状态失败");
    }
    /**
     * 根据id设置缴费状态
     */
    @RequestMapping("updateNightBuy")
    public ResponseData updateNightBuy(int edId){
        if (this.emergencyService.updateNightBuy(edId))
            return ResponseData.success("根据id设置缴费状态成功");
        return ResponseData.success("根据id设置缴费状态失败");
    }
}
