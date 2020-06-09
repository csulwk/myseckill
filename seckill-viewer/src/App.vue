<template>
<!-- import func from './vue-temp/vue-editor-bridge'; -->
  <div id="app">

    <!-- <h1>Hello Test</h1>
    <p>
      
      <router-link to="/foo">Go to foo</router-link>
      <router-link to="/bar">Go to bar</router-link>


      <button @click="gotoByUrl('/foo')">Go to Foo</button>
      <button @click="gotoByUrl('/bar')">Go to bar</button>
    </p> -->

    <!-- 路由出口 -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {

  name:'app',
  data(){
    return{

    }
  },
  methods: {
    gotoByUrl(url){
      //do something here
      this.$router.push(url)
    }
  }
}
</script>

<style lang="scss">
@import './assets/css/replace-elementui.css';
html{
  height: 100%;
  margin: 0;
  padding: 0;

}

body{
  height: 100%;
  margin: 0;
  padding: 0;
  font-family :"PingFang SC","Helvetica Neue","Hiragino Sans GB","Segoe UI","Microsoft YaHei","微软雅黑",sans-serif;

}


#app{
  width:100%;
  height: 100%;
  display:flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}


a{
  text-decoration: none;
}

</style>
