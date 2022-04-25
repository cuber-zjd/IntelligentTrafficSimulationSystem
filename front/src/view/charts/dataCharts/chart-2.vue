<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>车速、车辆数目变化图</span>
        <el-button class="button" type="text">重置</el-button>
      </div>
    </template>
    <!-- 开始时间-结束时间 -->

    <el-form>
      <el-form-item>
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

      <el-form-item width="150px">
        <el-input
          v-model="segmentValue"
          placeholder="分割时间(分钟)"
        ></el-input>
      </el-form-item>

      <el-form-item>
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

      <el-form-item>
        <!-- 查询按钮 -->
        <el-button type="primary" class="m-2" @click="query">查询</el-button>
      </el-form-item>
    </el-form>

    <div ref="chart" style="width: 100%; height: 400px"></div>
  </el-card>
</template>
<script setup>
import * as echarts from "echarts";
import { onMounted, ref } from "vue-demi";
import { getAllByUserId } from "@/api/road";
import { useStore } from "vuex";
import { getAllByRoadIdAndTime } from "@/api/data";
import { getDateFromString } from "@/utils/formatDate";
import { dateSegGroup } from "@/utils/chartUtil/dateSegGroup";

const store = useStore();

let roadList = ref([]);

const initRoadList = () => {
  getAllByUserId(store.state.user.userid).then((res) => {
    roadList.value = res.data;
  });
};

const chart = ref(null);

const segments = ref([]);

const init = () => {
  const myChart = echarts.init(chart.value);
  myChart.setOption({
    title: {
      text: "路口统计",
      subtext: "统计数据",
    },
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: ["路口数量"],
    },
    toolbox: {
      show: true,
      feature: {
        dataView: { show: true, readOnly: false },
        magicType: { show: true, type: ["line", "bar"] },
        restore: { show: true },
        saveAsImage: { show: true },
      },
    },
    calculable: true,
    xAxis: [
      {
        type: "category",
      },
    ],
    yAxis: [
      {
        type: "value",
      },
    ],
    series: [
      {
        name: "车辆数目",
        type: "bar",
        data: segments.value.map((item) => item.data.length),
      },
    ],
  });
};

const dateTime = ref([]);

const segmentValue = ref(null);

const roadId = ref(null);

const query = () => {
  console.log(dateTime.value);
  getAllByRoadIdAndTime({
    roadId: roadId.value,
    startTime: dateTime.value[0],
    endTime: dateTime.value[1],
  }).then((res) => {
    const data = res.data;
    segments.value = dateSegGroup(
      data,
      segmentValue.value,
      getDateFromString(dateTime.value[0]),
      getDateFromString(dateTime.value[1])
    );
    init();
  });
};

onMounted(() => {
  init();
  initRoadList();
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
</style>