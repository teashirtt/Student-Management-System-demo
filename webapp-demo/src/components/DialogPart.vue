<template>
    <el-button type="primary" @click="dialogVisible = true">添加学生</el-button>
    <el-dialog v-model="dialogVisible" title="Tips" width="30%" :before-close="handleClose">
        <div class="error" v-show="err">请重新输入学生信息</div>
        <div>学号<input v-model="newstudent.number" /></div>
        <div>姓名<input v-model="newstudent.name" /></div>
        <div>年龄<input v-model="newstudent.age" /></div>
        <div>语文<input v-model="newstudent.chinese" /></div>
        <div>数学<input v-model="newstudent.math" /></div>
        <div>英语<input v-model="newstudent.english" /></div>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="dialogVisible = false">Cancel</el-button>
                <el-button type="primary" @click="dialogVisible = false; addStudent()">Confirm</el-button>
            </span>
        </template>
    </el-dialog>
</template>

<script setup>
import axios from 'axios';
import { defineEmits, ref } from 'vue'
import { ElMessageBox } from 'element-plus'

const dialogVisible = ref(false)
const err = ref(false)

const emit = defineEmits(['reacquire'])

let newstudent = {
    number: '',
    name: '',
    age: '',
    chinese: '',
    math: '',
    english: ''
}
const handleClose = () => {
    ElMessageBox.confirm('Are you sure to close this dialog?')
        // .then(() => {
        //     dialogVisible.value = false
        // })
}

const check = () => {
    if (newstudent.number === '' || newstudent.name === '' || newstudent.age === '') return false;
    if (newstudent.chinese === '' || newstudent.math === '' || newstudent.english === '') return false;
    if (newstudent.chinese < 0 || newstudent.chinese > 100) return false;
    if (newstudent.math < 0 || newstudent.math > 100) return false;
    if (newstudent.english < 0 || newstudent.english > 100) return false;
    return true;
}

const addStudent = () => {
    if (check()) {
        axios({
            method: "POST",
            url: "http://localhost:8060/add",
            data: newstudent
        }).then(() => emit('reacquire'))
    } else {
        dialogVisible.value = true
        err.value = true;
    }
}
</script>

<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}

.error {
    color: red;
}
</style>
