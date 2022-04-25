<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>车速分布</span>
        <el-button class="button" type="text">重置</el-button>
      </div>
    </template>

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
import { getByRoadIdAndTime } from "@/api/data";
import { speedGroup, GroupConversion } from "@/utils/chartUtil/speedGroup";

const store = useStore();

let roadList = ref([]);

const initRoadList = () => {
  getAllByUserId(store.state.user.userid).then((res) => {
    roadList.value = res.data;
  });
};

const roadId = ref(null);

let chart = ref(null);

let dateTime = ref([]);

const speeds = ref([]);

const init = () => {
  const myChart = echarts.init(chart.value);
  myChart.setOption({
    title: {
      text: "车速分布图",
      subtext: "数据统计",
      left: "center",
    },
    tooltip: {
      trigger: "item",
    },
    legend: {
      orient: "vertical",
      left: "left",
    },
    series: [
      {
        name: "车速分布",
        type: "pie",
        radius: "50%",
        data: GroupConversion(speedGroup(speeds.value)),
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: "rgba(0, 0, 0, 0.5)",
          },
        },
      },
    ],
  });
};

const query = () => {
  console.log(dateTime.value);
  getByRoadIdAndTime({
    roadId: roadId.value,
    startTime: dateTime.value[0],
    endTime: dateTime.value[1],
  }).then((res) => {
    speeds.value = res.data;
    init();
    console.log(speeds.value);
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