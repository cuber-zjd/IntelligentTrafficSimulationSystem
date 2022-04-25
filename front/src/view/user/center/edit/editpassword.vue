<template>
  <el-form
    ref="formRef"
    :model="word"
    label-width="120px"
    style="font-weight: bold"
    :rule="rules"
  >
    <el-form-item label="旧密码" style="margin-top: 10px" prop="password">
      <el-input v-model="word.password" type="password"></el-input>
    </el-form-item>
    <el-form-item label="新密码" prop="newPassword">
      <el-input v-model="word.newPassword" type="password"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="confirmPassword">
      <el-input v-model="word.confirmPassword" type="password"></el-input>
    </el-form-item>
    <el-button type="primary" @click="update">保存</el-button>
    <el-button type="danger" @click="clear">重置</el-button>
  </el-form>
</template>
<script setup>
import { reactive, ref } from "vue";
import { updatePassword } from "@/api/user";
import { useStore } from "vuex";
import { ElMessage } from "element-plus/lib/components";

const store = useStore();

let word = reactive({
  password: "",
  newPassword: "",
  confirmPassword: "",
});

const update = () => {
  updatePassword(store.state.user.userid, word).then((res) => {
    ElMessage({
      message: res.data,
      type: "success",
    });
  });
  clear();
};

const formRef = ref(null);

const clear = () => {
  formRef.value.resetFields();
};

const rules = reactive({
  password: [
    {
      required: true,
      message: "Please select Activity zone",
      trigger: "blur",
    },
  ],
});
</script>