<template>
  <!-- 查询 -->
  <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-form-item label="道路名称">
      <el-input placeholder="输入道路名称" v-model="searchForm.name"></el-input>
    </el-form-item>
    <el-form-item>
      <el-select
        v-model="searchForm.roadTypes"
        multiple
        collapse-tags
        placeholder="选择道路类型"
        style="width: 240px"
      >
        <el-option
          v-for="item in types"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="query">查找</el-button>
    </el-form-item>
  </el-form>

  <!-- 功能按钮 -->
  <el-row>
    <el-button type="primary" plain alin @click="addRoad">
      <el-icon><plus /></el-icon>
      <span>添加</span>
    </el-button>
  </el-row>

  <!-- 表格 -->
  <el-table :data="roadTable" stripe style="width: 100%">
    <el-table-column prop="id" label="序号" />
    <el-table-column prop="name" label="道路名称" />
    <el-table-column prop="type" label="道路类型" />
    <el-table-column prop="lng" label="纬度" />
    <el-table-column prop="lat" label="经度" />
    <el-table-column fixed="right" label="操作" align="center" width="300">
      <template v-slot="scope">
        <el-button type="primary" size="small" @click="openData(scope.row.id)">
          <el-icon><document-checked /></el-icon>
          <span>数据</span>
        </el-button>
        <el-button type="success" size="small" @click="openEdit(scope.row.id)">
          <el-icon><edit /></el-icon>
          <span>修改</span></el-button
        >

        <!-- 删除 -->
        <el-popconfirm title="是否删除此道路？" @confirm="del(scope.row.id)">
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

  <!-- 修改框 -->
  <el-dialog
    v-model="editDialogVisible"
    title="Tips"
    width="30%"
    :before-close="handleClose"
    style="align-items: center"
  >
    <el-form ref="formRef" :model="editForm" label-width="120px">
      <el-form-item label="道路名称">
        <el-input v-model="editForm.name"></el-input>
      </el-form-item>
      <el-form-item label="道路类型">
        <el-input v-model="editForm.type"></el-input>
      </el-form-item>
      <el-form-item label="道路纬度">
        <el-input v-model="editForm.lng"></el-input>
      </el-form-item>
      <el-form-item label="道路经度">
        <el-input v-model="editForm.lat"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="editRoad">修改</el-button>
      </span>
    </template>
  </el-dialog>

  <el-pagination
    background
    layout="prev, pager, next"
    :total="total"
    :page-size="5"
    v-model:current-page="current"
  />
</template>
<script setup>
import { onMounted, ref, watch } from "vue-demi";
import { getPageList, getById, updateById, deleteById } from "@/api/road";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";

const store = useStore();
const router = useRouter();

// 分页查询
const types = [
  {
    value: "十字路口",
    label: "十字路口",
  },
  {
    value: "三岔路口(北向)",
    label: "三岔路口(北向)",
  },
  {
    value: "三岔路口(南向)",
    label: "三岔路口(南向)",
  },
  {
    value: "三岔路口(西向)",
    label: "三岔路口(西向)",
  },
  {
    value: "三岔路口(东向)",
    label: "三岔路口(东向)",
  },
  {
    value: "直行路口(南北)",
    label: "直行路口(南北)",
  },
  {
    value: "直行路口(东西)",
    label: "直行路口(东西)",
  },
];

let searchForm = ref({
  name: "",
  roadTypes: [],
});
let roadTable = ref([]);
const current = ref(1);
const total = ref(0);

const Table = (currentPage, searchForm) => {
  getPageList(store.state.user.userid, currentPage, searchForm).then((res) => {
    roadTable.value = res.data.records;
    total.value = res.data.total;
  });
};
watch(current, (newVal) => {
  Table(newVal, searchForm.value);
});
const query = () => {
  Table(current.value, searchForm.value);
  console.log(searchForm.value);
};

//修改
let editDialogVisible = ref(false);
let editForm = ref({});

const openEdit = (id) => {
  getById(id).then((res) => {
    editForm.value = res.data;
  });
  editDialogVisible.value = true;
  console.log(id);
};

const editRoad = () => {
  updateById(editForm.value).then(() => {
    editDialogVisible.value = false;
    ElMessage({
      message: "修改成功",
      type: "success",
    });
    Table(current.value, searchForm.value);
  });
};

//删除

const del = (id) => {
  deleteById(id).then(() => {
    Table(1, searchForm.value);
    ElMessage({
      message: "删除成功",
      type: "success",
    });
  });
};

//跳转到添加页面
const addRoad = () => {
  router.push("/addRoad");
};

//实例构造完成后
onMounted(() => {
  Table(current.value, searchForm.value);
});
</script>
