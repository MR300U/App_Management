package com.example.management.mvp.presenter

import com.example.management.db.model.TaskEntity
import com.example.management.mvp.ext.BaseLifeCycle
import com.example.management.mvp.ext.OnBindData
import com.example.management.mvp.model.ModelMainActivity
import com.example.management.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view : ViewMainActivity ,
    private val model : ModelMainActivity
) : BaseLifeCycle {

    override fun onCreate() {

        setNewTask()

        setDataInitRecycler()

        dataHandel()

    }

    private fun setNewTask(){

        view.showDialog(
            object : OnBindData{
                override fun saveData(taskEntity: TaskEntity) {
                    model.setData(taskEntity)
                }
            }
        )

    }



    private fun setDataInitRecycler(){
        view.initRecycler(arrayListOf(),
            object : OnBindData {
                override fun editData(taskEntity: TaskEntity) {
                    model.editData(taskEntity)
                }

                override fun deleteData(taskEntity: TaskEntity) {
                    model.deleteData(taskEntity)
                }

            })
    }

    private fun dataHandel(){

        view.setData(
            object :OnBindData{

                override fun requestData(state: Boolean) {
                    model.getData(state ,
                        object : OnBindData {
                            override fun getData(taskEntity: List<TaskEntity>) {
                                view.showTask(taskEntity)
                            }
                        })
                }

            }
        )

    }


    override fun onDestroy() {
        model.closeDatabase()
    }

}

