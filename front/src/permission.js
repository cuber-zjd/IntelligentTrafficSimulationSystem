import router from '@/router'
import { getToken } from '@/utils/auth'

const whiteList = ['/login']

router.beforeEach((to) => {
    const hasToken = getToken()

    if (hasToken) {

        // return true;
        if (to.path === '/login') {
            return "/"
        } else {
            return
        }
    } else {
        if (whiteList.indexOf(to.path) !== -1) {
            return
        } else {
            return "/login"
        }
    }

})