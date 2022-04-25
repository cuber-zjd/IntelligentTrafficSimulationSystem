import { getDetail } from "@/api/data";

export function GetDetail(dataid) {
    return new Promise((resolve, reject) => {
        getDetail(dataid).then((res) => {
            resolve(res)
        }).catch((error) => {
            reject(error)
        })
    })
}