<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style="flex: 1">
      <el-image style="width: 100%;height: 100%" fit="cover"
                src="https://s2.loli.net/2025/02/20/vKxXiCSsZN8WElL.jpg"/>
    </div>
    <div class="welcome-title">
      <div style="font-size: 30px;font-weight: bold">欢迎来到校园论坛系统</div>
      <div style="margin-top: 10px">在这里你可以：发布学术探讨、学习心得，共享教育资源，增进学术氛围；</div>
      <div style="margin-top: 5px">及时获取校内新闻动态、通知公告，确保重要信息不遗漏；</div>
      <div style="margin-top: 5px">组织或参与各类校园活动，丰富课余生活，结交志同道合的朋友；</div>
      <div style="margin-top: 5px">展示个人才艺与创新成果，搭建起才华展示与交流的舞台。</div>
    </div>
    <div class="right-card">
      <router-view v-slot="{ Component }">
        <transition name="el-fade-in-linear" mode="out-in">
          <component :is="Component" style="height: 100%"/>
        </transition>
      </router-view>
    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.right-card {
  width: 400px;
  z-index: 1;
  background-color: var(--el-bg-color);
}

.welcome-title {
  position: absolute;
  bottom: 30px;
  left: 30px;
  color: white;
  text-shadow: 0 0 10px black;
}
</style>
