package com.example.management.mvp.model

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.management.db.DBHandler
import com.example.management.db.model.TaskEntity
import com.example.management.mvp.ext.OnBindData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModelMainActivity(private val activity: AppCompatActivity) {

    private val db = DBHandler.getDatabase(activity)

    fun setData(taskEntity: TaskEntity){

        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                db.taskDao().insertTask(taskEntity)
            }
        }

    }


    fun editData(taskEntity: TaskEntity){

        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                db.taskDao().updateTask(taskEntity)
            }
        }

    }

    fun deleteData(taskEntity: TaskEntity){

        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                db.taskDao().deleteTask(taskEntity)
            }
        }

    }

    fun getData(state: Boolean , onBindData: OnBindData){

        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val task = db.taskDao().getTasksByColumn(state)
                withContext(Dispatchers.Main){
                    task.collect{
                        onBindData.getData(it)
                    }
                }
            }
        }

    }


    fun getTasks(state:Boolean , onBindData: OnBindData){

        activity.lifecycleScope.launch {
            withContext(Dispatchers.IO){
                val tasks = db.taskDao().getTasksByColumn(state)
                withContext(Dispatchers.Main){
                    tasks.collect{
                        onBindData.getData(it)
                    }
                }
            }
        }

    }

    fun closeDatabase(){
        db.close()
    }

}