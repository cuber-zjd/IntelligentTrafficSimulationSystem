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
                  <el-button type="primary" @click="login">登录</el-button>
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

<script>
import "@/assets/css/login.css";
import { userPost } from "@/utils/api/user";
export default {
  name: "login",
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      const user = this.user;
      userPost(user, "/login")
        .then((result) => {
          if (result.data.flag === true) {
            console.log(result);
            console.result;
            this.$store.commit("setUserId", result.data.data.id);
            this.$store.commit("setUsername", result.data.data.username);
            this.$store.commit("setUserToken", result.data.msg);
            this.$router.push("/main");
          } else {
            alert(result.data.msg);
            this.$router.push("/");
            this.user.username = "";
            this.user.password = "";
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
