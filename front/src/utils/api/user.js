import axios from '@/utils/http'

import base from '@/utils/base'

/**
 * post方法，对应post请求
 * @desc注册请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function userPost(data, url) {
    return axios({
        url: `${base.url}/users` + url,
        method: 'post',
        data: data
    })
}
/**
 * get方法，对应get请求
 * @desc登录请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function userGet(url) {
    return axios({
        url: `${base.url}/users` + url,
        method: 'get'
    })
}

export function userPut(data, url) {
    return axios({
        url: `${base.url}/users` + url,
        method: 'put',
        data: data
    })
}

export function userDelete(url) {
    return axios({
        url: `${base.url}/users` + url,
        method: 'delete'
    })
}