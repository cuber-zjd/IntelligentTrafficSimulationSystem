<template>
  <el-row class="tac">
    <el-col :span="24">
      <!-- 侧边栏 -->
      <el-menu
        active-text-color="#ffd04b"
        background-color="#304156"
        class="el-menu-vertical-demo"
        default-active="activeIndex"
        text-color="#fff"
        router="true"
      >
        <!-- 道路管理 -->
        <el-menu-item index="/road">
          <el-icon><location-filled /></el-icon>
          道路管理
        </el-menu-item>

        <!-- 数据管理 -->
        <el-menu-item index="/data">
          <el-icon><list /></el-icon>
          数据管理
        </el-menu-item>

        <el-sub-menu index="1">
          <template #title>
            <el-icon><setting /></el-icon>
            <span>统计与分析</span>
          </template>

          <el-menu-item index="/dataCharts"> 统计与图表 </el-menu-item>
          <el-menu-item index=""> 交通热力图 </el-menu-item>
        </el-sub-menu>

        <el-sub-menu index="2">
          <template #title>
            <el-icon><setting /></el-icon>
            <span>设置管理</span>
          </template>

          <el-menu-item index="/detectParam"> 检测参数设置 </el-menu-item>
          <el-menu-item index="/lightParam"> 智能信号灯设置管理 </el-menu-item>
        </el-sub-menu>

        <el-menu-item index="/light">
          <el-icon><opportunity /></el-icon>
          <span>智能信号灯</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script setup>
</script>

