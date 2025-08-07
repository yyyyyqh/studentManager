import axios from 'axios'

// 创建一个 axios 实例，可以进行统一的配置
const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api', // 后端 API 的基础路径
  timeout: 5000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json',
  },
})

// 导出一个对象，包含所有与学生相关的 API 请求方法
export default {
  getStudents() {
    return apiClient.get('/students')
  },
  getStudentById(id) {
    return apiClient.get(`/students/${id}`)
  },
  createStudent(studentData) {
    return apiClient.post('/students', studentData)
  },
  updateStudent(id, studentData) {
    return apiClient.put(`/students/${id}`, studentData)
  },
  deleteStudent(id) {
    return apiClient.delete(`/students/${id}`)
  },
}
