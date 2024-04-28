package com.rabbiter.hospital.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.PharmacyMapper;
import com.rabbiter.hospital.pojo.Doctor;
import com.rabbiter.hospital.pojo.Pharmacy;
import com.rabbiter.hospital.service.PharmacyService;
import com.rabbiter.hospital.utils.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("PharmacyService")

public class PharmacyServiceImpl implements PharmacyService {

    @Resource
    private PharmacyMapper pharmacyMapper;

    /**
     * 登录数据校验
     * */
    @Override
    public Pharmacy login(int phId, String phPassword) {
        Pharmacy pharmacy = this.pharmacyMapper.selectById(phId);
        String password = Md5Util.getMD5(phPassword);
        if (pharmacy == null) {
            return null;
        } else {
            if ((pharmacy.getPhPassword()).equals(password)) {
                return pharmacy;
            }
        }
        return null;
    }

    /**
     * 分页模糊查询所有药库人员信息
     */
    @Override
    public HashMap<String, Object> findAllPharmacy(int pageNumber, int size, String query) {
        Page<Pharmacy> page = new Page<>(pageNumber, size);
        QueryWrapper<Pharmacy> wrapper = new QueryWrapper<>();
        wrapper.like("ph_name", query).orderByDesc("ph_id");
        IPage<Pharmacy> iPage = this.pharmacyMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("pharmacys", iPage.getRecords()); //查询到的记录
        return hashMap;
    }

    /**
     * 根据id查找医生
     */
    @Override
    public Pharmacy findPharmacy(int phId) {
        return this.pharmacyMapper.selectById(phId);
    }
    /**
     * 根据新建药库人员
     */
    @Override
    public Boolean addPharmacy(Pharmacy pharmacy) {
        //如果账号已存在则返回false
        List<Pharmacy> pharmacys = this.pharmacyMapper.selectList(null);
        for (Pharmacy pharmacy1 : pharmacys) {
            if (pharmacy.getPhId() == pharmacy1.getPhId()) {
                return false;
            }
        }
        //密码加密
        String password = Md5Util.getMD5(pharmacy.getPhPassword());
        pharmacy.setPhPassword(password);
        this.pharmacyMapper.insert(pharmacy);
        return true;
    }


    /**
     * 删除药库人员信息
     */
    @Override
    public Boolean deletePharmacy(int phId) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setPhId(phId);
        this.pharmacyMapper.deletePharmacyById(pharmacy.getPhId());
        return true;
    }

    /**
     * 修改医生信息
     */
    @Override
    public Boolean modifyPharmacy(Pharmacy pharmacy) {
//        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("d_id", doctor.getdId());
//        this.doctorMapper.update(doctor, queryWrapper);
        int i = this.pharmacyMapper.updateById(pharmacy);
        System.out.println("影响行数："+i);
        return true;
    }
}
