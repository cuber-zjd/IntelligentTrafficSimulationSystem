<template>
  <el-descriptions
    class="margin-top"
    title="参数"
    :column="1"
    :size="size"
    border
  >
    <template #extra>
      <el-button type="primary">重置</el-button>
    </template>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <user />
          </el-icon>
          道路名称
        </div>
      </template>
      {{ $store.state.adddata.name }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <iphone />
          </el-icon>
          经纬度
        </div>
      </template>
      {{ $store.state.adddata.lng }},
      {{ $store.state.adddata.lat }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon :style="iconStyle">
            <location />
          </el-icon>
          类型
        </div>
      </template>
      {{ $store.state.adddata.type }}
    </el-descriptions-item>
  </el-descriptions>
  <div style="float: left; margin-top: 10px">车辆行进方向：</div>
  <br /><br />
  <router-view></router-view>
  <br /><br /><br />
  <el-button type="primary" @click="next">下一步</el-button>
</template>

<script>
export default {
  watch: {
    type(newType) {
      this.typeRouter(newType);
    },
  },
  computed: {
    type() {
      return this.$store.state.adddata.type;
    },
  },
  methods: {
    // 根据道路类型跳转
    typeRouter(newType) {
      if (newType === "十字路口") {
        this.$router.push("/cross");
      }
      if (newType === "三岔路口(北向)") {
        this.$router.push("/upfork");
      }
      if (newType === "三岔路口(南向)") {
        this.$router.push("/downfork");
      }
      if (newType === "三岔路口(西向)") {
        this.$router.push("/leftfork");
      }
      if (newType === "三岔路口(东向)") {
        this.$router.push("/rightfork");
      }
      if (newType === "直行道路(南北)") {
        this.$router.push("/updown");
      }
      if (newType === "直行道路(东西)") {
        this.$router.push("/leftright");
      }
    },

    next() {},
  },
  mounted() {
    this.typeRouter(this.$store.state.adddata.type);
  },
};
</script>
