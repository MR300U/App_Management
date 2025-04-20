package com.example.management.mvp.ext

import com.example.management.db.model.TaskEntity

interface OnBindData {

    fun saveData(taskEntity: TaskEntity){}

    fun editData(taskEntity: TaskEntity){}

    fun deleteData(taskEntity: TaskEntity){}


    fun getData(taskEntity: List<TaskEntity>){}

    fun requestData(state : Boolean){}

}