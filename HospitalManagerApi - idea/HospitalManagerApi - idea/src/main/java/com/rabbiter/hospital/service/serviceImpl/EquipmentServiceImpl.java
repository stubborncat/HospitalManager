package com.rabbiter.hospital.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.hospital.mapper.EquipmentMapper;
import com.rabbiter.hospital.pojo.Equipment;
import com.rabbiter.hospital.pojo.Pharmacy;
import com.rabbiter.hospital.service.EquipmentService;
import com.rabbiter.hospital.utils.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
@Service("EquipmentService")

public class EquipmentServiceImpl implements EquipmentService {

    @Resource
    private EquipmentMapper equipmentMapper;



    /**
     * 登录数据校验
     * */
    @Override
    public Equipment login(int eId, String ePassword) {
        Equipment equipment = this.equipmentMapper.selectById(eId);
        String password = Md5Util.getMD5(ePassword);
        if (equipment == null) {
            return null;
        } else {
            if ((equipment.getePassword()).equals(password)) {
                return equipment;
            }
        }
        return null;
    }



    /**
     * 分页模糊查询所有药库人员信息
     */
    @Override
    public HashMap<String, Object> findAllEquipment(int pageNumber, int size, String query) {
        Page<Equipment> page = new Page<>(pageNumber, size);
        QueryWrapper<Equipment> wrapper = new QueryWrapper<>();
        wrapper.like("e_name", query).orderByDesc("e_id");
        Page<Equipment> iPage = this.equipmentMapper.selectPage(page, wrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", iPage.getTotal());       //总条数
        hashMap.put("pages", iPage.getPages());       //总页数
        hashMap.put("pageNumber", iPage.getCurrent());//当前页
        hashMap.put("equipments", iPage.getRecords()); //查询到的记录
        return hashMap;
    }

    /**
     * 根据id查找医疗哭人员
     */
    @Override
    public Equipment findEquipment(int eId) {
        return this.equipmentMapper.selectById(eId);
    }
    /**
     * 新增医疗库人员信息
     */
    @Override
    public Boolean addEquipment(Equipment equipment) {
        //如果账号已存在则返回false
        List<Equipment> equipments = this.equipmentMapper.selectList(null);
        for (Equipment equipment1 : equipments) {
            if (equipment.geteId() == equipment1.geteId()) {
                return false;
            }
        }
        String password = Md5Util.getMD5(equipment.getePassword());
        equipment.setePassword(password);
        this.equipmentMapper.insert(equipment);
        return true;
    }
    /**
     * 删除医疗库人员信息
     */
    @Override
    public Boolean deleteEquipment(int eId) {
        Equipment equipment = new Equipment();
        equipment.seteId(eId);
        this.equipmentMapper.deleteEquipmentById(equipment.geteId());
        return true;
    }

    /**
     * 修改医疗库人员信息
     */
    @Override
    public Boolean modifyEquipment(Equipment equipment) {
        int i = this.equipmentMapper.updateById(equipment);
        System.out.println("影响行数："+i);
        return true;
    }



}
