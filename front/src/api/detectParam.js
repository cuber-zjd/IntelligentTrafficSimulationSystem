import request from '@/utils/request'

export function save(data) {
    return request({
        url: '/detectSettings',
        method: 'post',
        data
    })
}

export function delById(id) {
    return request({
        url: '/detectSettings/' + id,
        method: 'delete',
    })
}

export function update(data) {
    return request({
        url: '/detectSettings',
        method: 'put',
        data
    })
}

export function getPageList(data) {
    return request({
        url: '/detectSettings/getPageList',
        method: 'post',
        data
    })
}

export function getById(id) {
    return request({
        url: '/detectSettings/' + id,
        method: 'get',
    })
}

export function getByUserId(userid) {
    return request({
        url: '/detectSettings/getByUserId/' + userid,
        method: 'get',
    })
}



