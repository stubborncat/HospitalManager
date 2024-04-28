<template>
  <!-- 卡片 -->
  <el-card>
    <!-- 搜索栏 -->
    <el-row type="flex">



      <el-col :span="6">
        <el-input v-model="query" placeholder="请输入患者id查询">
          <el-button
            slot="append"
            style="font-size: 18px;"
            @click="requestBuy"
          ><i class="iconfont icon-r-find" style="font-size: 22px;"></i> 搜索</el-button>
        </el-input>
      </el-col>

    </el-row>
    <!-- 表格 -->
    <el-table :data="buyData" stripe  border>
      <el-table-column
        prop="edId"
        label="急诊单号"
      ></el-table-column>
      <el-table-column
        prop="dId"
        label="医生工号"
      ></el-table-column>
      <el-table-column
        prop="dName"
        label="患者姓名"
      ></el-table-column>
      <el-table-column
        prop="dCard"
        label="患者身份证号"
      ></el-table-column>
      <el-table-column
        prop="dRecord"
        label="诊断结果"
      ></el-table-column>
      <el-table-column
        prop="dEnd"
        label="急诊时间"
      ></el-table-column>
      <el-table-column
        prop="dDrug"
        label="药物"
      ></el-table-column>
      <el-table-column
        prop="dCheck"
        label="检查"
      ></el-table-column>
      <el-table-column
        prop="cTotalPrice"
        label="检查费用/元"
      ></el-table-column>
      <el-table-column
        prop="dTotalPrice"
        label="药品费用/元"
      ></el-table-column>
      <el-table-column
        prop="dSum"
        label="共计/元"
      ></el-table-column>
      <el-table-column
        prop="dNight"
        label="出库路径">
        <template slot-scope="scope">
          <span v-if="scope.row.dNight === 2">药局</span>
          <span v-if="scope.row.dNight === 1">药柜</span>
          <span v-if="scope.row.dNight === 0"></span>
        </template>
      </el-table-column>
      <el-table-column prop="dState" label="缴费状态" >
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.dState === 1">已缴费</el-tag>
          <!-- <el-tag type="danger" v-if="scope.row.oPriceState === 0 && scope.row.oState === 1">未缴费</el-tag> -->
          <el-button
            type="danger"
            size="mini"
            v-if="scope.row.dState === 0"
            @click="priceClick(scope.row.edId)">
            点击缴费</el-button>
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
  </el-card>
</template>
<script>
import request from "@/utils/request.js";
import { toLoad } from "@/utils/initialize.js";
import MyOrder from "@/views/MyOrder.vue";
export default {
  name: "DepartmentList",
  data() {
    return {
      pageNumber: 1,
      size: 8,
      query: "",
      buyData: [],
      total: 3,
      options: [
        { label: '药局', value: 1 },
        { label: '药柜', value: 2 },
      ]
    };
  },
  methods: {
    //删除挂号操作
    deleteOrder(id) {
      request
        .get("admin/deleteOrder", {
          params: {
            oId: id,
          },
        })
        .then((res) => {
          this.requestBuy();
          console.log(res);
        });
    },
    //缴费
    priceClick(edId) {
      request
        .get("emergency/updateBuy", {
          params: {
            edId: edId,
          },
        })
        .then((res) => {
          if (res.data.status !== 200) {
            this.$message.error("请求数据失败");
            return;
          }
          this.$message.success("单号 " + edId + " 缴费成功！");
        });
      window.location.reload();
    },
    //页面大小改变时触发
    handleSizeChange(size) {
      this.size = size;
      this.requestBuy();
    },
    //   页码改变时触发
    handleCurrentChange(num) {
      console.log(num);
      this.pageNumber = num;
      this.requestBuy();
    },
    // 加载订单列表
    requestBuy() {
      request
        .get("admin/findAllEmergency", {
          params: {
            pageNumber: this.pageNumber,
            size: this.size,
            query: this.query,
          },
        })
        .then((res) => {
          this.buyData = res.data.data.buy;
          this.total = res.data.data.total;
          toLoad()
        });
    },
  },
  created() {
    this.requestBuy();
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