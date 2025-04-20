package com.example.management.adapter

import androidx.recyclerview.widget.DiffUtil
import java.util.ArrayList

class RecyclerDiffUtil(
    private val oldList: ArrayList<*>,
    private val newList: ArrayList<*>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() =newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList == newList
}