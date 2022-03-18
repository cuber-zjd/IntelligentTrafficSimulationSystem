<template>
  <el-upload
    ref="upload"
    class="upload-demo"
    :action="url"
    :limit="1"
    :on-exceed="handleExceed"
    :auto-upload="false"
  >
    <template #trigger>
      <el-button type="primary">选择文件</el-button>
    </template>
    &nbsp;&nbsp;&nbsp;
    <el-button class="ml-3" type="success" @click="submitUpload"
      >上传文件</el-button
    >
  </el-upload>
</template>

<script>
import { ref } from "vue";
export default {
  setup() {
    const upload = ref(null);
    const handleExceed = (files) => {
      upload.value.clearFiles();
      upload.value.handleStart(files[0]);
    };
    const submitUpload = () => {
      upload.value.submit();
    };
    return {
      upload,
      handleExceed,
      submitUpload,
    };
  },
  computed: {
    url() {
      return (
        "http://localhost/datas/upload?" +
        "filename=" +
        this.$store.state.userId +
        "--" +
        this.$store.state.adddata.sectionId +
        "--" +
        this.$store.state.adddata.date +
        "--" +
        this.$store.state.adddata.direction +
        "--" +
        this.$store.state.adddata.detectDirection
      );
    },
  },
};
// import { ref } from "vue";
// const upload = ref();

// const handleExceed = (files) => {
//   upload.value.clearFiles();
//   upload.value.handleStart(files[0]);
// };
// const submitUpload = () => {
//   upload.value.submit();
// };
</script>
