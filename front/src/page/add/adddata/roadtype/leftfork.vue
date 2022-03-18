<template>
  <div class="all">
    <div class="right"></div>
    <div class="upleft"></div>
    <div class="downleft"></div>
  </div>

  <el-radio-group v-model="direction">
    <el-radio label="北"
      ><el-icon><bottom /></el-icon
    ></el-radio>
    <el-radio label="南"
      ><el-icon><top /></el-icon
    ></el-radio>
    <el-radio label="西"
      ><el-icon><right /></el-icon
    ></el-radio>
  </el-radio-group>
</template>
<script>
import "@/assets/css/roadtype/leftfork.css";
export default {
  data() {
    return {
      direction: "",
    };
  },
  watch: {
    direction(newDirection) {
      this.$store.commit("setDirection", newDirection);
    },
  },
};
</script>