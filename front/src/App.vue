<template>
  <router-view></router-view>
</template>

<script>
export default {
  name: "App",
  components: {},
  methods: {},
  mounted() {
    let token = JSON.parse(window.localStorage.getItem("userToken"));
    let userId = JSON.parse(window.localStorage.getItem("userId"));

    if (token && userId) {
      this.$router.push("/main");
    } else {
      this.$router.push("/");
    }
  },
};
</script>

<style>
</style>
