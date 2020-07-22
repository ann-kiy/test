package com.kiyanitsa.test.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Result<T> (
    @JsonProperty("results")
    var results: List<T>,
    @JsonProperty("_meta")
    var meta: Meta
    )
data class Meta (
        @JsonProperty("count")
        var count: Int? = 0,
        @JsonProperty("limit")
        var limit: Int? = 0,
        @JsonProperty("offset")
        var offset: Int? = 0
)