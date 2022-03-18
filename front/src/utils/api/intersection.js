import axios from '@/utils/http'

import base from '@/utils/base'

/**
 * post方法，对应post请求
 * @desc注册请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function intersectionPost(data, url) {
    console.log(data)
    return axios({
        url: `${base.url}/intersections` + url,
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
export function intersectionGet(url) {
    return axios({
        url: `${base.url}/intersections` + url,
        method: 'get'
    })
}

export function intersectionPut(data, url) {
    return axios({
        url: `${base.url}/intersections` + url,
        method: 'put',
        data: data
    })
}

export function intersectionDelete(url) {
    return axios({
        url: `${base.url}/intersections` + url,
        method: 'delete'
    })
}