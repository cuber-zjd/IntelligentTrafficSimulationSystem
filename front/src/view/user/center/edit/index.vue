<template>
  <el-card class="box-card">
    <!-- 头部 -->
    <template #header>
      <div class="card-header">
        <span>基本资料</span>
      </div>
    </template>
    <div class="el-card-body">
      <el-menu :default-active="1" class="el-menu-demo" mode="horizontal">
        <el-menu-item index="1" @click="OnMessage">基本资料</el-menu-item>
        <el-menu-item index="2" @click="OnPassword">修改密码</el-menu-item>
      </el-menu>
      <div class="">
        <keep-alive>
          <component :is="componentId"></component>
        </keep-alive>
      </div>
    </div>
  </el-card>
</template>
<script setup>
import { ref } from "vue";
import UserMessage from "@/view/user/center/edit/message";
import UserPassword from "@/view/user/center/edit/editpassword";

let componentId = ref(UserMessage);

const OnMessage = () => {
  componentId.value = UserMessage;
};
const OnPassword = () => {
  componentId.value = UserPassword;
};
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>