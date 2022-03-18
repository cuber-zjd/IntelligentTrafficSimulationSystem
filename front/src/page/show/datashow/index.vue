<template>
  <!-- 查询 -->
  <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-form-item label="道路名称">
      <el-input placeholder="道路名称" v-model="searchForm.name"></el-input>
    </el-form-item>
    <!-- <el-form-item label="Activity zone">
      <el-select placeholder="Activity zone">
        <el-option label="Zone one" value="shanghai"></el-option>
        <el-option label="Zone two" value="beijing"></el-option>
      </el-select>
    </el-form-item> -->
    <el-form-item>
      <el-button type="primary" @click="query">查找</el-button>
    </el-form-item>
  </el-form>

  <!-- 表格 -->
  <el-table :data="roadTable" stripe style="width: 100%">
    <el-table-column prop="id" label="编号" />
    <el-table-column prop="name" label="道路名称" />
    <el-table-column prop="type" label="道路类型" />
    <el-table-column prop="lng" label="纬度" />
    <el-table-column prop="lat" label="经度" />
    <el-table-column fixed="right" label="操作" align="center">
      <template v-slot="scope">
        <el-button type="primary" size="small" @click="openData(scope.row.id)">
          数据
        </el-button>
        <el-button type="success" size="small" @click="openEdit(scope.row.id)"
          >修改</el-button
        >

        <!-- 删除 -->
        <el-popconfirm
          title="Are you sure to delete this?"
          @confirm="del(scope.row.id)"
        >
          <template #reference>
            <el-button type="danger" size="small">删除</el-button>
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
        <el-button type="primary" @click="edit">修改</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 数据显示框 -->
  <el-dialog
    v-model="dataDialogVisible"
    title="Tips"
    width="50%"
    :before-close="handleClose"
  >
    <el-table :data="dataList" stripe style="width: 100%">
      <el-table-column prop="id" label="编号" />
      <el-table-column prop="starttime" label="开始时间" />
      <el-table-column prop="endtime" label="结束时间" />
      <el-table-column prop="avgspeed" label="平均速度" />
      <el-table-column prop="direction" label="车辆行进方向" />
    </el-table>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editDialogVisible = false">关闭</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
import { ElMessage } from "element-plus";
import { intersectionGet } from "@/utils/api/intersection";
import { intersectionDelete } from "@/utils/api/intersection";
import { intersectionPut } from "@/utils/api/intersection";
import { dataGet } from "@/utils/api/data";
export default {
  data() {
    return {
      searchForm: {
        name: "",
      },
      roadTable: [],
      editDialogVisible: false,
      dataDialogVisible: false,
      editForm: {},
      dataList: [],
    };
  },
  methods: {
    // 查询
    query() {
      intersectionGet("/getByName?name=" + this.searchForm.name).then((res) => {
        this.roadTable = res.data.data;
        console;
      });
    },

    // 查看对应道路的数据
    openData(id) {
      dataGet("/getByIntersectionId/" + id).then((res) => {
        this.dataList = res.data.data;
        this.dataDialogVisible = true;
        console.log(this.dataList);
      });
    },

    //修改
    edit() {
      intersectionPut(this.editForm, "").then((res) => {
        if (res.data.flag) {
          this.editDialogVisible = false;
          ElMessage.success("修改成功");
          this.init();
        } else {
          ElMessage.error("修改失败");
        }
      });
    },

    //打开修改弹出框
    openEdit(id) {
      console.log(id);
      intersectionGet("/" + id).then((res) => {
        this.editForm = res.data.data;
      });
      this.editDialogVisible = true;
    },

    //确认删除
    del(id) {
      intersectionDelete("/" + id).then((res) => {
        if (res.data.flag) {
          ElMessage.success("删除成功");
          this.init();
        } else {
          ElMessage.error("删除失败");
        }
      });
    },

    //查询所有数据，初始化页面
    init() {
      intersectionGet("/getByUser/" + this.$store.state.userId).then((res) => {
        this.roadTable = res.data.data;
      });
    },
  },
  mounted() {
    this.init();
  },
  computed: {
    name() {
      if (this.searchForm.name != "") {
        return "/" + this.searchForm.name;
      } else {
        return "";
      }
    },
  },
};
</script>