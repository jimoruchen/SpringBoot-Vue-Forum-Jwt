<script setup>

const props = defineProps({
    data: Object
});
</script>

<template>
    <div style="height: 160px" v-loading="!props.data.success"
         element-loading-text="正在加载天气信息...">
        <div style="display: flex;justify-content: space-between;margin: 10px 20px"
             v-if="props.data.success">
            <div style="font-size: 45px;color: grey">
                <i :class="`qi-${props.data.now.icon}-fill`"></i>
            </div>
            <div style="font-weight: bold;text-align: center">
                <div style="font-size: 25px">{{props.data.now.temp}}℃</div>
                <div style="font-size: 15px">{{props.data.now.text}}</div>
            </div>
            <div style="margin-top: 13px">
                <div style="font-size: 15px">{{`${props.data.location.country} ${props.data.location.adm1}`}}</div>
                <div style="font-size: 14px;color: grey">{{`${props.data.location.adm2} ${props.data.location.name}区`}}</div>
            </div>
        </div>
        <el-divider style="margin: 10px 0"/>
        <div style="display: grid;grid-template-columns: repeat(5, 1fr);text-align: center">
            <div v-for="item in props.data.hourly">
                <div style="font-size: 13px">{{new Date(item.fxTime).getHours()}} 时</div>
                <div style="font-size: 23px">
                    <i :class="`qi-${item.icon}-fill`"></i>
                </div>
                <div style="font-size: 12px">
                    {{item.temp}}℃
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>

</style>
