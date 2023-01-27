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
        object ServerTwoToken: EndPoints("9863d92c-9d58-11ed-b59d-0242ac130002-9863d9f4-9d58-11ed-b59d-0242ac130002")
        object ServerTwoParams: EndPoints(listOf("airTemperature", "pressure").joinToString(","))
        object ServerThreeToken: EndPoints("ecdb281a-7451-49ba-b415-c35b511ec6df")
    }
}