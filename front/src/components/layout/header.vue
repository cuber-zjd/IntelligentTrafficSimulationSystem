<template>
  <el-dropdown>
    <el-avatar :size="40" :src="circleUrl" />
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="toUserCenter">个人中心</el-dropdown-item>
        <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>
<script setup>
import { ref } from "@vue/reactivity";
import { useRouter } from "vue-router";
import { removeToken } from "@/utils/auth";
// 头像地址
const circleUrl = ref(
  "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F201604%2F23%2F20160423234655_hj4HZ.thumb.1000_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1650803686&t=d946d5e2caef0b00e19affc766ca5f5b"
);

//路由到个人中心
const router = useRouter();
const toUserCenter = () => {
  router.push("/usercenter");
};

//退出登录
const logout = () => {
  removeToken();
  router.push("/login");
};
</script>