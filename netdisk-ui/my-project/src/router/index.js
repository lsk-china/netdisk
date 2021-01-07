import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      // path: '/',
      // name: 'HelloWorld',
      // component: HelloWorld
      path: '/',
      name: 'login',
      component: () => import('@/components/login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/components/register.vue')
    },
    {
      path: '/doneRegister',
      name: 'doneRegister',
      component: () => import('@/components/done-register.vue')
    },
    {
      path: '/index',
      name: 'index',
      component: () => import('@/components/index.vue')
    }
  ]
})
