<template>
  <el-card class="box-card" style="float: left">
    <template #header>
      <div class="card-header">
        <span>参数与权重 </span>
        <el-button class="button" type="text">重置</el-button>
      </div>
    </template>

    <el-form label-width="120px">
      <el-form-item label="时间范围：">
        <el-date-picker
          v-model="dateTime"
          type="datetimerange"
          align="right"
          unlink-panels
          range-separator="-"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          format="YYYY/MM/DD HH:mm:ss"
          value-format="YYYY-MM-DD HH:mm:ss"
          @change="handleChange"
        ></el-date-picker>
      </el-form-item>

      <el-form-item label="选择道路：">
        <el-select
          v-model="roadId"
          class="m-2"
          placeholder="选择道路"
          size="large"
        >
          <el-option
            v-for="item in roadList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="相邻道路长度：">
        <el-input
          placeholder="请输入相邻道路长度"
          v-model="roadLength"
        ></el-input>
      </el-form-item>

      <el-form-item label="选择参数：">
        <el-select
          v-model="paramId"
          class="m-2"
          placeholder="选择参数"
          size="large"
        >
          <el-option
            v-for="item in paramList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-descriptions class="margin-top" :column="2" :size="size" border>
        <!-- 道路名称 -->
        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon :style="iconStyle">
                <user />
              </el-icon>
              车流量权值
            </div>
          </template>
          {{ param.trafficFlow }}
        </el-descriptions-item>

        <el-descriptions-item>
          <template #label>
            <div class="cell-item">
              <el-icon :style="iconStyle">
                <user />
              </el-icon>
              车速权值
            </div>
          </template>
          {{ param.speed }}
        </el-descriptions-item>
      </el-descriptions>

      <el-form-item style="margin-top: 20px">
        <!-- 查询按钮 -->
        <el-button type="primary" class="m-2" @click="query">计算</el-button>
      </el-form-item>
    </el-form>

    <div ref="chart" style="width: 100%; height: 400px"></div>
  </el-card>
  <el-card class="box-card" style="float: right">
    <template #header>
      <div class="card-header">
        <span>信号灯 </span>
      </div>
    </template>

    <div class="light-box">
      <div class="light" style="background-color: #b22222">{{ lightTime }}</div>
      <div class="light" style="background-color: #ffd700">03</div>
      <div class="light" style="background-color: #32cd32">{{ lightTime }}</div>
    </div>
  </el-card>
</template>

<script setup>
import { getAllByUserId } from "@/api/road";
import { onMounted, ref, watch } from "vue-demi";
import { useStore } from "vuex";
import { getParamsByUserId, getById } from "@/api/lightParam";
import { getByRoadIdAndTime } from "@/api/data";
import { getDateFromString } from "@/utils/formatDate";

const store = useStore();

const dateTime = ref([]);
const lightTime = ref(0);
const roadLength = ref(0);

const param = ref({
  trafficFlow: 0,
  speed: 0,
});
const paramId = ref(null);

const roadId = ref(null);
let roadList = ref([]);
const initRoadList = () => {
  getAllByUserId(store.state.user.userid).then((res) => {
    roadList.value = res.data;
  });
};

let paramList = ref([]);
const initParamList = () => {
  getParamsByUserId(store.state.user.userid).then((res) => {
    paramList.value = res.data;
  });
};

watch(
  () => paramId.value,
  (val) => {
    getById(val).then((res) => {
      param.value = res.data;
    });
  }
);

const query = () => {
  getByRoadIdAndTime({
    roadId: roadId.value,
    startTime: dateTime.value[0],
    endTime: dateTime.value[1],
  }).then((res) => {
    const flow =
      res.data.length /
      ((getDateFromString(dateTime.value[1]) -
        getDateFromString(dateTime.value[0])) /
        1000 /
        60);
    let sum = 0;
    for (let i = 0; i < res.data.length; i++) {
      sum += res.data[i];
    }
    const speed = sum / res.data.length;
    lightTime.value = (
      ((roadLength.value / (speed * param.value.speed)) *
        (flow * param.value.trafficFlow)) /
      2
    ).toFixed(0);
    console.log(lightTime.value);
  });
};

onMounted(() => {
  initRoadList();
  initParamList();
});
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}
.box-card {
  width: 48%;
}

.light-box {
  border: 3px black;
  border-style: solid;
  border-radius: 100px;
  width: 100%;
  height: 200px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.light {
  width: 30%;
  margin-left: 1%;
  height: 160px;
  border-radius: 100%;
  color: white;
  line-height: 160px;
  font-size: 70px;
}
</style>