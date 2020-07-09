package com.example.gitfinder.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repositories(
    val result: List<Repository>
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Repository(
    @JsonProperty(value = "name") val name: String,
    @JsonProperty(value = "full_name") val fullName: String,
    @JsonProperty(value = "description") val description: String?,
    @JsonProperty(value = "owner") val owner: Owner
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Owner(
    @JsonProperty(value = "login") val login: String,
    @JsonProperty(value = "avatar_url")val avatarUrl: String
)