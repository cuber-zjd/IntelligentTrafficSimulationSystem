<template>
  <el-form :inline="true" class="demo-form-inline">
    <el-form-item label="参数名称">
      <el-input placeholder="输入参数名称"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary">查找</el-button>
    </el-form-item>
  </el-form>

  <el-row>
    <el-button type="primary" plain alin>
      <el-icon><plus /></el-icon>
      <span>添加</span>
    </el-button>
  </el-row>

  <!-- param表格 -->
  <el-table :data="paramTable" stripe style="width: 100%">
    <el-table-column prop="id" label="序号" />
    <el-table-column prop="name" label="名称" />
    <el-table-column prop="trafficFlow" label="车流量权重" />
    <el-table-column prop="num" label="车辆数目权重" />
    <el-table-column prop="speed" label="车速权重" />
    <el-table-column fixed="right" label="操作" align="center" width="300">
      <template v-slot="scope">
        <el-button type="success" size="small" @click="openEdit(scope.row.id)">
          <el-icon><edit /></el-icon>
          <span>修改</span></el-button
        >

        <!-- 删除 -->
        <el-popconfirm
          title="是否删除此参数列表？"
          @confirm="del(scope.row.id)"
        >
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

  <!-- 分页 -->
  <el-pagination
    background
    layout="prev, pager, next"
    :total="3"
    :page-size="5"
    :current-page="1"
  />
</template>

<script setup>
import { ref } from "vue";
const paramTable = ref([]);

paramTable.value = [
  {
    id: 1,
    name: "test1",
    trafficFlow: "0.3",
    num: "0.5",
    speed: "0.2",
  },
  {
    id: 3,
    name: "test2",
    trafficFlow: "0.2",
    num: "0.4",
    speed: "0.4",
  },
  {
    id: 4,
    name: "test3",
    trafficFlow: "0.4",
    num: "0.2",
    speed: "0.4",
  },
];
</script>


<style scoped>
.my-image {
}
.yellow {
  height: 10px;
  background: yellow;
  opacity: 0.5;
}
.blue {
  height: 10px;
  background: blue;
  opacity: 0.5;
}
.slider-demo-block {
  display: flex;
  align-items: center;
}
.slider-demo-block .el-slider {
  margin-top: 0;
  margin-left: 12px;
}
.slider-demo-block .demonstration {
  font-size: 14px;
  color: var(--el-text-color-secondary);
  line-height: 44px;
  flex: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-bottom: 0;
}
.slider-demo-block .demonstration + .el-slider {
  flex: 0 0 70%;
}
</style>