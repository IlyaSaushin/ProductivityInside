package com.earl.productivityinside.data.retrofit

interface NetworkService {

    companion object {
        const val SERVER_ONE = "https://api.openweathermap.org/data/2.5/weather"
        const val SERVER_TWO = "https://api.stormglass.io/v2/weather/point"
        const val SERVER_THREE = "https://api.weather.yandex.ru/v2/forecast/"
    }

    sealed class EndPoints(val url: String) {
        object ServerOneToken: EndPoints("fd8a9b5c5f69bcfc4b2d0c8057ab48ef")
        object ServerOneMetrics: EndPoints("metric")
    }
}

//interface WebSocketService {
//
//    companion object {
//        // default
////        const val BASE_URL = "ws://10.0.2.2:8080/"
//        // genymotion
//        const val BASE_URL = "ws://10.0.3.2:8080/"
//    }
//
//    sealed class Endpointss(val url: String) {
//        object Rooms: Endpointss("$BASE_URL/socketRooms")
//        object Chat: Endpoints("$BASE_URL/chat")
//        object Messaging: Endpoints("$BASE_URL/messaging")
//        object GroupMessaging: Endpoints("$BASE_URL/group")
//        object Searching: Endpoints("$BASE_URL/searching")
//    }
//}