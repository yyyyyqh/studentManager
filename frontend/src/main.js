import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css' // 引入 Element Plus 的核心 CSS 文件
import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(router) //注册路由
app.use(ElementPlus) // 注册 Element Plus 组件库

app.mount('#app')
