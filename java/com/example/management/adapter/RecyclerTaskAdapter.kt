package com.example.management.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.management.databinding.RecyclerItemBinding
import com.example.management.db.model.TaskEntity
import com.example.management.mvp.ext.OnBindData
import java.util.ArrayList

class RecyclerTaskAdapter(private val tasks : ArrayList<TaskEntity>,
    private val onBindData: OnBindData):
    RecyclerView.Adapter<RecyclerTaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(
        private val binding: RecyclerItemBinding
    ): ViewHolder(binding.root){

        fun setData(data : TaskEntity){
            binding.txtTitle.text = data.title
            binding.checkBox.isChecked = data.state
            binding.checkBox.setOnClickListener {
                if (binding.checkBox.isChecked)
                    onBindData.editData(TaskEntity(data.id,data.title,true))
                else
                    onBindData.editData(TaskEntity(data.id,data.title,false))
            }
            binding.imgDelete.setOnClickListener {

                onBindData.deleteData(data)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),
            parent ,
            false))


    override fun getItemCount() = tasks.size


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.setData(tasks[position])
    }

    fun dataUpdate(newList: ArrayList<TaskEntity>){
        val diffCallback = RecyclerDiffUtil(tasks , newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        tasks.clear()

        tasks.addAll(newList)
        diffResult.dispatchUpdatesTo(this)


    }

}