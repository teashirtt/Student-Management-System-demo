<template>
    <tr>
        <td v-show="isedit">{{ props.student.number }}</td>
        <td v-show="isedit">{{ props.student.name }}</td>
        <td v-show="isedit">{{ props.student.age }}</td>
        <td v-show="isedit">{{ props.student.chinese }}</td>
        <td v-show="isedit">{{ props.student.math }}</td>
        <td v-show="isedit">{{ props.student.english }}</td>
        <td v-show="isedit">
            <el-button type="primary" @click="modifyStudent">修改</el-button>
            <el-button type="danger" @click="deleteStudent">删除</el-button>
        </td>
        <td v-show="!isedit"><input type="text" v-model="stu.number" /></td>
        <td v-show="!isedit"><input type="text" v-model="stu.name" /></td>
        <td v-show="!isedit"><input type="text" v-model="stu.age" /></td>
        <td v-show="!isedit"><input type="text" v-model="stu.chinese" /></td>
        <td v-show="!isedit"><input type="text" v-model="stu.math" /></td>
        <td v-show="!isedit"><input type="text" v-model="stu.english" /></td>
        <td v-show="!isedit">
            <el-button type="primary" @click="saveStudent">保存</el-button>
            <el-button type="danger" @click="deleteStudent">删除</el-button>
        </td>
    </tr>
</template>

<script setup>
import axios from 'axios';
import { defineProps, ref, toRaw, defineEmits } from 'vue';

const props = defineProps({
    student: {
        type: Object,
        required: true
    }
})
const emit = defineEmits(['reacquire'])

const stu = ref(props.student)

let isedit = ref(true)
const modifyStudent = () => {
    isedit.value = !isedit.value
}

const saveStudent = () => {
    axios({
        method: "POST",
        url: "http://localhost:8060/update",
        data: toRaw(stu).value
    }).then(() => emit('reacquire'))
    isedit.value = !isedit.value
}

const deleteStudent = () => {
    axios({
        method: "POST",
        url: "http://localhost:8060/delete",
        data: toRaw(stu).value
    }).then(() => emit('reacquire'))
}
</script> 

<style scoped>
</style>
