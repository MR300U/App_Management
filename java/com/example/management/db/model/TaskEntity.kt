package com.example.management.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.management.db.DBHandler

@Entity(tableName = DBHandler.TASK_TABLE)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0 ,
    @ColumnInfo val title : String ,
    @ColumnInfo val state : Boolean

)
