<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px"><SideBar></SideBar></el-aside>
      <el-container>
        <el-header><Header></Header></el-header>
        <el-main><router-view /></el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup>
import SideBar from "@/components/layout/sidebar";
import Header from "@/components/layout/header";
import "@/assets/css/main.css";
</script>

<style scoped>
.common-layout {
  height: 100%;
}
.el-container {
  height: 100%;
}
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
  text-align: center;
}
.el-footer {
}
.el-header,
div {
  padding-top: 0px;
  text-align: right;
}

.el-aside {
  background-color: #304156;
  color: var(--el-text-color-primary);
  text-align: center;
}

.el-main {
  background-color: white;

  color: var(--el-text-color-primary);
  text-align: center;
}
</style>