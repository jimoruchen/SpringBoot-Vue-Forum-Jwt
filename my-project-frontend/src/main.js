import { createPinia} from "pinia";
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios";

import 'element-plus/theme-chalk/dark/css-vars.css'
import '@/assets/quill.css'

axios.defaults.baseURL = 'http://localhost:8080'

const pinia = createPinia()
const app = createApp(App)

app.use(pinia)
app.use(router)

app.mount('#app')
