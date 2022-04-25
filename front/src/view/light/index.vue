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

      <el-form-item label="车流量权值">
        <el-input placeholder="请输入车流量权重"></el-input>
      </el-form-item>

      <el-form-item label="车辆数目权重">
        <el-input placeholder="请输入车辆数目权重"></el-input>
      </el-form-item>

      <el-form-item label="车速权值">
        <el-input placeholder="请输入车速权值"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="success" class="m-2" @click="query"
          >保存权值</el-button
        >
        <!-- 查询按钮 -->
        <el-button type="primary" class="m-2" @click="query">查询</el-button>
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
      <div class="light" style="background-color: #b22222">00</div>
      <div class="light" style="background-color: #ffd700">00</div>
      <div class="light" style="background-color: #32cd32">00</div>
    </div>
  </el-card>
</template>

<script setup>
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