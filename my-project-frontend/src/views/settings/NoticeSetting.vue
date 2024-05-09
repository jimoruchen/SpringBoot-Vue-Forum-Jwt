<script setup>
import LightCard from "@/components/LightCard.vue";
import {EditPen} from "@element-plus/icons-vue";
import {computed, onMounted, reactive, ref} from "vue";
import axios from 'axios';
import {get} from "@/net";
import {useStore} from "@/store";
import router from "@/router";

const store = useStore()

const list = ref(null)

onMounted(() => {

    get('/api/forum/types', data => store.forum.types = data)
    function updateList() {
        get('/api/forum/list_topic?page=0&type=6', data => list.value = data)
    }
    updateList()
});

</script>

<template>
    <div style="display: flex;margin: 20px auto;gap: 20px;max-width: 900px">
        <div style="flex: 1">
            <div style="margin-top: 10px;display: flex;flex-direction: column">
                <light-card v-for="item in list" class="topic-card" @click="router.push(`/index/topic-detail/${item.id}`)">
                    <div style="display: flex">
                        <div>
                            <el-avatar :size="30" :src="`${axios.defaults.baseURL}/images${item.avatar}`"/>
                        </div>
                        <div style="margin-left: 7px;transform: translateY(-2px)">
                            <div style="font-size: 13px;font-weight: bold">{{item.username}}</div>
                            <div style="font-size: 12px;color: grey">
                                {{new Date(item.time).toLocaleString()}}
                            </div>
                        </div>
                    </div>
                    <div style="margin-top: 5px">
                        <div class="topic-type"
                             :style="{
                                    color: store.findTypeById(item.type)?.color + 'EE',
                                    'border-color': store.findTypeById(item.type)?.color + '77',
                                    'background':store.findTypeById(item.type)?.color + '33',
                                    'text-align':'center'
                                }">
                            {{store.findTypeById(item.type)?.name}}
                        </div>
                        <span style="font-weight: bold;margin-left: 7px">{{item.title}}</span>
                    </div>
                    <div class="topic-content">{{item.text}}</div>
                    <el-divider style="margin: 10px 0"/>
                    <div style="display: grid;grid-template-columns: repeat(3, 1fr);grid-gap: 10px">
                        <el-image class="topic-image" v-for="img in item.images" :src="img" fit="cover"></el-image>
                    </div>
                </light-card>
            </div>
        </div>
        </div>
</template>

<style lang="less" scoped>
.topic-card .title-wrap {
    white-space: normal;
    word-break: break-all;
    overflow-wrap: break-word;
}
</style>
