import { getAllByUserId } from '@/api/road'

export function GetAllByUserId(userid) {
    return new Promise((resolve, reject) => {
        getAllByUserId(userid).then((res) => {
            resolve(res);
        }).catch(error => {
            reject(error)
        })
    });
}