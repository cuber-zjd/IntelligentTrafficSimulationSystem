import { createRouter, createWebHistory } from 'vue-router'
import layout from '@/components/layout'
import route from '@/router/route'


const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('@/view/login')
    },
    {
        path: '/',
        name: 'main',
        component: layout,
        children: route
    },



]

const router = createRouter({
    history: createWebHistory(),
    routes,
})
export default router;