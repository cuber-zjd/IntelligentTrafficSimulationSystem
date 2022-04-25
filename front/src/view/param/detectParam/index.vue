<template>
  <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-form-item label="参数名称">
      <el-input placeholder="输入参数名称" v-model="searchForm.name"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="query">查找</el-button>
    </el-form-item>
  </el-form>

  <el-row>
    <el-button type="primary" plain alin @click="openAdd()">
      <el-icon><plus /></el-icon>
      <span>添加</span>
    </el-button>
  </el-row>

  <!-- param表格 -->
  <el-table :data="paramTable" stripe style="width: 100%">
    <el-table-column prop="id" label="序号" />
    <el-table-column prop="name" label="名称" />
    <el-table-column prop="twoLineDistant" label="两线距离(px)" />
    <el-table-column prop="lineLength" label="两线长度(%)" />
    <el-table-column prop="linePosy" label="黄线与顶部距离(%)" />
    <el-table-column prop="linePosx" label="线的水平位置(%)" />
    <el-table-column prop="distant" label="两线间的实际距离(m)" />
    <el-table-column fixed="right" label="操作" align="center" width="300">
      <template v-slot="scope">
        <el-button
          type="primary"
          size="small"
          @click="openDetail(scope.row.id)"
        >
          <el-icon><document-checked /></el-icon>
          <span>显示</span>
        </el-button>
        <el-button type="success" size="small" @click="openEdit(scope.row.id)">
          <el-icon><edit /></el-icon>
          <span>修改</span></el-button
        >

        <!-- 删除 -->
        <el-popconfirm
          title="是否删除此参数列表？"
          @confirm="del(scope.row.id)"
        >
          <template #reference>
            <el-button type="danger" size="small">
              <el-icon><delete /></el-icon>
              <span>删除</span>
            </el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <!-- 分页 -->
  <el-pagination
    background
    layout="prev, pager, next"
    :total="searchForm.total"
    :page-size="5"
    v-model:current-page="searchForm.current"
  />
  <!-- 添加框 -->
  <el-dialog
    v-model="addDialogVisible"
    title="添加检测参数"
    width="50%"
    :before-close="handleClose"
  >
    <el-form>
      <el-form-item label="选择背景图片：">
        <el-upload
          ref="uploadImg"
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-change="handleChange"
          :limit="1"
          :on-exceed="handleExceed"
          :auto-upload="false"
        >
          <template #trigger>
            <el-button type="primary">选择图片</el-button>
          </template>
        </el-upload>
      </el-form-item>
      <!-- 输入框 -->
      <el-form-item label="请输入距离(m)：">
        <el-input
          placeholder="两线之间的真实距离"
          v-model="param.distant"
        ></el-input>
      </el-form-item>

      <el-form-item label="名称：">
        <el-input
          placeholder="请为此检测参数命名以便区分"
          v-model="param.name"
        ></el-input>
      </el-form-item>
    </el-form>

    <div class="slider-demo-block">
      <span class="demonstration">两线之间的距离</span>
      <el-slider v-model="param.twoLineDistant" />
    </div>
    <div class="slider-demo-block">
      <span class="demonstration">线的长度</span>
      <el-slider v-model="param.lineLength" />
    </div>
    <div class="slider-demo-block">
      <span class="demonstration">线的位置(X轴)</span>
      <el-slider v-model="param.linePosx" />
    </div>
    <div class="slider-demo-block">
      <span class="demonstration">线的位置(Y轴)</span>
      <el-slider v-model="param.linePosy" />
    </div>

    <div class="my-image" :style="backClass">
      <div class="blue" :style="yellowLineStyle"></div>
      <div class="yellow" :style="blueLineStyle"></div>
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveParam">添加</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 图显框 -->
  <el-dialog
    v-model="detailDialogVisible"
    title="图显"
    width="50%"
    :before-close="handleClose"
  >
    <el-form>
      <el-form-item label="更换背景图片：">
        <el-upload
          ref="uploadImg"
          class="upload-demo"
          action="#"
          :on-change="detailHandleChange"
          :limit="1"
          :on-exceed="handleExceed"
          :auto-upload="false"
        >
          <template #trigger>
            <el-button type="primary">更换背景图片</el-button>
          </template>
        </el-upload>
      </el-form-item>
    </el-form>

    <div class="my-image" :style="detailBackClass">
      <div class="blue" :style="detailYellowLineStyle"></div>
      <div class="yellow" :style="detailBlueLineStyle"></div>
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDialogVisible = false">关闭</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 修改框 -->
  <el-dialog
    v-model="editDialogVisible"
    title="修改检测参数"
    width="50%"
    :before-close="handleClose"
  >
    <el-form>
      <el-form-item label="选择背景图片：">
        <el-upload
          ref="uploadImg"
          class="upload-demo"
          action="#"
          :on-change="editHandleChange"
          :limit="1"
          :on-exceed="handleExceed"
          :auto-upload="false"
        >
          <template #trigger>
            <el-button type="primary">选择图片</el-button>
          </template>
        </el-upload>
      </el-form-item>
      <el-form-item label="请输入距离(m)：">
        <el-input
          placeholder="两线之间的真实距离"
          v-model="editParam.distant"
        ></el-input>
      </el-form-item>

      <el-form-item label="名称：">
        <el-input
          placeholder="请为此检测参数命名以便区分"
          v-model="editParam.name"
        ></el-input>
      </el-form-item>

      <div class="slider-demo-block">
        <span class="demonstration">两线之间的距离</span>
        <el-slider v-model="editParam.twoLineDistant" />
      </div>
      <div class="slider-demo-block">
        <span class="demonstration">线的长度</span>
        <el-slider v-model="editParam.lineLength" />
      </div>
      <div class="slider-demo-block">
        <span class="demonstration">线的位置(X轴)</span>
        <el-slider v-model="editParam.linePosx" />
      </div>
      <div class="slider-demo-block">
        <span class="demonstration">线的位置(Y轴)</span>
        <el-slider v-model="editParam.linePosy" />
      </div>

      <div class="my-image" :style="editBackClass">
        <div class="blue" :style="editYellowLineStyle"></div>
        <div class="yellow" :style="editBlueLineStyle"></div>
      </div>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="editSaveParam">修改</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup>
import { onMounted, reactive, ref, watch } from "vue";
import { save, getPageList, getById, delById, update } from "@/api/detectParam";
import { ElMessage } from "element-plus/lib/components";
import { useStore } from "vuex";

const store = useStore();

//打开添加框
let addDialogVisible = ref(false);
const openAdd = () => {
  addDialogVisible.value = true;
};

//上传图片设置
let uploadImg = ref(null);

const handleExceed = (files) => {
  uploadImg.value.clearFiles();
  uploadImg.value.handleStart(files[0]);
  console.log(files[0]);
};

//将本地图片展示在浏览器上
let imageUrl = ref("");

//参数
let param = ref({
  name: "",
  userid: store.state.user.userid,
  lineLength: 100,
  twoLineDistant: 20,
  linePosx: 0,
  linePosy: 20,
  distant: 15,
});

//黄线参数
let yellowLineStyle = ref({
  width: param.value.lineLength * 6.82 + "px",
  marginLeft: param.value.linePosx + 6.82 + "px",
  marginTop: param.value.linePosy * 3.84 + "px",
});

//蓝线参数
let blueLineStyle = ref({
  width: param.value.lineLength * 6.82 + "px",
  marginLeft: param.value.linePosx * 6.82 + "px",
  marginTop: param.value.twoLineDistant + "px",
});

//监听参数，改变黄线和蓝线的位置
watch(
  param,
  (val) => {
    yellowLineStyle.value.width = val.lineLength * 6.82 + "px";
    yellowLineStyle.value.marginLeft = val.linePosx * 6.82 + "px";
    yellowLineStyle.value.marginTop = val.linePosy * 3.84 + "px";

    blueLineStyle.value.width = val.lineLength * 6.82 + "px";
    blueLineStyle.value.marginLeft = val.linePosx * 6.82 + "px";
    blueLineStyle.value.marginTop = val.twoLineDistant + "px";
  },
  {
    deep: true,
    immediate: true,
  }
);

//监听图片，改变背景图片
let backClass = ref({
  overflow: "hidden",
  width: "682px",
  height: "384px",
  backgroundImage: "url(" + imageUrl.value + ")",
  backgroundSize: "auto 100%",
});

const handleChange = (file) => {
  const reader = new FileReader();
  reader.onloadend = (e) => {
    imageUrl.value = e.target.result;
    backClass.value.backgroundImage = "url(" + imageUrl.value + ")";
  };
  reader.readAsDataURL(file.raw);
};

let searchForm = reactive({
  current: 1,
  total: 0,
  userid: store.state.user.userid,
});

//保存请求发起
const saveParam = () => {
  console.log("test");
  save(param.value).then((res) => {
    addDialogVisible.value = false;
    Table(searchForm);
    ElMessage.success(res.message);
  });
};

//表格展示
const paramTable = ref([]);

const Table = (searchForm) => {
  getPageList(searchForm).then((res) => {
    paramTable.value = res.data.records;
    searchForm.total = res.data.total;
  });
};

watch(
  () => searchForm.current,
  () => {
    Table(searchForm);
  },
  {
    deep: true,
    immediate: true,
  }
);

onMounted(() => {
  Table(searchForm);
});

//图显
let detailDialogVisible = ref(false);
let detailParam = ref({});
let detailYellowLineStyle = ref({
  width: detailParam.value.lineLength * 6.82 + "px",
  marginLeft: detailParam.value.linePosx * 6.82 + "px",
  marginTop: detailParam.value.linePosy * 3.84 + "px",
});

let detailBlueLineStyle = ref({
  width: detailParam.value.lineLength * 6.82 + "px",
  marginLeft: detailParam.value.linePosx * 6.82 + "px",
  marginTop: detailParam.value.twoLineDistant + "px",
});

watch(
  detailParam,
  (val) => {
    detailYellowLineStyle.value.width = val.lineLength * 6.82 + "px";
    detailYellowLineStyle.value.marginLeft = val.linePosx * 6.82 + "px";
    detailYellowLineStyle.value.marginTop = val.linePosy * 3.84 + "px";

    detailBlueLineStyle.value.width = val.lineLength * 6.82 + "px";
    detailBlueLineStyle.value.marginLeft = val.linePosx * 6.82 + "px";
    detailBlueLineStyle.value.marginTop = val.twoLineDistant + "px";
  },
  {
    deep: true,
    immediate: true,
  }
);

const detailImageUrl = ref(
  "https://static01.imgkr.com/temp/b79f912dc39149739085cba86bfcb690.jpg"
);

const detailBackClass = ref({
  overflow: "hidden",
  width: "682px",
  height: "384px",
  backgroundImage: "url(" + detailImageUrl.value + ")",
  backgroundSize: "auto 100%",
});

const detailHandleChange = (file) => {
  const reader = new FileReader();
  reader.onloadend = (e) => {
    detailImageUrl.value = e.target.result;
    detailBackClass.value.backgroundImage = "url(" + detailImageUrl.value + ")";
  };
  reader.readAsDataURL(file.raw);
};

const openDetail = (id) => {
  getById(id).then((res) => {
    detailParam.value = res.data;
    detailDialogVisible.value = true;
  });
};

//修改请求发起

const editDialogVisible = ref(false);
const editParam = ref({});
const editImageUrl = ref("");

const editHandleChange = (file) => {
  const reader = new FileReader();
  reader.onloadend = (e) => {
    editImageUrl.value = e.target.result;
    editBackClass.value.backgroundImage = "url(" + editImageUrl.value + ")";
  };
  reader.readAsDataURL(file.raw);
};

const editYellowLineStyle = ref({
  width: editParam.value.lineLength * 6.82 + "px",
  marginLeft: editParam.value.linePosx * 6.82 + "px",
  marginTop: editParam.value.linePosy * 3.84 + "px",
});
const editBlueLineStyle = ref({
  width: editParam.value.lineLength * 6.82 + "px",
  marginLeft: editParam.value.linePosx * 6.82 + "px",
  marginTop: editParam.value.twoLineDistant + "px",
});

const editBackClass = ref({
  overflow: "hidden",
  width: "682px",
  height: "384px",
  backgroundImage: "url(" + editImageUrl.value + ")",
  backgroundSize: "auto 100%",
});

watch(
  editParam,
  (val) => {
    editYellowLineStyle.value.width = val.lineLength * 6.82 + "px";
    editYellowLineStyle.value.marginLeft = val.linePosx * 6.82 + "px";
    editYellowLineStyle.value.marginTop = val.linePosy * 3.84 + "px";

    editBlueLineStyle.value.width = val.lineLength * 6.82 + "px";
    editBlueLineStyle.value.marginLeft = val.linePosx * 6.82 + "px";
    editBlueLineStyle.value.marginTop = val.twoLineDistant + "px";
  },
  {
    deep: true,
    immediate: true,
  }
);

const editSaveParam = () => {
  update(editParam.value).then((res) => {
    editDialogVisible.value = false;
    Table(searchForm);
    ElMessage.success(res.message);
  });
};

const openEdit = (id) => {
  getById(id).then((res) => {
    editParam.value = res.data;
    editDialogVisible.value = true;
  });
};

//删除请求发起
const del = (id) => {
  delById(id).then((res) => {
    ElMessage.success(res.message);
    Table(searchForm);
  });
};

//查询请求发起
const query = () => {
  Table(searchForm);
};
</script>

<style scoped>
.my-image {
}
.yellow {
  height: 10px;
  background: yellow;
  opacity: 0.5;
}
.blue {
  height: 10px;
  background: blue;
  opacity: 0.5;
}
.slider-demo-block {
  display: flex;
  align-items: center;
}
.slider-demo-block .el-slider {
  margin-top: 0;
  margin-left: 12px;
}
.slider-demo-block .demonstration {
  font-size: 14px;
  color: var(--el-text-color-secondary);
  line-height: 44px;
  flex: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  margin-bottom: 0;
}
.slider-demo-block .demonstration + .el-slider {
  flex: 0 0 70%;
}
</style>