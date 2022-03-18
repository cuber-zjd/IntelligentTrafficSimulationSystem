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
    <el-button type="primary">保存</el-button>
    <el-button type="danger">关闭</el-button>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      user: {
        username: "admin",
        phone: "11111",
        email: "111",
        gender: "1111",
      },
    };
  },
};
</script>