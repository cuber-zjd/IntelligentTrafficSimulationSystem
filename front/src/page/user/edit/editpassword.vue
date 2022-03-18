<template>
  <el-form
    ref="formRef"
    :model="user"
    label-width="120px"
    style="font-weight: bold"
  >
    <el-form-item label="旧密码" style="margin-top: 10px">
      <el-input v-model="word.password"></el-input>
    </el-form-item>
    <el-form-item label="新密码">
      <el-input v-model="word.newPassword"></el-input>
    </el-form-item>
    <el-form-item label="确认密码">
      <el-input v-model="word.confirmPassword"></el-input>
    </el-form-item>
    <el-button type="primary">保存</el-button>
    <el-button type="danger">关闭</el-button>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      word: {
        password: "",
        newPassword: "",
        confirmPassword: "",
      },
    };
  },
};
</script>