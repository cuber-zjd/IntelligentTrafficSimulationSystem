import axios from "axios";
import { ElMessage } from 'element-plus'
import { getToken, removeToken } from '@/utils/auth'

import router from '@/router'

const service = axios.create({
    baseURL: "http://localhost",
})

service.interceptors.request.use(
    config => {
        if (getToken()) {
            config.headers['token'] = getToken()
        }
        return config
    },
    error => {
        // do something with request error
        console.log(error) // for debug
        return Promise.reject(error)
    }
)


service.interceptors.response.use(
    response => {
        const res = response.data
        if (res.code !== 200) {
            ElMessage({
                message: res.message || 'Error',
                type: 'error',
                duration: 5 * 1000
            })
        }
        else {
            return res
        }
    },
    error => {
        if (error.message === "Request failed with status code 401") {
            ElMessage({
                message: "登录已过期，请重新的登录",
                type: 'error',
                duration: 3 * 1000
            })
            removeToken();
            router.replace({ path: "/login" })
        }
        return Promise.reject(error)
    }
)

export default service