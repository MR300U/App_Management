package com.example.management.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.management.db.DBHandler
import com.example.management.db.model.TaskEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Insert
    fun insertTask(vararg task : TaskEntity)

    @Update
    fun updateTask(vararg task : TaskEntity) : Int

    @Delete
    fun deleteTask(vararg task : TaskEntity) : Int

    @Query("DELETE FROM ${DBHandler.TASK_TABLE}")
    fun deleteAllTask()

    @get:Query("SELECT * FROM ${DBHandler.TASK_TABLE}")
    val getTask : Flow<List<TaskEntity>>

    @Query("SELECT * FROM ${DBHandler.TASK_TABLE} WHERE state = :type")
    fun getTasksByColumn(type: Boolean): Flow<List<TaskEntity>>

}