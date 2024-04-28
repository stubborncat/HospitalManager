<template>
  <!-- 卡片 -->
  <el-card>
    <!-- 搜索栏及增加药柜人员 -->
    <el-row type="flex">
      <el-col :span="8">
        <el-input v-model="query" placeholder="请输入姓名查询">
          <el-button
            slot="append"
            style="font-size: 18px;"
            @click="requestDrugagnts"
          ><i class="iconfont icon-r-find" style="font-size: 22px;"></i> 搜索</el-button>
        </el-input>
      </el-col>
      <el-col :span="4"></el-col>
      <el-col :span="4">
        <el-button type="primary" @click="addFormVisible = true"
                   style="font-size: 18px;"
        >
          <i class="iconfont icon-r-add" style="font-size: 22px;"></i>
          增加药柜人员</el-button
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
    <el-table :data="drugagntdata" stripe style="width: 100%" border>
      <el-table-column prop="nId" label="账号" width="100">
      </el-table-column>
      <el-table-column prop="nName" label="姓名" width="80">
      </el-table-column>
      <el-table-column prop="nGender" label="性别" width="60">
      </el-table-column>
      <el-table-column prop="nPhone" label="手机号">
      </el-table-column>
      <el-table-column prop="nEmail" label="邮箱" width="170">
      </el-table-column>
      <el-table-column label="操作" width="240" fixed="right">
        <template slot-scope="scope">
          <el-button
            style="font-size: 18px;"
            type="success"
            @click="modifyDialog(scope.row.nId)"
          ><i class="iconfont icon-r-edit" style="font-size: 22px;"></i> 编辑</el-button>
          <el-button
            style="font-size: 18px;"
            type="danger"
            @click="deleteNight(scope.row.nId)"
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
    <el-dialog title="增加药柜人员" :visible.sync="addFormVisible">
      <el-form :model="addForm" :rules="rules" ref="ruleForm">
        <el-form-item label="账号" label-width="80px" prop="nId">
          <el-input
            v-model.number="addForm.nId"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="80px" >
          <el-input
            v-model="addForm.nPassword"
            autocomplete="off"

          ></el-input>
        </el-form-item>
        <el-form-item label="姓名" label-width="80px" prop="nName">
          <el-input
            v-model="addForm.nName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" label-width="80px">
          <el-radio v-model="addForm.nGender" label="男">男</el-radio>
          <el-radio v-model="addForm.nGender" label="女">女</el-radio>
        </el-form-item>
        <el-form-item label="手机号" label-width="80px" prop="nPhone">
          <el-input
            v-model="addForm.nPhone"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="邮箱" label-width="80px" prop="nEmail">
          <el-input
            v-model="addForm.nEmail"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addFormVisible = false" style="font-size: 18px;"><i class="iconfont icon-r-left" style="font-size: 20px;"></i> 取 消</el-button>
        <el-button type="primary" @click="addNight('ruleForm')"
                   style="font-size: 18px;"><i class="iconfont icon-r-yes" style="font-size: 20px;"></i> 确 定</el-button
        >
      </div>
    </el-dialog>

    <!-- 修改医生对话框 -->
    <el-dialog title="修改药柜人员信息" :visible.sync="modifyFormVisible">
      <el-form :model="modifyForm" :rules="rules" ref="ruleForm">
        <el-form-item label="账号" label-width="80px" prop="nId">
          <el-input
            v-model.number="modifyForm.nId"
            autocomplete="off"
            disabled
          ></el-input>
        </el-form-item>

        <el-form-item label="姓名" label-width="80px" prop="nName">
          <el-input
            v-model="modifyForm.nName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" label-width="80px">
          <el-radio v-model="modifyForm.nGender" label="男"
          >男</el-radio
          >
          <el-radio v-model="modifyForm.nGender" label="女"
          >女</el-radio
          >
        </el-form-item>
        <el-form-item label="手机号" label-width="80px" prop="ePhone">
          <el-input
            v-model="modifyForm.nPhone"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="邮箱" label-width="80px" prop="eEmail">
          <el-input
            v-model="modifyForm.nEmail"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="modifyFormVisible = false" style="font-size: 18px;"><i class="iconfont icon-r-left" style="font-size: 20px;"></i> 取 消</el-button>
        <el-button type="primary" @click="modifyNight('ruleForm')"
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
  name: "DrugAgntList",
  computed: {
    drugagnt() {
      return drugagnt
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
      drugagntdata: [],
      total: 3,
      addFormVisible: false,
      addForm: {
        nPassword: 123456,
        nGender: "男",
      },
      rules: {
        nId: [
          { required: true, message: "请输入账号", trigger: "blur" },
          {
            type: "number",
            message: "账号必须数字类型",
            trigger: "blur",
          },
        ],
        nName: [
          { required: true, message: "请输入姓名", trigger: "blur" },
          {
            min: 2,
            max: 5,
            message: "账号必须是2到5个字符",
            trigger: "blur",
          },
        ],
        nEmail: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],

        nPhone: [{ validator: validateMoblie }],
      },
      modifyFormVisible: false,
      modifyForm: {},
    };
  },
  methods: {
    //导出医生信息
    exportDrugagnts() {
      window.location.href = "http://localhost:9281/equipment/downloadExcel";
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
    modifyNight(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request
            .get("admin/modifyNight", {
              params: {
                nId: this.modifyForm.nId,
                nGender: this.modifyForm.nGender,
                nName: this.modifyForm.nName,
                nPhone: this.modifyForm.nPhone,
                nEmail: this.modifyForm.nEmail,
              },
            })
            .then((res) => {
              if (res.data.status !== 200)
                return this.$message.error("修改信息失败！");
              this.modifyFormVisible = false;
              this.requestDrugagnts();
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
        .get("admin/findNight", {
          params: {
            nId: id,
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
    deleteNight(id) {
      request
        .get("admin/deleteNight", {
          params: {
            nId: id,
          },
        })
        .then((res) => {
          this.requestDrugagnts();
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
          this.deleteNight(id);
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
    addNight(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          request
            .get("admin/addNight", {
              params: {
                nId: this.addForm.nId,
                nGender: this.addForm.nGender,
                nPassword: this.addForm.nPassword,
                nName: this.addForm.nName,
                nPhone: this.addForm.nPhone,
                nEmail: this.addForm.nEmail,
              },
            })
            .then((res) => {
              if (res.data.status !== 200)
                return this.$message.error(
                  "账号不合法或已被占用！"
                );
              this.addFormVisible = false;
              this.requestDrugagnts();
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
      this.requestDrugagnts();
    },
    //   页码改变时触发
    handleCurrentChange(num) {
      console.log(num);
      this.pageNumber = num;
      this.requestDrugagnts();
    },
    // 加载医生列表
    requestDrugagnts() {
      request
        .get("admin/findAllNight", {
          params: {
            pageNumber: this.pageNumber,
            size: this.size,
            query: this.query,
          },
        })
        .then((res) => {
          this.drugagntdata = res.data.data.doctors;

          this.total = res.data.data.total;
          toLoad()
        });
    },
  },
  created() {
    this.requestDrugagnts();
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