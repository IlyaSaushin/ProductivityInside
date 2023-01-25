package com.earl.productivityinside.data

import android.util.Log
import com.earl.productivityinside.data.mappers.LocationDataToDomainMapper
import com.earl.productivityinside.data.mappers.LocationInfoRemoteToDataMapper
import com.earl.productivityinside.data.models.LocationInfoData
import com.earl.productivityinside.data.retrofit.Service
import com.earl.productivityinside.domain.Repository
import com.earl.productivityinside.domain.models.LocationInfoDomain
import kotlinx.serialization.decodeFromString
import org.json.JSONObject
import javax.inject.Inject

class BaseRepository @Inject constructor(
    private val service: Service,
    private val jsonParseHelper: JsonParseHelper,
    private val locationInfoRemoteToDataMapper: LocationInfoRemoteToDataMapper<LocationInfoData>,
    private val locationDataToDomainMapper: LocationDataToDomainMapper<LocationInfoDomain>
) : Repository {

    override suspend fun getLocationByIp(ipAdr: String): LocationInfoDomain {
        try {
            val jsonString = service.getLocationByIp().string()
            val jsonObject = JSONObject(jsonString)
            val locationInfo = jsonParseHelper.parseJsonToLocationInfo(jsonString)
            Log.d("tag", "getLocationByIp: success ip -> $ipAdr")
            Log.d("tag", "getLocationByIp: ${JSONObject(jsonString)}")
            Log.d("tag", "getLocationByIp: ${jsonObject.getBoolean("success")}")
        } catch (e: Exception) {
            Log.d("tag", "getLocationByIp: fail ip -> $ipAdr")
            Log.d("tag", "getLocationByIp: $e")
        }
        return LocationInfoDomain.Base("", "", "", "")
    }
}