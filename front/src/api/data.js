import request from '@/utils/request'

export function getPageList(userid, current, data) {
    return request({
        url: '/datas/getByUser/' + userid + "/" + current,
        method: 'post',
        data
    })
}

export function getByRoadId(userid) {
    return request({
        url: '/datas/getByRoadId/' + userid,
        method: 'get',

    })
}

export function getById(id) {
    return request({
        url: '/datas/' + id,
        method: 'get',
    })
}

export function updateById(data) {
    return request({
        url: '/datas',
        method: 'put',
        data
    })
}

export function deleteById(id) {
    return request({
        url: '/datas/' + id,
        method: 'delete',
    })
}

export function autoAdd(data) {
    return request({
        url: '/datas/autoadd',
        method: 'post',
        data
    })
}

export function getDetail(dataid) {
    return request({
        url: '/dataandspeeds/getByDataId/' + dataid,
        method: 'get',
    })
}

export function manualAdd(data) {
    return request({
        url: '/datas/manualAdd',
        method: 'post',
        data
    })
}

export function getByRoadIdAndTime(data) {
    return request({
        url: '/dataandspeeds/getByRoadIdAndTime',
        method: 'post',
        data
    })
}

export function getAllByRoadIdAndTime(data) {
    return request({
        url: '/dataandspeeds/getAllByRoadIdAndTime',
        method: 'post',
        data
    })
}

