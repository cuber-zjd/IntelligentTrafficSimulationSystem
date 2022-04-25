import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'
import VueAxios from 'vue-axios'
import '@/assets/css/common.css'
import * as ElIconModules from '@element-plus/icons'
import store from '@/store'
import '@/permission'



const app = createApp(App)

for (let iconName in ElIconModules) {
    app.component(iconName, ElIconModules[iconName])
}

app.use(router)
app.use(ElementPlus)
app.use(VueAxios, axios)
app.use(store)
app.mount('#app')
