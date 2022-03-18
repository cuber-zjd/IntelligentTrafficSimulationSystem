<template>
  <el-card class="box-card">
    <!-- 头部 -->
    <template #header>
      <div class="card-header">
        <span>基本资料</span>
      </div>
    </template>
    <div class="el-card-body">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        router="true"
      >
        <el-menu-item index="/message">基本资料</el-menu-item>
        <el-menu-item index="/editpassword">修改密码</el-menu-item>
      </el-menu>
      <div class="">
        <router-view></router-view>
      </div>
    </div>
  </el-card>
</template>
<script>
import { ref } from "vue";

const activeIndex = ref("/message");

export default {
  data() {
    return {
      activeIndex,
    };
  },
  mounted() {
    this.$router.push("/message");
  },
};
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>