<template>
  <div id="course" v-loading.fullscreen.lock="load"
       element-loading-text="秒杀排队中"
       element-loading-background="rgba(0, 0, 0, 0.8)">
      <!-- 面包屑导航 -->
    <div>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item to="/home/course/list">首页</el-breadcrumb-item>
        <el-breadcrumb-item to="/home/course/list">课程列表</el-breadcrumb-item>
        <el-breadcrumb-item>{{course.courseName}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <h1>{{course.courseName}}</h1>
        <!-- 整个第三行的外层div - 阴影 -->
    <div class="detail">
      <el-row>
        <el-col :span="16" class="detail-left">
          <!-- 左侧底层图片div -->
          <div class="detail-pic"
            :style="{background: ' url(' + require('../assets/images/course/' + course.coursePic) + ')'}">
            <!-- 上层蓝色前景色div -->
            <div class="blue-bg">
            <!-- 内容布局div -->
              <div class="detail-content">
                <!-- 课程名加粗div -->
                <span class="detail-content-text">{{course.courseName}}</span>
                  <!-- 倒计时div -->
                <span class="detail-content-timer">{{courseTimerStatus}}</span>
              </div>
              <!-- 播放图标div -->
              <img class="course-play" src="../assets/images/play.png"/>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <!-- 右侧课程信息div -->
          <div class="detail-right">
            <!-- 右上讲师信息div -->
            <div class="detail-teacher-container">
              <!-- 讲师头像div -->
              <!-- <img src="../assets/images/sam.png"></img> -->
              <!-- 讲师姓名 -->
              <span>讲师: {{course.teacherName}}</span>
            </div>
            <!-- 右下课程信息div-->
            <div class="detail-info">
              <!-- 报名按钮 -->
              <div>
                <!--  0:立即报名, 1:查看订单, 2:课程售罄, 3:已结束  -->
                <el-button id="book-btn" type="info" v-if="showButtonType == 0"
                        ref="book" :class="isBookButtonDisabled ? '' : 'el-button--primary'" :disabled="isBookButtonDisabled"
                        @click="book">
                  <span>立即报名</span>
                </el-button>
                <!-- 使用router-link 来跳转到订单页面，也可以给这个按钮加一个监听 -->
                <router-link to="/home/order/list"><el-button type="info" v-if="showButtonType == 1" class="el-button--primary" id="trun-btn" >
                  <span>查看订单</span>
                </el-button></router-link>
                <el-button type="info" disabled v-if="showButtonType == 2" ><!----><!---->
                  <span>课程售罄</span>
                </el-button>
                <el-button type="info" disabled v-if="showButtonType == 3" ><!----><!---->
                  <span>已结束</span>
                </el-button>
              </div>
              <!-- 课程时间、描述div -->
              <div class="">
                <br/>
                <p>课程编号: {{course.courseNo}}<br/></p>
                <p>课程开始时间: {{course.startTime | date-format}}<br/></p>
                <p>课程结束时间: {{course.endTime | date-format}}<br/><br/></p>
                <p>{{course.courseDesciption}}</p>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'course',
  data () {
    return {
      course: {
        "courseNo": "",
        "courseName": "",
        "teacherName": "",
        "courseDesciption": "",
        "coursePeriod": "",
        "startTime": 0,
        "endTime": 0,
        "courcePrice": 0,
        "stockQuantity": 0,
        "courseType": 0,
        "coursePic": ""
      },
      // courseTimerStatus: '距离开课时间还有 1天20小时20分钟30秒',
      courseTimerStatus:'',
      timer: null,
      //是否启动计时器
      isTimerStop: true,
      showButtonType: 0,
      isBookButtonDisabled: false,
      load: false
    }
  },
  //初始化数据
  created() {
    //距离开课时间还有 1天20小时20分钟30秒
    var self = this;
    // 从url中获取courseNo
    self.axios.get('/api/courseDetail/' + self.$route.params.courseNo)
     .then(function (response) {
       self.course = response.data.data.course;
       self.checkTimerStatus();
     })
     .catch(function (error) {
       self.$message.error(response)
     });
  },

  methods: {
    //秒课功能
    // 1.获取下单地址 -》 '/api/getPath/' + self.$route.params.courseNo
    // 2.根据地址下单 -》 `/api/${path}/seckill/${courseNo}`
    book() {
      var self = this;
      self.axios.get('/api/getPath/' + self.$route.params.courseNo)
       .then(function (response) {
         var path = response.data;
         var courseNo = self.$route.params.courseNo;
         self.axios.get(`/api/${path}/seckill/${courseNo}`)
          .then(function (response) {
            self.getResult(response)
          })
          .catch(function (error) {
            self.$message.error('出错')
          });
       })
       .catch(function (error) {
         self.$message.error('/秒课功能出错')
       });
    },
    //轮询：查询购买
    getResult(response) {
      var code = response.data.code;
      var self = this;
      switch(code) {
        case 200:
        //显示下单成功
          self.$message.success('你已下单成功');
          // self.$message.success(response.data.message);

          self.showButtonType = 1;
          //其他状态下，页面不转圈，下同
          self.load = false;
          break;
        case 500100:
          var self = this;
          //排队的时候，将按钮disable
          self.isBookButtonDisabled = true;
          //只有在500100排队中的时候，页面才显示转圈
          self.load = true;
          self.axios.get('/api/seckillResult/' + self.$route.params.courseNo)
           .then(function (response) {
             //如果还是排队中的话，调用本身
             self.getResult(response);
           })
           .catch(function (error) {
             self.$message.error(response)
           });
          break;
        case 500101:
          self.load = false;
          self.isBookButtonDisabled = false;
          self.$message.error(response.data.message);
          break;
        case 500102:
          self.load = false;
          self.showButtonType = 1;
          self.$message.error(response.data.message);
          break;
        case 500104:
          self.load = false;
          self.isBookButtonDisabled = false;
          self.$message.error(response.data.message);
          break;
        case 500105:
          self.load = false;
          self.isBookButtonDisabled = false;
          self.$message.error(response.data.message);
          break;

        default:
          self.$message.error('/查询购买出错??????')
          self.load = false; 
          break;
      }
    },
    //检查课程状态
    checkTimerStatus() {
      var self = this;
      var course = self.course;
      var startTime = new Date(course.startTime);
      var endTime = new Date(course.endTime);
      var currentTime = new Date();
      // 1. 课程开始时间前
      if (startTime > currentTime) {
        if (course.stockQuantity > 0) {
          self.isTimerStop = false;
          self.showButtonType = 0;
          self.isBookButtonDisabled = true;
          self.startTimer()
        } else {
          self.courseTimerStatus = '已售罄';
          self.showButtonType = 2;
        }
      }
      // 2. 课程开始时间后，结束前
      else if(startTime <= currentTime && endTime > currentTime) {
        if (course.stockQuantity > 0) {
          self.courseTimerStatus = '开始报名';
          self.showButtonType = 0;
          self.isBookButtonDisabled = false;
        } else {
          self.courseTimerStatus = '已售罄';
          self.showButtonType = 2;
        }
      }
      // 3. 课程结束后
      else {
        self.courseTimerStatus = '已结束';
        self.showButtonType = 3;
      }

    },
    startTimer() {
      var self = this;
      //setInterval 是设置定时，间隔一段时间执行里面的方法
      self.timer = setInterval(function() {
        if (self.isTimerStop) {
          clearInterval(self.timer)
        }
        //更新时间
        self.updateTimer()
      }, 1000)
    },
    updateTimer() {
      var self = this;
      var startTime = new Date(self.course.startTime);
      var currentTime = new Date();
      var leftTime = parseInt((startTime.getTime() - currentTime.getTime()) / 1000)
      var days = parseInt(leftTime / (24 * 60 * 60))
      var hours = parseInt(leftTime / (24 * 60) % 24)
      var mins = parseInt(leftTime / 60 % 60)
      var sec = parseInt(leftTime % 60)
      self.courseTimerStatus = `距离开课时间还有 ${days}天${hours}小时${mins}分钟${sec}秒`
      if (leftTime <= 0) {
        self.isTimerStop = true;
      }
    }
  }
}
</script>

<style >
@import '../assets/css/course.css'
</style>
