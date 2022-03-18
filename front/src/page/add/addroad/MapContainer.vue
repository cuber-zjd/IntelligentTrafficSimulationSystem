<template>
  <div id="container"></div>
</template>


<style  scoped>
#container {
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: 800px;
}
</style>

<script>
import mapServe from "@/utils/getMap";

import { shallowRef } from "@vue/reactivity";
export default {
  setup() {
    const map = shallowRef(null);
    const marker = shallowRef(null);
    return {
      map,
      marker,
    };
  },

  data() {
    return {
      location: {
        lng: "",
        lat: "",
      },
    };
  },

  methods: {
    // 选择位置
    markLocation() {
      this.location = this.marker.getPosition();
      //调用mutation修改全局变量
      this.$store.commit("setAddRoadLngLat", this.location);
    },

    // 初始化地图
    initMap() {
      mapServe
        .then((AMap) => {
          // 定义缩放大小
          this.map = new AMap.Map("container", {
            zoom: 15,
          });

          // 定义所有标记点
          this.marker = new AMap.Marker({
            position: this.map.getCenter(),
            draggable: true,
            cursor: "move",
            // 设置拖拽效果
            raiseOnDrag: true,
          });
          this.map.add(this.marker);
          this.map.on("mousemove", this.markLocation);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    //DOM初始化完成进行地图初始化
    this.initMap();
  },
};
</script>