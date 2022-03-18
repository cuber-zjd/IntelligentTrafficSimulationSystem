<template>
  <el-row>
    <!-- 选择道路 -->
    <el-col :span="15" style="padding-left: 10px; padding-right: 10px">
      <el-card class="box-card">
        <!-- 头部 -->
        <template #header>
          <div class="card-header">
            <span>选择道路</span>
          </div>
        </template>

        <div class=".card-header-body">
          <map-container></map-container>
        </div>
      </el-card>
    </el-col>

    <!-- 相关参数 -->
    <el-col :span="9" style="padding-left: 10px; padding-right: 10px">
      <el-card class="box-card">
        <!-- 头部 -->
        <template #header>
          <div class="card-header">
            <span>相关参数</span>
          </div>
        </template>

        <div class=".card-header-body">
          <params></params>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>
<script>
import MapContainer from "@/page/add/adddata/MapContainer";
import Params from "@/page/add/adddata/params/index";
export default {
  components: {
    MapContainer,
    Params,
  },
  template: {
    MapContainer,
    Params,
  },
};
</script>