<template>

    <portal-template>
        <div id="login">
            <el-form label-position="top" label-width="80px" :model="user" ref="ruleForm" :rules="rules">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="user.username" placeholder="请输入用户名">
                    </el-input>
                </el-form-item>

                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="user.password" placeholder="请输入密码" @keypress.enter.native="login('userValid')">
                    </el-input>
                </el-form-item>

                <el-button type="primary" class="submit-btn" @click="login('ruleForm')">登录</el-button>
            </el-form>
            <router-link to="/register"><el-button type="text" icon="el-icon-edit">去注册页</el-button></router-link>

        </div>

    </portal-template>

</template>

<script>
import PortalTemplate from './PortalTemplate'
import { encryptWithSalt } from "../js/util/data-md5.js";
export default{
    name: 'login',
    components:{
        PortalTemplate
    },
    data () {
        return{
            user:{
                username:'test',
                password:'test'
            },
            msg: 'Welcome to login page',
            rules: {
            username:[
                  {required:true, message:'用户名不能为空', trigger:'blur'}
            ],
            password: [
                {required:true, message:'密码不能为空',trigger:'blur'}
            ]
            }
        }

    },
    methods:{
        login(formName){
            var self = this;
            self.$refs[formName].validate((valid) => {
                if (valid) {
                    self.$store.dispatch('login', {username: self.user.username, password: encryptWithSalt(self.user.password)})
                        .then((response) => {
                            self.$message.success(response.data.message);
                            self.$router.push('/home/course/list');
                        })
                        .catch((response) => {
                            self.$message.error(response.data.message)
                        })
                }
            });
        }
    }

}

</script>

<style lang="scss">
</style>
