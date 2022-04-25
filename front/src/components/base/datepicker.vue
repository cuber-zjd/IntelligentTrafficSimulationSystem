<template>
  <div class="demo-datetime-picker">
    <div class="block">
      <el-date-picker
        v-model="date"
        type="datetime"
        placeholder="Pick a Date"
        format="YYYY/MM/DD HH:mm:ss"
      >
      </el-date-picker>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref } from "vue-demi";

const date = ref("");
// watch(date, (newVal) => {
//   console.log(newVal);
// });
defineProps({
  date.value;
})
</script>