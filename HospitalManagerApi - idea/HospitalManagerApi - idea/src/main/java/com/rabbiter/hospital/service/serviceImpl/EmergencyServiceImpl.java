package com.rabbiter.hospital.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.EmergencyMapper;
import com.rabbiter.hospital.pojo.Buy;
import com.rabbiter.hospital.pojo.Emergency;
import com.rabbiter.hospital.service.EmergencyService;
import com.rabbiter.hospital.utils.RandomUtil;
import com.rabbiter.hospital.utils.ResponseData;
import com.rabbiter.hospital.utils.TodayUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service("Emergencyservice")

public class EmergencyServiceImpl implements EmergencyService {
    @Resource
    private EmergencyMapper emergencyMapper;

    /**
     * 增加购买信息
     */
    @Override
    public ResponseData addEmergency(Emergency emergency, String arId){
        emergency.setEdId(RandomUtil.randomOid(emergency.getdId()));
        emergency.setdState(0);
        int insert = this.emergencyMapper.insert(emergency);
        if(insert == 0 ){
            return ResponseData.fail("插入购买信息失败");
        }
        return ResponseData.success("插入购买信息成功",emergency.getEdId());
    }
    /**
     * 更新急诊信息
     */

    @Override
    public Boolean updateEmergency(Emergency emergency) {
        emergency.setdState(0);
        emergency.setdSum(emergency.getdTotalPrice() + emergency.getcTotalPrice());
        emergency.setDend(TodayUtil.getToday());
        QueryWrapper<Emergency> wrapper = new QueryWrapper<>();
        wrapper.eq("ed_id", emergency.getEdId());
        this.emergencyMapper.update(emergency, wrapper);
        return true;
    }


    /**
     * 根据id设置缴费状态
     */
    @Override
    public Boolean updateBuy(int edId){
        /**
         * 用QueryWrapper如果不把外键的值也传进来，会报错
         * 用UpdateWrapper就正常
         */
        UpdateWrapper<Emergency> wrapper = new UpdateWrapper<>();
        wrapper.eq("ed_id", edId).set("d_state",1).set("d_night",2);
        int i = this.emergencyMapper.update(null, wrapper);
        System.out.println("影响行数"+i);
        return true;
    }
    /**
     * 根据id设置缴费状态
     */
    @Override
    public Boolean updateNightBuy(int edId){
        /**
         * 用QueryWrapper如果不把外键的值也传进来，会报错
         * 用UpdateWrapper就正常
         */
        UpdateWrapper<Emergency> wrapper = new UpdateWrapper<>();
        wrapper.eq("ed_id", edId).set("d_state",1).set("d_night",1);
        int i = this.emergencyMapper.update(null, wrapper);
        System.out.println("影响行数"+i);
        return true;
    }

    /**
     * 分页模糊查询所有购买信息
     */
    @Override
    public HashMap<String, Object> findAllEmergency(int pageNumber, int size, String query) {
        Page<Emergency> page = new Page<>(pageNumber, size);
        QueryWrapper<Emergency> wrapper = new QueryWrapper<>();
        wrapper.like("ed_id", query);
        IPage<Emergency> iPage = this.emergencyMapper.selectPage(page , wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("buy", iPage.getRecords()); //查询到的记录
        return hashMap;
    }
}
