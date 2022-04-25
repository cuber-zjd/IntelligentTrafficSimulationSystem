import { createStore } from 'vuex'
import VuexPersistence from 'vuex-persist'

import user from '@/store/modules/user'

import mutations from './mutation'
import state from './state'
import getters from './getters'
import actions from './action'

const vuexLocal = new VuexPersistence({
    storage: window.localStorage,
})

const store = createStore({
    modules: {
        user
    },
    state,
    mutations,
    actions,
    getters,

    plugins: [vuexLocal.plugin]
})

export default store;