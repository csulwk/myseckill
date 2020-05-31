import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Element from 'element-ui'
import Axios from 'axios'
import VueAxios from 'vue-axios'
import Qs from 'qs'
import Vuex from 'vuex'

//import css
import 'element-ui/lib/theme-chalk/index.css'
//import js
import routes from './js/router/routes'
import storeModules from './js/store/store-modules'
import {formatDate} from './js/util/date-format'


Vue.use(VueRouter)
Vue.use(Element)
Vue.use(VueAxios, Axios)
Vue.use(Qs)
Vue.use(Vuex)

//使用Axios向后端服务器发送请求进行登录
Axios.defaults.baseURL = 'http://localhost:8080'
Axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
Axios.defaults.withCredentials = true


const store = new Vuex.Store({
 modules: storeModules
})

Vue.prototype.$store = store





// const routes = [
//   { path: '/foo', component: Foo },
//   { path: '/bar', component: Bar }
// ]


const router = new VueRouter({
  mode:'history',
  routes
})


//过滤器 调用 util 里的 date-format.js
Vue.filter('date-format', function (value) {
  var date = new Date(value)
  return formatDate(date, 'yyyy-MM-dd hh:mm')
})

// Vue.filter('date-format', function (value) {
//   var date = new Date(value)
//   return formatDate(date, 'yyyy-MM-dd hh:mm')
// })



new Vue({
  store, // store 的写法相当于写成 store: store
  router, // 同上
  el: '#app',
  render: h => h(App)
})
