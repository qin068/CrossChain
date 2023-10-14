import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import vueLib from '@starport/vue'
import ElementUI from "element-plus";
import "element-plus/theme-chalk/index.css"
import axios from 'axios'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// import echarts from './echarts';
import * as echarts from 'echarts'

const app = createApp(App)
app.config.globalProperties._depsLoaded = true
app.use(store).use(router).use(vueLib).mount('#app')
app.use(ElementUI)
app.config.globalProperties.$axios = axios
// app.config.globalProperties.$echarts = echarts
app.echarts=echarts
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
