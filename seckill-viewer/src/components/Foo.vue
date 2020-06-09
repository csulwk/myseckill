<template>
  <div id="foo">
    foo  ----- {{getUsername}}
    <button @click="changeUsername">Change username to 'foo'</button>
  
  </div>
</template>

<script>
export default {
  name: 'foo',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },

  methods:{
    changeUsername:function(){
      this.$store.dispatch('changeUsername', {username:foo})
      this.$router.push('/bar')
    }
  },
  computed: {
    getUsername: function(){
      return this.$store.getters.getUsername
    }
  }

}
</script>

<style lang="scss">
</style>
