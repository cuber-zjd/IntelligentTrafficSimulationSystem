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
import { intersectionGet } from "@/utils/api/intersection";
export default {
  setup() {
    const map = shallowRef(null);
    return {
      map,
    };
  },
  data() {
    return {
      intersectionLIst: [],
      section: {
        lng: "",
        lat: "",
        name: "",
        type: "",
      },
    };
  },
  methods: {
    initMap(intersectionLIst) {
      // 设置缩放大小
      mapServe.then((AMap) => {
        this.map = new AMap.Map("container", {
          zoom: 15,
        });

        // 获取点标记列表
        var markerList = [];
        for (let i = 0; i < intersectionLIst.length; i++) {
          var marker = new AMap.Marker({
            position: new AMap.LngLat(
              intersectionLIst[i].lng,
              intersectionLIst[i].lat
            ),
            title: intersectionLIst[i].name,
          });
          marker.setClickable(true);

          //获取点标记属性
          marker.on("click", () => {
            let section = {
              id: intersectionLIst[i].id,
              lng: intersectionLIst[i].lng,
              lat: intersectionLIst[i].lat,
              type: intersectionLIst[i].type,
              name: intersectionLIst[i].name,
            };
            this.$store.commit("setSelectedSection", section);
          });

          markerList.push(marker);
        }

        this.map.add(markerList);
      });
    },
  },
  mounted() {
    new Promise((resolve) => {
      // 获取点标记列表
      intersectionGet("/getByUser/" + this.$store.state.userId).then((res) => {
        this.intersectionList = res.data.data;
        resolve(res.data.data);
      });
    }).then((intersectionList) => {
      //DOM初始化完成进行地图初始化
      this.initMap(intersectionList);
    });
  },
};
</script>