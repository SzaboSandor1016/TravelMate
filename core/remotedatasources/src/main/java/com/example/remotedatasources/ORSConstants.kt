package com.example.remotedatasources

import com.example.core.remotedatasources.BuildConfig

class ORSConstants {

    companion object {
        val API_KEY: String = BuildConfig.OPEN_ROUTE_SERVICE_KEY.toString()
        const val API_URL: String = "https://api.openrouteservice.org/"
    }
}