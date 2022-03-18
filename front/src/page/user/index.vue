<template>
  <div class="user-container">
    <el-row style="">
      <!-- 信息展示卡片 -->
      <el-col :span="6" style="padding-left: 10px; padding-right: 10px">
        <user-show></user-show>
      </el-col>
      <el-col :span="18" style="padding-left: 10px; padding-right: 10px">
        <user-edit></user-edit>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import "@/assets/css/user.css";
import UserShow from "@/page/user/show";
import UserEdit from "@/page/user/edit/index";

export default {
  components: {
    UserShow,
    UserEdit,
  },
  templates: {
    UserShow,
    UserEdit,
  },
};
</script>

