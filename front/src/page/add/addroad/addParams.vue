<template>
  <el-form ref="formRef" :model="form" label-width="120px">
    <el-form-item label="道路名称">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="经度">
      <el-input :model-value="$store.state.addroad.addRoadLng"></el-input>
    </el-form-item>
    <el-form-item label="纬度">
      <el-input :model-value="$store.state.addroad.addRoadLat"></el-input>
    </el-form-item>
    <el-form-item label="路口类型">
      <el-radio-group v-model="form.type">
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
</template>

<script>
import { intersectionPost } from "@/utils/api/intersection";

export default {
  data() {
    return {
      form: { userid: "" },
    };
  },
  watch: {
    lng(newLng) {
      this.form.lng = newLng;
    },
    lat(newLat) {
      this.form.lat = newLat;
    },
  },
  methods: {
    submit() {
      this.form.userid = this.$store.state.userId;
      // this.axios.post("http://localhost/intersections", this.form);
      intersectionPost(this.form, "").then((res) => {
        this.form = {};
        alert(res.data.msg);
      });
    },
  },
  computed: {
    lng() {
      return this.$store.state.addroad.addRoadLng;
    },
    lat() {
      return this.$store.state.addroad.addRoadLat;
    },
  },
};
</script>