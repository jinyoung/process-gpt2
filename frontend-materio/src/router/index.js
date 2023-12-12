import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/todolists',
      component: () => import('../components/ui/TodolistGrid.vue'),
    },
  ],
})

export default router;
