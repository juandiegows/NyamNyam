package com.example.appnyam.Model

class Order {
    var Id:Long  = 0
    var CreatedDT = ""
    var ClientId = 0
    var AppointedDT = ""
    var AppointedAddress = ""

    var  NameClient = ""

    var Dishes = listOf<OrderDish>()
}