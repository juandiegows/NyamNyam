package com.example.appnyam.ui.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appnyam.Adapter.OrderAdapter
import com.example.appnyam.Adapter.OrderRecicleViewAdapter
import com.example.appnyam.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

    companion object {
        fun newInstance() = OrdersFragment()
    }

    private lateinit var viewModel: OrdersViewModel
    lateinit var binding : FragmentOrdersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(OrdersViewModel::class.java)
        // TODO: Use the ViewModel

        val  ordersViewModel=ViewModelProvider(this).get(OrdersViewModel::class.java)
        ordersViewModel.listOrder.observe(viewLifecycleOwner){
            var Adapter = OrderAdapter(this,it)
            val recicleViewAdapter = OrderRecicleViewAdapter(this, it)
            binding.listOrders.layoutManager = LinearLayoutManager(this.requireContext())
            binding.listOrders.adapter =  recicleViewAdapter
        }
        viewModel.getInfo()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}