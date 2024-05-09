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
        get('/api/forum/recommend', data => list.value = data)
    }
    updateList()
});

// Delta to Plain Text Converter Function
function deltaToPlainText(delta) {
    // 检查delta是否为字符串类型，若是则尝试将其解析为JSON
    if (typeof delta === 'string') {
        try {
            delta = JSON.parse(delta);
        } catch (e) {
            console.error('解析Delta字符串失败:', e);
            return ''; // 解析失败，返回空字符串
        }
    }

    // 检查解析后的对象是否包含ops属性
    if (!delta || !Array.isArray(delta.ops)) {
        console.error('Delta格式无效: 没有ops属性');
        return ''; // Delta格式不正确，返回空字符串
    }

    // 遍历ops数组，连接insert操作中的字符串来生成普通文本
    return delta.ops.reduce((text, op) => {
        if (op.insert) {
            if (typeof op.insert === 'string') {
                text += op.insert; // 将文本添加到结果中
            } else if (op.insert.hasOwnProperty('image')) {

            }
        }
        return text;
    }, '');
}

</script>

<template>
    <div style="display: flex;margin: 20px auto;gap: 20px;max-width: 900px">
        <div style="flex: 1">
            <div style="margin-top: 10px;display: flex;flex-direction: column">
                <light-card v-for="item in list" class="topic-card" @click="router.push(`/index/topic-detail/${item.id}`)">
                    <div style="display: flex">
                        <div>
                            <el-avatar :size="30" :src="store.avatarUserUrl(item.avatar)"/>
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
                    <div class="topic-content">{{deltaToPlainText(item.text)}}</div>
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
