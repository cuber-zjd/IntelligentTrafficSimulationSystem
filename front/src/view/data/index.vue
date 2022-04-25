<template>
  <!-- 查询 -->
  <el-form
    :inline="true"
    :model="formInline"
    class="demo-form-inline"
    style="text-align: left"
  >
    <el-form-item label="道路名称：">
      <el-input placeholder="道路名称" v-model="searchForm.roadName"></el-input>
    </el-form-item>

    <el-form-item label="行驶方向：">
      <el-select
        v-model="searchForm.direction"
        multiple
        collapse-tags
        placeholder="选择道路类型"
        style="width: 240px"
      >
        <el-option
          v-for="item in directions"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
    </el-form-item>

    <el-form-item label="开始时间:">
      <div class="demo-datetime-picker">
        <div class="block">
          <el-date-picker
            v-model="searchForm.startDate"
            type="datetime"
            placeholder="选择开始时间"
            format="YYYY/MM/DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
          >
          </el-date-picker>
        </div>
      </div>
    </el-form-item>

    <el-form-item label="结束时间:">
      <div class="demo-datetime-picker">
        <div class="block">
          <el-date-picker
            v-model="searchForm.endDate"
            type="datetime"
            placeholder="选择结束时间"
            format="YYYY/MM/DD HH:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
          >
          </el-date-picker>
        </div>
      </div>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="query">查找</el-button>
    </el-form-item>
  </el-form>

  <!-- 功能按钮 -->
  <el-row>
    <el-button type="primary" plain alin @click="addRoad">
      <el-icon><plus /></el-icon>
      <span>检测添加</span>
    </el-button>

    <el-button type="primary" plain alin @click="manualAddRoad">
      <el-icon><plus /></el-icon>
      <span>手动添加</span>
    </el-button>
  </el-row>

  <!-- 表格 -->
  <el-table :data="dataTable" stripe style="width: 100%">
    <el-table-column prop="id" label="序号" />1
    <el-table-column prop="roadName" label="所属道路" />
    <el-table-column prop="direction" label="方向" />
    <el-table-column prop="starttime" label="开始时间" />
    <el-table-column prop="endtime" label="结束时间" />
    <el-table-column prop="paramName" label="检测参数" />
    <el-table-column prop="sourceName" label="来源" />

    <el-table-column fixed="right" label="操作" align="center" width="200">
      <template #default="props">
        <el-button
          type="primary"
          size="small"
          @click="openDetail(props.row.id)"
        >
          <el-icon><document-checked /></el-icon>
          <span>数据</span></el-button
        >

        <!-- 删除 -->
        <el-popconfirm title="是否删除此数据？" @confirm="del(props.row.id)">
          <template #reference>
            <el-button type="danger" size="small">
              <el-icon><delete /></el-icon>
              <span>删除</span>
            </el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <!-- 数据显示框 -->
  <el-dialog
    v-model="detailDialogVisible"
    title="数据"
    width="50%"
    :before-close="handleClose"
    style="text-align: center"
  >
    <el-descriptions title="统计" :column="4" border direction="vertical">
      <el-descriptions-item
        label="车辆数量"
        label-align="center"
        align="center"
      >
        {{ detailForm.num }}
      </el-descriptions-item>

      <el-descriptions-item
        label="平均速度"
        label-align="center"
        align="center"
      >
        {{ detailForm.avgSpeed }}
      </el-descriptions-item>

      <el-descriptions-item
        label="最大速度"
        label-align="center"
        align="center"
      >
        {{ detailForm.maxSpeed }}
      </el-descriptions-item>
      <el-descriptions-item
        label="最小速度"
        label-align="center"
        align="center"
      >
        {{ detailForm.minSpeed }}
      </el-descriptions-item>
    </el-descriptions>

    <div
      ref="chart"
      style="width: 600px; height: 400px; margin: auto; margin-top: 20px"
    ></div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="detailDialogVisible = false">关闭</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 分页 -->
  <el-pagination
    background
    layout="prev, pager, next"
    :total="total"
    :page-size="5"
    v-model:current-page="current"
  />
</template>
<script setup>
import { onMounted, ref, watch } from "vue-demi";
import { getPageList, deleteById } from "@/api/data";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
import { GetDetail } from "@/model/data";
import * as echarts from "echarts";
import { speedGroup } from "@/utils/chartUtil/speedGroup";

const store = useStore();
const router = useRouter();

// 分页查询
let searchForm = ref({});
const directions = [
  {
    value: "东",
    label: "东",
  },
  {
    value: "西",
    label: "西",
  },
  {
    value: "南",
    label: "南",
  },
  {
    value: "北",
    label: "北",
  },
];
let dataTable = ref([]);
const current = ref(1);
const total = ref(0);

const Table = (currentPage, searchForm) => {
  getPageList(store.state.user.userid, currentPage, searchForm).then((res) => {
    dataTable.value = res.data.records;
    total.value = res.data.total;
  });
};
watch(current, (newVal) => {
  Table(newVal, searchForm.value);
});
const query = () => {
  Table(current.value, searchForm.value);
};

// 数据;
let detailDialogVisible = ref(false);
let detailForm = ref({});

const chart = ref(null);
const chartsInit = (speedGroup) => {
  const myChart = echarts.init(chart.value);
  myChart.setOption({
    title: {
      text: "速度统计",
      subtext: "统计数据",
    },
    tooltip: {
      trigger: "axis",
    },
    legend: {
      data: ["车辆数目"],
    },
    toolbox: {
      show: true,
      feature: {
        dataView: { readOnly: false },
        magicType: { type: ["line", "bar", "pie"] },
        restore: {},
        saveAsImage: {},
      },
    },
    xAxis: {
      type: "category",
      data: Object.keys(speedGroup),
    },
    yAxis: {
      type: "value",
    },
    series: [
      {
        name: "车辆数目",
        type: "bar",
        data: Object.values(speedGroup),
      },
    ],
  });
};

let speedList = [];

const openDetail = (dataid) => {
  GetDetail(dataid)
    .then((res) => {
      speedList = res.data;

      detailForm.value = {
        num: speedList.length,
        avgSpeed: (
          speedList.reduce((a, b) => a + b, 0) / speedList.length
        ).toFixed(2),
        maxSpeed: Math.max(...speedList).toFixed(2),
        minSpeed: Math.min(...speedList).toFixed(2),
      };
      detailDialogVisible.value = true;
    })
    .then(() => {
      chartsInit(speedGroup(speedList));
    });
};

//删除
const del = (id) => {
  deleteById(id).then(() => {
    Table(1, searchForm.value);
    ElMessage({
      message: "删除成功",
      type: "success",
    });
  });
};

//跳转到添加页面
const addRoad = () => {
  router.push("/addData");
};

const manualAddRoad = () => {
  router.push("/manualAddData");
};

//实例构造完成后
onMounted(() => {
  Table(current.value, searchForm.value);
});
</script>
