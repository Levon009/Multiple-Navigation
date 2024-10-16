package com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack_multiplenavigation.databinding.ListItemBinding
import com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation.model.Employee

class EmployeeRecyclerView : RecyclerView.Adapter<EmployeeRecyclerView.ViewHolder>() {

    private var oldList = emptyList<Employee>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nameTextView.text = oldList[position].name
        holder.binding.ageTextView.text = oldList[position].age.toString()
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    fun setData(newList: List<Employee>) {
        val diffUtil = EmployeeDiffUtil(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}