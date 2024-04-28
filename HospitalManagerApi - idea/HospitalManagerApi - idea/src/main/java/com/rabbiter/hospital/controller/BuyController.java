package com.rabbiter.hospital.controller;
import com.rabbiter.hospital.pojo.Buy;
import com.rabbiter.hospital.service.BuyService;
import com.rabbiter.hospital.utils.ResponseData;
import com.rabbiter.hospital.utils.TodayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("buy")
public class BuyController {
    @Autowired
    private BuyService buyService;
    /**
     * 根据id更新购买信息
     */
    @PostMapping("updatePurch")
    @ResponseBody
    public ResponseData updatePurch(@RequestBody Buy buy) {
        if (this.buyService.updatePurch(buy)) return ResponseData.success("更新挂号信息成功");
        return ResponseData.fail("更新挂号信息失败！");
    }
    /**
     * 根据id设置缴费状态
     */
    @RequestMapping("updateBuy")
    public ResponseData updateBuy(int dbId){
        if (this.buyService.updateBuy(dbId))
            return ResponseData.success("根据id设置缴费状态成功");
        return ResponseData.success("根据id设置缴费状态失败");
    }
    /**
     * 根据id设置夜间缴费状态
     */
    @RequestMapping("updatenightBuy")
    public ResponseData updatenightBuy(int dbId){
        if (this.buyService.updatenightBuy(dbId))
            return ResponseData.success("根据id设置缴费状态成功");
        return ResponseData.success("根据id设置缴费状态失败");
    }



}
