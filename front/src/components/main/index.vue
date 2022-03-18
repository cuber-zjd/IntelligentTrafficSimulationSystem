<template>
  <el-container>
    <el-aside width="200px">
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
            <!-- 用户信息 -->
            <el-sub-menu index="1">
              <template #title>
                <el-icon><avatar /></el-icon>
                <span>用户信息</span>
              </template>

              <el-menu-item index="/user" @click="toPersonCenter">
                个人中心
              </el-menu-item>
              <el-menu-item index="1-2">使用情况</el-menu-item>
            </el-sub-menu>

            <!-- 道路管理 -->
            <el-sub-menu index="2">
              <template #title>
                <el-icon><avatar /></el-icon>
                <span>道路管理</span>
              </template>
              <el-menu-item index="/roadshow"> 道路管理 </el-menu-item>
              <el-menu-item index="/addroad"> 添加路口 </el-menu-item>
            </el-sub-menu>

            <!-- 数据显示 -->
            <el-sub-menu index="3">
              <template #title>
                <el-icon><avatar /></el-icon>
                <span>数据管理</span>
              </template>
              <el-menu-item index="/adddata">注入数据</el-menu-item>
              <el-menu-item index="/datashow">数据管理</el-menu-item>
            </el-sub-menu>

            <!-- 设置 -->
            <el-menu-item index="4">
              <el-icon><setting /></el-icon>
              <span>设置</span>
            </el-menu-item>
          </el-menu>
        </el-col>
      </el-row>
    </el-aside>
    <el-container>
      <el-header>
        <div>
          用户名:{{ $store.state.username }}
          <el-button type="info" plain @click="logout">退出</el-button>
        </div>
      </el-header>

      <!-- 主页面 -->
      <el-main>
        <div class="app-container">
          <router-view></router-view>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
<style scoped>
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

.el-aside {
  background-color: #304156;
  color: var(--el-text-color-primary);
  text-align: center;
}

.el-main {
  background-color: #e9eef3;
  color: var(--el-text-color-primary);
  text-align: center;
}
</style>

<script>
import "@/assets/css/main.css";
export default {
  data() {
    return {
      username: "",
    };
  },
  methods: {
    // 退出登录
    logout() {
      console.log("testlogout--------");
      window.localStorage.removeItem("userId");
      window.localStorage.removeItem("userToken");
      window.localStorage.removeItem("username");
      this.$router.push("/");
    },
  },
  mounted() {
    this.username = this.$store.state.username;
    // 跳转到个人中心页面
    {
      this.$router.push("/user");
    }
  },
};
</script>

<style scoped>
.el-main {
  background-color: white;
}
</style>