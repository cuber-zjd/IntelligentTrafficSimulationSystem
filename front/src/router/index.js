import { createRouter, createWebHistory } from 'vue-router'

const routes = [
    {
        path: '/',
        name: 'login',
        component: () => import('@/components/login')
    },
    {
        path: '/main',
        name: 'main',
        meta: {
            requiresAuth: true
        },
        component: () => import('@/components/main'),
        children: [
            // 用户
            {
                path: '/user',
                name: 'user',
                component: () => import('@/page/user'),
                children: [
                    {
                        path: '/message',
                        name: 'message',
                        component: () => import('@/page/user/edit/message'),
                    },
                    {
                        path: '/editpassword',
                        name: 'editpassword',
                        component: () => import('@/page/user/edit/editpassword'),
                    },
                ]
            },

            // 添加道路
            {
                path: '/addroad',
                name: 'addroad',
                component: () => import('@/page/add/addroad'),
            },

            // 添加数据
            {
                path: '/adddata',
                name: 'adddata',
                component: () => import('@/page/add/adddata'),
                // 道路类型
                children: [
                    {
                        path: '/cross',
                        name: 'cross',
                        component: () => import('@/page/add/adddata/roadtype/cross'),
                    },
                    {
                        path: '/upfork',
                        name: 'upfork',
                        component: () => import('@/page/add/adddata/roadtype/upfork'),
                    },
                    {
                        path: '/downfork',
                        name: 'downfork',
                        component: () => import('@/page/add/adddata/roadtype/downfork'),
                    },
                    {
                        path: '/rightfork',
                        name: 'rightfork',
                        component: () => import('@/page/add/adddata/roadtype/rightfork'),
                    },
                    {
                        path: '/leftfork',
                        name: 'leftfork',
                        component: () => import('@/page/add/adddata/roadtype/leftfork'),
                    },
                    {
                        path: '/updown',
                        name: 'updown',
                        component: () => import('@/page/add/adddata/roadtype/updown'),
                    },
                    {
                        path: '/leftright',
                        name: 'leftright',
                        component: () => import('@/page/add/adddata/roadtype/leftright'),
                    },

                ]
            },

            //展示道路
            {
                path: '/roadshow',
                name: 'roadshow',
                component: () => import('@/page/show/roadshow'),
            },

            {
                path: '/datashow',
                name: 'datashow',
                component: () => import('@/page/show/datashow'),
            },
        ]
    },


]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

// router.beforeEach((to, from) => {

//     console.log(to)
//     console.log(from)
//     let token = JSON.parse(window.sessionStorage.getItem('userToken'));
//     let userId = JSON.parse(window.sessionStorage.getItem('userId'));
//     if (to.meta.requiresAuth == true) {
//         if (token && userId) {
//             return true;
//         }
//         return false;
//     }
//     return true;
// })

export default router;