import axios from 'axios'

const server = axios.create({
    baseURL: 'http://localhost',
    timeout: 5000,
})


// 请求拦截
server.interceptors.request.use(
    (confing) => {
        //设置请求头
        if (localStorage.userToken) {
            confing.headers.Authorization = localStorage.userToken
        }
        return confing
    },
    (error) => {
        return Promise.reject(error)
    }
)

//响应拦截

server.interceptors.response.use(
    (response) => {
        // if (response.status) {
        //     switch (response.status) {
        //         case 200:
        //     }
        // }
        return response
    },
    (error) => {
        return Promise.reject(error)
    }
)
export default server