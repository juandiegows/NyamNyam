package com.example.appnyam.ui.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appnyam.Adapter.OrderDishAdapter
import com.example.appnyam.Adapter.OrderRecicleViewAdapter
import com.example.appnyam.databinding.FragmentOrderDishesBinding
import com.example.appnyam.singleton

class OrderDishesFragment : Fragment() {

    companion object {
        fun newInstance() = OrderDishesFragment()
    }

    private lateinit var viewModel: OrderDishesViewModel
    lateinit var binding: FragmentOrderDishesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var viewModel = ViewModelProvider(this).get(OrderDishesViewModel::class.java)
        binding = FragmentOrderDishesBinding.inflate(layoutInflater)
        viewModel.listOrderDish.observe(viewLifecycleOwner) {

        }
        var AdapterOrders = OrderDishAdapter(this, singleton.OrderDish)


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderDishesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}