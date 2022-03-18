import { createStore } from 'vuex'
import createPersistedState from "vuex-persistedstate"
import addroad from './modules/addroad'
import adddata from './modules/adddata'
import actions from '@/store/actions'


const store = createStore({
    state() {
        return {
            userId: "",
            userToken: "",
            username: ""
        }
    },
    modules: {
        addroad: addroad,
        adddata: adddata
    },
    actions,
    getters: {
        // userId: state => {
        //     let userId = state.userId;
        //     if (!userId) {
        //         userId = JSON.parse(window.sessionStorage.getItem('userId'));
        //     }
        //     return userId;
        // },
        // userToken: state => {
        //     let userToken = state.userToken;
        //     if (!userToken) {
        //         userToken = JSON.parse(window.sessionStorage.getItem('userToken'));
        //     }
        //     return userToken;
        // },
    },
    mutations: {
        setUserId: (state, userId) => {
            state.userId = userId;
            window.localStorage.setItem('userId', JSON.stringify(userId));
        },
        setUserToken: (state, userToken) => {
            state.userToken = userToken;
            window.localStorage.setItem('userToken', JSON.stringify(userToken));
        },
        setUsername: (state, username) => {
            state.username = username
        },
        setNull: (state) => {
            state.userId = null
            state.username = null
            state.userToken = null
        }
    },
    plugins: [createPersistedState({
        storage: window.localStorage
    })]
})

export default store