package com.example.appnyam.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appnyam.Model.Order
import com.example.appnyam.R
import com.example.appnyam.databinding.ItemOrderBinding
import com.example.appnyam.singleton
import java.text.SimpleDateFormat
import java.util.*

class OrderRecicleViewAdapter (var context: Fragment, var orders: List<Order>)  :
    RecyclerView.Adapter<OrderRecicleViewAdapter.OrderRecicleViewHolder>() {

    inner  class OrderRecicleViewHolder(val binding: ItemOrderBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderRecicleViewHolder {
       val binding = ItemOrderBinding.bind(LayoutInflater.from(context.requireContext()).inflate(R.layout.item_order,parent,false))

        return OrderRecicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderRecicleViewHolder, position: Int) {
       with(holder){
           with(orders.get(position)){
               var total = 0.0
               binding.txtCustomer.text = NameClient
               binding.txtDishes.text = ""
               var pendiente = true
               var progreso = false
               singleton.OrderDish = Dishes
               for (item in Dishes) {
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
               val date: String = CreatedDT.split('T')[0]
               val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
               val formatter2 = SimpleDateFormat("dd MMMM", Locale.US)
               val dateDate: Date = formatter.parse(date)
               binding.txtDate.text =formatter2.format(dateDate)
               binding.txtValue.text = "Total cost : ${total}$"

               binding.btnStatus.setOnClickListener {
                   Toast.makeText(context.requireContext(),"Clic in status", Toast.LENGTH_SHORT).show()
               }
           }
       }
    }

    override fun getItemCount(): Int {
        return  orders.count()
    }
}