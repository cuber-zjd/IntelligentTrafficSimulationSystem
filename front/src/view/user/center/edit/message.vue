<template>
  <el-form
    ref="formRef"
    :model="user"
    label-width="120px"
    style="font-weight: bold"
  >
    <el-form-item label="用户名称" style="margin-top: 10px">
      <el-input v-model="user.username"></el-input>
    </el-form-item>
    <el-form-item label="电话号码">
      <el-input v-model="user.phone"></el-input>
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model="user.email"></el-input>
    </el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="user.gender">
        <el-radio label="男"></el-radio>
        <el-radio label="女"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-button type="primary" @click="update">保存</el-button>
    <el-button type="danger" @click="get">重置</el-button>
  </el-form>
</template>
<script setup>
import { onMounted, ref } from "vue";
import { GetInfo } from "@/model/user";
import { useStore } from "vuex";
import { updateInfo } from "@/api/user";
import { ElMessage } from "element-plus";

const store = useStore();
let user = ref({
  username: "",
  phone: "",
  email: "",
  gender: "",
});

const update = () => {
  updateInfo(store.state.user.userid, user.value).then(() => {
    ElMessage({
      message: "修改成功",
      type: "success",
    });
  });
};

const get = () => {
  GetInfo(store.state.user.userid).then((res) => {
    user.value = res.data;
  });
};

onMounted(() => {
  get();
});
</script>