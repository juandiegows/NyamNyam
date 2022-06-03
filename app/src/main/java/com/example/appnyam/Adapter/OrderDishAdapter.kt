package com.example.appnyam.Adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import com.example.appnyam.Model.OrderDish
import com.example.appnyam.R
import com.example.appnyam.databinding.ItemOrderBinding
import com.example.appnyam.databinding.ItemOrderDishesBinding

class OrderDishAdapter(var context: Fragment, var list: List<OrderDish>) : BaseAdapter() {

    override fun getCount(): Int {
        return list.count()
    }

    override fun getItem(position: Int): OrderDish {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return list.get(position).OrderId
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var binding: ItemOrderDishesBinding = ItemOrderDishesBinding.bind(
            LayoutInflater.from(context.requireContext()).inflate(
                R.layout.item_order_dishes, parent, false
            )
        )

        var orderDish = list.get(position)

        binding.txtName.text = orderDish.NameDish
        binding.txtServings.text = orderDish.ServingsNumber.toString()
        if (orderDish.StartCookingDT == "null") {
            binding.txtStatus.text = "Waiting"
            binding.button.visibility = View.VISIBLE
            binding.txtMessage.visibility = View.INVISIBLE
        } else {
            binding.button.visibility = View.INVISIBLE
            if (orderDish.EndCookingDT == "null") {
                binding.txtStatus.text = "Process"
                binding.txtMessage.visibility = View.VISIBLE
                binding.txtMessage.text = "Started at ${orderDish.StartCookingDT.split('T')[1]}"
            } else {
                binding.txtStatus.text = "Finished"
                binding.txtMessage.visibility = View.VISIBLE
                binding.txtMessage.text = "Finished at ${orderDish.StartCookingDT.split('T')[1]}"
            }
        }

        return binding.root
    }
}