<template>
  <div style="float: left">上传文件：</div>
  <br /><br />
  <upload ref="upload"></upload><br />
  <div style="float: left">选择视频中要检测的车辆行进方向：</div>
  <br /><br />
  <el-radio-group v-model="direction">
    <el-radio label="1">上行</el-radio>
    <el-radio label="2">下行</el-radio>
  </el-radio-group>
  <br />
  <div style="float: left">选择时间：</div>
  <br />
  <br />
  <datepicker></datepicker>

  <el-button type="primary" @click="submitData">提交</el-button>
</template>
<script>
import { ref } from "vue";

import upload from "@/components/elementplus/uploadone";
import Datepicker from "@/components/elementplus/datepicker";
import { dataPost } from "@/utils/api/data";

export default {
  setup() {
    const upload = ref(null);
    const uploadSubmit = () => {
      upload.value.submitUpload();
    };
    return {
      upload,
      uploadSubmit,
    };
  },
  data() {
    return {
      direction: "",
    };
  },
  components: {
    upload,
    Datepicker,
  },
  watch: {
    direction(newDirection) {
      this.$store.commit("setDetectDirection", newDirection);
    },
  },
  methods: {
    submitData() {
      this.uploadSubmit();
      dataPost(
        null,
        "/autoadd?" +
          "userid=" +
          this.$store.state.userId +
          "&&sectionid=" +
          this.$store.state.adddata.sectionId +
          "&&date=" +
          this.$store.state.adddata.date +
          "&&direction=" +
          this.$store.state.adddata.direction +
          "&&detectDirection=" +
          this.$store.state.adddata.detectDirection
      )
        .then((res) => {
          if (res.data.flag == true) {
            alert("添加成功");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>