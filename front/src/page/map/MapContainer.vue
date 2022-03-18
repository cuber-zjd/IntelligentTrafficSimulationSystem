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
import AMapLoader from "@amap/amap-jsapi-loader";
import { shallowRef } from "@vue/reactivity";
export default {
  setup() {
    const map = shallowRef(null);
    return {
      map,
    };
  },
  methods: {
    initMap() {
      AMapLoader.load({
        key: "339ce89acd28bd971dffd76ea92badf3", // 申请好的Web端开发者Key，首次调用 load 时必填
        version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: [""], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      })
        .then((AMap) => {
          let mymap = new AMap.Map("container", {
            zoom: 15,
          });

          // 定位插件
          mymap.plugin("AMap.Geolocation", function () {
            let geolocation = new AMap.Geolocation({
              enableHighAccuracy: true, //是否使用高精度定位，默认:true
            });
            mymap.addControl(geolocation);
            geolocation.getCurrentPosition();
          });

          //鼠标工具类
          mymap.plugin(["AMap.MouseTool"], function () {
            var mousetool = new AMap.MouseTool(mymap);
            mousetool.marker(); //使用鼠标工具，在地图上画标记点
            // mousetool.rectangle();
            // mousetool.circle();
          });

          this.map = mymap;
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