package com.earl.productivityinside.data

import com.earl.productivityinside.data.models.LocationInfoRemote
import org.json.JSONObject
import javax.inject.Inject

interface JsonParseHelper {

    fun parseJsonToLocationInfo(json: String) : LocationInfoRemote?

    class Base @Inject constructor() : JsonParseHelper {

        override fun parseJsonToLocationInfo(json: String) : LocationInfoRemote? {
            val jsonObject = JSONObject(json)
            return if (jsonObject.getBoolean(IS_SUCCESS)) {
                LocationInfoRemote(
                    jsonObject.getString(CONTINENT),
                    jsonObject.getString(COUNTRY),
                    jsonObject.getString(REGION),
                    jsonObject.getString(CITY),
                )
            } else null
        }
    }

    companion object {
        private const val IS_SUCCESS = "success"
        private const val CONTINENT = "continent"
        private const val COUNTRY = "country"
        private const val REGION = "region"
        private const val CITY = "city"
    }
}