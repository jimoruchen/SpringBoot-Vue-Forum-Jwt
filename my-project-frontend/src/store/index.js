import { defineStore} from "pinia";
import axios from "axios";

export const useStore = defineStore('general',  {
    state: () => {
        return {
            user: {
                id: -1,
                username: '',
                email: '',
                role: '',
                avatar: null,
                registerTime: null
            },
            forum: {
                types: []
            }
        }
    }, getters: {
        avatarUrl() {
            if(this.user.avatar)
                return `${axios.defaults.baseURL}/images${this.user.avatar}`
            else
                return 'https://image.itbaima.cn/images/445/image-20240314139970195.png'
        }
    }, actions: {
        findTypeById(id) {
            for(let type of this.forum.types) {
                if(type.id === id)
                    return type
            }
        },avatarUserUrl(avatar) {
            if(avatar)
                return `${axios.defaults.baseURL}/images${avatar}`
            else
                return 'https://image.itbaima.cn/images/445/image-20240314139970195.png'
        }
    }
})






