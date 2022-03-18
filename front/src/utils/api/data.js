import axios from '@/utils/http'

import base from '@/utils/base'

/**
 * post方法，对应post请求
 * @desc注册请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function dataPost(data, url) {
    console.log(data)
    return axios({
        url: `${base.url}/datas` + url,
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
export function dataGet(url) {
    return axios({
        url: `${base.url}/datas` + url,
        method: 'get'
    })
}

export function dataPut(data, url) {
    return axios({
        url: `${base.url}/datas` + url,
        method: 'put',
        data: data
    })
}

export function dataDelete(url) {
    return axios({
        url: `${base.url}/datas` + url,
        method: 'delete'
    })
}