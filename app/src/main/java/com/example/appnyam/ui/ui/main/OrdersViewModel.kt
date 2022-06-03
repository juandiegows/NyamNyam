package com.example.appnyam.ui.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnyam.Model.Order
import com.example.appnyam.Model.OrderDish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class OrdersViewModel : ViewModel() {

    private val _listOrders = MutableLiveData<List<Order>>().apply {
        value = listOf<Order>()
    }
    val listOrder: LiveData<List<Order>> = _listOrders


    fun getInfo() {
        try {
            Log.e("getInfo: ", "Se inicio")
            CoroutineScope(Dispatchers.IO).launch {
                val client = URL("http://10.0.2.2:9797/api/Orders")
                    .openConnection() as HttpURLConnection
                try {
                    var i = ""
                    val data = client.inputStream.bufferedReader().use {
                        i = it.readText()
                    }
                    Log.e("getInfo: ", i.toString())
                    val array = JSONArray(i)
                    var listaOrders = ArrayList<Order>()
                    for (i in 0 until array.length()) {
                        val temp = array.getJSONObject(i)
                        var order = Order().apply {
                            Id = temp.getLong("Id")
                            CreatedDT = temp.getString("CreatedDT")
                            ClientId = temp.getInt("ClientId")
                            AppointedDT = temp.getString("AppointedDT")
                            AppointedAddress = temp.getString("AppointedAddress")
                            NameClient = temp.getString("NameClient")
                            var listDish = ArrayList<OrderDish>()
                            for (a in 0 until temp.getJSONArray("Dishes").length()) {
                                val temp2 = temp.getJSONArray("Dishes").getJSONObject(a)
                                var OrderDishO = OrderDish().apply {
                                    OrderId = temp2.getLong("OrderId")
                                    DishId = temp2.getLong("DishId")
                                    ServingsNumber = temp2.getInt("ServingsNumber")
                                    StartCookingDT = temp2.getString("StartCookingDT")
                                    EndCookingDT = temp2.getString("EndCookingDT")
                                    NameDish = temp2.getString("NameDish")
                                    Price = temp2.getDouble("Price")
                                }
                                listDish.add(OrderDishO)
                            }
                            Dishes = listDish
                        }
                        listaOrders.add(order)
                    }
                    _listOrders.postValue(listaOrders)
                } catch (e: Exception) {
                    client.disconnect()
                    Log.e("getInfo: ", "error :( ${e.message}")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}