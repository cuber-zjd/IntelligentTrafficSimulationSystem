import { login } from '@/api/user'
import { getToken, setToken } from '@/utils/auth'

const getDefaultState = () => {
    return {
        userid: ''
    }
}

const state = getDefaultState()

const mutations = {
    RESET_STATE: (state) => {
        Object.assign(state, getToken())
    },
    SET_TOKEN: (state, token) => {
        state.token = token
    },
    SET_USERNAME: (state, username) => {
        state.username = username
    },
    SET_USERID: (state, userid) => {
        state.userid = userid
    }
}

const actions = {
    login({ commit }, userInfo) {
        const { username, password } = userInfo
        return new Promise((resolve, reject) => {
            login({ username: username, password: password }).then(res => {
                const { data } = res
                setToken(data.token);
                commit('SET_USERID', data.userid)
                resolve()
            }).catch(error => {
                reject(error)
            })
        })
    }
}

export default {
    state,
    mutations,
    actions
}