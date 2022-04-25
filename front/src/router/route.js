export default [
    // 用户

    {
        path: '/usercenter',
        name: 'usercenter',
        component: () => import('@/view/user/center'),
    },

    // 道理管理
    {
        path: '/road',
        name: 'road',
        component: () => import('@/view/road'),
        children: [

        ]
    },
    {
        path: '/addRoad',
        name: 'addRoad',
        component: () => import('@/view/road/add'),
    },
    //数据管理
    {
        path: '/data',
        name: 'data',
        component: () => import('@/view/data'),
    },
    {
        path: '/addData',
        name: 'addData',
        component: () => import('@/view/data/add'),
    },
    {
        path: '/manualAddData',
        name: 'manualAddData',
        component: () => import('@/view/data/manualAdd'),
    },
    {
        path: '/detectParam',
        name: 'detectParam',
        component: () => import('@/view/param/detectParam'),
    },
    {
        path: '/lightParam',
        name: 'lightParam',
        component: () => import('@/view/param/lightParam'),
    },
    {
        path: '/light',
        name: 'light',
        component: () => import('@/view/light'),
    },
    {
        path: '/dataCharts',
        name: 'dataCharts',
        component: () => import('@/view/charts/dataCharts'),
    },
]