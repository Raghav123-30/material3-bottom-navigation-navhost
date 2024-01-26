package com.example.myapplication.models

interface Route  {
    val route:String
}

object Home : Route{
    override val route = "home"
}

object NewBooking:Route {
    override val route = "new-booking"
}

object  Notifications:Route {
    override val route: String = "notifications"
}