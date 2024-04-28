package com.rabbiter.hospital.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.NightMapper;
import com.rabbiter.hospital.pojo.Drugadmin;
import com.rabbiter.hospital.pojo.Night;
import com.rabbiter.hospital.service.NightService;
import com.rabbiter.hospital.utils.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("NightService")

public class NightServiceImpl implements NightService {

    @Resource
    private NightMapper nightMapper;
    /**
     * 登录数据校验
     * */
    @Override
    public Night login(int nId, String nPassword) {
        Night night = this.nightMapper.selectById(nId);
        String password = Md5Util.getMD5(nPassword);
        if (night == null) {
            return null;
        } else {
            if ((night.getnPassword()).equals(password)) {
                return night;
            }
        }
        return null;
    }
    /**
     * 分页模糊查询所有医护人员信息
     */
    @Override
    public HashMap<String, Object> findAllNight(int pageNumber, int size, String query) {
        Page<Night> page = new Page<>(pageNumber, size);
        QueryWrapper<Night> wrapper = new QueryWrapper<>();
        wrapper.like("n_name", query).orderByAsc("n_id");
        IPage<Night> iPage = this.nightMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("doctors", iPage.getRecords()); //查询到的记录
        return hashMap;
    }
    /**
     * 根据id查找
     */
    @Override
    public Night findNight(int nId) {
        return this.nightMapper.selectById(nId);
    }

    /**
     * 根据新建药局人员
     */
    @Override
    public Boolean addNight(Night night) {
        //如果账号已存在则返回false
        List<Night> nights = this.nightMapper.selectList(null);
        for (Night night1 : nights) {
            if (night.getnId() == night1.getnId()) {
                return false;
            }
        }
        //密码加密
        String password = Md5Util.getMD5(night.getnPassword());
        night.setnPassword(password);
        this.nightMapper.insert(night);
        return true;
    }

    /**
     * 删除药柜人员信息
     */
    @Override
    public Boolean deleteNight(int nid) {
        Night night = new Night();
        night.setnId(nid);
        this.nightMapper.updateById(night);
        return true;
    }
    /**
     * 修改药柜信息
     */
    @Override
    public Boolean modifyNight(Night night) {
        int i = this.nightMapper.updateById(night);
        System.out.println("影响行数："+i);
        return true;
    }
}
