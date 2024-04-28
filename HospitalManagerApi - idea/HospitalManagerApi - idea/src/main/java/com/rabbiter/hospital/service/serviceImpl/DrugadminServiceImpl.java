package com.rabbiter.hospital.service.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.DrugadminMapper;
import com.rabbiter.hospital.pojo.Doctor;
import com.rabbiter.hospital.pojo.Drugadmin;
import com.rabbiter.hospital.service.DrugadminService;
import com.rabbiter.hospital.utils.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("DrugadminService")

public class DrugadminServiceImpl implements DrugadminService{
    @Resource
    private DrugadminMapper drugadminMapper;

    /**
     * 登录数据校验
     * */
    @Override
    public Drugadmin login(int daId, String daPassword) {
        Drugadmin drugadmin = this.drugadminMapper.selectById(daId);
        String password = Md5Util.getMD5(daPassword);
        if (drugadmin == null) {
            return null;
        } else {
            if ((drugadmin.getDaPassword()).equals(password)) {
                return drugadmin;
            }
        }
        return null;
    }

    /**
     * 根据id查找
     */
    @Override
    public Drugadmin findDrugadmin(int daId) {
        return this.drugadminMapper.selectById(daId);
    }

    /**
     * 分页模糊查询所有医护人员信息
     */
    @Override
    public HashMap<String, Object> findAllDrugadmin(int pageNumber, int size, String query) {
        Page<Drugadmin> page = new Page<>(pageNumber, size);
        QueryWrapper<Drugadmin> wrapper = new QueryWrapper<>();
        wrapper.like("da_name", query).orderByAsc("da_id");
        IPage<Drugadmin> iPage = this.drugadminMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("doctors", iPage.getRecords()); //查询到的记录
        return hashMap;
    }


    /**
     * 根据新建药局人员
     */
    @Override
    public Boolean addDrugadmin(Drugadmin drugadmin) {
        //如果账号已存在则返回false
        List<Drugadmin> drugadmins = this.drugadminMapper.selectList(null);
        for (Drugadmin drugadmin1 : drugadmins) {
            if (drugadmin.getDaId() == drugadmin1.getDaId()) {
                return false;
            }
        }
        //密码加密
        String password = Md5Util.getMD5(drugadmin.getDaPassword());
        drugadmin.setDaPassword(password);
        this.drugadminMapper.insert(drugadmin);
        return true;
    }

    /**
     * 删除药局人员信息
     */
    @Override
    public Boolean deleteDrugadmin(int daid) {
        Drugadmin drugadmin = new Drugadmin();
        drugadmin.setDaId(daid);
        this.drugadminMapper.updateById(drugadmin);
        return true;
    }
    /**
     * 修改药库信息
     */
    @Override
    public Boolean modifyDrugadmin(Drugadmin drugadmin) {
        int i = this.drugadminMapper.updateById(drugadmin);
        System.out.println("影响行数："+i);
        return true;
    }





}
