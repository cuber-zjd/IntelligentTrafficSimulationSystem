import AMapLoader from "@amap/amap-jsapi-loader";
let serve = AMapLoader.load({
    key: "339ce89acd28bd971dffd76ea92badf3", // 申请好的Web端开发者Key，首次调用 load 时必填
    version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
    plugins: [""], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
})
export default serve