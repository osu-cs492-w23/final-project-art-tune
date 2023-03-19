package com.example.githubsearchwithsettings.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubRepo(
    @Json(name = "full_name") val name: String,
    @Json(name = "html_url") val url: String,
    val description: String?,
    @Json(name = "stargazers_count") val stars: Int
) : java.io.Serializable
