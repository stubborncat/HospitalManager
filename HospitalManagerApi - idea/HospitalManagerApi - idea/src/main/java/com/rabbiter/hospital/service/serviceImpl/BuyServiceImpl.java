package com.rabbiter.hospital.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rabbiter.hospital.mapper.BuyMapper;
import com.rabbiter.hospital.pojo.Buy;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rabbiter.hospital.pojo.Orders;
import com.rabbiter.hospital.service.BuyService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.utils.RandomUtil;
import com.rabbiter.hospital.utils.ResponseData;
import com.rabbiter.hospital.utils.TodayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
@Service("Buyservice")

public class BuyServiceImpl implements BuyService {

    @Resource
    private BuyMapper buyMapper;
    @Autowired
    private JedisPool jedisPool;//redis连接池
    /**
     * 分页模糊查询所有购买信息
     */
    @Override
    public HashMap<String, Object> findAllBuy(int pageNumber, int size, String query) {
        Page<Buy> page = new Page<>(pageNumber, size);
        QueryWrapper<Buy> wrapper = new QueryWrapper<>();
        wrapper.like("p_id", query);
        IPage<Buy> iPage = this.buyMapper.selectPage(page , wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("buy", iPage.getRecords()); //查询到的记录
        return hashMap;
    }
    /**
     * 根据id更新挂号信息
     */
    @Override
    public Boolean updatePurch(Buy buy) {
        buy.setDbState(0);
        buy.setDbend(TodayUtil.getToday());
        QueryWrapper<Buy> wrapper = new QueryWrapper<>();
        wrapper.eq("db_id", buy.getDbId());
        this.buyMapper.update(buy, wrapper);
//        this.buyMapper.updateByIdTest(buy);
        return true;
    }
    /**
     * 根据id设置缴费状态
     */
    @Override
    public Boolean updateBuy(int dbId){
        /**
         * 用QueryWrapper如果不把外键的值也传进来，会报错
         * 用UpdateWrapper就正常
         */
        UpdateWrapper<Buy> wrapper = new UpdateWrapper<>();
        wrapper.eq("db_id", dbId).set("db_state",1);
        int i = this.buyMapper.update(null, wrapper);
        System.out.println("影响行数"+i);
        return true;
    }
    /**
     * 根据id设置夜间缴费状态
     */
    @Override
    public Boolean updatenightBuy(int dbId){
        /**
         * 用QueryWrapper如果不把外键的值也传进来，会报错
         * 用UpdateWrapper就正常
         */
        UpdateWrapper<Buy> wrapper = new UpdateWrapper<>();
        wrapper.eq("db_id", dbId).set("db_state",1);
        wrapper.eq("db_id", dbId).set("db_night",1);
        int i = this.buyMapper.update(null, wrapper);
        System.out.println("影响行数"+i);
        return true;
    }
    /**
     * 增加购买信息
     */
    @Override
    public ResponseData addBuy(Buy buy, String arId){
        buy.setDbId(RandomUtil.randomOid(buy.getpId()));
        buy.setDbState(0);
        int insert = this.buyMapper.insert(buy);
        if(insert == 0 ){
            return ResponseData.fail("插入购买信息失败");
        }
        return ResponseData.success("插入购买信息成功",buy.getDbId());
    }



}
