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
        prop="dbId"
        label="挂号单号"
      ></el-table-column>
      <el-table-column
        prop="pId"
        label="患者id"
      ></el-table-column>
      <el-table-column
        prop="dbend"
        label="购买时间"
      ></el-table-column>
      <el-table-column
        prop="dbDrug"
        label="药物"
      ></el-table-column>
      <el-table-column
        prop="dbTotalPrice"
        label="费用/元"
      ></el-table-column>
      <el-table-column
        prop="dbNight"
        label="出库路径">
        <template slot-scope="scope">
          <span v-if="scope.row.dbnight === 1">药柜</span>
          <span v-else>药局</span>
        </template>
      </el-table-column>
      <el-table-column prop="dbState" label="缴费状态" >
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.dbState === 1">已缴费</el-tag>
          <!-- <el-tag type="danger" v-if="scope.row.oPriceState === 0 && scope.row.oState === 1">未缴费</el-tag> -->
          <el-button
            type="danger"
            size="mini"
            v-if="scope.row.dbState === 0"
            @click="priceClick(scope.row.dbId)">
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
  name: "BuyListAgain",
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
    priceClick(dbId) {
      request
        .get("buy/updateBuy", {
          params: {
            dbId: dbId,
          },
        })
        .then((res) => {
          if (res.data.status !== 200) {
            this.$message.error("请求数据失败");
            return;
          }
          this.$message.success("单号 " + dbId + " 缴费成功！");
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
        .get("admin/findAllBuy", {
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