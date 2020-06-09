<template>
  <div id="course-list">
    <h1>所有课程</h1>
    <el-row class="list-filter">
      <!-- tab 切换 -->
      <el-col>
        <!-- 通过class绑定实现切换 -->
        <el-button :class="courseType == 0 ? 'el-button--primary' : 'el-button--text'"
                     size="mini" round @click="changeCourseType(0)">全部课程</el-button>
          <el-button :class="courseType == 1 ? 'el-button--primary' : 'el-button--text'"
                     size="mini" round @click="changeCourseType(1)" >自然科学</el-button>
          <el-button :class="courseType == 2 ? 'el-button--primary' : 'el-button--text'"
                     size="mini" round @click="changeCourseType(2)">园艺栽培</el-button>
          <el-button :class="courseType == 3 ? 'el-button--primary' : 'el-button--text'"
                     size="mini" round @click="changeCourseType(3)">创意绘画</el-button>
          <el-button :class="courseType == 4 ? 'el-button--primary' : 'el-button--text'"
                     size="mini" round @click="changeCourseType(4)">摄影技巧</el-button>
      </el-col>
    </el-row>
    <el-row :gutter="30">
      <!-- 单个的卡片列 -->
      <el-col :span="6" v-for="(item, index) in courseList"
              :index="index"
              :key="index"
              :class="courseType == 0 || courseType == item.courseType ? '' : 'hide'"><!-- 0 == flag || item.courseType == flag ? '' : 'hide' -->
        <!-- card div -->
        <router-link :to="'/home/course/' + item.courseNo">
          <div class="course" >
            <!-- img div -->
            <!-- 使用require方法请求图片地址实现图片绑定 -->
            <div :style="{background: 'transparent url(' + require('../assets/images/course/' + item.coursePic) + ') no-repeat scroll center center'}"
             class="course-img" >
              <!-- time div -->
              <div class="course-date">
                <!-- 使用过滤器实现日期格式化 -->
                <time class="time">{{item.startTime | date-format}}</time>
              </div>
            </div>
            <!-- info div -->
            <div class="course-info">
              <!-- class name div -->
              <div class="course-name">
                {{item.courseName}}
              </div>
              <!-- teacher name div -->
              <div class="teacher-name">
                {{item.teacherName}}
              </div>
            </div>
          </div>
        </router-link>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'course-list',
  data () {
    return {
      courseList: [],
      courseType: 0
    }
  },
  created: function() {
    var self = this;
    self.axios.get('/api/courseList')
     .then(function (response) {
       self.courseList = response.data.data;
       // alert("success" + response)
         // self.$message.success(response)
        // self.$message.success("success" + response)
     })
     .catch(function (error) {
       // alert(response)
       self.$message.error(response)
     });
  },
  methods: {
    changeCourseType: function(type) {
      this.courseType = type;
    }
  }
}
</script>

<style scoped>
@import '../assets/css/course-list.css';
</style>
