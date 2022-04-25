import { login, getInfo } from '@/api/user'
import { setToken } from '@/utils/auth'
import store from '@/store'



export function Login(user) {
    return new Promise((resolve, reject) => {
        login(user).then((res) => {
            const { data } = res;
            setToken(data.token);
            store.commit("SET_USERID", data.userid)
            resolve()
        }).catch(error => {
            reject(error)
        })
    });
}

export function GetInfo(id) {
    return new Promise((resolve, reject) => {
        getInfo(id).then((res) => {
            resolve(res)
        }).catch(error => {
            reject(error)
        })
    });
}

