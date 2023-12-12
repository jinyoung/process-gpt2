import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/managements',
      component: () => import('../components/ProcessManagerChat.vue'),
    },
    {
      path: '/process/:id',
      component: () => import('../components/ProcessDefinition.vue'),
    },
    {
      path: '/executes',
      component: () => import('../components/ProcessParticipantChat.vue'),
    },
    {
      path: '/todolists',
      component: () => import('../components/ui/TodolistGrid.vue'),
    },
  ],
})

export default router;
