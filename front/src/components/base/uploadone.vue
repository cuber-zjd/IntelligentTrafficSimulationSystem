<template>
  <el-upload
    class="upload-demo"
    :action="uploadUrl"
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

<script setup lang="ts">
import { ref } from "vue";

const uploadUrl = ref("");
const upload = ref(null);
const handleExceed = (files) => {
  upload.value.clearFiles();
  upload.value.handleStart(files[0]);
};
const submitUpload = (url) => {
  console.log(url);
  uploadUrl.value = url;
  upload.value.submit();
};
</script>
