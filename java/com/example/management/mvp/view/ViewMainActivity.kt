package com.example.management.mvp.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.management.adapter.RecyclerTaskAdapter
import com.example.management.databinding.ActivityMainBinding
import com.example.management.databinding.CustomDialogBinding
import com.example.management.db.model.TaskEntity
import com.example.management.mvp.ext.OnBindData
import java.util.ArrayList


class ViewMainActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

    private lateinit var adapter: RecyclerTaskAdapter


    fun showDialog(onBindData: OnBindData){

        binding.fab.setOnClickListener {

            val view = CustomDialogBinding.inflate(LayoutInflater.from(context))

            val dialog = Dialog(context)
            dialog.setContentView(view.root)
            dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()


            view.btnCancel.setOnClickListener { dialog.dismiss() }

            view.btnSave.setOnClickListener {

                val text = view.edtTask.text.toString()

                if (text.isNotEmpty()){
                    onBindData.saveData(TaskEntity(title = text, state = false))
                    Toast.makeText(context, "وظیفه ایجاد شد", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }else
                    Toast.makeText(context, "لطفا وظیفه را وارد کنید", Toast.LENGTH_SHORT).show()

            }

        }

    }

    fun initRecycler(tasks:ArrayList<TaskEntity>,onBindData: OnBindData){

        adapter = RecyclerTaskAdapter(tasks,onBindData)
        binding.recycler.layoutManager =
            LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        binding.recycler.adapter = adapter

    }


    fun setData(onBindData: OnBindData){

        onBindData.requestData(false)

        binding.rdbTrue.setOnClickListener {
            onBindData.requestData(true)
        }
        binding.rdbFalse.setOnClickListener {
            onBindData.requestData(false)
        }

    }

    fun showTask(tasks: List<TaskEntity>){

        val data = arrayListOf<TaskEntity>()

        tasks.forEach { data.add(it) }

        adapter.dataUpdate(data)

    }



}

