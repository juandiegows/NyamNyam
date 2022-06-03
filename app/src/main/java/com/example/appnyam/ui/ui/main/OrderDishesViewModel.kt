package com.example.appnyam.ui.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appnyam.Model.OrderDish

class OrderDishesViewModel : ViewModel() {
    private val _listOrderDish = MutableLiveData<List<OrderDish>>().apply {
        value = listOf<OrderDish>()
    }
    var listOrderDish: LiveData<List<OrderDish>> = _listOrderDish
}