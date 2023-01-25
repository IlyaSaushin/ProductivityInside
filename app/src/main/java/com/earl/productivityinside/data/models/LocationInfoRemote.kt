package com.earl.productivityinside.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LocationInfoRemote(
    val continent: String,
    val country: String,
    val region: String,
    val city: String
)

//
//{
//    "ip": "8.8.4.4",
//    "success": true,
//    "type": "IPv4",
//    "continent": "North America",
//    "continent_code": "NA",
//    "country": "United States",
//    "country_code": "US",
//    "region": "California",
//    "region_code": "CA",
//    "city": "Mountain View",
//    "latitude": 37.3860517,
//    "longitude": -122.0838511,
//    "is_eu": false,
//    "postal": "94039",
//    "calling_code": "1",
//    "capital": "Washington D.C.",
//    "borders": "CA,MX",
//    flag {
//    "img": "https://cdn.ipwhois.io/flags/us.svg",
//    "emoji": "🇺🇸",
//    "emoji_unicode": "U+1F1FA U+1F1F8"
//},
//    connection {
//    "asn": 15169,
//    "org": "Google LLC",
//    "isp": "Google LLC",
//    "domain": "google.com"
//},
//    timezone {
//    "id": "America/Los_Angeles",
//    "abbr": "PDT",
//    "is_dst": true,
//    "offset": -25200,
//    "utc": "-07:00",
//    "current_time": "2022-04-22T14:31:48-07:00"
//},
//    currency {
//    "name": "US Dollar",
//    "code": "USD",
//    "symbol": "$",
//    "plural": "US dollars",
//    "exchange_rate": 1
//},
//    security {
//    "anonymous": false,
//    "proxy": false,
//    "vpn": false,
//    "tor": false,
//    "hosting": false
//}
//}
