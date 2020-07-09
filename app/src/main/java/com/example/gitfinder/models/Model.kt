package com.example.gitfinder.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repositories(
    @JsonProperty(value = "items") val items: List<Repository>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repository(
    @JsonProperty(value = "name") val name: String,
    @JsonProperty(value = "full_name") val fullName: String,
    @JsonProperty(value = "description") val description: String?,
    @JsonProperty(value = "owner") val owner: Owner
) : Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class Owner(
    @JsonProperty(value = "login") val login: String,
    @JsonProperty(value = "avatar_url")val avatarUrl: String
) : Serializable