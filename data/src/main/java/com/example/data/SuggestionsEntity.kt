package com.example.data

import com.google.gson.annotations.SerializedName

interface SuggestionsEntity {
    val text: String
    @SerializedName("suggestions")
    abstract fun suggestions(): List<String>?

}