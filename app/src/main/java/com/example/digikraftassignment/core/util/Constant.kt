package com.example.digikraftassignment.core.util

object Constant {
    const val BASE_URL = "https://www.poznan.pl/mim/plan/"
}

object ApiResponseCodes {
    const val UNAUTHORIZED = 401
    const val SERVER_ERROR = 500
}

object Msg {
    const val ERROR_COMMON = "Oops, something went wrong. Let\'s try it again."
    const val INTERNET_ISSUE = "Sorry, Seems your internet connection is not available. Check & try again."
}

object RequestCode {
    const val LOCATION_REQUEST_CODE = 199
}

object IntentParcelable {
    const val BIKE_STATION_DETAILS = "bike_station_details"
    const val LATITUDE = "latitude"
    const val LONGITUDE = "longitude"
}