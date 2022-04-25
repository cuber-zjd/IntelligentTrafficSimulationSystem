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
          <div id="container"></div>
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
          <el-form ref="formRef" :model="form" label-width="120px">
            <el-form-item label="道路名称">
              <el-input v-model="roadParam.name"></el-input>
            </el-form-item>
            <el-form-item label="经度">
              <el-input :model-value="roadParam.lng"></el-input>
            </el-form-item>
            <el-form-item label="纬度">
              <el-input :model-value="roadParam.lat"></el-input>
            </el-form-item>
            <el-form-item label="路口类型">
              <el-radio-group v-model="roadParam.type">
                <el-radio label="十字路口"></el-radio>
                <el-radio label="三岔路口(北向)"></el-radio>
                <el-radio label="三岔路口(南向)"></el-radio>
                <el-radio label="三岔路口(西向)"></el-radio>
                <el-radio label="三岔路口(东向)"></el-radio>
                <el-radio label="直行路口(南北)"></el-radio>
                <el-radio label="直行路口(东西)"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-button type="primary" @click="submit">保存</el-button>
            <el-button type="danger">重置</el-button>
          </el-form>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>
<script setup>
import { onMounted, reactive, ref, shallowRef } from "vue";
import mapServe from "@/utils/getMap";
import { save } from "@/api/road";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const router = useRouter();
const store = useStore();

//地图相关
let map = shallowRef(null);
let marker = reactive({});

const markLocation = () => {
  roadParam.value.lng = marker._position[0];
  roadParam.value.lat = marker._position[1];
};

const initMap = () => {
  mapServe
    .then((AMap) => {
      // 定义缩放大小
      map.value = new AMap.Map("container", {
        zoom: 15,
      });

      // 定义所有标记点
      marker = new AMap.Marker({
        position: map.value.getCenter(),
        draggable: true,
        cursor: "move",
        // 设置拖拽效果
        raiseOnDrag: true,
      });
      map.value.add(marker);
      map.value.on("mousemove", markLocation);
    })
    .catch((e) => {
      console.log(e);
    });
};

//参数相关
let roadParam = ref({
  name: "",
  type: "",
  lng: "",
  lat: "",
  userid: "",
});

const submit = () => {
  roadParam.value.userid = store.state.user.userid;
  save(roadParam.value).then(() => {
    router.push("/road");
    ElMessage({
      message: "保存成功！",
      type: "success",
    });
  });
};

//实例构建完成后
onMounted(() => {
  initMap();
});
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.card-header-body {
  padding: 15px 20px 20px 20px;
}

#container {
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: 800px;
}
</style>