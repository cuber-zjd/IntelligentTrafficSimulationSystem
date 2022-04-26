import request from '@/utils/request'

export function getAll(data) {
    return request({
        url: '/lightParams/getAll',
        method: 'post',
        data
    })
}

export function getById(id) {
    return request({
        url: '/lightParams/' + id,
        method: 'get',
    })
}

export function getParamsByUserId(userid) {
    return request({
        url: '/lightParams/getAllByUserId/' + userid,
        method: 'get',
    })
}

export function save(data) {
    return request({
        url: '/lightParams',
        method: 'post',
        data
    })
}

export function update(data) {
    return request({
        url: '/lightParams',
        method: 'put',
        data
    })
}

export function del(id) {
    return request({
        url: '/lightParams/' + id,
        method: 'delete',
    })
}

