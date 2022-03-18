<template>
  <div class="demo-datetime-picker">
    <div class="block">
      <el-date-picker
        v-model="value"
        type="datetime"
        placeholder="Pick a Date"
        format="YYYY/MM/DD HH:mm:ss"
      >
      </el-date-picker>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      value: "",
    };
  },
  watch: {
    value(newvalue) {
      this.$store.commit("setDate", newvalue);
    },
  },
};
</script>