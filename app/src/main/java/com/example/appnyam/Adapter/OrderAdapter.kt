package com.example.appnyam.Adapter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appnyam.Model.Order
import com.example.appnyam.R
import com.example.appnyam.databinding.ItemOrderBinding
import com.example.appnyam.singleton
import java.text.SimpleDateFormat
import java.util.*

class OrderAdapter(var context: Fragment, var orders: List<Order>) : BaseAdapter() {


    override fun getCount(): Int {
        return orders.count()
    }

    override fun getItem(position: Int): Any {
        return orders.get(position)
    }

    override fun getItemId(position: Int): Long {
        return orders.get(position).Id as Long
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = LayoutInflater.from(context.requireContext()).inflate(R.layout.item_order, parent, false);
        var binding: ItemOrderBinding = ItemOrderBinding.bind(view)
        var orders = getItem(position) as Order
        var total = 0.0
        binding.txtCustomer.text = orders.NameClient
        binding.txtDishes.text = ""
        var pendiente = true
        var progreso = false
        singleton.OrderDish = orders.Dishes
        for (item in orders.Dishes) {
            binding.txtDishes.text = binding.txtDishes.text.toString() + " , " + item.NameDish
            total += item.Price
            if(item.StartCookingDT != "null"){
                pendiente = false
            }
            if(item.EndCookingDT == "null"){
                progreso = true
            }
        }
        if(pendiente){
            binding.btnStatus.setBackgroundColor(Color.RED)
            binding.root.setOnClickListener{
                context.findNavController().navigate(R.id.action_ordersFragment_to_ingredientsFragment)
            }
        }else if(progreso) {
            binding.btnStatus.setBackgroundColor(Color.YELLOW)
            binding.root.setOnClickListener{
                context.findNavController().navigate(R.id.action_ordersFragment_to_orderDishesFragment
                )
            }
        }else {
            binding.btnStatus.setBackgroundColor(Color.GREEN)
            binding.root.setOnClickListener{
                context.findNavController().navigate(R.id.action_ordersFragment_to_orderDishesFragment)
            }
        }
        binding.txtDishes.text =  binding.txtDishes.text.substring(3, binding.txtDishes.text.length)
        val date: String = orders.CreatedDT.split('T')[0]
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val formatter2 = SimpleDateFormat("dd MMMM", Locale.US)
        val dateDate: Date = formatter.parse(date)
        binding.txtDate.text =formatter2.format(dateDate)
        binding.txtValue.text = "Total cost : ${total}$"

        binding.btnStatus.setOnClickListener {
            Toast.makeText(context.requireContext(),"Clic in status",Toast.LENGTH_SHORT).show()
        }
        return view
    }
}

