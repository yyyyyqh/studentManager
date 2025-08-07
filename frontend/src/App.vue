<template>
  <el-container class="app-container">
    <el-header class="app-header">
      <h1>学生管理系统</h1>
    </el-header>
    <el-main>
      <div class="actions">
        <el-button type="primary" @click="handleAdd">新增学生</el-button>
      </div>

      <el-table :data="studentList" style="width: 100%" v-loading="loading">
        <el-table-column prop="id" label="ID" width="100" />
        <el-table-column prop="name" label="姓名" width="150" />
        <el-table-column prop="gender" label="性别" width="100" />
        <el-table-column prop="age" label="年龄" width="100" />
        <el-table-column prop="department" label="院系" />
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row.id)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>

  <el-dialog v-model="dialogVisible" :title="formTitle" width="500px" @close="resetForm">
    <el-form :model="studentForm" label-width="80px">
      <el-form-item label="姓名">
        <el-input v-model="studentForm.name" placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="studentForm.gender" placeholder="请输入性别" />
      </el-form-item>
      <el-form-item label="年龄">
        <el-input v-model.number="studentForm.age" type="number" placeholder="请输入年龄" />
      </el-form-item>
      <el-form-item label="院系">
        <el-input v-model="studentForm.department" placeholder="请输入院系" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import studentApi from './api/student.js' // 导入我们的 API 服务
import { ElMessage, ElMessageBox } from 'element-plus'

// --- 响应式状态定义 ---
const studentList = ref([]) // 学生列表
const loading = ref(false) // 表格加载状态
const dialogVisible = ref(false) // 对话框可见性
const formTitle = ref('') // 对话框标题
const studentForm = ref({
  // 学生表单的响应式数据
  id: null,
  name: '',
  gender: '',
  age: null,
  department: '',
})

// --- 方法定义 ---

// 封装获取学生列表的逻辑
const fetchStudentList = async () => {
  loading.value = true
  try {
    const response = await studentApi.getStudents()
    studentList.value = response.data
  } catch (error) {
    ElMessage.error('获取学生列表失败！')
    console.error(error)
  } finally {
    loading.value = false
  }
}

// 在组件挂载到 DOM 后，立即获取一次学生列表
onMounted(() => {
  fetchStudentList()
})

// 重置表单
const resetForm = () => {
  studentForm.value = { id: null, name: '', gender: '', age: null, department: '' }
}

// 处理“新增”按钮点击事件
const handleAdd = () => {
  resetForm()
  formTitle.value = '新增学生'
  dialogVisible.value = true
}

// 处理“编辑”按钮点击事件
const handleEdit = (row) => {
  formTitle.value = '编辑学生'
  studentForm.value = { ...row } // 将行数据复制到表单中
  dialogVisible.value = true
}

// 处理“删除”按钮点击事件
const handleDelete = (id) => {
  ElMessageBox.confirm('确定要删除该学生吗？此操作不可撤销。', '警告', {
    confirmButtonText: '确定删除',
    cancelButtonText: '取消',
    type: 'warning',
  })
    .then(async () => {
      // 用户点击了“确定删除”
      await studentApi.deleteStudent(id)
      ElMessage.success('删除成功！')
      fetchStudentList() // 重新加载列表以反映删除
    })
    .catch(() => {
      // 用户点击了“取消”或关闭了对话框
      ElMessage.info('已取消删除')
    })
}

// 处理对话框中的“确定”按钮点击事件
const handleConfirm = async () => {
  try {
    // 根据表单中是否有 id 来判断是新增还是更新
    if (studentForm.value.id) {
      await studentApi.updateStudent(studentForm.value.id, studentForm.value)
      ElMessage.success('学生信息更新成功！')
    } else {
      await studentApi.createStudent(studentForm.value)
      ElMessage.success('新学生添加成功！')
    }
    dialogVisible.value = false // 关闭对话框
    fetchStudentList() // 重新加载列表
  } catch (error) {
    ElMessage.error('操作失败！')
    console.error(error)
  }
}
</script>

<style>
/* 添加一些简单的样式让页面更好看 */
.app-container {
  max-width: 1000px;
  margin: 20px auto;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}
.app-header {
  text-align: center;
  border-bottom: 1px solid #ebeef5;
  padding-bottom: 10px;
}
.actions {
  margin-bottom: 20px;
  text-align: left;
}
</style>
