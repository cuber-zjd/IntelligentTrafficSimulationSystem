<template>
  <el-tabs
    v-model="activeName"
    type="card"
    class="demo-tabs"
    @tab-click="handleClick"
  >
    <el-tab-pane label="道路参数" name="first">
      <road-params></road-params>
    </el-tab-pane>

    <el-tab-pane label="数据参数" name="second">
      <detector-params></detector-params>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
import DetectorParams from "./detectorParams/index";
import roadParams from "./roadParams.vue";
export default {
  components: { roadParams, DetectorParams },
};
</script>