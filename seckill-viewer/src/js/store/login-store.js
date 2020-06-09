import Vue from 'vue'
import qs from 'qs'

export default {
  state: {
    isLogin: false,
    user: {
      username: ''
    }
  },
  mutations: {
    login(state, payload) {
      state.isLogin = true
      state.user.username = payload.username
    },
    logout(state, payload) {
      state.isLogin = false
      state.user.username = payload.username
    }
  },
  actions: {
    login(context, payload) {
      return new Promise((resolve, reject) => {
        Vue.axios.post('/api/login', qs.stringify(payload))
         .then(function (response) {
           var result = response.data;
           if (result.code == 200) {
             context.commit('login', payload)
             resolve(response)
           } else {
             reject(response)
           }
         })
         .catch(function (error) {
           var errorData = {
             message: '系统出错'
           }
           error.data = errorData
           reject(error)
         });

      })

    }
  },
  getters: {
    getUsername(state) {
      return state.user.username
    }
  }
}
