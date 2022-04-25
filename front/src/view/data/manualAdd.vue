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
          <el-tabs
            v-model="activeName"
            type="card"
            class="demo-tabs"
            @tab-click="handleClick"
          >
            <!-- 道路参数 -->
            <el-tab-pane
              label="道路参数"
              name="first"
              style="text-align: center"
            >
              <el-descriptions
                class="margin-top"
                title="参数"
                :column="1"
                :size="size"
                border
              >
                <!-- 重置按钮 -->
                <template #extra>
                  <el-button type="primary">重置</el-button>
                </template>
                <!-- 道路名称 -->
                <el-descriptions-item>
                  <template #label>
                    <div class="cell-item">
                      <el-icon :style="iconStyle">
                        <user />
                      </el-icon>
                      道路名称
                    </div>
                  </template>
                  {{ road.name }}
                </el-descriptions-item>
                <!-- 经纬度 -->
                <el-descriptions-item>
                  <template #label>
                    <div class="cell-item">
                      <el-icon :style="iconStyle">
                        <iphone />
                      </el-icon>
                      经纬度
                    </div>
                  </template>
                  {{ road.lng }},{{ road.lat }}
                </el-descriptions-item>
                <!-- 类型 -->
                <el-descriptions-item>
                  <template #label>
                    <div class="cell-item">
                      <el-icon :style="iconStyle">
                        <location />
                      </el-icon>
                      类型
                    </div>
                  </template>
                  {{ road.type }}
                </el-descriptions-item>
              </el-descriptions>
              <!-- 车辆行进方向 -->
              <div style="float: left; margin-top: 10px">车辆行进方向：</div>
              <br /><br />
              <component :is="componentId" />
              <el-radio-group v-model="paramForm.direction">
                <el-radio label="北"
                  ><el-icon><bottom /></el-icon
                ></el-radio>
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
              <br /><br /><br />
              <el-button type="primary" @click="next">下一步</el-button>
            </el-tab-pane>

            <!-- 数据参数 -->
            <el-tab-pane label="数据参数" name="second">
              <el-form label-width="100px">
                <el-form-item label="开始时间:">
                  <el-date-picker
                    v-model="paramForm.startDate"
                    type="datetime"
                    placeholder="选择开始时间"
                    format="YYYY/MM/DD HH:mm:ss"
                    value-format="YYYY-MM-DD HH:mm:ss"
                  >
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="结束时间:">
                  <el-date-picker
                    v-model="paramForm.endDate"
                    type="datetime"
                    placeholder="选择结束时间"
                    format="YYYY/MM/DD HH:mm:ss"
                    value-format="YYYY-MM-DD HH:mm:ss"
                  >
                  </el-date-picker>
                </el-form-item>
                <el-form-item label="车辆数量：">
                  <el-input
                    placeholder="车辆数量"
                    v-model="paramForm.num"
                  ></el-input>
                </el-form-item>
                <el-form-item label="最大车速:">
                  <el-input
                    placeholder="最大车速"
                    v-model="paramForm.maxSpeed"
                  ></el-input>
                </el-form-item>
                <el-form-item label="最小车速:">
                  <el-input
                    placeholder="最小车速"
                    v-model="paramForm.minSpeed"
                  ></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitData">提交</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup>
import { ref, shallowRef } from "@vue/reactivity";
import { onMounted, watch } from "vue-demi";
import { useStore } from "vuex";

import cross from "@/components/roadtype/cross";
import upfork from "@/components/roadtype/upfork";
import leftfork from "@/components/roadtype/leftfork";
import rightfork from "@/components/roadtype/rightfork";
import downfork from "@/components/roadtype/downfork";
import updown from "@/components/roadtype/updown";
import leftright from "@/components/roadtype/leftright";

import { GetAllByUserId } from "@/model/road";
import mapServe from "@/utils/getMap";
import { manualAdd } from "@/api/data";
import { ElMessage } from "element-plus/lib/components";

//地图选择地点部分
let map = shallowRef(null);
let road = ref({});
const store = useStore();

let activeName = ref("first");

const initMap = (roadList) => {
  // 设置缩放大小
  mapServe.then((AMap) => {
    map = new AMap.Map("container", {
      zoom: 15,
    });

    // 获取点标记列表
    var markerList = [];
    for (let i = 0; i < roadList.length; i++) {
      var marker = new AMap.Marker({
        position: new AMap.LngLat(roadList[i].lng, roadList[i].lat),
        title: [i].name,
      });

      marker.setClickable(true);
      //获取点标记属性
      marker.on("click", () => {
        road.value = roadList[i];
      });

      markerList.push(marker);
    }

    map.add(markerList);
  });
};

//车辆行进方向

let componentId = ref(cross);

watch(
  road,
  (newVal) => {
    if (newVal.type === "十字路口") {
      componentId.value = cross;
    }
    if (newVal.type === "三岔路口(北向)") {
      componentId.value = upfork;
    }
    if (newVal.type === "三岔路口(南向)") {
      componentId.value = downfork;
    }
    if (newVal.type === "三岔路口(西向)") {
      componentId.value = leftfork;
    }
    if (newVal.type === "三岔路口(东向)") {
      componentId.value = rightfork;
    }
    if (newVal.type === "直行路口(南北)") {
      componentId.value = updown;
    }
    if (newVal.type === "直行路口(东西)") {
      componentId.value = leftright;
    }
  },
  {
    immediate: true,
    deep: true,
  }
);

//提交
let paramForm = ref({});
const submitData = () => {
  paramForm.value.userid = store.state.user.userid;
  paramForm.value.roadid = road.value.id;

  manualAdd(paramForm.value).then(() => {
    ElMessage({
      message: "添加成功",
      type: "success",
    });
  });
};

//实例构建完成后
onMounted(() => {
  GetAllByUserId(store.state.user.userid).then((res) => {
    initMap(res.data);
  });
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
