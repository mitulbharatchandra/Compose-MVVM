package com.app.myapplication.data.remote.dto

import com.squareup.moshi.Json

data class Product(
    @Json(name = "image_front_thumb_url")
    val imageFrontThumbUrl: String?,
    @Json(name = "nutriments")
    val nutriments: Nutriments,
    @Json(name = "product_name")
    val productName: String?
)
