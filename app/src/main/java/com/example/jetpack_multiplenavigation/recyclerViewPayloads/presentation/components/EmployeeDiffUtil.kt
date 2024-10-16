package com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation.components

import androidx.recyclerview.widget.DiffUtil
import com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation.model.Employee

class EmployeeDiffUtil(
    private val oldList: List<Employee>,
    private val newList: List<Employee>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return when {
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                EmployeeChangePayloads.Name(newList[newItemPosition].name)
            }
            oldList[oldItemPosition].age != newList[newItemPosition].age -> {
                EmployeeChangePayloads.Age(newList[newItemPosition].age)
            }
            else -> super.getChangePayload(oldItemPosition, newItemPosition)
        }
    }
}