package com.example.remotedatasources

import com.example.core.remotedatasources.BuildConfig

class ORSConstants {

    companion object {
        const val API_KEY: String = BuildConfig.OPEN_ROUTE_SERVICE_KEY
        const val API_URL: String = "https://api.openrouteservice.org/"
    }
}