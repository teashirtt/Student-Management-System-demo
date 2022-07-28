<template>
  <div class="col-8 offset-2 tot">
    <table class="table caption-top table-hover">
      <caption class="text-center">
        <h1>学生管理系统</h1>
        <el-button type="primary" @click="getStudent" class="m-2">查询所有</el-button>
        <DialogPart @reacquire="getStudent" class="m-2" />
        <div class="search m-3">
          <el-input v-model="word" placeholder="Please input" class="w-25" />
          <el-button type="success" round @click="searchStudent">查询</el-button>
        </div>
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
        <StudentPart v-for="student in current_page" :key="student.id" :student="student" @reacquire="getStudent" />
      </tbody>
    </table>

    <el-button-group>
      <el-button type="primary" class="pagebutton" @click="lastpage">上一页</el-button>
      <el-button type="primary" class="pagebutton" @click="nextpage">下一页</el-button>
    </el-button-group>
  </div>
</template>

<script setup>
import axios from 'axios'
import StudentPart from '../components/StudentPart.vue'
import DialogPart from '../components/DialogPart.vue'
import { onMounted, ref, toRaw } from 'vue'
import { computed } from '@vue/reactivity';

let students = ref('[]')
let page = ref(1)
let word = ref('')

onMounted(() => getStudent())

const getStudent = () => {
  axios({
    method: "GET",
    url: "http://localhost:8060/students"
  }).then((resp) => {
    students.value = resp.data
  })
}

const searchStudent = () => {
  axios({
    method: "POST",
    url: "http://localhost:8060/search",
    data: {
      name: word.value
    }
  }).then((resp) => {
    students.value = resp.data
  })
}

let current_page = computed(() => {
  return students.value.slice(page.value * 5 - 5, page.value * 5)
})
let total_page = computed(() => {
  return Math.ceil(students.value.length / 5)
})
const lastpage = () => {
  if (page.value > 1) page.value -= 1
}
const nextpage = () => {
  if (page.value < total_page.value) page.value += 1
}

</script>

<style scoped>
.tot {
  margin-top: 8px;
}

.pagebutton {
  width: 120px;
  height: 40px;
}

.search {
  margin-top: 10px;
}

.search>button {
  margin-left: 5px;
}
</style>

