<template>
  <el-card class="box-card">
    <!-- 头部 -->
    <template #header>
      <div class="card-header">
        <span>个人信息</span>
      </div>
    </template>

    <div class=".card-header-body">
      <!-- 头像 -->
      <div style="border-bottom: 1px solid #e6ebf5">
        <el-avatar
          src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        ></el-avatar>
      </div>
      <!-- 信息展示 -->
      <div class="item">
        <ul class="list-group">
          <li class="list-group-item">
            <el-icon><avatar /></el-icon>
            用户名称
            <div class="right">{{ user.username }}</div>
          </li>
          <li class="list-group-item">
            <el-icon><phone /></el-icon>
            手机号码
            <div class="right">{{ user.phone }}</div>
          </li>
          <li class="list-group-item">
            <el-icon><chat-line-square /></el-icon>
            用户邮箱
            <div class="right">{{ user.email }}</div>
          </li>
        </ul>
      </div>
    </div>
  </el-card>
</template>
<script>
import { userGet } from "@/utils/api/user";
export default {
  data() {
    return {
      user: {
        username: "",
        phone: "",
        email: "",
      },
    };
  },
  beforeMount() {
    userGet("/" + this.$store.state.userId).then((res) => {
      this.user = res.data.data;
    });
  },
};
</script>
<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-header-body {
  padding: 15px 20px 20px 20px;
}

.item {
  font-size: 14px;
  border-bottom: 1px solid #e6ebf5;
  margin-top: 10px;
  margin-bottom: 18px;
}

.list-group {
  padding-left: 0px;
  list-style-type: none;
  text-align: left;
}

.list-group-item {
  border-bottom: 1px solid #e7eaec;
  border-top: 1px solid #e7eaec;
  margin-bottom: -1px;
  padding: 11px 0;
  font-size: 13px;
}

.right {
  float: right;
  text-align: right;
}
</style>