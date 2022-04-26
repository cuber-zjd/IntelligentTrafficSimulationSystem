<template>
  <el-form :inline="true" class="demo-form-inline">
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
    <el-table-column prop="trafficFlow" label="车流量权重" />
    <el-table-column prop="num" label="车辆数目权重" />
    <el-table-column prop="speed" label="车速权重" />
    <el-table-column fixed="right" label="操作" align="center" width="300">
      <template v-slot="scope">
        <el-button type="success" size="small" @click="openEdit(scope.row.id)">
          <el-icon><edit /></el-icon>
          <span>修改</span></el-button
        >

        <!-- 删除 -->
        <el-popconfirm
          title="是否删除此参数列表？"
          @confirm="deleteParam(scope.row.id)"
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
    @current-change="query"
  />

  <!-- 添加框 -->
  <el-dialog
    v-model="addDialogVisible"
    title="添加检测参数"
    width="50%"
    :before-close="handleClose"
  >
    <el-form label-width="130px">
      <el-form-item label="名称：">
        <el-input
          placeholder="请为此检测参数命名以便区分"
          v-model="addParam.name"
        ></el-input>
      </el-form-item>

      <el-form-item label="车流量权重：">
        <el-input
          placeholder="请输入车流量权重"
          v-model="addParam.trafficFlow"
        ></el-input>
      </el-form-item>

      <el-form-item label="车辆数目权重：">
        <el-input
          placeholder="请输入车辆数目权重"
          v-model="addParam.num"
        ></el-input>
      </el-form-item>

      <el-form-item label="车速权重：">
        <el-input
          placeholder="请输入车速权重"
          v-model="addParam.speed"
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="add">添加</el-button>
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
    <el-form label-width="130px">
      <el-form-item label="名称：">
        <el-input
          placeholder="请为此检测参数命名以便区分"
          v-model="editParam.name"
        ></el-input>
      </el-form-item>

      <el-form-item label="车流量权重：">
        <el-input
          placeholder="请输入车流量权重"
          v-model="editParam.trafficFlow"
        ></el-input>
      </el-form-item>

      <el-form-item label="车辆数目权重：">
        <el-input
          placeholder="请输入车辆数目权重"
          v-model="editParam.num"
        ></el-input>
      </el-form-item>

      <el-form-item label="车速权重：">
        <el-input
          placeholder="请输入车速权重"
          v-model="editParam.speed"
        ></el-input>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="editLightParam()">修改</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { onMounted, reactive, ref } from "vue";
import { getAll, save, getById, update, del } from "@/api/lightParam";
import { useStore } from "vuex";
import { ElMessage } from "element-plus/lib/components";

let paramTable = ref([]);
const store = useStore();

// 查询参数
let searchForm = reactive({
  current: 1,
  total: 0,
  userid: store.state.user.userid,
});

const Table = (searchForm) => {
  getAll(searchForm).then((res) => {
    paramTable.value = res.data.records;
    searchForm.total = res.data.total;
  });
};

const query = () => {
  Table(searchForm);
};

// 添加
let addParam = ref({
  userid: store.state.user.userid,
});
let addDialogVisible = ref(false);
const openAdd = () => {
  addDialogVisible.value = true;
};

const add = () => {
  save(addParam.value).then((res) => {
    if (res.code == 200) {
      addDialogVisible.value = false;
      addParam.value = {
        userid: store.state.user.userid,
      };
      ElMessage.success("添加成功");
      query();
    }
  });
};

// 修改
let editParam = ref({});

let editDialogVisible = ref(false);

const openEdit = (id) => {
  editDialogVisible.value = true;
  getById(id).then((res) => {
    editParam.value = res.data;
  });
};

const editLightParam = () => {
  update(editParam.value).then((res) => {
    if (res.code == 200) {
      editDialogVisible.value = false;
      ElMessage.success("修改成功");
      editParam.value = {};
      query();
    }
  });
};

// 删除
const deleteParam = (id) => {
  del(id).then((res) => {
    if (res.code == 200) {
      ElMessage.success("删除成功");
      query();
    }
  });
};

onMounted(() => {
  Table(searchForm);
});
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