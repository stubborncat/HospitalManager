<template>
  <!-- 卡片 -->
  <el-card>
    <!-- 搜索栏及增加医生 -->
    <el-row type="flex">
      <el-col :span="8">
        <el-input v-model="query" placeholder="请输入姓名查询">
          <el-button
            slot="append"
            style="font-size: 18px;"
            @click="requestDrugadmin"
          ><i class="iconfont icon-r-find" style="font-size: 22px;"></i> 搜索</el-button>
        </el-input>
      </el-col>
      <el-col :span="4"></el-col>
      <el-col :span="4">
        <el-button type="primary" @click="addFormVisible = true"
                   style="font-size: 18px;"
        >
          <i class="iconfont icon-r-add" style="font-size: 22px;"></i>
          增加药局人员</el-button
        >
      </el-col>
      <el-col :span="4"></el-col>
      <!-- 导入数据 -->
      <!-- <el-col :span="2">
          <el-upload
              class="upload-demo"
              action="doctor/uploadExcel"
              accept=".xlsx,.xls "
              :limit="1"
              :show-file-list="false"
              :on-progress="handleProgress"
              :on-exceed="handleExceed"
              :on-success="handleSuccess"
              :on-error="handleError"
              :file-list="fileList"
          >
              <el-button size="small" type="success">一键导入</el-button>
          </el-upload>
      </el-col>

      <el-col :span="2">
          <el-button size="small" type="success" @click="exportDoctors"
              >一键导出</el-button
          ></el-col
      > -->
    </el-row>
    <!-- 表格 -->
    <el-table :data="drugadminData" stripe style="width: 100%" border>
      <el-table-column prop="daId" label="账号" width="100">
      </el-table-column>
      <el-table-column prop="daName" label="姓名" width="80">
      </el-table-column>
      <el-table-column prop="daGender" label="性别" width="60">
      </el-table-column>
      <el-table-column prop="daPhone" label="手机号">
      </el-table-column>
      <el-table-column prop="daEmail" label="邮箱" width="170">
      </el-table-column>
      <el-table-column label="操作" width="240" fixed="right">
        <template slot-scope="scope">
          <el-button
            style="font-size: 18px;"
            type="success"
            @click="modifyDialog(scope.row.daId)"
          ><i class="iconfont icon-r-edit" style="font-size: 22px;"></i> 编辑</el-button>
          <el-button
            style="font-size: 18px;"
            type="danger"
            @click="deleteDialog(scope.row.daId)"
          ><i class="iconfont icon-r-delete" style="font-size: 22px;"></i> 删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      background
      layout="total, sizes, prev, pager, next, jumper"
      :current-page="pageNumber"
      :page-size="size"
      :page-sizes="[1, 2, 4, 8, 16]"
      :total="total"
    >
    </el-pagination>
    <!-- 增加医生对话框 -->
    <el-dialog title="增加药房人员" :visible.sync="addFormVisible">
      <el-form :model="addForm" :rules="rules" ref="ruleForm">
        <el-form-item label="账号" label-width="80px" prop="daId">
          <el-input
            v-model.number="addForm.daId"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="80px" >
          <el-input
            v-model="addForm.daPassword"
            autocomplete="off"

          ></el-input>
        </el-form-item>
        <el-form-item label="姓名" label-width="80px" prop="daName">
          <el-input
            v-model="addForm.daName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" label-width="80px">
          <el-radio v-model="addForm.daGender" label="男">男</el-radio>
          <el-radio v-model="addForm.daGender" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="手机号" label-width="80px" prop="daPhone">
          <el-input
            v-model="addForm.daPhone"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="邮箱" label-width="80px" prop="daEmail">
          <el-input
            v-model="addForm.daEmail"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addFormVisible = false" style="font-size: 18px;"><i class="iconfont icon-r-left" style="font-size: 20px;"></i> 取 消</el-button>
        <el-button type="primary" @click="addDrugadmin('ruleForm')"
                   style="font-size: 18px;"><i class="iconfont icon-r-yes" style="font-size: 20px;"></i> 确 定</el-button
        >
      </div>
    </el-dialog>

    <!-- 修改医生对话框 -->
    <el-dialog title="修改药房信息" :visible.sync="modifyFormVisible">
      <el-form :model="modifyForm" :rules="rules" ref="ruleForm">
        <el-form-item label="账号" label-width="80px" prop="daId">
          <el-input
            v-model.number="modifyForm.daId"
            autocomplete="off"
            disabled
          ></el-input>
        </el-form-item>

        <el-form-item label="姓名" label-width="80px" prop="daName">
          <el-input
            v-model="modifyForm.daName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" label-width="80px">
          <el-radio v-model="modifyForm.daGender" label="男"
          >男</el-radio
          >
          <el-radio v-model="modifyForm.daGender" label="女"
          >女</el-radio
          >
        </el-form-item>
        <el-form-item label="手机号" label-width="80px" prop="daPhone">
          <el-input
            v-model="modifyForm.daPhone"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="邮箱" label-width="80px" prop="daEmail">
          <el-input
            v-model="modifyForm.daEmail"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="modifyFormVisible = false" style="font-size: 18px;"><i class="iconfont icon-r-left" style="font-size: 20px;"></i> 取 消</el-button>
        <el-button type="primary" @click="modifyDrugadmin('ruleForm')"
                   style="font-size: 18px;"><i class="iconfont icon-r-yes" style="font-size: 20px;"></i> 确 定</el-button
        >
      </div>
    </el-dialog>
  </el-card>
</template>
<script>
import request from "@/utils/request.js";
import { toLoad } from "@/utils/initialize.js";
import pharmacy from "@/views/Pharmacy.vue";
export default {
  name: "DrugadminList",
  computed: {
    drugadmin() {
      return drugadmin
    }
  },
  data() {
    var validateMoblie = (rule, value, callback) => {
      if (value === undefined) {
        callback(new Error("请输入手机号"));
      } else {
        let reg =
          /^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8]|8[0-9]|9[1,8,9])\d{8}$/;
        if (!reg.test(value)) {
          callback(new Error("请输入合法的手机号"));
        }
        callback();
      }
    };
    return {
      //文件上传
      fileList: [],
      pageNumber: 1,
      size: 8,
      query: "",
      drugadminData: [],
      total: 3,
      addFormVisible: false,
      addForm: {
        daPassword: 123456,
        daGender: "男",
      },
      rules: {
        daId: [
          { required: true, message: "请输入账号", trigger: "blur" },
          {
            type: "number",
            message: "账号必须数字类型",
            trigger: "blur",
          },
        ],
        daName: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          {
            min: 2,
            max: 5,
            message: "账号必须是2到5个字符",
            trigger: "blur",
          },
        ],
        daEmail: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],

        daPhone: [{ validator: validateMoblie }],
      },
      modifyFormVisible: false,
      modifyForm: {},
    };
  },
  methods: {
    //导出医生信息
    exportDrugadmins() {
      window.location.href = "http://localhost:9281/drugadmin/downloadExcel";
    },
    //文件上传
    handleProgress() {
      this.$message.warning("文件正在解析中！");
    },
    // 文件上传成功时的钩子
    handleSuccess() {
      this.$message.success("数据导入成功！");
      this.requestDoctors();
    },
    handleError() {
      //this.$message.error("数据导入失败！");
      this.$message.success("数据导入成功！");
      this.requestDoctors();
    },
    handleExceed() {
      this.$message.warning("当前限制选择 1 个文件");
    },
    //点击修改医生信息
    modifyDrugadmin(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request
            .get("admin/modifyDrugadmin", {
              params: {
                daId: this.modifyForm.daId,
                daGender: this.modifyForm.daGender,
                daName: this.modifyForm.daName,
                daPhone: this.modifyForm.daPhone,
                daEmail: this.modifyForm.daEmail,
              },
            })
            .then((res) => {
              if (res.data.status !== 200)
                return this.$message.error("修改信息失败！");
              this.modifyFormVisible = false;
              this.requestDrugadmin();
              this.$message.success("修改医生信息成功！");
              console.log(res);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    //打开修改对话框
    modifyDialog(id) {
      request
        .get("admin/findDrugadmin", {
          params: {
            daId: id,
          },
        })
        .then((res) => {
          if (res.data.status !== 200)
            this.$message.error("请求数据失败");
          this.modifyForm = res.data.data;
          this.modifyFormVisible = true;
          console.log(res);
        });
    },
    //删除医生操作
    deleteDrugadmin(id) {
      request
        .get("admin/deleteDrugadmin", {
          params: {
            daId: id,
          },
        })
        .then((res) => {
          this.requestDrugadmin();
          console.log(res);
        });
    },
    //删除对话框
    deleteDialog(id) {
      this.$confirm("此操作将删除该医生信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.deleteDrugadmin(id);
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    // 增加医生
    addDrugadmin(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request
            .get("admin/addDrugadmin", {
              params: {
                daId: this.addForm.daId,
                daGender: this.addForm.daGender,
                daPassword: this.addForm.daPassword,
                daName: this.addForm.daName,
                daPhone: this.addForm.daPhone,
                daEmail: this.addForm.daEmail,
              },
            })
            .then((res) => {
              if (res.data.status !== 200)
                return this.$message.error(
                  "账号不合法或已被占用！"
                );
              this.addFormVisible = false;
              this.requestDrugadmin();
              this.$message.success("增加药库人员成功！");
              console.log(res);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    //页面大小改变时触发
    handleSizeChange(size) {
      this.size = size;
      this.requestDrugadmin();
    },
    //   页码改变时触发
    handleCurrentChange(num) {
      console.log(num);
      this.pageNumber = num;
      this.requestDrugadmin();
    },
    // 加载医生列表
    requestDrugadmin() {
      request
        .get("admin/findAllDrugadmin", {
          params: {
            pageNumber: this.pageNumber,
            size: this.size,
            query: this.query,
          },
        })
        .then((res) => {
          this.drugadminData = res.data.data.doctors;

          this.total = res.data.data.total;
          toLoad()
        });
    },
  },
  created() {
    this.requestDrugadmin();
  },
};
</script>
<style scoped lang="scss">
.el-table {
  margin-top: 20px;
  margin-bottom: 20px;
}
.el-form {
  margin-top: 0;
}
</style>