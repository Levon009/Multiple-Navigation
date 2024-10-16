package com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpack_multiplenavigation.databinding.FragmentRecyclerBinding
import com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation.components.EmployeeRecyclerView
import com.example.jetpack_multiplenavigation.recyclerViewPayloads.presentation.model.Employee

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { EmployeeRecyclerView() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        binding.recyclerId.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerId.adapter = adapter

        val employee1 = Employee(1, "Kara", 21)
        val employee2 = Employee(2, "Levon", 35)
        val employee3 = Employee(3, "John", 14)

        adapter.setData(listOf(employee1, employee2, employee3))

        binding.addBtnId.setOnClickListener {
            val employee4 = Employee(4, "Armen", 77)
            adapter.setData(listOf(employee1, employee2, employee3, employee4))
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}