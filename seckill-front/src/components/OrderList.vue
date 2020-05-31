<template>
  <div id="order-list">
    <!-- 上面的筛选 -->
    <div class="order-list-title">
      <h1 class="order-list-title-name">我的订单</h1>
      <div class="order-list-title-filter">
          <!--    -1:已失效, 0:未支付, 1: 已完成, 2:全部 -->
        <el-button :class="orderStatus == 2 ? 'el-button--primary' : 'el-button--text'"
                   size="mini" round @click="changeOrderStatus(2)">全部</el-button>
        <el-button :class="orderStatus == 0 ? 'el-button--primary' : 'el-button--text'"
                   size="mini" round @click="changeOrderStatus(0)">未支付</el-button>
        <el-button :class="orderStatus == 1 ? 'el-button--primary' : 'el-button--text'"
                   size="mini" round @click="changeOrderStatus(1)">已完成</el-button>
        <el-button :class="orderStatus == -1 ? 'el-button--primary' : 'el-button--text'"
                   size="mini" round @click="changeOrderStatus(-1)">已失效</el-button>
      </div>
      <div class="order-list-search" >
        <el-input placeholder="搜索关键字" prefix-icon="el-icon-search" ></el-input>
      </div>
    </div>

    <!-- 筛选结果 -->
    <div class="order-list-body">
      <div v-for="(item, index) in orderList" :key='index' class="order-info-container"
           :class="orderStatus == 2 || orderStatus == item.payStatus ? '' : 'hide'"><!-- 2 == orderStatus || item.payStatus == orderStatus ? '' : 'hide' -->
        <div class="order-nubmer">
          <span>订单编号:&nbsp;&nbsp; {{item.orderId}}</span>
          <span class="order-datetime"><time>{{item.createDate | date-format}}</time></span>
        </div>

        <div class="order-info">
          <img :src="'/src/assets/images/course/' + item.coursePic" class="order-image">
          <!-- <img v-else src="/src/assets/images/course/c1.jpg" class="image order-image"> -->

          <div class="course-info">
            <span class="course-info-name">{{item.courseName}}</span>
            <span class="course-info-price" > ￥{{item.payPrice == null ? 0 : item.payPrice}} </span>
          </div>
          <div class="order-pay-info">
            <div v-if="item.payStatus == 1">
              <span>已完成</span><br/>
              <!-- <span>支付方式</span> -->
              <span v-if="item.payment == 0 ">支付宝支付</span>
              <span v-else-if="item.payment == 1">微信支付</span>
              <span v-else>其他支付</span>
            </div>
            <div v-else-if="item.payStatus == 0"><span>未支付</span></div>
            <div v-else><span>已失效</span></div>
            <!-- <div v-else> -->
              <!-- <span v-if="item.payStatus == -1 ">已失效</span> -->
              <!-- <span v-else>未支付</span> <br/> -->
              <!-- <span >未支付</span> -->
            <!-- </div> -->
          </div>
        </div>
      </div>
      <!-- <div v-if="itemsSize == 0" >
        <div class="no-data">暂无数据</div>
      </div> -->
    </div>
  </div>
</template>

<script>
export default {
  name: 'order-list',
  data () {
    return {
      orderList: [],
      orderStatus: 2
    }
  },
  created: function() {
    var self = this;
    self.axios.get('/api/orderList')
     .then(function (response) {
       //与返回值作比较
       if (response.data.code == 200) {
         self.orderList = response.data.data;
       } else {
         self.$message.error(response.data.message)
       }
     })
     .catch(function (error) {
       // alert(response)
       self.$message.error(response)
     });
  },
  methods: {
    changeOrderStatus: function(orderStatus) {
      this.orderStatus = orderStatus
    }
  }
}
</script>

<style>
@import '../assets/css/order-list.css';
</style>
