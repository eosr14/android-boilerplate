package com.github.fj.android.awesomeapp.dto.photo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Francesco Jo(nimbusob@gmail.com)
 * @since 12 - Nov - 2018
 */
@JsonClass(generateAdapter = true)
data class ImageDetailDto(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @Json(name = "download_url") val downloadUrl: String
)
