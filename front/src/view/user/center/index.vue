<template>
  <div class="user-container">
    <el-row style="">
      <!-- 信息展示卡片 -->
      <el-col :span="6" style="padding-left: 10px; padding-right: 10px">
        <UserShow />
      </el-col>
      <el-col :span="18" style="padding-left: 10px; padding-right: 10px">
        <UserEdit />
      </el-col>
    </el-row>
  </div>
</template>
<script setup>
import "@/assets/css/user.css";
import UserShow from "@/view/user/center/show";
import UserEdit from "@/view/user/center/edit";
</script>