<template>
  <div class="col-8 offset-2">
    <table class="table caption-top table-hover">
      <caption class="text-center">
        <h1>学生管理系统</h1>
        <el-button type="primary" @click="getStudent">查询所有</el-button>
        <DialogPart @reacquire="getStudent" />
      </caption>
      <thead>
        <tr>
          <th scope="col">学号</th>
          <th scope="col">姓名</th>
          <th scope="col">年龄</th>
          <th scope="col">语文</th>
          <th scope="col">数学</th>
          <th scope="col">英语</th>
          <th scope="col">操作</th>
        </tr>
      </thead>
      <tbody>
        <StudentPart v-for="student in students" :key="student.id" :student="student" @reacquire="getStudent" />
      </tbody>
    </table>
  </div>
</template>

<script setup>
import axios from 'axios'
import StudentPart from '../components/StudentPart.vue'
import DialogPart from '../components/DialogPart.vue'
import { onMounted, ref } from 'vue'

let students = ref('')

onMounted(() => getStudent())

const getStudent = () => {
  axios({
    method: "GET",
    url: "http://localhost:8060/students"
  }).then((resp) => {
    students.value = resp.data;
  })
}
</script>

