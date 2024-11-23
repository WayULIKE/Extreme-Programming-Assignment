import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import( '../views/AboutView.vue')
  },
  {
    path: '/inputclass',
    name: 'input',
    component: () => import( '../views/AboutView.vue')
  },
  {
    path: '/delectclass',
    name: 'delect',
    component: () => import( '../views/delectView.vue')
  },
  {
    path: '/myclass',
    name: 'my',
    component: () => import( '../views/myclass.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
