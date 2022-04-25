import request from '@/utils/request'

export function login(data) {
    return request({
        url: '/users/login',
        method: 'post',
        data
    })
}

export function getInfo(id) {
    return request({
        url: '/users/' + id,
        method: 'get'
    })
}

export function updateInfo(id, data) {
    return request({
        url: '/users/' + id,
        method: 'put',
        data
    })
}

export function updatePassword(id, data) {
    return request({
        url: '/users/updatePassword/' + id,
        method: 'put',
        data
    })
}