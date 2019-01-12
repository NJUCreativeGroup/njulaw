import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import BaseLayout from './views/BaseLayout.vue'
import Search from './views/SearchPage.vue'
import ContentPage from './views/ContentPage.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'base',
      component: BaseLayout,
      children: [
        {
          path: '',
          name: 'home',
          component: Home
        },
        {
          path: 'search/:kw',
          name: 'search',
          component: Search
        },
        {
          path: 'content/:id',
          name: 'content',
          component: ContentPage
        }
      ]
    }
  ]
})
