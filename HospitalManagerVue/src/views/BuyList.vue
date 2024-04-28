<template>
  <div>
    <el-card>
      <!-- 药物表格 -->
      <el-row>
        <el-col :span="12">
          <el-input
            v-model="queryDrug"
            placeholder="请输入名称查询"
            class="drugInput"
          >
            <el-button
              slot="append"
              icon="el-icon-search"
              @click="requestDrug"
            ></el-button>
          </el-input>
          <el-table :data="drugData" stripe border>
            <el-table-column label="编号" prop="drId"></el-table-column>
            <el-table-column label="名称" prop="drName"></el-table-column>
            <el-table-column label="剩余数量" prop="drNumber"></el-table-column>
            <el-table-column label="单位" prop="drUnit"></el-table-column>
            <el-table-column label="单价" prop="drPrice"></el-table-column>
            <el-table-column label="操作" width="140" fixed="right">
              <template slot-scope="scope">
                <el-button
                  type="success"
                  style="font-size: 14px;"
                  @click="addDrug(scope.row.drId)"
                >
                  <i class="iconfont icon-r-add" style="font-size: 16px;"></i>
                  增加</el-button
                >
              </template>
            </el-table-column>
          </el-table>
          <!-- 分页 -->
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            background
            layout="total, sizes, prev, pager, next"
            :total="total"
            :page-size="size"
            :page-sizes="[1, 2, 4, 8, 16]"
          >
          </el-pagination>
          <el-row></el-row>
        </el-col>
        <!-- 右边已选择的药物 -->
        <el-col :span="12" class="drugRigth">
          <el-table stripe border :data="drugBuyData" class="rigthTable">
            <el-table-column label="编号" prop="drId"></el-table-column>
            <el-table-column label="名称" prop="drName"></el-table-column>
            <el-table-column label="单价" prop="drPrice"></el-table-column>
            <el-table-column label="数量" prop="drNum"></el-table-column>
            <el-table-column label="小记" prop="drSum"></el-table-column>
            <el-table-column label="操作" width="140" fixed="right">
              <template slot-scope="scope">
                <el-button
                  type="danger"

                  style="font-size: 14px;"
                  icon="iconfont icon-r-delete"
                  @click="deleteDrug(scope.row.drId)"
                > 移除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
          <el-tag>共计：{{ dbTotalPrice }}元</el-tag>
        </el-col>
      </el-row>
      <el-col :span="3">
        <el-button type="success" @click="submitClick" style="font-size: 18px;"><i class="iconfont icon-r-yes" style="font-size: 22px"></i> 提交</el-button>
      </el-col>
    </el-card>
  </div>
</template>
<script>
import jwtDecode from "jwt-decode";
import { getToken } from "@/utils/storage.js";
import request from "@/utils/request.js";
import { toLoad } from "@/utils/initialize.js";
export default {
  name: "buyList",
  data() {
    return {
      dbId: 1,
      pId: 1,
      pName: "aa",
      drugData: [],
      size: 4,
      pageNumber: 1,
      total: 3,
      queryDrug: "",
      drugBuyData: [],
      dbTotalPrice: 0,
      idTime: "",
      buyForm: { buyDate: "" },
      buyDate: "",


    };
  },
  methods: {
    //根据id减少药物数量
    reduceDrugNumber(drId, usedNumber) {
      request
        .get("drug/reduceDrugNumber", {
          params: {
            drId: drId,
            usedNumber: usedNumber,
          },
        })
        .then((res) => {
          if (res.data.status !== 200) {
            this.$message.error("药物数量不足！！");
          }
        });
    },
    //点击提交按钮
    submitClick() {
      let data2 = {
        buy : {
          pId : this.drugBuyData.pId
        }
      }
      // const addBuyTest =  async() => {
      //   await
      request.post("patient/addBuy",data2)
      .then((res)=>{
        if (res.data.status !== 200) {
          this.$message.error("请求信息错误");
          return;
        }
        this.dbId = res.data.data;
        for (let i = 0; i < this.drugBuyData.length; i++) {
          this.reduceDrugNumber(
            this.drugBuyData[i].drId,
            this.drugBuyData[i].drNum
          );
        }

        let data = {
          dbId: this.dataPackage().dbId,
          pId: this.pId,
          pName: this.pName,
          dbDrug: this.dataPackage().dbDrug,
          dbTotalPrice: this.dataPackage().dbTotalPrice,
        };

        // console.log(data)
        // return
        request
          .post("buy/updatePurch", data)
          .then((res) => {
            if (res.data.status !== 200) {
              this.$message.error("请求信息错误");
              return;
            }
            this.$message.success("提交成功！请通知患者登录系统自助缴费！");
            this.$router.push("/patientLayout");
            console.log(res.data);
          })
          .catch((err) => {
            console.error(err);
          });
      })
    },
    //封装数据
    dataPackage() {
      let dbDrug = "";
      let dbTotalPrice = this.dbTotalPrice;
      let dbId = this.dbId;

      for (let i = 0; i < this.drugBuyData.length; i++) {
        dbDrug +=
          this.drugBuyData[i].drName +
          "*" +
          this.drugBuyData[i].drPrice +
          "(元)*" +
          this.drugBuyData[i].drNum +
          " ";
      }
      return { dbId, dbDrug, dbTotalPrice };

    },
    //药物页面大小切换时触发
    checkSizeChange(size) {
      this.checkSize = size;
      this.requestCheck();
    },

    /**
     * 此处逻辑较复杂，容易出现错误
     */
    //药物列表点击移除按钮
    deleteDrug(drId) {
      for (let i = 0; i < this.drugBuyData.length; i++) {
        if (this.drugBuyData[i].drId === drId) {
          for (let j = 0; j < this.drugData.length; j++) {
            if (this.drugData[j].drId === drId) this.drugData[j].drNumber += 1;
          }
          this.drugBuyData[i].drNum -= 1;
          this.drugBuyData[i].drSum =
            this.drugBuyData[i].drPrice * this.drugBuyData[i].drNum;
          this.dbTotalPrice -= this.drugBuyData[i].drPrice; //药物价格总计
          if (this.drugBuyData[i].drNum === 0) this.drugBuyData.splice(i, 1); //！！！！！！删除数组中下标为i的元素
        }
      }
    },
    //药物列表点击增加按钮
    addDrug(drId) {
      request
        .get("drug/findDrug", {
          params: {
            drId: drId,
          },
        })
        .then((res) => {
          if (res.data.status != 200) return this.$message.error("信息请求失败");
          //后端传过来的是对象，表格绑定的数组
          for (let i = 0; i < this.drugBuyData.length; i++) {
            if (this.drugBuyData[i].drId === res.data.data.drId) {
              for (let j = 0; j < this.drugData.length; j++) {
                if (
                  this.drugData[j].drId === res.data.data.drId &&
                  this.drugData[j].drNumber > 0
                ) {
                  this.drugData[j].drNumber -= 1;
                  this.drugBuyData[i].drNum += 1;
                  this.drugBuyData[i].drSum =
                    this.drugBuyData[i].drPrice * this.drugBuyData[i].drNum;
                  this.dbTotalPrice += this.drugBuyData[i].drPrice; //药物价格总计
                  //return;
                }
              }
              return;
            }
          }
          for (let j = 0; j < this.drugData.length; j++) {
            if (
              this.drugData[j].drId === res.data.data.drId &&
              this.drugData[j].drNumber <= 0
            )
              return;
          }
          this.drugBuyData.push({
            drId: res.data.data.drId,
            drPrice: res.data.data.drPrice,
            drName: res.data.data.drName,
            drNum: 1,
            drSum: res.data.data.drPrice,
          });
          for (let j = 0; j < this.drugData.length; j++) {
            if (this.drugData[j].drId === res.data.data.drId)
              this.drugData[j].drNumber -= 1;
          }
          this.dbTotalPrice += res.data.data.drPrice; //药物价格总计

          console.log(res.data.data);
        });
    },
    //药物页面大小切换时触发
    handleSizeChange(size) {
      this.size = size;
      this.requestDrug();
    },
    //药物页数切换时触发
    handleCurrentChange(num) {
      this.pageNumber = num;
      this.requestDrug();
    },

    //获取药物列表
    requestDrug() {
      request
        .get("drug/findAllDrugs", {
          params: {
            size: this.size,
            pageNumber: this.pageNumber,
            query: this.queryDrug,
          },
        })
        .then((res) => {
          if (res.data.status != 200) this.$message.error("获取信息失败");
          this.drugData = res.data.data.drugs;
          this.total = res.data.data.total;

          console.log("=============="+res.data.data);
        });
    },
    //获取患者信息
    requestPatient() {
      request
        .get("doctor/findPatientById", {
          params: {
            pId: this.pId,
          },
        })
        .then((res) => {
          console.log(res);
          if (res.data.status != 200) this.$message.error("获取信息失败");
          this.pName = res.data.data.pName;
          this.pGender = res.data.data.pGender;
          this.pPhone = res.data.data.pPhone;
        });
    },
    //token解码
    tokenDecode(token) {
      if (token !== null) return jwtDecode(token);
    },
  },
  created() {
    this.pName = this.tokenDecode(getToken()).pName;
    this.pId = this.tokenDecode(getToken()).pId;
    this.dbId = this.$route.query.dbId;
    this.requestPatient();
    this.requestDrug();
    this.requestCheck();
  },
};
</script>
<style lang="scss">
// 让文字居中
.drugRigth {
  text-align: center;
}
.el-tag {
  margin: 8px;
}
.rigthTable {
  margin-top: 56px;
  margin-left: 8px;
}
.drugInput {
  margin-top: 8px;
  margin-bottom: 8px;
}
.el-row {
  margin: 5px;
}
.orderInput {
  width: 240px;
}
.el-pagination {
  margin: 8px;
}
</style>