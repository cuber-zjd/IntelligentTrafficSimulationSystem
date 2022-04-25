import request from '@/utils/request'

export function getPageList(userid, current, data) {
    return request({
        url: '/roads/getByUser/' + userid + "/" + current,
        method: 'post',
        data
    })
}

export function getById(id) {
    return request({
        url: '/roads/' + id,
        method: 'get',
    })
}

export function getAllByUserId(userid) {
    return request({
        url: '/roads/allByUserId/' + userid,
        method: 'get',
    })
}

export function updateById(data) {
    return request({
        url: '/roads',
        method: 'put',
        data
    })
}

export function deleteById(id) {
    return request({
        url: '/roads/' + id,
        method: 'delete',
    })
}

export function save(data) {
    return request({
        url: '/roads',
        method: 'post',
        data
    })
}