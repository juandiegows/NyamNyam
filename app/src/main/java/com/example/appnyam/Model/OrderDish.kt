package com.example.appnyam.Model

import android.text.format.DateUtils
import android.widget.DatePicker
import androidx.annotation.Nullable
import java.text.DateFormat
import java.util.*

class OrderDish {
    var  OrderId:Long  = 0
    var  DishId:Long  = 0
    @Nullable
    var ServingsNumber  = 0
    @Nullable
    var StartCookingDT= ""
    @Nullable
    var  EndCookingDT  = ""
    var NameDish  = ""
    var Price = 0.0
}