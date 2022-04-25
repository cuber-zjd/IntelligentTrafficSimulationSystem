<template>
  <center class="all">
    <br /><br /><br /><br /><br /><br /><br /><br /><br />
    <el-row :gutter="20" class="login-panel">
      <el-col :span="2"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="20">
        <div class="grid-content bg-purple">
          <h3>用户登录</h3>
          <br />
          <el-form ref="form">
            <el-form-item label="">
              <el-input
                v-model="user.username"
                placeholder="请输入用户名"
              ></el-input>
            </el-form-item>
            <el-form-item label="">
              <el-input
                v-model="user.password"
                placeholder="请输入密码"
              ></el-input>
            </el-form-item>
            <el-row>
              <el-col :span="7"></el-col>
              <el-col :span="12">
                <el-form-item>
                  <el-button type="primary" @click="handleLogin"
                    >登录</el-button
                  >
                  <el-button>取消</el-button>
                </el-form-item>
              </el-col>
              <el-col :span="6"></el-col>
            </el-row>
          </el-form>
        </div>
      </el-col>
      <el-col :span="2"><div class="grid-content bg-purple"></div></el-col>
      <el-link>点击注册</el-link>
    </el-row>
  </center>
</template>


<script setup >
import "@/assets/css/login.css";
import { reactive } from "vue";
import { Login } from "@/model/user";
import { useRouter } from "vue-router";

const router = useRouter();

const user = reactive({
  username: "",
  password: "",
});

const handleLogin = () => {
  // console.log(store);
  // console.log(window.localStorage.getItem("my_token"));
  Login(user).then(() => {
    router.push("/");
  });
};
</script>

