<template>

  <portal-template>
    <div id="register">
      <el-form label-position="top" label-width="80px" :model="user"
       :rules="rules" ref="ruleForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="user.username" placeholder="请输用户名  "></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="user.password" placeholder="请输密码  "></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="repassword">
          <el-input type="password" v-model="user.repassword" placeholder="请确认密码  "></el-input>
        </el-form-item>
        <el-button class="submit-btn" type="primary" @click="register('ruleForm')">注册</el-button>
      </el-form>

      <router-link to="/login"><el-button type="text" icon="el-icon-edit">去登录页</el-button></router-link>
    </div>
  </portal-template>
</template>

<script>
import PortalTemplate from './PortalTemplate'
export default {
  name: 'register',
  components: {
    PortalTemplate
  },
  data () {
    return {
      user: {
        username: '',
        password: '',
        repassword: ''
      },
      msg: 'Welcome to Your Vue.js App',
      rules: {
        username: [
           { required: true, message: '请输入用户名', trigger: 'blur' }
         ],
         password: [
          { required: true, message: '请输入密码 ', trigger: 'blur' }
        ],
        repassword: [
         { required: true, message: '请确认密码 ', trigger: 'blur' },
         { validator: (rule, value, callback) => {
            if (value === '') {
              callback(new Error('请再次输入密码'));
            } else if (value !== this.user.password) {
              callback(new Error('两次输入密码不一致!'));
            } else {
              callback();
            }
          }, trigger: 'blur' }
       ]
      }
    }
  },
  methods: {
    register(formName) {
      var self = this;
      self.$refs[formName].validate((valid) => {
         if (valid) {
alert('可以注册了');
           // self.$store.dispatch('register', {username: self.user.username, password: self.user.password})
           // .then((response) => {
           //   self.$message.success(response.data.message)
           //   self.$router.push('/foo');
           // })
           // .catch((response) => {
           //   self.$message.error(response.data.message)
           // })
         }
       });
    }
  }
}
</script>

<style lang="scss">
</style>
