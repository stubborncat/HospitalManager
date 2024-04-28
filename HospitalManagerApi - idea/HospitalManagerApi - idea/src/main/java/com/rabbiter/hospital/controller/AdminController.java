package com.rabbiter.hospital.controller;

import com.rabbiter.hospital.pojo.*;
import com.rabbiter.hospital.service.*;
import com.rabbiter.hospital.utils.JwtUtil;
import com.rabbiter.hospital.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PharmacyService pharmacyService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private DrugadminService drugadminService;
    @Autowired
    private BuyService buyService;
    @Autowired
    private NightService nightService;
    @Autowired
    private EmergencyService emergencyService;

    /**
     * 登录数据验证
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(@RequestParam("aId") int aId, @RequestParam("aPassword") String aPassword) {
        Admin admin = this.adminService.login(aId, aPassword);
        if (admin != null) {
            Map<String,String> map = new HashMap<>();
            map.put("aName", admin.getAName());
            map.put("aId", String.valueOf(admin.getAId()));
            String token = JwtUtil.getToken(map);
            map.put("token", token);
            return ResponseData.success("登录成功", map);
        } else {
            return ResponseData.fail("登录失败，密码或账号错误");
        }
    }

    /**
     * 分页模糊查询所有医护人员信息
     */
    @RequestMapping("findAllDoctors")
    public ResponseData findAllDoctors(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回医护人员信息成功",  this.doctorService.findAllDoctors(pageNumber, size, query));
    }/**
     * 分页模糊查询所有药库人员信息
     */
    @RequestMapping("findAllPharmacy")
    public ResponseData findAllPharmacy(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回药库人员信息成功",  this.pharmacyService.findAllPharmacy(pageNumber, size, query));
    }
    /**
     * 分页模糊查询所有医疗库人员信息
     */
    @RequestMapping("findAllEquipment")
    public ResponseData findAllEquipment(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回医疗库人员信息成功",  this.equipmentService.findAllEquipment(pageNumber, size, query));
    }
    /**
     * 分页模糊查询所有药局人员信息
     */
    @RequestMapping("findAllDrugadmin")
    public ResponseData findAllDrugadmin(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回药局人员信息成功",  this.drugadminService.findAllDrugadmin(pageNumber, size, query));
    }
    /**
     * 分页模糊查询所有药库人员信息
     */
    @RequestMapping("findAllNight")
    public ResponseData findAllNight(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回药局人员信息成功",  this.nightService.findAllNight(pageNumber, size, query));
    }
    /**
     * 根据id查找医生
     */
    @RequestMapping("findDoctor")
    public ResponseData findDoctor(@RequestParam(value = "dId") int dId) {
        return ResponseData.success("查询医生成功", this.doctorService.findDoctor(dId));
    }
    /**
     * 根据id查找药库
     */
    @RequestMapping("findPharmacy")
    public ResponseData findPharmacy(@RequestParam(value = "phId") int phId) {
        return ResponseData.success("查询医生成功", this.pharmacyService.findPharmacy(phId));
    }
    /**
     * 根据id查找药局
     */
    @RequestMapping("findDrugadmin")
    public ResponseData findDrugadmin(@RequestParam(value = "daId") int daId) {
        return ResponseData.success("查询药局人员成功", this.drugadminService.findDrugadmin(daId));
    }
    /**
     * 根据id查找医疗库
     */
    @RequestMapping("findEquipment")
    public ResponseData findEquipment(@RequestParam(value = "eId") int eId) {
        return ResponseData.success("查询医生成功", this.equipmentService.findEquipment(eId));
    }
    /**
     * 根据id查找药柜
     */
    @RequestMapping("findNight")
    public ResponseData findNight(@RequestParam(value = "nId") int nId) {
        return ResponseData.success("查询医生成功", this.nightService.findNight(nId));
    }
    /**
     * 增加医生信息
     */
    @RequestMapping("addDoctor")
    @ResponseBody
    public ResponseData addDoctor(Doctor doctor) {
        Boolean bo = this.doctorService.addDoctor(doctor);
        if (bo) {
            return ResponseData.success("增加医生信息成功");
        }
        return ResponseData.fail("增加医生信息失败！账号或已被占用");
    }
    /**
     * 增加药库人员信息
     */
    @RequestMapping("addPharmacy")
    @ResponseBody
    public ResponseData addPharmacy(Pharmacy pharmacy) {
        Boolean bo = this.pharmacyService.addPharmacy(pharmacy);
        if (bo) {
            return ResponseData.success("增加药库信息成功");
        }
        return ResponseData.fail("增加药库信息失败！账号或已被占用");
    }
    /**
     * 增加医疗库人员信息
     */
    @RequestMapping("addEquipment")
    @ResponseBody
    public ResponseData addEquipment(Equipment equipment) {
        Boolean bo = this.equipmentService.addEquipment(equipment);
        if (bo) {
            return ResponseData.success("增加医疗库信息成功");
        }
        return ResponseData.fail("增加医疗库信息失败！账号或已被占用");
    }
    /**
     * 增加药局人员信息
     */
    @RequestMapping("addDrugadmin")
    @ResponseBody
    public ResponseData addDrugadmin(Drugadmin drugadmin) {
        Boolean bo = this.drugadminService.addDrugadmin(drugadmin);
        if (bo) {
            return ResponseData.success("增加医疗库信息成功");
        }
        return ResponseData.fail("增加医疗库信息失败！账号或已被占用");
    }
    /**
     * 增加药柜人员信息
     */
    @RequestMapping("addNight")
    @ResponseBody
    public ResponseData addNight(Night night) {
        Boolean bo = this.nightService.addNight(night);
        if (bo) {
            return ResponseData.success("增加医疗库信息成功");
        }
        return ResponseData.fail("增加医疗库信息失败！账号或已被占用");
    }

    /**
     * 删除医生信息
     */
    @RequestMapping("deleteDoctor")
    public ResponseData deleteDoctor(@RequestParam(value = "dId") int dId) {
        Boolean bo = this.doctorService.deleteDoctor(dId);
        if (bo){
            return ResponseData.success("删除医生信息成功");
        }
        return ResponseData.fail("删除医生信息失败");
    }
    /**
     * 删除药库人员信息
     */
    @RequestMapping("deletePharmacy")
    public ResponseData deletePharmacy(@RequestParam(value = "phId") int phId) {
        Boolean bo = this.pharmacyService.deletePharmacy(phId);
        if (bo){
            return ResponseData.success("删除药库信息成功");
        }
        return ResponseData.fail("删除药库信息失败");
    }
    /**
     * 删除医疗库人员信息
     */
    @RequestMapping("deleteEquipment")
    public ResponseData deleteEquipment(@RequestParam(value = "eId") int eId) {
        Boolean bo = this.equipmentService.deleteEquipment(eId);
        if (bo){
            return ResponseData.success("删除药库信息成功");
        }
        return ResponseData.fail("删除药库信息失败");
    }
    /**
     * 删除药局人员信息
     */
    @RequestMapping("deleteDrugadmin")
    public ResponseData deleteDrugadmin(@RequestParam(value = "daId") int daId) {
        Boolean bo = this.drugadminService.deleteDrugadmin(daId);
        if (bo){
            return ResponseData.success("删除药库信息成功");
        }
        return ResponseData.fail("删除药库信息失败");
    }
    /**
     * 删除药柜人员信息
     */
    @RequestMapping("deleteNight")
    public ResponseData deleteNight(@RequestParam(value = "nId") int nId) {
        Boolean bo = this.nightService.deleteNight(nId);
        if (bo){
            return ResponseData.success("删除药库信息成功");
        }
        return ResponseData.fail("删除药库信息失败");
    }
    /**
     * 修改医生信息
     * bug: dState会自动更新为0
     */
    @RequestMapping("modifyDoctor")
    @ResponseBody
    public ResponseData modifyDoctor(Doctor doctor) {
        this.doctorService.modifyDoctor(doctor);
        return ResponseData.success("修改医生信息成功");
    }
    /**
     * 修改药库人员信息
     */
    @RequestMapping("modifyPharmacy")
    @ResponseBody
    public ResponseData modifyPharmacy(Pharmacy pharmacy) {
        this.pharmacyService.modifyPharmacy(pharmacy);
        return ResponseData.success("修改药库信息成功");
    }
    /**
     * 修改医疗库人员信息
     */
    @RequestMapping("modifyEquipment")
    @ResponseBody
    public ResponseData modifyEquipment(Equipment equipment) {
        this.equipmentService.modifyEquipment(equipment);
        return ResponseData.success("修改医疗库信息成功");
    }

    /**
     * 修改药局人员信息
     */
    @RequestMapping("modifyDrugadmin")
    @ResponseBody
    public ResponseData modifyDrugadmin(Drugadmin drugadmin) {
        this.drugadminService.modifyDrugadmin(drugadmin);
        return ResponseData.success("修改药局人员信息成功");
    }
    /**
     * 修改药柜人员信息
     */
    @RequestMapping("modifyNight")
    @ResponseBody
    public ResponseData modifyNight(Night night) {
        this.nightService.modifyNight(night);
        return ResponseData.success("修改药局人员信息成功");
    }


    /**
     * 分页模糊查询所有患者信息
     */
    @RequestMapping("findAllPatients")
    public ResponseData findAllPatients(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回患者信息成功",  this.patientService.findAllPatients(pageNumber, size, query));
    }
    /**
     * 删除患者信息
     */
    @RequestMapping("deletePatient")
    public ResponseData deletePatient(@RequestParam(value = "pId") int pId) {
        Boolean bo = this.patientService.deletePatient(pId);
        if (bo){
            return ResponseData.success("删除患者信息成功");
        }
        return ResponseData.fail("删除患者信息失败");
    }
    /**
     * 分页模糊查询所有挂号信息
     */
    @RequestMapping("findAllOrders")
    public ResponseData findAllOrders(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回挂号信息成功",  this.orderService.findAllOrders(pageNumber, size, query));
    }
    /**
     * 分页模糊查询所有购买信息
     */
    @RequestMapping("findAllBuy")
    public ResponseData findAllBuy(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回购买信息成功",  this.buyService.findAllBuy(pageNumber, size, query));
    }
    /**
     * 分页模糊查询所有急诊信息
     */
    @RequestMapping("findAllEmergency")
    public ResponseData findAllEmergency(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "size") int size, @RequestParam(value = "query") String query){
        return ResponseData.success("返回购买信息成功",  this.emergencyService.findAllEmergency(pageNumber, size, query));
    }
    /**
     * 删除挂号信息
     */
    @RequestMapping("deleteOrder")
    public ResponseData deleteOrder(@RequestParam(value = "oId") int oId) {
        Boolean bo = this.orderService.deleteOrder(oId);
        if (bo){
            return ResponseData.success("删除挂号信息成功");
        }
        return ResponseData.fail("删除挂号信息失败");
    }

}
