<template>
  <div class="all">
    <div class="up"></div>
    <div class="downleft"></div>
    <div class="downright"></div>
  </div>

  <el-radio-group v-model="direction">
    <el-radio label="南"
      ><el-icon><top /></el-icon
    ></el-radio>
    <el-radio label="西"
      ><el-icon><right /></el-icon
    ></el-radio>
    <el-radio label="东"
      ><el-icon><back /></el-icon
    ></el-radio>
  </el-radio-group>
</template>
<script>
import "@/assets/css/roadtype/downfork.css";
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